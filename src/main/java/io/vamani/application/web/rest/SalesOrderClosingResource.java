package io.vamani.application.web.rest;

import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.Adstorage;
import io.vamani.application.db2.domain.AdstorageId;
import io.vamani.application.db2.domain.Project;
import io.vamani.application.db2.domain.ProjectId;
import io.vamani.application.db2.repository.AdstorageRepository;
import io.vamani.application.db2.repository.ProjectRepository;
import io.vamani.application.db2.repository.SalesorderRepository;
import io.vamani.application.db2.repository.SalesorderlineRepository;
import io.vamani.application.model.SalesOrderClosingBean;
import io.vamani.application.model.SalesOrderClosingDetailsBean;
import io.vamani.application.model.SalesOrderClosingHeaderBean;
import io.vamani.application.model.SalesOrderClosingSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link }.
 */
@RestController
@RequestMapping("/api")
public class SalesOrderClosingResource {
    private final Logger log = LoggerFactory.getLogger(SalesOrderClosingResource.class);
    private static final String ENTITY_NAME = "salesOrderClosing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesorderlineRepository salesorderlineRepository;

    @Autowired
    private SalesorderRepository salesorderRepository;

    @Autowired
    private AdstorageRepository adstorageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public SalesOrderClosingResource(SalesorderlineRepository salesorderlineRepository) {
        this.salesorderlineRepository = salesorderlineRepository;
    }

    @PostMapping("/sales-order-closings-filter")
    public ResponseEntity<List<SalesOrderClosingBean>> getAllBillRegisterFilter(@RequestBody SalesOrderClosingSearch search) {
        log.debug("REST request to get a page of BillRegisterMasterResource");
        String projectcode = "%";
        if (search.getCode() != null && search.getCode()!=null) {
            projectcode = "%"+search.getCode().toUpperCase()+"%";
        }
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by("CODE").descending()
                    : Sort.by("CODE").ascending()
            )
        );
        Page<Object[]> page = salesorderlineRepository.fetchSalesOrderByCodeAndShippedPercentage(projectcode, search.getStatus(), search.getShippedPercentage(), search.getPage());
        List<SalesOrderClosingBean> salesOrderClosings = new ArrayList<>();
        for(Object[] objects : page.getContent()) {
            SalesOrderClosingBean salesOrderClosingBean = new SalesOrderClosingBean();
            salesOrderClosingBean.setProjectCode(objects[0].toString());
            salesOrderClosingBean.setStyle(objects[1].toString());
            salesOrderClosingBean.setCustomerCode(objects[2].toString());
            salesOrderClosingBean.setCustomerName(objects[3].toString());
            salesOrderClosingBean.setStatus(objects[4] != null && ((Short) objects[4]) == 1 ? "Closed" : "Active");
            salesOrderClosingBean.setCustomerName(objects[3].toString());
            salesOrderClosingBean.setOrderQuantity((BigDecimal) objects[5]);
            salesOrderClosingBean.setTolerance((BigDecimal) objects[6]);
            salesOrderClosingBean.setTotalQuantity((BigDecimal) objects[7]);
            salesOrderClosingBean.setShippedQuantity((BigDecimal) objects[8]);
            salesOrderClosingBean.setShippedPercentage((BigDecimal) objects[9]);
            salesOrderClosings.add(salesOrderClosingBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(salesOrderClosings);
    }

    @PostMapping("/sales-order-closings")
    public ResponseEntity<SalesOrderClosingBean> getAllBillRegister(@RequestBody SalesOrderClosingSearch search) {
        List<Object[]> objects = salesorderlineRepository.fetchByProject(search.getCode());
        String style = "";
        String customerCode = "";
        String customerName = "";
        String status = "";
        BigDecimal orderQuantity = new BigDecimal(0);
        BigDecimal tolerance = new BigDecimal(0);
        BigDecimal totalQuantity = new BigDecimal(0);
        BigDecimal shippedQuantity = new BigDecimal(0);

        String statusHeader = "";
        BigDecimal orderHeaderQuantity = new BigDecimal(0);
        BigDecimal toleranceHeader = new BigDecimal(0);
        BigDecimal totalHeaderQuantity = new BigDecimal(0);
        BigDecimal shippedHeaderQuantity = new BigDecimal(0);

        SalesOrderClosingBean salesOrderClosingBean = new SalesOrderClosingBean();
        List<SalesOrderClosingHeaderBean> salesOrderClosingHeaderBeans = new ArrayList<>();
        List<SalesOrderClosingDetailsBean> salesOrderClosingDetailsBeans = new ArrayList<>();
        String salesordercode = "";
        SalesOrderClosingHeaderBean salesOrderClosingHeaderBean = null;
        int i = 0;
        for (Object[] object : objects) {
            if (!salesordercode.equals(object[3].toString())) {
                if (i != 0) {
                    BigDecimal shippedPercentage = shippedHeaderQuantity.divide(orderHeaderQuantity, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
                    salesOrderClosingHeaderBean.setOrderQuantity(orderHeaderQuantity);
                    salesOrderClosingHeaderBean.setTolerance(toleranceHeader);
                    salesOrderClosingHeaderBean.setTotalQuantity(totalHeaderQuantity);
                    salesOrderClosingHeaderBean.setShippedQuantity(shippedHeaderQuantity);
                    salesOrderClosingHeaderBean.setShippedPercentage(shippedPercentage);
                    if (statusHeader != null && statusHeader.equalsIgnoreCase("SUSPENDED")) {
                        salesOrderClosingHeaderBean.setStatus(Boolean.TRUE);
                    } else {
                        salesOrderClosingHeaderBean.setStatus(Boolean.FALSE);
                    }
                    salesOrderClosingHeaderBean.setSalesOrderClosingDetailsBeans(salesOrderClosingDetailsBeans);
                    salesOrderClosingHeaderBeans.add(salesOrderClosingHeaderBean);
                    orderHeaderQuantity = new BigDecimal(0);
                    toleranceHeader = new BigDecimal(0);
                    totalHeaderQuantity = new BigDecimal(0);
                    shippedHeaderQuantity = new BigDecimal(0);
                    salesOrderClosingDetailsBeans = new ArrayList<>();
                }
                salesordercode = object[3].toString();
                salesOrderClosingHeaderBean = new SalesOrderClosingHeaderBean();
                salesOrderClosingHeaderBean.setProjectCode(object[0].toString());
                salesOrderClosingHeaderBean.setCompanycode(object[1].toString());
                salesOrderClosingHeaderBean.setDivisioncode(object[2].toString());
                salesOrderClosingHeaderBean.setSalesordercode(object[3].toString());
                salesOrderClosingHeaderBean.setSalesorderdate(object[6].toString());
                salesOrderClosingHeaderBean.setCustomerCode(object[7].toString());
                salesOrderClosingHeaderBean.setCustomerName(object[8].toString());
                salesOrderClosingHeaderBean.setStyle(object[9].toString());
                statusHeader = object[17].toString();
            }
            ++i;
            orderHeaderQuantity = orderHeaderQuantity.add((BigDecimal) object[13]);
            toleranceHeader = (BigDecimal) object[14];
            totalHeaderQuantity = totalHeaderQuantity.add((BigDecimal) object[15]);
            shippedHeaderQuantity = shippedHeaderQuantity.add((BigDecimal) object[16]);

            SalesOrderClosingDetailsBean salesOrderClosingDetailsBean = new SalesOrderClosingDetailsBean();
            salesOrderClosingDetailsBean.setProjectCode(object[0].toString());
            salesOrderClosingDetailsBean.setCompanycode(object[1].toString());
            salesOrderClosingDetailsBean.setDivisioncode(object[2].toString());
            salesOrderClosingDetailsBean.setSalesordercode(object[3].toString());
            salesOrderClosingDetailsBean.setOrderline(object[4].toString());
            salesOrderClosingDetailsBean.setOrdersubline(object[5].toString());
            salesOrderClosingDetailsBean.setSalesorderdate(object[6].toString());
            salesOrderClosingDetailsBean.setCustomerCode(object[7].toString());
            salesOrderClosingDetailsBean.setCustomerName(object[8].toString());
            salesOrderClosingDetailsBean.setStyle(object[9].toString());
            salesOrderClosingDetailsBean.setStyleColor(object[10].toString());
            salesOrderClosingDetailsBean.setStyleSize(object[11].toString());
            salesOrderClosingDetailsBean.setOrderQuantity((BigDecimal) object[13]);
            salesOrderClosingDetailsBean.setTolerance((BigDecimal) object[14]);
            salesOrderClosingDetailsBean.setTotalQuantity((BigDecimal) object[15]);
            salesOrderClosingDetailsBean.setShippedQuantity((BigDecimal) object[16]);
            salesOrderClosingDetailsBean.setStatus(object[17].toString());
            BigDecimal shippedPercentage = ((BigDecimal) object[13]) != null && ((BigDecimal) object[13]).doubleValue() > 0 ? ((BigDecimal) object[16]).divide(((BigDecimal) object[13]), RoundingMode.HALF_UP).multiply(new BigDecimal(100)) : new BigDecimal(0);
            salesOrderClosingDetailsBean.setShippedPercentage(shippedPercentage);
            salesOrderClosingDetailsBeans.add(salesOrderClosingDetailsBean);

            customerCode = object[7].toString();
            customerName = object[8].toString();
            style = object[9].toString();
            orderQuantity = orderQuantity.add((BigDecimal) object[13]);
            tolerance = (BigDecimal) object[14];
            totalQuantity = totalQuantity.add((BigDecimal) object[15]);
            shippedQuantity = shippedQuantity.add((BigDecimal) object[16]);
            status = object[12] != null && ((Short) object[12]) == 1 ? "Closed" : "Active";

            if(i == objects.size()) {
                BigDecimal shippedPercentageTemp = shippedHeaderQuantity.divide(orderHeaderQuantity, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
                salesOrderClosingHeaderBean.setOrderQuantity(orderHeaderQuantity);
                salesOrderClosingHeaderBean.setTolerance(toleranceHeader);
                salesOrderClosingHeaderBean.setTotalQuantity(totalHeaderQuantity);
                salesOrderClosingHeaderBean.setShippedQuantity(shippedHeaderQuantity);
                salesOrderClosingHeaderBean.setShippedPercentage(shippedPercentageTemp);
                if (statusHeader != null && statusHeader.equalsIgnoreCase("SUSPENDED")) {
                    salesOrderClosingHeaderBean.setStatus(Boolean.TRUE);
                } else {
                    salesOrderClosingHeaderBean.setStatus(Boolean.FALSE);
                }
                salesOrderClosingHeaderBean.setSalesOrderClosingDetailsBeans(salesOrderClosingDetailsBeans);
                salesOrderClosingHeaderBeans.add(salesOrderClosingHeaderBean);
            }
        }
        salesOrderClosingBean.setProjectCode(search.getCode());
        salesOrderClosingBean.setStyle(style);
        salesOrderClosingBean.setCustomerCode(customerCode);
        salesOrderClosingBean.setCustomerName(customerName);
        salesOrderClosingBean.setStatus(status);
        salesOrderClosingBean.setOrderQuantity(orderQuantity);
        salesOrderClosingBean.setTolerance(tolerance);
        salesOrderClosingBean.setTotalQuantity(totalQuantity);
        salesOrderClosingBean.setShippedQuantity(shippedQuantity);
        BigDecimal shippedPercentage = shippedQuantity.divide(orderQuantity, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        salesOrderClosingBean.setShippedPercentage(shippedPercentage);
        salesOrderClosingBean.setSalesOrderClosingHeaderBeans(salesOrderClosingHeaderBeans);
        return ResponseUtil.wrapOrNotFound(Optional.of(salesOrderClosingBean));
    }

    @PostMapping("/sales-order-closings-save")
    public ResponseEntity<SalesOrderClosingBean> getAllBillRegister(@RequestBody SalesOrderClosingBean salesOrderClosingBean) {
        if (salesOrderClosingBean != null && salesOrderClosingBean.getSalesOrderClosingHeaderBeans() != null) {
            int i = 0;
            for (SalesOrderClosingHeaderBean salesOrderClosingHeaderBean : salesOrderClosingBean.getSalesOrderClosingHeaderBeans()) {
                if (salesOrderClosingHeaderBean.getStatus() != null && salesOrderClosingHeaderBean.getStatus().booleanValue() == true) {
                    salesorderRepository.updateSalesOrderStatus(salesOrderClosingHeaderBean.getSalesordercode().trim());
                    ++i;
                }
            }
            if(i>0 && i == salesOrderClosingBean.getSalesOrderClosingHeaderBeans().size()) {
                Project project = projectRepository.findById(new ProjectId(Constants.COMPANY_CODE, salesOrderClosingBean.getProjectCode())).orElse(null);
                if(project != null) {
                    Adstorage adstorage = new Adstorage();
                    adstorage.setId(new AdstorageId(project.getAbsuniqueid(), "Project", "ProjectClosed", "ProjectClosed"));
                    adstorage.setKeysequence(0);
                    adstorage.setShared((short) 0);
                    adstorage.setDatatype(2);
                    adstorage.setValueint(0);
                    adstorage.setValueboolean((short) 1);
                    adstorage.setValuelong(0L);
                    adstorage.setAbsuniqueid(0L);
                    adstorageRepository.save(adstorage);
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(salesOrderClosingBean));
    }
}

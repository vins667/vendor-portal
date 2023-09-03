package io.vamani.application.db2.web.rest;

import io.micrometer.core.annotation.Timed;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.model.DestinationBean;
import io.vamani.application.db2.model.SizesBean;
import io.vamani.application.db2.repository.ColorRepository;
import io.vamani.application.db2.repository.SalesorderlineRepository;
import io.vamani.application.db2.repository.ViewprodorderdemandRepository;
import io.vamani.application.model.*;
import io.vamani.application.repository.CutPlanBundleRepository;
import io.vamani.application.repository.CutPlanEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class SalesorderlineResource {

	private final Logger log = LoggerFactory.getLogger(SalesorderlineResource.class);

	private static final String ENTITY_NAME = "salesorder";

	private final SalesorderlineRepository salesorderlineRepository;

	@Autowired
	private CutPlanBundleRepository cutPlanBundleRepository;

	@Autowired
    private ColorRepository colorRepository;

	@Autowired
    private CutPlanEntryRepository cutPlanEntryRepository;

    @Autowired
    private ViewprodorderdemandRepository viewprodorderdemandRepository;

	public SalesorderlineResource(SalesorderlineRepository salesorderlineRepository) {
		this.salesorderlineRepository = salesorderlineRepository;
	}

	@PostMapping("/db2-salesorder-countries")
	@Timed
	public List<Master> getAllCountryById(@Valid @RequestBody Master search) {
		List<Master> master = new ArrayList<Master>();
		List<Object[]> objectList = salesorderlineRepository.getAllCountryById(search.getName());
		for (Object obj : objectList) {
			Master bean = new Master();
			Object[] objects = (Object[]) obj;
			bean.setName(objects[0].toString());
			bean.setDesc(objects[1].toString());
			master.add(bean);
		}
		return master;
	}

	@PostMapping("/db2-salesorder-colors")
	@Timed
	public List<Master> getAllColorByCountry(@Valid @RequestBody Master search) {
		List<Master> master = new ArrayList<Master>();
		List<Object[]> objectList = salesorderlineRepository.getAllColorByCountry(search.getName());
		for (Object obj : objectList) {
			Master bean = new Master();
			Object[] objects = (Object[]) obj;
			bean.setName(objects[0].toString());
			bean.setDesc(objects[1].toString());
			master.add(bean);
		}
		return master;
	}

    @PostMapping("/db2-salesorder-style-factory")
    @Timed
    public Master getFactorybyStyle(@Valid @RequestBody Master search) {
        Master master = new Master();
        String object = salesorderlineRepository.getFactorycodebyProject(search.getName());
        master.setCode(object);
        return master;
    }

	@PostMapping("/db2-salesorder-size")
	@Timed
	public MarkerDestinationBean getAllSizesByStyle(@Valid @RequestBody Master search) {
        MarkerDestinationBean markerDestinationBean = new MarkerDestinationBean();
		List<SizesBean> sizesBeans = new ArrayList<>();
        List<String> sizes = new ArrayList<>();
		Map<String, Map<String, MarkerEntryDetailsBean>> destinations = new LinkedHashMap<>();
		List<Object[]> objectList = salesorderlineRepository.getAllSizesByStyle(search.getCode(), search.getDesc());
        for (Object obj : objectList) {
            Object[] objects = (Object[]) obj;
            if (destinations.containsKey(objects[1].toString())) {
                Map<String, MarkerEntryDetailsBean> destinationsDetails = destinations.get(objects[1].toString());
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSequence((Integer) objects[0]);
                markerEntryDetailsBean.setSizeCode(objects[2].toString());
                markerEntryDetailsBean.setOrderQty(Double.parseDouble(objects[3].toString()));
                destinationsDetails.put(objects[2].toString(), markerEntryDetailsBean);
                destinations.put(objects[1].toString(), destinationsDetails);
            } else {
                Map<String, MarkerEntryDetailsBean> destinationsDetails = new LinkedHashMap<>();
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSequence((Integer) objects[0]);
                markerEntryDetailsBean.setSizeCode(objects[2].toString());
                markerEntryDetailsBean.setOrderQty(Double.parseDouble(objects[3].toString()));
                destinationsDetails.put(objects[2].toString(), markerEntryDetailsBean);
                destinations.put(objects[1].toString(), destinationsDetails);
            }
            if(sizes.contains(objects[2].toString())) {} else {
                SizesBean sizesBean = new SizesBean();
                sizesBean.setSequence((Integer) objects[0]);
                sizesBean.setSizeCode(objects[2].toString());
                sizesBeans.add(sizesBean);
                sizes.add(objects[2].toString());
            }
        }
        Collections.sort(sizesBeans, Comparator.comparing(SizesBean::getSequence));
        markerDestinationBean.setSizeCodes(sizesBeans);

        List<DestinationBean> destinationBeans = new ArrayList<>();
        for(String destKey : destinations.keySet()) {
            DestinationBean destinationBean = new DestinationBean();
            destinationBean.setDestination(destKey);
            Double totalQty = 0.0;
            List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
            Map<String, MarkerEntryDetailsBean> destinationsDetails = destinations.get(destKey);
            for (SizesBean sizesBean : sizesBeans) {
                if (destinationsDetails.keySet().contains(sizesBean.getSizeCode())) {
                    MarkerEntryDetailsBean markerEntryDetailsBean = destinationsDetails.get(sizesBean.getSizeCode());
                    totalQty += markerEntryDetailsBean.getOrderQty();
                    markerEntryDetailsBeans.add(markerEntryDetailsBean);
                } else {
                    MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                    markerEntryDetailsBean.setSequence(sizesBean.getSequence());
                    markerEntryDetailsBean.setSizeCode(sizesBean.getSizeCode());
                    markerEntryDetailsBean.setOrderQty(0.0);
                    markerEntryDetailsBeans.add(markerEntryDetailsBean);
                }
            }
            Collections.sort(markerEntryDetailsBeans, Comparator.comparing(MarkerEntryDetailsBean::getSequence));
            destinationBean.setTotalQty(totalQty);
            destinationBean.setMarkerEntryDetailsBeans(markerEntryDetailsBeans);
            destinationBeans.add(destinationBean);
        }
        markerDestinationBean.setDestinationBeans(destinationBeans);
		return markerDestinationBean;
	}

    @PostMapping("/db2-viewsalesorder-destinations")
    @Timed
    public MarkerDestinationBean getAllSizesByStyleAndDestinations(@Valid @RequestBody BalanceSuggestionSearch search) {
        MarkerDestinationBean markerDestinationBean = new MarkerDestinationBean();
        List<SizesBean> sizesBeans = new ArrayList<>();
        List<String> sizes = new ArrayList<>();
        Map<String, Map<String, MarkerEntryDetailsBean>> destinations = new LinkedHashMap<>();
        // List<Object[]> objectList = salesorderlineRepository.getAllSizesByStyleAndDestination(search.getStyle(), search.getDestinationcode(), search.getColor());
        List<Object[]> objectList = viewprodorderdemandRepository.getAllSizesByStyleAndDestination(Constants.COMPANY_CODE, search.getProductionorder(), search.getColor());
        for (Object obj : objectList) {
            Object[] objects = (Object[]) obj;
            if (destinations.containsKey(objects[1].toString())) {
                Map<String, MarkerEntryDetailsBean> destinationsDetails = destinations.get(objects[1].toString());
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSequence((Integer) objects[0]);
                markerEntryDetailsBean.setSizeCode(objects[2].toString().trim());
                markerEntryDetailsBean.setOrderQty(Double.parseDouble(objects[3].toString()));
                destinationsDetails.put(objects[2].toString().trim(), markerEntryDetailsBean);
                destinations.put(objects[1].toString(), destinationsDetails);
            } else {
                Map<String, MarkerEntryDetailsBean> destinationsDetails = new LinkedHashMap<>();
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSequence((Integer) objects[0]);
                markerEntryDetailsBean.setSizeCode(objects[2].toString().trim());
                markerEntryDetailsBean.setOrderQty(Double.parseDouble(objects[3].toString()));
                destinationsDetails.put(objects[2].toString().trim(), markerEntryDetailsBean);
                destinations.put(objects[1].toString(), destinationsDetails);
            }
            if (sizes.contains(objects[2].toString().trim())) {
            } else {
                SizesBean sizesBean = new SizesBean();
                sizesBean.setSequence((Integer) objects[0]);
                sizesBean.setSizeCode(objects[2].toString().trim());
                sizesBeans.add(sizesBean);
                sizes.add(objects[2].toString());
            }
        }
        Collections.sort(sizesBeans, Comparator.comparing(SizesBean::getSequence));
        markerDestinationBean.setSizeCodes(sizesBeans);

        List<DestinationBean> destinationBeans = new ArrayList<>();
        for (String destKey : destinations.keySet()) {
            DestinationBean destinationBean = new DestinationBean();
            destinationBean.setDestination("Order Qty");
            Double totalQty = 0.0;
            List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
            Map<String, MarkerEntryDetailsBean> destinationsDetails = destinations.get(destKey);
            for (SizesBean sizesBean : sizesBeans) {
                if (destinationsDetails.keySet().contains(sizesBean.getSizeCode())) {
                    MarkerEntryDetailsBean markerEntryDetailsBean = destinationsDetails.get(sizesBean.getSizeCode());
                    totalQty += markerEntryDetailsBean.getOrderQty();
                    markerEntryDetailsBeans.add(markerEntryDetailsBean);
                } else {
                    MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                    markerEntryDetailsBean.setSequence(sizesBean.getSequence());
                    markerEntryDetailsBean.setSizeCode(sizesBean.getSizeCode().trim());
                    markerEntryDetailsBean.setOrderQty(0.0);
                    markerEntryDetailsBeans.add(markerEntryDetailsBean);
                }
            }
            Collections.sort(markerEntryDetailsBeans, Comparator.comparing(MarkerEntryDetailsBean::getSequence));
            destinationBean.setTotalQty(totalQty);
            destinationBean.setMarkerEntryDetailsBeans(markerEntryDetailsBeans);
            destinationBeans.add(destinationBean);
        }


        Map<String, MarkerEntryDetailsBean> destinationsDetailsTotal = new LinkedHashMap<>();
        //List<Object[]> cuttingDetails = cutPlanEntryRepository.findAllByStyleColorAndDestination(search.getStyle(), search.getColor(), search.getDestinationcode(), search.getSubcode01().trim(), search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(), search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(), search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim());
        List<Object[]> cuttingDetails = cutPlanEntryRepository.findAllByPOAndStyleColorAndDestination(search.getProductionorder().trim(), search.getStyle(), search.getColor(), search.getDestinationcode(), search.getSubcode01().trim(), search.getSubcode02().trim(), search.getSubcode03().trim(), search.getSubcode04().trim(), search.getSubcode05().trim(), search.getSubcode06().trim(), search.getSubcode07().trim(), search.getSubcode08().trim(), search.getSubcode09().trim(), search.getSubcode10().trim());
        destinations = new LinkedHashMap<>();
        for (Object obj : cuttingDetails) {
            Object[] objects = (Object[]) obj;
            if (destinations.containsKey(objects[1].toString())) {
                Map<String, MarkerEntryDetailsBean> destinationsDetails = destinations.get(objects[1].toString());
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSizeCode(objects[2].toString().trim());
                markerEntryDetailsBean.setOrderQty(Double.parseDouble(objects[3].toString()));
                markerEntryDetailsBean.setSizeQty(Integer.parseInt(objects[4].toString()));
                destinationsDetails.put(objects[2].toString().trim(), markerEntryDetailsBean);
                if (destinationsDetailsTotal.containsKey(objects[2].toString().trim())) {
                    MarkerEntryDetailsBean markerEntryDetails = destinationsDetailsTotal.get(objects[2].toString().trim());
                    markerEntryDetails.setOrderQty(markerEntryDetails.getOrderQty() + Double.parseDouble(objects[3].toString()));
                    destinationsDetailsTotal.put(objects[2].toString().trim(), markerEntryDetails);
                } else {
                    MarkerEntryDetailsBean markerEntryDetails = new MarkerEntryDetailsBean();
                    markerEntryDetails.setSizeCode(objects[2].toString().trim());
                    markerEntryDetails.setOrderQty(Double.parseDouble(objects[3].toString()));
                    markerEntryDetails.setSizeQty(Integer.parseInt(objects[4].toString()));
                    destinationsDetailsTotal.put(objects[2].toString().trim(), markerEntryDetails);
                }
                destinations.put(objects[1].toString(), destinationsDetails);
            } else {
                Map<String, MarkerEntryDetailsBean> destinationsDetails = new LinkedHashMap<>();
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSizeCode(objects[2].toString().trim());
                markerEntryDetailsBean.setOrderQty(Double.parseDouble(objects[3].toString()));
                markerEntryDetailsBean.setSizeQty(Integer.parseInt(objects[4].toString()));
                destinationsDetails.put(objects[2].toString(), markerEntryDetailsBean);
                destinations.put(objects[1].toString(), destinationsDetails);

                if (destinationsDetailsTotal.containsKey(objects[2].toString().trim())) {
                    MarkerEntryDetailsBean markerEntryDetails = destinationsDetailsTotal.get(objects[2].toString().trim());
                    markerEntryDetails.setOrderQty(markerEntryDetails.getOrderQty() + Double.parseDouble(objects[3].toString()));
                    destinationsDetailsTotal.put(objects[2].toString().trim(), markerEntryDetails);
                } else {
                    MarkerEntryDetailsBean markerEntryDetails = new MarkerEntryDetailsBean();
                    markerEntryDetails.setSizeCode(objects[2].toString().trim());
                    markerEntryDetails.setOrderQty(Double.parseDouble(objects[3].toString()));
                    destinationsDetailsTotal.put(objects[2].toString().trim(), markerEntryDetails);
                }
            }
        }
        for (String destKey : destinations.keySet()) {
            DestinationBean destinationBean = new DestinationBean();
            destinationBean.setDestination(destKey);
            Double totalQty = 0.0;
            List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
            Map<String, MarkerEntryDetailsBean> destinationsDetails = destinations.get(destKey);
            for (SizesBean sizesBean : sizesBeans) {
                if (destinationsDetails.keySet().contains(sizesBean.getSizeCode())) {
                    MarkerEntryDetailsBean markerEntryDetailsBean = destinationsDetails.get(sizesBean.getSizeCode());
                    markerEntryDetailsBean.setSequence(sizesBean.getSequence());
                    totalQty += markerEntryDetailsBean.getOrderQty();
                    markerEntryDetailsBeans.add(markerEntryDetailsBean);
                } else {
                    MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                    markerEntryDetailsBean.setSequence(sizesBean.getSequence());
                    markerEntryDetailsBean.setSizeCode(sizesBean.getSizeCode().trim());
                    markerEntryDetailsBean.setOrderQty(0.0);
                    markerEntryDetailsBean.setSizeQty(0);
                    markerEntryDetailsBeans.add(markerEntryDetailsBean);
                }
            }
            Collections.sort(markerEntryDetailsBeans, Comparator.comparing(MarkerEntryDetailsBean::getSequence));
            destinationBean.setTotalQty(totalQty);
            destinationBean.setMarkerEntryDetailsBeans(markerEntryDetailsBeans);
            destinationBeans.add(destinationBean);
        }

        Double totalQty = 0.0;
        List<MarkerEntryDetailsBean> markerEntryDetailsBeans = new ArrayList<>();
        for (SizesBean sizesBean : sizesBeans) {
            if (destinationsDetailsTotal.keySet().contains(sizesBean.getSizeCode())) {
                MarkerEntryDetailsBean markerEntryDetailsBean = destinationsDetailsTotal.get(sizesBean.getSizeCode());
                markerEntryDetailsBean.setSequence(sizesBean.getSequence());
                totalQty += markerEntryDetailsBean.getOrderQty();
                markerEntryDetailsBeans.add(markerEntryDetailsBean);
            } else {
                MarkerEntryDetailsBean markerEntryDetailsBean = new MarkerEntryDetailsBean();
                markerEntryDetailsBean.setSequence(sizesBean.getSequence());
                markerEntryDetailsBean.setSizeCode(sizesBean.getSizeCode().trim());
                markerEntryDetailsBean.setOrderQty(0.0);
                markerEntryDetailsBeans.add(markerEntryDetailsBean);
            }
        }
        Collections.sort(markerEntryDetailsBeans, Comparator.comparing(MarkerEntryDetailsBean::getSequence));
        DestinationBean destinationBean = new DestinationBean();
        destinationBean.setDestination("Total Qty");
        destinationBean.setTotalQty(totalQty);
        destinationBean.setMarkerEntryDetailsBeans(markerEntryDetailsBeans);
        destinationBeans.add(destinationBean);

        markerDestinationBean.setDestinationBeans(destinationBeans);

        return markerDestinationBean;
    }

    @PostMapping("/db2-salesorders-style")
    @Timed
    public ResponseEntity<CutIssueBean> getStyleByProject(@Valid @RequestBody Master search) {
	    String style = salesorderlineRepository.getStyleByProjectcode(search.getCode());
        CutIssueBean cutIssueBean = new CutIssueBean();
        cutIssueBean.setStyle(style.trim());

	    List<Master> masters = new ArrayList<>();
	    List<String> colors = cutPlanBundleRepository.findAllColorsByStyle(style.trim());
        if(colors != null && colors.size()>0) {
            List<Object[]> objectList = colorRepository.findAllColorsByCodes(colors);
            for (Object obj : objectList) {
                Master bean = new Master();
                Object[] objects = (Object[]) obj;
                bean.setName(objects[0].toString());
                bean.setDesc(objects[1].toString());
                masters.add(bean);
            }
        } else {
            colors = cutPlanBundleRepository.findAllHFColorsByStyle(style.trim());
            List<Object[]> objectList = colorRepository.findAllColorsByCodes(colors);
            for (Object obj : objectList) {
                Master bean = new Master();
                Object[] objects = (Object[]) obj;
                bean.setName(objects[0].toString());
                bean.setDesc(objects[1].toString());
                masters.add(bean);
            }
        }
        cutIssueBean.setColors(masters);
        return ResponseEntity.ok().body(cutIssueBean);
    }

    @PostMapping("/db2-salesorders-destinations")
    @Timed
    public ResponseEntity<List<Master>> getDestinationByStyle(@Valid @RequestBody Master search) {

        List<Master> masters = new ArrayList<>();
        List<Object[]> destinations = salesorderlineRepository.getDestinationsByStyle(search.getId().trim(), search.getDesc().trim());
        if(destinations != null && destinations.size()>0){
            for (Object[] obj : destinations) {
                Master bean = new Master();
                bean.setName(obj[0].toString().trim());
                bean.setDesc(obj[1].toString().trim());
                masters.add(bean);
            }
        }else{
            Master bean = new Master();
            bean.setName("Not Applicable");
            bean.setDesc("Not Applicable");
            masters.add(bean);
        }
        return ResponseEntity.ok().body(masters);
    }
}

package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.domain.AssetAuditDetails;
import io.vamani.application.domain.AssetMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.model.ParameterListBean;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Findocument}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FindocumentResource {
    private final Logger log = LoggerFactory.getLogger(FindocumentResource.class);

    private static final String ENTITY_NAME = "findocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindocumentRepository findocumentRepository;

    @Autowired
    private ViewfindocumentRepository viewfindocumentRepository;

    @Autowired
    private FinfinancialyearRepository finfinancialyearRepository;

    @Autowired
    private DebitNoteEntryRepository debitNoteEntryRepository;
    private final AdstorageRepository adstorageRepository;

    public FindocumentResource(FindocumentRepository findocumentRepository,
                               AdstorageRepository adstorageRepository) {
        this.findocumentRepository = findocumentRepository;
        this.adstorageRepository = adstorageRepository;
    }

    @GetMapping("/findocuments/{findocumentcode}")
    @Timed
    public ResponseEntity<List<Findocument>> getAllEstprdemployeedetails(@PathVariable String findocumentcode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of findocument");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        findocumentcode = URLDecoder.decode(findocumentcode, "UTF-8");
        Page<Findocument> page = findocumentRepository.findAllByFindocumentIgnoreCaseLike("%" + findocumentcode.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/findocuments-all/{findocumentcode}")
    @Timed
    public ResponseEntity<List<Findocument>> getAllEstprdemployeedetailsAll(@PathVariable String findocumentcode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of findocument");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        findocumentcode = URLDecoder.decode(findocumentcode, "UTF-8");
        Page<Findocument> page = findocumentRepository.findAllByFindocumentAllIgnoreCaseLike("%" + findocumentcode.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/findocuments-gl-change/{findocumentcode}")
    @Timed
    public ResponseEntity<List<Findocument>> getAllFinDocumentFinYear(@PathVariable String findocumentcode) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of findocument");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("id.code").ascending());
        findocumentcode = URLDecoder.decode(findocumentcode, "UTF-8");
        Page<Findocument> page = findocumentRepository.findAllByFindocumentGLChangeIgnoreCaseLike("%" + findocumentcode.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/viewfindocuments")
    @Timed
    public List<ViewfindocumentBean> getAllView(@RequestBody FindocumentId id) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of findocument");
        List<ViewfindocumentBean> viewfindocumentBeans = new ArrayList<>();
        List<Viewfindocument> viewfindocuments = viewfindocumentRepository.findAllByFindocument(id.getCompanycode(), id.getBusinessunitcode(), id.getFinancialyearcode(), id.getDocumenttemplatecode(), id.getCode());
        Boolean isNarration = false;
        String narration = "";
        for (Viewfindocument viewfindocument : viewfindocuments) {
            ViewfindocumentBean viewfindocumentBean = new ViewfindocumentBean();
            BeanUtils.copyProperties(viewfindocument, viewfindocumentBean);
            if (isNarration == false) {
                Findocument findocument = findocumentRepository.findById(new FindocumentId(viewfindocument.getId().getCompanycode(), viewfindocument.getId().getBusinessunitcode(), viewfindocument.getId().getFinancialyearcode(), viewfindocument.getId().getDocumenttemplatecode(), viewfindocument.getId().getFindocumentcode(), viewfindocument.getFindocstatisticalgroupcode())).orElse(null);
                if (findocument != null) {
                    Adstorage adstorage = adstorageRepository.findById(new AdstorageId(findocument.getAbsuniqueid(), "FINDocument", "Remarks2", "Remarks2")).orElse(null);
                    if (adstorage != null) {
                        narration = adstorage.getValuestring();
                        viewfindocumentBean.setNarration(narration);
                    }
                }
                isNarration = true;
            } else {
                viewfindocumentBean.setNarration(narration);
            }
            viewfindocumentBeans.add(viewfindocumentBean);
        }
        return viewfindocumentBeans;
    }

    @PostMapping("/viewfindocuments-save")
    @Timed
    public List<ViewfindocumentBean> getAllView(@RequestBody List<ViewfindocumentBean> viewfindocuments) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of findocument");
        int ctr = 0;
        for (ViewfindocumentBean viewfindocument : viewfindocuments) {
            findocumentRepository.updateGL(viewfindocument.getGlcode(), viewfindocument.getId().getCompanycode(), viewfindocument.getId().getBusinessunitcode(), viewfindocument.getId().getFinancialyearcode(), viewfindocument.getId().getFindocumentcode(), viewfindocument.getId().getLinenumber());
            findocumentRepository.updateOpenDocumentGL(viewfindocument.getGlcode(), viewfindocument.getId().getCompanycode(), viewfindocument.getId().getBusinessunitcode(), viewfindocument.getId().getFinancialyearcode(), viewfindocument.getId().getFindocumentcode(), viewfindocument.getId().getLinenumber());
            if (ctr == viewfindocuments.size() - 1 && viewfindocument.getNarration() != null && viewfindocument.getNarration() != null && viewfindocument.getNarration().length()>0) {
                Findocument findocument = findocumentRepository.findById(new FindocumentId(viewfindocument.getId().getCompanycode(), viewfindocument.getId().getBusinessunitcode(), viewfindocument.getId().getFinancialyearcode(), viewfindocument.getId().getDocumenttemplatecode(), viewfindocument.getId().getFindocumentcode(), viewfindocument.getFindocstatisticalgroupcode())).orElse(null);
                if (findocument != null) {
                    Adstorage adstorage = adstorageRepository.findById(new AdstorageId(findocument.getAbsuniqueid(), "FINDocument", "Remarks2", "Remarks2")).orElse(null);
                    if (adstorage != null) {
                        adstorage.setValuestring(viewfindocument.getNarration());
                        adstorageRepository.save(adstorage);
                    } else {
                        adstorage = new Adstorage();
                        adstorage.setId(new AdstorageId(findocument.getAbsuniqueid(), "FINDocument", "Remarks2", "Remarks2"));
                        adstorage.setKeysequence(0);
                        adstorage.setShared((short) 0);
                        adstorage.setDatatype(0);
                        adstorage.setValuestring(viewfindocument.getNarration());
                        adstorage.setValueint(0);
                        adstorage.setValueboolean((short) 0);
                        adstorage.setValuedate(null);
                        adstorage.setValuedecimal(null);
                        adstorage.setValuelong(0L);
                        adstorage.setValuetime(null);
                        adstorage.setValuetimestamp(null);
                        adstorage.setAbsuniqueid(0L);
                        adstorageRepository.save(adstorage);
                    }
                }
            }
            ++ctr;
        }
        return viewfindocuments;
    }

    @PostMapping("/debit-note-details")
    @Timed
    public ResponseEntity<List<DebitNoteEntry>> getAllDocumentByCode(@RequestBody MasterParameters id) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of findocument");
        return ResponseUtil.wrapOrNotFound(Optional.of(debitNoteEntryRepository.findAllDebitNoteByCode(id.getParastring01(), id.getParastring02(), id.getParastring03(), id.getParastring04(), id.getParastring05())));
    }

    @PostMapping("/debit-note-entry-mrn-fetch")
    @Timed
    public List<MrnBean> getMrnDetails(@RequestBody MasterParameters search) throws UnsupportedEncodingException {
        List<Object[]> res = viewfindocumentRepository.fetchMrnDetailByFindoc(search.getParastring01(), search.getParastring02(), search.getParastring03(), search.getParastring04(), search.getParastring05());
        List<MrnBean> mrnBeans = new ArrayList<>();
        if(res != null && res.size()>0) {
            for(Object[] object : res) {
                MrnBean mrnBean = new MrnBean();
                mrnBean.setTariffcode(object[0].toString());
                mrnBean.setItemtypeaficode(object[1].toString());
                mrnBean.setItemcode(object[2].toString() + " " + object[3].toString() + " " + object[4].toString() + " " + object[5].toString() + " " + object[6].toString() + " " + object[7].toString() + " " + object[8].toString() + " " + object[9].toString() + " " + object[10].toString() + " " + object[11].toString());
                mrnBean.setUom(object[12].toString());
                mrnBean.setItemdescription(object[13].toString());
                mrnBeans.add(mrnBean);
            }
        }
        return mrnBeans;
    }

    @PostMapping("/debit-note-entry-gst")
    @Timed
    public MasterParameters getGstType(@RequestBody MasterParameters search) throws UnsupportedEncodingException {
        String res = viewfindocumentRepository.getGstType(search.getParastring01(), search.getParastring02(), search.getParastring03(), search.getParastring04(), search.getParastring05());
        MasterParameters gsttype = new MasterParameters();
        gsttype.setParastring06(res);
        return gsttype;
    }

    @PostMapping("/debit-note-entry-save")
    @Timed
    public ResponseEntity<List<DebitNoteEntry>> createDebitNoteEntry(@Valid @RequestBody List<DebitNoteEntry> debitNoteEntry) {
        log.debug("REST request to save AssetMaster : {}", debitNoteEntry);
        List<DebitNoteEntry> debitNoteEntries = new ArrayList<>();
        for (DebitNoteEntry documents : debitNoteEntry) {
            if (documents.getHsncode() != null && documents.getDescription() != null && documents.getHsncode().length()>0 && documents.getDescription().length()>0) {
                documents.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                documents.setCreateddate(Instant.now());
                DebitNoteEntry result = debitNoteEntryRepository.save(documents);
                debitNoteEntries.add(result);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(debitNoteEntries));
    }

    @PostMapping("/debit-note-entry-savelock")
    @Timed
    public ResponseEntity<List<DebitNoteEntry>> createDebitNoteEntrySaveLock(@Valid @RequestBody List<DebitNoteEntry> debitNoteEntry) {
        log.debug("REST request to save AssetMaster : {}", debitNoteEntry);
        List<DebitNoteEntry> debitNoteEntries = new ArrayList<>();
        for (DebitNoteEntry documents : debitNoteEntry) {
            if (documents.getHsncode() != null && documents.getDescription() != null && documents.getHsncode().length()>0 && documents.getDescription().length()>0) {
                documents.setLockedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                documents.setLockeddate(Instant.now());
                DebitNoteEntry result = debitNoteEntryRepository.save(documents);
                debitNoteEntries.add(result);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(debitNoteEntries));
    }

    @PostMapping("fetch-supplier-outstanding")
    public ResponseEntity<OutstandingBean> fetchSupplierOutstanding(@RequestBody OutstandingBean outstandingBean) throws ParseException {
        log.debug("REST request to save AssetMaster : {}", outstandingBean);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Finfinancialyear finfinancialyear = finfinancialyearRepository.findByDate(date);
        BigDecimal amount = findocumentRepository.fetchOutstandingAmount(simpleDateFormat2.format(finfinancialyear.getFromdate()), simpleDateFormat2.format(date), outstandingBean.getSupplierCode());
        outstandingBean.setAmount(amount);
        return ResponseUtil.wrapOrNotFound(Optional.of(outstandingBean));
    }

    @DeleteMapping("/debit-note-entry-delete/{id}")
    public ResponseEntity<Void> deleteDebitNoteEntry(@PathVariable Long id) {
        log.debug("REST request to delete TrimsTemplateMaster : {}", id);
        debitNoteEntryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(io.github.jhipster.web.util.HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("fin-document-by-cheque-number")
    public ResponseEntity<ChequeBean> fetchSupplierOutstanding(@RequestBody ChequeBean chequeBean) throws ParseException {
        log.debug("REST request to save AssetMaster : {}", chequeBean);
        List<Object[]> objects = findocumentRepository.findByChequeNumber(chequeBean.getCode());
        ChequeBean bean = null;
        if(objects != null && objects.size()>0) {
            Object[] object = objects.get(0);
            if(object != null && object.length == 3) {
                bean = new ChequeBean();
                bean.setBusinessunitcode(object[0].toString().trim());
                bean.setFinancialyearcode(((BigDecimal) object[1]).toBigInteger().toString());
                bean.setCode(object[2].toString().trim());
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }
}

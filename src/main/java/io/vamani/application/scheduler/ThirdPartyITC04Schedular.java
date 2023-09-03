package io.vamani.application.scheduler;

import io.vamani.application.db2.domain.DiStocktransactionissue;
import io.vamani.application.db2.domain.DiStocktransactionissueId;
import io.vamani.application.db2.domain.DiStocktransactionreciept;
import io.vamani.application.db2.domain.DiStocktransactionrecieptId;
import io.vamani.application.db2.repository.DiStocktransactionissueRepository;
import io.vamani.application.db2.repository.DiStocktransactionrecieptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
public class ThirdPartyITC04Schedular {
    @Autowired
    private DiStocktransactionissueRepository diStocktransactionissueRepository;

    @Autowired
    private DiStocktransactionrecieptRepository diStocktransactionrecieptRepository;

    /* @Bean
    @Scheduled(cron = "0 48 9 * * ?")
    public void postThirdParty() {
        List<Object[]> stockTransactions = diStocktransactionissueRepository.fetchIssuesByTransactionDate();
        for(Object[] object : stockTransactions) {
            DiStocktransactionissueId id = new DiStocktransactionissueId();
            id.setTransactionnumber(object[0].toString().trim());
            id.setTransactiondetailnumber((Integer) object[1]);
            DiStocktransactionissue stocktransactionissue = diStocktransactionissueRepository.findById(id).orElse(null);
            if (stocktransactionissue != null) {
            } else {
                stocktransactionissue = new DiStocktransactionissue();
                stocktransactionissue.setId(id);
                stocktransactionissue.setTransactiondate((Date) object[2]);
                stocktransactionissue.setTransactiontime((Time) object[3]);
                if (object[5].toString() != null && object[6].toString() != null && object[5].toString().trim().equalsIgnoreCase(object[6].toString().trim())) {
                    stocktransactionissue.setCountercode(null);
                    stocktransactionissue.setCode(null);
                    stocktransactionissue.setProductionordercode(object[6].toString().trim());
                } else {
                    stocktransactionissue.setCountercode(object[4].toString().trim());
                    stocktransactionissue.setCode(object[5].toString().trim());
                    stocktransactionissue.setProductionordercode(object[6].toString().trim());
                }
                stocktransactionissue.setItemtypecompanycode(object[7].toString().trim());
                stocktransactionissue.setItemtypecode(object[8].toString().trim());
                stocktransactionissue.setDecosubcode01(object[9].toString().trim());
                stocktransactionissue.setDecosubcode02(object[10].toString().trim());
                stocktransactionissue.setDecosubcode03(object[11].toString().trim());
                stocktransactionissue.setDecosubcode04(object[12].toString().trim());
                stocktransactionissue.setDecosubcode05(object[13].toString().trim());
                stocktransactionissue.setDecosubcode06(object[14].toString().trim());
                stocktransactionissue.setDecosubcode07(object[15].toString().trim());
                stocktransactionissue.setDecosubcode08(object[16].toString().trim());
                stocktransactionissue.setDecosubcode09(object[17].toString().trim());
                stocktransactionissue.setDecosubcode10(object[18].toString().trim());
                stocktransactionissue.setUserprimaryuomcode(object[19].toString().trim());
                stocktransactionissue.setUserprimaryquantity(new BigDecimal(object[20].toString()));
                stocktransactionissue.setCreateddate(Instant.now());
                diStocktransactionissueRepository.save(stocktransactionissue);
            }
        }

        List<Object[]> stockTransactionReciepts = diStocktransactionrecieptRepository.fetchRecieptByTransactionDate();
        for (Object[] objects : stockTransactionReciepts) {
            Double reservationQuantity = ((BigDecimal) objects[20]).doubleValue();
            Double stockQuantity = ((BigDecimal) objects[21]).doubleValue();
            Double recieptQuantity = ((BigDecimal) objects[22]).doubleValue();
            Double issueRecieptQuantity = stockQuantity.doubleValue() * (reservationQuantity.doubleValue()/stockQuantity.doubleValue());
            Double balanceQuantity = 0.0;
            Double currentBalanceQuantity = issueRecieptQuantity;

            List<DiStocktransactionissue> diStocktransactionissues = diStocktransactionissueRepository.findDiStocktransactionissueByDemandCode(objects[4].toString().trim(), objects[5].toString().trim(), objects[6].toString().trim());
            if (diStocktransactionissues != null && diStocktransactionissues.size() > 0) {
                for (DiStocktransactionissue diStocktransactionissue : diStocktransactionissues) {
                    if (diStocktransactionissue != null && diStocktransactionissue.getRecieptquantity() != null && diStocktransactionissue.getRecieptquantity().doubleValue() == diStocktransactionissue.getUserprimaryquantity().doubleValue()) {
                    } else {
                        if(balanceQuantity.doubleValue() != issueRecieptQuantity.doubleValue()) {
                            if(diStocktransactionissue.getRecieptquantity() != null && diStocktransactionissue.getUserprimaryquantity() != null && diStocktransactionissue.getRecieptquantity().doubleValue() != diStocktransactionissue.getUserprimaryquantity().doubleValue()) {
                                if ((issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) > 0 && (issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) <= (diStocktransactionissue.getUserprimaryquantity().doubleValue() - diStocktransactionissue.getRecieptquantity().doubleValue())) {
                                    diStocktransactionissue.setRecieptquantity(new BigDecimal(currentBalanceQuantity.doubleValue() + diStocktransactionissue.getRecieptquantity().doubleValue()));
                                    DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                    if (result != null) {
                                        DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                        DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                        id.setTransactionnumber(objects[0].toString().trim());
                                        id.setTransactiondetailnumber((Integer) objects[1]);
                                        id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                        id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                        stocktransactionreciept.setId(id);

                                        stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                        stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                        stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                        stocktransactionreciept.setCode(objects[5].toString().trim());
                                        stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                        stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                        stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                        stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                        stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                        stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                        stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                        stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                        stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                        stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                        stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                        stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                        stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                        stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                        stocktransactionreciept.setUserprimaryquantity(new BigDecimal(currentBalanceQuantity.doubleValue() + diStocktransactionissue.getRecieptquantity().doubleValue()));
                                        stocktransactionreciept.setCreateddate(Instant.now());
                                        diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                        balanceQuantity += issueRecieptQuantity;
                                    }
                                } else if((issueRecieptQuantity - balanceQuantity) > (diStocktransactionissue.getUserprimaryquantity().doubleValue() - diStocktransactionissue.getRecieptquantity().doubleValue())) {
                                    Double tempQuantity = diStocktransactionissue.getUserprimaryquantity().doubleValue() - diStocktransactionissue.getRecieptquantity().doubleValue();
                                    currentBalanceQuantity -= tempQuantity;
                                    balanceQuantity += tempQuantity;
                                    diStocktransactionissue.setRecieptquantity(new BigDecimal(tempQuantity.doubleValue()));
                                    DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                    if (result != null) {
                                        DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                        DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                        id.setTransactionnumber(objects[0].toString().trim());
                                        id.setTransactiondetailnumber((Integer) objects[1]);
                                        id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                        id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                        stocktransactionreciept.setId(id);

                                        stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                        stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                        stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                        stocktransactionreciept.setCode(objects[5].toString().trim());
                                        stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                        stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                        stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                        stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                        stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                        stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                        stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                        stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                        stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                        stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                        stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                        stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                        stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                        stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                        stocktransactionreciept.setUserprimaryquantity(new BigDecimal(tempQuantity));
                                        stocktransactionreciept.setCreateddate(Instant.now());
                                        diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                        balanceQuantity += issueRecieptQuantity;
                                    }
                                }
                            } else {
                                if ((issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) > 0 && (issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) <= (diStocktransactionissue.getUserprimaryquantity().doubleValue())) {
                                    diStocktransactionissue.setRecieptquantity(new BigDecimal(currentBalanceQuantity.doubleValue()));
                                    DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                    if (result != null) {
                                        DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                        DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                        id.setTransactionnumber(objects[0].toString().trim());
                                        id.setTransactiondetailnumber((Integer) objects[1]);
                                        id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                        id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                        stocktransactionreciept.setId(id);

                                        stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                        stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                        stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                        stocktransactionreciept.setCode(objects[5].toString().trim());
                                        stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                        stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                        stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                        stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                        stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                        stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                        stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                        stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                        stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                        stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                        stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                        stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                        stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                        stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                        stocktransactionreciept.setUserprimaryquantity(new BigDecimal(currentBalanceQuantity.doubleValue()));
                                        stocktransactionreciept.setCreateddate(Instant.now());
                                        diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                        balanceQuantity += issueRecieptQuantity;
                                    }
                                } else if((issueRecieptQuantity - balanceQuantity) > (diStocktransactionissue.getUserprimaryquantity().doubleValue())) {
                                    Double tempQuantity = diStocktransactionissue.getUserprimaryquantity().doubleValue();
                                    currentBalanceQuantity -= tempQuantity;
                                    balanceQuantity += tempQuantity;
                                    diStocktransactionissue.setRecieptquantity(new BigDecimal(tempQuantity.doubleValue()));
                                    DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                    if (result != null) {
                                        DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                        DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                        id.setTransactionnumber(objects[0].toString().trim());
                                        id.setTransactiondetailnumber((Integer) objects[1]);
                                        id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                        id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                        stocktransactionreciept.setId(id);

                                        stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                        stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                        stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                        stocktransactionreciept.setCode(objects[5].toString().trim());
                                        stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                        stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                        stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                        stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                        stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                        stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                        stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                        stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                        stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                        stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                        stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                        stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                        stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                        stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                        stocktransactionreciept.setUserprimaryquantity(new BigDecimal(tempQuantity));
                                        stocktransactionreciept.setCreateddate(Instant.now());
                                        diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                        balanceQuantity += issueRecieptQuantity;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                diStocktransactionissues = diStocktransactionissueRepository.findDiStocktransactionissueByProductionOrderCode(objects[6].toString().trim(), objects[8].toString().trim(), objects[9].toString().trim(), objects[10].toString().trim(), objects[11].toString().trim(), objects[12].toString().trim(), objects[13].toString().trim(), objects[14].toString().trim(), objects[15].toString().trim(), objects[16].toString().trim(), objects[17].toString().trim(), objects[18].toString().trim());
                if (diStocktransactionissues != null && diStocktransactionissues.size() > 0) {
                    for (DiStocktransactionissue diStocktransactionissue : diStocktransactionissues) {

                        if (diStocktransactionissue != null && diStocktransactionissue.getRecieptquantity() != null && diStocktransactionissue.getRecieptquantity().doubleValue() == diStocktransactionissue.getUserprimaryquantity().doubleValue()) {
                        } else {
                            if(balanceQuantity.doubleValue() != issueRecieptQuantity.doubleValue()) {
                                if(diStocktransactionissue.getRecieptquantity() != null && diStocktransactionissue.getUserprimaryquantity() != null && diStocktransactionissue.getRecieptquantity().doubleValue() != diStocktransactionissue.getUserprimaryquantity().doubleValue()) {
                                    if ((issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) > 0 && (issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) <= (diStocktransactionissue.getUserprimaryquantity().doubleValue())) {
                                        diStocktransactionissue.setRecieptquantity(new BigDecimal(currentBalanceQuantity.doubleValue() + diStocktransactionissue.getRecieptquantity().doubleValue()));
                                        DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                        if (result != null) {
                                            DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                            DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                            id.setTransactionnumber(objects[0].toString().trim());
                                            id.setTransactiondetailnumber((Integer) objects[1]);
                                            id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                            id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                            stocktransactionreciept.setId(id);

                                            stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                            stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                            stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                            stocktransactionreciept.setCode(objects[5].toString().trim());
                                            stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                            stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                            stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                            stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                            stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                            stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                            stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                            stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                            stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                            stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                            stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                            stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                            stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                            stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                            stocktransactionreciept.setUserprimaryquantity(new BigDecimal(currentBalanceQuantity.doubleValue() + diStocktransactionissue.getRecieptquantity().doubleValue()));
                                            stocktransactionreciept.setCreateddate(Instant.now());
                                            diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                            balanceQuantity += issueRecieptQuantity;
                                        }
                                    } else if((issueRecieptQuantity - balanceQuantity) > (diStocktransactionissue.getUserprimaryquantity().doubleValue() - diStocktransactionissue.getRecieptquantity().doubleValue())) {
                                        Double tempQuantity = diStocktransactionissue.getUserprimaryquantity().doubleValue() - diStocktransactionissue.getRecieptquantity().doubleValue();
                                        currentBalanceQuantity -= tempQuantity;
                                        balanceQuantity += tempQuantity;
                                        diStocktransactionissue.setRecieptquantity(new BigDecimal(tempQuantity.doubleValue()));
                                        DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                        if (result != null) {
                                            DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                            DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                            id.setTransactionnumber(objects[0].toString().trim());
                                            id.setTransactiondetailnumber((Integer) objects[1]);
                                            id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                            id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                            stocktransactionreciept.setId(id);

                                            stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                            stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                            stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                            stocktransactionreciept.setCode(objects[5].toString().trim());
                                            stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                            stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                            stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                            stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                            stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                            stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                            stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                            stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                            stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                            stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                            stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                            stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                            stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                            stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                            stocktransactionreciept.setUserprimaryquantity(new BigDecimal(tempQuantity));
                                            stocktransactionreciept.setCreateddate(Instant.now());
                                            diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                            balanceQuantity += issueRecieptQuantity;
                                        }
                                    }
                                } else {
                                    if ((issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) > 0 && (issueRecieptQuantity.doubleValue() - balanceQuantity.doubleValue()) <= diStocktransactionissue.getUserprimaryquantity().doubleValue()) {
                                        diStocktransactionissue.setRecieptquantity(new BigDecimal(currentBalanceQuantity.doubleValue()));
                                        DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                        if (result != null) {
                                            DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                            DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                            id.setTransactionnumber(objects[0].toString().trim());
                                            id.setTransactiondetailnumber((Integer) objects[1]);
                                            id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                            id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                            stocktransactionreciept.setId(id);

                                            stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                            stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                            stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                            stocktransactionreciept.setCode(objects[5].toString().trim());
                                            stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                            stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                            stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                            stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                            stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                            stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                            stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                            stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                            stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                            stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                            stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                            stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                            stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                            stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                            stocktransactionreciept.setUserprimaryquantity(new BigDecimal(currentBalanceQuantity.doubleValue()));
                                            stocktransactionreciept.setCreateddate(Instant.now());
                                            diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                            balanceQuantity += issueRecieptQuantity;
                                        }
                                    } else if((issueRecieptQuantity - balanceQuantity) > (diStocktransactionissue.getUserprimaryquantity().doubleValue())) {
                                        Double tempQuantity = diStocktransactionissue.getUserprimaryquantity().doubleValue();
                                        currentBalanceQuantity -= tempQuantity;
                                        balanceQuantity += tempQuantity;
                                        diStocktransactionissue.setRecieptquantity(new BigDecimal(tempQuantity.doubleValue()));
                                        DiStocktransactionissue result = diStocktransactionissueRepository.save(diStocktransactionissue);
                                        if (result != null) {
                                            DiStocktransactionreciept stocktransactionreciept = new DiStocktransactionreciept();

                                            DiStocktransactionrecieptId id = new DiStocktransactionrecieptId();
                                            id.setTransactionnumber(objects[0].toString().trim());
                                            id.setTransactiondetailnumber((Integer) objects[1]);
                                            id.setIssuetransactionnumber(result.getId().getTransactionnumber());
                                            id.setIssuetransactiondetailnumber(result.getId().getTransactiondetailnumber());
                                            stocktransactionreciept.setId(id);

                                            stocktransactionreciept.setTransactiondate((Date) objects[2]);
                                            stocktransactionreciept.setTransactiontime((Time) objects[3]);
                                            stocktransactionreciept.setCountercode(objects[4].toString().trim());
                                            stocktransactionreciept.setCode(objects[5].toString().trim());
                                            stocktransactionreciept.setProductionordercode(objects[6].toString().trim());

                                            stocktransactionreciept.setItemtypecompanycode(objects[7].toString().trim());
                                            stocktransactionreciept.setItemtypecode(objects[8].toString().trim());
                                            stocktransactionreciept.setDecosubcode01(objects[9].toString().trim());
                                            stocktransactionreciept.setDecosubcode02(objects[10].toString().trim());
                                            stocktransactionreciept.setDecosubcode03(objects[11].toString().trim());
                                            stocktransactionreciept.setDecosubcode04(objects[12].toString().trim());
                                            stocktransactionreciept.setDecosubcode05(objects[13].toString().trim());
                                            stocktransactionreciept.setDecosubcode06(objects[14].toString().trim());
                                            stocktransactionreciept.setDecosubcode07(objects[15].toString().trim());
                                            stocktransactionreciept.setDecosubcode08(objects[16].toString().trim());
                                            stocktransactionreciept.setDecosubcode09(objects[17].toString().trim());
                                            stocktransactionreciept.setDecosubcode10(objects[18].toString().trim());
                                            stocktransactionreciept.setUserprimaryuomcode(objects[19].toString().trim());
                                            stocktransactionreciept.setUserprimaryquantity(new BigDecimal(tempQuantity));
                                            stocktransactionreciept.setCreateddate(Instant.now());
                                            diStocktransactionrecieptRepository.save(stocktransactionreciept);
                                            balanceQuantity += issueRecieptQuantity;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/
}

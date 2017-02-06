package com.crawlermanage.service.pbccrc;

import com.module.dao.entity.pbccrc.*;
import com.module.dao.repository.pbccrc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PbccrcDBService {


    @Autowired
    private PbccrcAccountRepository pbccrcAccountRepository;

    @Autowired
    private PlainPbccrcJsonRepository plainPbccrcJsonRepository;

    @Autowired
    private ReportBaseRepository reportBaseRepository;

    @Autowired
    private CreditRecordProfileRepository creditRecordProfileRepository;

    @Autowired
    private CreditCardDetailRepository creditCardDetailRepository;

    @Autowired
    private OtherLoanDetailRepository otherLoanDetailRepository;

    @Autowired
    private GuarantyForOtherDetailRepository guarantyForOtherDetailRepository;

    @Autowired
    private QueryRecordRepository queryRecordRepository;


    public PbccrcAccount findOne(Long id){
        return pbccrcAccountRepository.findOne(id);
    }

    public List<PbccrcAccount> findByUsername(String usernam){
        return pbccrcAccountRepository.findByUsername(usernam);
    }

    public void save(PlainPbccrcJson plainPbccrcJson){
        plainPbccrcJsonRepository.save(plainPbccrcJson);
    }

//    public List<PlainPbccrcJson> findByCopyId(Long accountId){
//        return plainPbccrcJsonRepository.findByCopyId(accountId);
//    }

    public void save(PbccrcAccount pbccrcAccount) {
        pbccrcAccountRepository.save(pbccrcAccount);
    }

    public void save(PbcReportBase pbcReportBase) {
        reportBaseRepository.save(pbcReportBase);
    }

    public void saveAllCreditCardProfile(List<CreditRecordProfile> profiles) {
        creditRecordProfileRepository.save(profiles);
    }

    public void saveAllPbcCreditCardDetail(List<PbcCreditCardDetail> pbcCreditCardDetails) {
        creditCardDetailRepository.save(pbcCreditCardDetails);
    }

    public void saveAllLoanDetails(List<PbcLoanDetail> pbcLoanDetails) {
        otherLoanDetailRepository.save(pbcLoanDetails);
    }

    public void saveAllGuaranty(List<GuarantyForOtherDetail> guarantyForOtherDetails) {
        guarantyForOtherDetailRepository.save(guarantyForOtherDetails);
    }

    public void saveAllQueryRecords(List<PbcQueryRecord> pbcQueryRecords) {
        queryRecordRepository.save(pbcQueryRecords);
    }

    public PlainPbccrcJson findByTsf75e5b(String tsf75e5b) {
        return plainPbccrcJsonRepository.findByTsf75e5b(tsf75e5b);
    }
}

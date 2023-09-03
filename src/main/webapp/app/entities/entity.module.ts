import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'connect-me',
        loadChildren: () => import('./connect-me/connect-me.module').then(m => m.VamaniportalConnectMeModule)
      },
      {
        path: 'email-invitation',
        loadChildren: () => import('./email-invitation/email-invitation.module').then(m => m.VamaniportalEmailInvitationModule)
      },
      {
        path: 'event',
        loadChildren: () => import('./event/event.module').then(m => m.VamaniportalEventModule)
      },
      {
        path: 'forms-download',
        loadChildren: () => import('./forms-download/forms-download.module').then(m => m.VamaniportalFormsDownloadModule)
      },
      {
        path: 'induction',
        loadChildren: () => import('./induction/induction.module').then(m => m.VamaniportalInductionModule)
      },
      {
        path: 'job-profile',
        loadChildren: () => import('./job-profile/job-profile.module').then(m => m.VamaniportalJobProfileModule)
      },
      {
        path: 'leave-master',
        loadChildren: () => import('./leave-master/leave-master.module').then(m => m.VamaniportalLeaveMasterModule)
      },
      {
        path: 'leave-master-hod',
        loadChildren: () => import('./leave-master-hod/leave-master-hod.module').then(m => m.VamaniportalLeaveMasterHodModule)
      },
      {
        path: 'leave-master-hr',
        loadChildren: () => import('./leave-master-hr/leave-master-hr.module').then(m => m.VamaniportalLeaveMasterHrModule)
      },
      {
        path: 'menu-master',
        loadChildren: () => import('./menu-master/menu-master.module').then(m => m.VamaniportalMenuMasterModule)
      },
      {
        path: 'menu-access-master',
        loadChildren: () => import('./menu-access-master/menu-access-master.module').then(m => m.VamaniportalMenuAccessMasterModule)
      },
      {
        path: 'monthly-salary',
        loadChildren: () => import('./monthly/monthly.module').then(m => m.VamaniportalMonthlyModule)
      },
      {
        path: 'news-details',
        loadChildren: () => import('./news-details/news-details.module').then(m => m.VamaniportalNewsDetailsModule)
      },
      {
        path: 'news-details-approval',
        loadChildren: () =>
          import('./news-details-approval/news-details-approval.module').then(m => m.VamaniportalNewsDetailsApprovalModule)
      },
      {
        path: 'news-headline',
        loadChildren: () => import('./news/news.module').then(m => m.VamaniportalNewsModule)
      },
      {
        path: 'poll-master',
        loadChildren: () => import('./poll-master/poll-master.module').then(m => m.VamaniportalPollMasterModule)
      },
      {
        path: 'policies',
        loadChildren: () => import('./policies/policies.module').then(m => m.VamaniportalPoliciesModule)
      },
      {
        path: 'policies-entry',
        loadChildren: () => import('./policies-entry/policies-entry.module').then(m => m.VamaniportalPoliciesEntryModule)
      },
      {
        path: 'suggestion',
        loadChildren: () => import('./suggestion/suggestion.module').then(m => m.VamaniportalSuggestionModule)
      },
      {
        path: 'vehicle-master',
        loadChildren: () => import('./vehicle-master/vehicle-master.module').then(m => m.VamaniportalVehicleMasterModule)
      },
      {
        path: 'vehicle-approval',
        loadChildren: () => import('./vehicle-approval/vehicle-approval.module').then(m => m.VamaniportalVehiclApprovalModule)
      },
      {
        path: 'vehicle-trp-approval',
        loadChildren: () => import('./vehicle-trp-approval/vehicle-trp-approval.module').then(m => m.VamaniportalVehicleTrpApprovalModule)
      },
      {
        path: 'bank-master',
        loadChildren: () => import('./bank-master/bank-master.module').then(m => m.VamaniportalBankMasterModule)
      },
      {
        path: 'department-master',
        loadChildren: () => import('./department-master/department-master.module').then(m => m.VamaniportalDepartmentMasterModule)
      },
      {
        path: 'designation-master',
        loadChildren: () => import('./designation-master/designation-master.module').then(m => m.VamaniportalDesignationMasterModule)
      },
      {
        path: 'machine-master',
        loadChildren: () => import('./machine-master/machine-master.module').then(m => m.VamaniportalMachineMasterModule)
      },
      {
        path: 'operation-master',
        loadChildren: () => import('./operation-master/operation-master.module').then(m => m.VamaniportalOperationMasterModule)
      },
      {
        path: 'worker-recruitment',
        loadChildren: () => import('./worker-recruitment/worker-recruitment.module').then(m => m.VamaniportalWorkerRecruitmentModule)
      },
      {
        path: 'trail-mock-operation',
        loadChildren: () => import('./trail-mock-operation/trail-mock-operation.module').then(m => m.VamaniportalTrailMockOperationModule)
      },
      {
        path: 'worker-forward-type-master',
        loadChildren: () =>
          import('./worker-forward-type-master/worker-forward-type-master.module').then(m => m.VamaniportalWorkerForwardTypeMasterModule)
      },
      {
        path: 'worker-work-flow-master',
        loadChildren: () =>
          import('./worker-work-flow-master/worker-work-flow-master.module').then(m => m.VamaniportalWorkerWorkFlowMasterModule)
      },
      {
        path: 'relation-master',
        loadChildren: () => import('./relation-master/relation-master.module').then(m => m.VamaniportalRelationMasterModule)
      },
      {
        path: 'worker-joining',
        loadChildren: () => import('./worker-joining/worker-joining.module').then(m => m.VamaniportalWorkerJoiningModule)
      },
      {
        path: 'occupation-master',
        loadChildren: () => import('./occupation-master/occupation-master.module').then(m => m.VamaniportalOccupationMasterModule)
      },
      {
        path: 'language-master',
        loadChildren: () => import('./language-master/language-master.module').then(m => m.VamaniportalLanguageMasterModule)
      },
      {
        path: 'nomination-type-master',
        loadChildren: () =>
          import('./nomination-type-master/nomination-type-master.module').then(m => m.VamaniportalNominationTypeMasterModule)
      },
      {
        path: 'recruitment-country-master',
        loadChildren: () =>
          import('./recruitment-country-master/recruitment-country-master.module').then(m => m.VamaniportalRecruitmentCountryMasterModule)
      },
      {
        path: 'recruitment-state-master',
        loadChildren: () =>
          import('./recruitment-state-master/recruitment-state-master.module').then(m => m.VamaniportalRecruitmentStateMasterModule)
      },
      {
        path: 'recruitment-district',
        loadChildren: () => import('./recruitment-district/recruitment-district.module').then(m => m.VamaniportalRecruitmentDistrictModule)
      },
      {
        path: 'recruitment-city-master',
        loadChildren: () =>
          import('./recruitment-city-master/recruitment-city-master.module').then(m => m.VamaniportalRecruitmentCityMasterModule)
      },
      {
        path: 'education-master',
        loadChildren: () => import('./education-master/education-master.module').then(m => m.VamaniportalEducationMasterModule)
      },
      {
        path: 'education-type-master',
        loadChildren: () =>
          import('./education-type-master/education-type-master.module').then(m => m.VamaniportalEducationTypeMasterModule)
      },
      {
        path: 'institute-master',
        loadChildren: () => import('./institute-master/institute-master.module').then(m => m.VamaniportalInstituteMasterModule)
      },
      {
        path: 'recruitment-document-master',
        loadChildren: () =>
          import('./recruitment-document-master/recruitment-document-master.module').then(
            m => m.VamaniportalRecruitmentDocumentMasterModule
          )
      },
      {
        path: 'worker-join-flow-master',
        loadChildren: () =>
          import('./worker-join-flow-master/worker-join-flow-master.module').then(m => m.VamaniportalWorkerJoinFlowMasterModule)
      },
      {
        path: 'tds-group-master',
        loadChildren: () => import('./tds-group-master/tds-group-master.module').then(m => m.VamaniportalTdsGroupMasterModule)
      },
      {
        path: 'tds-group-details',
        loadChildren: () => import('./tds-group-details/tds-group-details.module').then(m => m.VamaniportalTdsGroupDetailsModule)
      },
      {
        path: 'tds-declarations',
        loadChildren: () => import('./tds-declaration/tds-declaration.module').then(m => m.VamaniportalTdsDeclarationModule)
      },
      {
        path: 'employee-hierarchy',
        loadChildren: () => import('./employee-hierarchy/employee-hierarchy.module').then(m => m.VamaniportalEmployeeHierarchyModule)
      },
      {
        path: 'tds-query',
        loadChildren: () => import('./tds-query/tds-query.module').then(m => m.VamaniportalTdsQueryModule)
      },
      {
        path: 'tds-year-master',
        loadChildren: () => import('./tds-year-master/tds-year-master.module').then(m => m.VamaniportalTdsYearMasterModule)
      },
      {
        path: 'monthly-report',
        loadChildren: () => import('./monthly-report/monthly-report.module').then(m => m.VamaniportalMonthlyReportModule)
      },
      {
        path: 'tds-computation',
        loadChildren: () => import('./tds-computation/tds-computation.module').then(m => m.VamaniportalTdsComputationModule)
      },
      {
        path: 'tds-computation-qry',
        loadChildren: () =>
          import('./tds-computation-entry/tds-computation-entry.module').then(m => m.VamaniportalTdsComputationEntryModule)
      },
      {
        path: 'tds-slab-master',
        loadChildren: () => import('./tds-slab-master/tds-slab-master.module').then(m => m.VamaniportalTdsSlabMasterModule)
      },
      {
        path: 'asset-company-master',
        loadChildren: () => import('./asset-company-master/asset-company-master.module').then(m => m.VamaniportalAssetCompanyMasterModule)
      },
      {
        path: 'asset-ownership-master',
        loadChildren: () =>
          import('./asset-ownership-master/asset-ownership-master.module').then(m => m.VamaniportalAssetOwnershipMasterModule)
      },
      {
        path: 'asset-soft-type-master',
        loadChildren: () =>
          import('./asset-soft-type-master/asset-soft-type-master.module').then(m => m.VamaniportalAssetSoftTypeMasterModule)
      },
      {
        path: 'asset-sub-type-detail-master',
        loadChildren: () =>
          import('./asset-sub-type-detail-master/asset-sub-type-detail-master.module').then(
            m => m.VamaniportalAssetSubTypeDetailMasterModule
          )
      },
      {
        path: 'asset-sub-type-master',
        loadChildren: () => import('./asset-sub-type-master/asset-sub-type-master.module').then(m => m.VamaniportalAssetSubTypeMasterModule)
      },
      {
        path: 'asset-supplier-master',
        loadChildren: () =>
          import('./asset-supplier-master/asset-supplier-master.module').then(m => m.VamaniportalAssetSupplierMasterModule)
      },
      {
        path: 'asset-type-master',
        loadChildren: () => import('./asset-type-master/asset-type-master.module').then(m => m.VamaniportalAssetTypeMasterModule)
      },
      {
        path: 'asset-warranty-type-master',
        loadChildren: () =>
          import('./asset-warranty-type-master/asset-warranty-type-master.module').then(m => m.VamaniportalAssetWarrantyTypeMasterModule)
      },
      {
        path: 'asset-location-master',
        loadChildren: () =>
          import('./asset-location-master/asset-location-master.module').then(m => m.VamaniportalAssetLocationMasterModule)
      },
      {
        path: 'asset-master',
        loadChildren: () => import('./asset-master/asset-master.module').then(m => m.VamaniportalAssetMasterModule)
      },
      {
        path: 'asset-audit-details',
        loadChildren: () => import('./asset-audit-details/asset-audit-details.module').then(m => m.VamaniportalAssetAuditDetailsModule)
      },
      {
        path: 'asset-document-master',
        loadChildren: () =>
          import('./asset-document-master/asset-document-master.module').then(m => m.VamaniportalAssetDocumentMasterModule)
      },
      {
        path: 'ignore-software-master',
        loadChildren: () =>
          import('./ignore-software-master/ignore-software-master.module').then(m => m.VamaniportalIgnoreSoftwareMasterModule)
      },
      {
        path: 'asset-audit-software-compare',
        loadChildren: () =>
          import('./asset-audit-software-compare/asset-audit-software-compare.module').then(
            m => m.VamaniportalAssetAuditSoftwareCompareModule
          )
      },
      {
        path: 'leave-entry-hr',
        loadChildren: () => import('./leave-entry-hr/leave-entry-hr.module').then(m => m.VamaniportalLeaveEntryHrModule)
      },
      {
        path: 'software-query',
        loadChildren: () => import('./software-query/software-query.module').then(m => m.VamaniportalSoftwareQueryModule)
      },
      {
        path: 'hardware-query',
        loadChildren: () => import('./hardware-query/hardware-query.module').then(m => m.VamaniportalHardwareQueryModule)
      },
      {
        path: 'software-key-details',
        loadChildren: () => import('./software-key-details/software-key-details.module').then(m => m.VamaniportalSoftwareKeyDetailsModule)
      },
      {
        path: 'mobile-attendance',
        loadChildren: () => import('./mobile-attendance/mobile-attendance.module').then(m => m.VamaniportalMobileAttendanceModule)
      },
      {
        path: 'mmr-master',
        loadChildren: () => import('./mmr-master/mmr-master.module').then(m => m.VamaniportalMMRMasterModule)
      },
      {
        path: 'comp-off-master',
        loadChildren: () => import('./comp-off-master/comp-off-master.module').then(m => m.VamaniportalCompOffMasterModule)
      },
      {
        path: 'comp-off-approval',
        loadChildren: () => import('./comp-off-approval/comp-off-approval.module').then(m => m.VamaniportalCompOffApprovalModule)
      },
      {
        path: 'employee-information-update',
        loadChildren: () =>
          import('./employee-information-update/employee-information-update.module').then(
            m => m.VamaniportalEmployeeInformationUpdateModule
          )
      },
      {
        path: 'employee-information-approval',
        loadChildren: () =>
          import('./employee-information-update-aprv/employee-information-update-aprv.module').then(
            m => m.VamaniportalEmployeeInformationUpdateAprvModule
          )
      },
      {
        path: 'mobile-version',
        loadChildren: () => import('./mobile-version/mobile-version.module').then(m => m.VamaniportalMobileVersionModule)
      },
      {
        path: 'leave-difference',
        loadChildren: () => import('./leave-difference/leave-difference.module').then(m => m.VamaniportalLeaveDifferenceModule)
      },
      {
        path: 'leave-pending-report',
        loadChildren: () => import('./leave-pending-report/leave-pending-report.module').then(m => m.VamaniportalLeavePendingReportModule)
      },
      {
        path: 'mmr-report',
        loadChildren: () => import('./mmr-report/mmr-report.module').then(m => m.VamaniportalMmrReportModule)
      },
      {
        path: 'strength-report',
        loadChildren: () => import('./strength-report/strength-report.module').then(m => m.VamaniportalStrengthReportModule)
      },
      {
        path: 'previous-employment-details',
        loadChildren: () =>
          import('./previous-employment-details/previous-employment-details.module').then(
            m => m.VamaniportalPreviousEmploymentDetailsModule
          )
      },
      {
        path: 'previous-employment-qry',
        loadChildren: () =>
          import('./previous-employment-qry/previous-employment-qry.module').then(m => m.VamaniportalPreviousEmploymentQryModule)
      },
      {
        path: 'rate-master',
        loadChildren: () => import('./rate-master/rate-master.module').then(m => m.VamaniportalRateMasterModule)
      },
      {
        path: 'conveyance-master',
        loadChildren: () => import('./conveyance-master/conveyance-master.module').then(m => m.VamaniportalConveyanceMasterModule)
      },
      {
        path: 'conveyance-master-approval',
        loadChildren: () =>
          import('./conveyance-master-approval/conveyance-master-approval.module').then(m => m.VamaniportalConveyanceMasterApprovalModule)
      },
      {
        path: 'conveyance-master-hr',
        loadChildren: () => import('./conveyance-master-hr/conveyance-master-hr.module').then(m => m.VamaniportalConveyanceMasterHrModule)
      },
      {
        path: 'conveyance-process-hr',
        loadChildren: () =>
          import('./conveyance-master-process/conveyance-master-process.module').then(m => m.VamaniportalConveyanceMasterProcessModule)
      },
      {
        path: 'travel-application-master',
        loadChildren: () =>
          import('./travel-application-master/travel-application-master.module').then(m => m.VamaniportalTravelApplicationMasterModule)
      },
      {
        path: 'travel-application-master-hod',
        loadChildren: () =>
          import('./travel-application-master-hod/travel-application-master-hod.module').then(
            m => m.VamaniportalTravelApplicationMasterHodModule
          )
      },
      {
        path: 'travel-application-master-hr',
        loadChildren: () =>
          import('./travel-application-master-hr/travel-application-master-hr.module').then(
            m => m.VamaniportalTravelApplicationMasterHrModule
          )
      },
      {
        path: 'conveyance-report',
        loadChildren: () => import('./conveyance-report/conveyance-report.module').then(m => m.VamaniportalConveyanceReportModule)
      },
      {
        path: 'monthly-news-data',
        loadChildren: () => import('./monthly-news-data/monthly-news-data.module').then(m => m.VamaniportalMonthlyNewsDataModule)
      },
      {
        path: 'tds-declaration-upload',
        loadChildren: () =>
          import('./tds-declaration-upload/tds-declaration-upload.module').then(m => m.VamaniportalTdsDeclarationUploadModule)
      },
      {
        path: 'supervisor-employee-details',
        loadChildren: () =>
          import('./supervisor-employee-details/supervisor-employee-details.module').then(
            m => m.VamaniportalSupervisorEmployeeDetailsModule
          )
      },
      {
        path: 'employee-search',
        loadChildren: () => import('./employees-search/employee-search.module').then(m => m.VamaniportalEmployeeSearchModule)
      },
      {
        path: 'tds-declaration-upload-qry',
        loadChildren: () =>
          import('./tds-declaration-upload-qry/tds-declaration-upload-qry.module').then(m => m.VamaniportalTdsDeclarationUploadQryModule)
      },
      {
        path: 'report-type-master',
        loadChildren: () => import('./report-type-master/report-type-master.module').then(m => m.VamaniportalReportTypeMasterModule)
      },
      {
        path: 'terms-condition-master',
        loadChildren: () =>
          import('./terms-condition-master/terms-condition-master.module').then(m => m.VamaniportalTermsConditionMasterModule)
      },
      {
        path: 'delivery-challan',
        loadChildren: () => import('./delivery-challan/delivery-challan.module').then(m => m.VamaniportalDeliveryChallanModule)
      },
      {
        path: 'deliver-challan-approval',
        loadChildren: () =>
          import('./deliver-challan-approval/deliver-challan-approval.module').then(m => m.VamaniportalDeliverChallanApprovalModule)
      },
      {
        path: 'gst-govt-upload',
        loadChildren: () => import('./gst-govt-upload/gst-govt-upload.module').then(m => m.VamaniportalGstGovtUploadModule)
      },
      {
        path: 'gst-vopl-upload',
        loadChildren: () => import('./gst-vopl-upload/gst-vopl-upload.module').then(m => m.VamaniportalGstVoplUploadModule)
      },
      {
        path: 'debit-note-entry',
        loadChildren: () => import('./debit-note-entry/debit-note-entry.module').then(m => m.VamaniportalDebitNoteEntryModule)
      },
      {
        path: 'bank-reconciliation',
        loadChildren: () => import('./bank-reconciliation/bank-reconciliation.module').then(m => m.VamaniportalBankReconciliationModule)
      },
      {
        path: 'gst-reconciliation',
        loadChildren: () => import('./gst-reconciliation/gst-reconciliation.module').then(m => m.VamaniportalGstReconciliationModule)
      },
      {
        path: 'form-16',
        loadChildren: () => import('./form-16/form-16.module').then(m => m.VamaniportalForm16Module)
      },
      {
        path: 'fin-gl-change',
        loadChildren: () =>
          import('./fin-document-gl-change/fin-document-gl-change.module').then(m => m.VamaniportalFinDocumentGlChangeComponentModule)
      },
      {
        path: 'brc-details-uploads',
        loadChildren: () =>
          import('./bank-realisation-certificate-upload/bank-realisation-certificate-upload.module').then(
            m => m.VamaniPortalBankRealizationCertificateModule
          )
      },
      {
        path: 'script-uploads',
        loadChildren: () => import('./script-details-upload/script-details-upload.module').then(m => m.VamaniPortalScriptDetailsModule)
      },
      {
        path: 'employee-salary',
        loadChildren: () => import('./employee-salary/employee-salary.module').then(m => m.VamaniportalEmployeeSalaryModule)
      },
      {
        path: 'transaction-upload',
        loadChildren: () => import('./transaction-upload/transaction-upload.module').then(m => m.VamaniPortalTransactionUploadModule)
      },
      {
        path: 'tds-document-query',
        loadChildren: () => import('./tds-document-query/tds-document-query.module').then(m => m.VamaniportalTdsDocumentQueryModule)
      },
      {
        path: 'tds-computation-report',
        loadChildren: () =>
          import('./tds-computation-report/tds-computation-report.module').then(m => m.VamaniportalTdsComputationReportModule)
      },
      {
        path: 'computation-download',
        loadChildren: () => import('./computation-download/computation-download.module').then(m => m.VamaniportalComputationDownloadModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VamaniportalEntityModule {}

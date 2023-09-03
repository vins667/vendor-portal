import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { VendorBuyerAuditService } from './vendor-buyer-audit.service';
import { IVendorBuyerAuditDetailsBean } from 'app/shared/model/vendor-buyer-audit-details-bean.model';
import { IMaster } from 'app/shared/model/master.modal';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';

@Component({
  selector: 'jhi-vendor-buyer-audit-update',
  templateUrl: './vendor-buyer-audit-update.component.html'
})
export class VendorBuyerAuditUpdateComponent implements OnInit {
  isSaving: boolean;
  vendorBuyerAuditDetail: IVendorBuyerAuditDetailsBean;
  auditQuesBuyerMapping: IAuditQuesBuyerMapping;
  selectedMaster: IMaster;
  currentDate: any;
  isProcess = false;

  constructor(
    protected vendorBuyerAuditService: VendorBuyerAuditService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.currentDate = new Date();
    this.activatedRoute.data.subscribe(({ vendorBuyerAuditDetail }) => {
      this.vendorBuyerAuditDetail = vendorBuyerAuditDetail;
      if (this.vendorBuyerAuditDetail && this.vendorBuyerAuditDetail.masters && this.vendorBuyerAuditDetail.masters.length > 0) {
        this.selectedMaster = this.vendorBuyerAuditDetail.masters[0];
        if (this.selectedMaster) {
          this.vendorBuyerAuditService
            .findByMap(this.selectedMaster.id, this.vendorBuyerAuditDetail.buyerMaster.buyerCode)
            .subscribe(auditQuesBuyerMapping => {
              this.auditQuesBuyerMapping = auditQuesBuyerMapping.body;
            });
        }
      }
    });
  }

  viewChange(master: IMaster) {
    this.selectedMaster = master;
    if (this.selectedMaster) {
      this.isProcess = true;
      this.vendorBuyerAuditService
        .findByMap(this.selectedMaster.id, this.vendorBuyerAuditDetail.buyerMaster.buyerCode)
        .subscribe(auditQuesBuyerMapping => {
          this.auditQuesBuyerMapping = auditQuesBuyerMapping.body;
          this.isProcess = false;
        });
    }
  }
}

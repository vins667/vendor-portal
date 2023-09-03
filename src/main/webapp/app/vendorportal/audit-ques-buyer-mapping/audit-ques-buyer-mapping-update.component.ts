import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';
import { AuditQuesBuyerMappingService } from './audit-ques-buyer-mapping.service';
import { IVendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';
import { VendorAuditQuesMasterService } from 'app/vendorportal/vendor-audit-ques-master';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { BuyerMasterSearchComponent } from './buyer-master-search.component';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { Master } from 'app/shared/model/master.modal';
import { IVendorAuditGroupMasterBean } from 'app/shared/model/vendor-audit-group-master-bean.model';
import { AuctionQuestionBuyerMappingBean } from 'app/shared/model/auction-question-buyer-mapping-bean.model';

@Component({
    selector: 'jhi-audit-ques-buyer-mapping-update',
    templateUrl: './audit-ques-buyer-mapping-update.component.html',
    styleUrls: ['./audit-ques-buyer-mapping.component.scss']
})
export class AuditQuesBuyerMappingUpdateComponent implements OnInit {
    auditQuesBuyerMapping: IAuditQuesBuyerMapping;
    isSaving: boolean;
    protected ngbModalRef: NgbModalRef;
    buyerMasters: IBuyerMaster[];
    vendorAuditGroupMasterBean?: IVendorAuditGroupMasterBean[];

    vendorauditquesmasters: IVendorAuditQuesMaster[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected auditQuesBuyerMappingService: AuditQuesBuyerMappingService,
        protected vendorAuditQuesMasterService: VendorAuditQuesMasterService,
        protected activatedRoute: ActivatedRoute,
        private eventManager: JhiEventManager,
        protected modalService: NgbModal
    ) {}

    ngOnInit() {
      this.vendorAuditQuesMasterService.query().subscribe(
        (res: HttpResponse<IVendorAuditQuesMaster[]>) => {
          this.vendorauditquesmasters = res.body;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      this.isSaving = false;
      this.vendorAuditGroupMasterBean = [];
      this.registerChangeInSelectedFabricContentDetails();
      this.activatedRoute.data.subscribe(({auditQuesBuyerMapping}) => {
        this.auditQuesBuyerMapping = auditQuesBuyerMapping;
        const resultMap = new Map(this.auditQuesBuyerMapping.buyerMaps.map(i => [i.key, i.value]));
        if (this.auditQuesBuyerMapping.vendorAuditQuesMaster !== undefined && this.auditQuesBuyerMapping.vendorAuditQuesMaster !== null) {
          this.vendorAuditQuesMasterService.findDetails(this.auditQuesBuyerMapping.vendorAuditQuesMaster.id).subscribe(vendorAuditGroupMasterBean => {
            this.vendorAuditGroupMasterBean = vendorAuditGroupMasterBean.body;
            if (this.vendorAuditGroupMasterBean) {
              this.vendorAuditGroupMasterBean.forEach(auditGroupMasterBean => {
                auditGroupMasterBean.initColumns = [
                  {id: 'Ques', desc: 'Questions'}
                ];
                auditGroupMasterBean.displayedColumns = auditGroupMasterBean.initColumns.map(col => col.id);
              });
              if (this.auditQuesBuyerMapping.buyerMasters) {
                this.buyerMasters = [];
                this.auditQuesBuyerMapping.buyerMasters.forEach(buyerMaster => {
                  this.buyerMasters.push(buyerMaster);
                  this.vendorAuditGroupMasterBean.forEach(auditGroupMasterBean => {
                    auditGroupMasterBean.displayedColumns = [];
                    const master = new Master();
                    master.id = buyerMaster.buyerCode;
                    master.desc = buyerMaster.buyerName;
                    master.extract = false;
                    master.name = buyerMaster.auditName;
                    auditGroupMasterBean.initColumns.push(master);
                    auditGroupMasterBean.displayedColumns = auditGroupMasterBean.initColumns.map(col => col.id);
                    auditGroupMasterBean.vendorAuditQuesDetails.forEach(vendorAuditQuesDetail => {
                      if (vendorAuditQuesDetail.buyerMastersMap) {
                        if (resultMap.has(vendorAuditQuesDetail.id + buyerMaster.buyerCode)) {
                          vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, resultMap.get(vendorAuditQuesDetail.id + buyerMaster.buyerCode));
                        } else {
                          vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, false);
                        }
                      } else {
                        vendorAuditQuesDetail.buyerMastersMap = new Map<string, boolean>();
                        if (resultMap.has(vendorAuditQuesDetail.id + buyerMaster.buyerCode)) {
                          vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, resultMap.get(vendorAuditQuesDetail.id + buyerMaster.buyerCode));
                        } else {
                          vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, false);
                        }
                      }
                    });
                  });
                });
              }
            }
          });
        }
      });
    }

    registerChangeInSelectedFabricContentDetails() {
      this.eventManager.subscribe('selectedBuyerList', message => {
        const buyerMaster = message.content;
        if (this.buyerMasters) {
          let exist = false;
          let ctr = 0;
          this.buyerMasters.forEach(buyerMasterT => {
            ++ctr;
            if (buyerMaster.buyerCode === buyerMasterT.buyerCode) {
              exist = true;
            }
          });
          if (exist === false && ctr === this.buyerMasters.length) {
            this.buyerMasters.push(buyerMaster);
            this.vendorAuditGroupMasterBean.forEach(vendorAuditGroupMasterBean => {
              vendorAuditGroupMasterBean.displayedColumns = [];
              const master = new Master();
              master.id = buyerMaster.buyerCode;
              master.desc = buyerMaster.buyerName;
              master.extract = false;
              master.name = this.auditQuesBuyerMapping.vendorAuditQuesMaster.auditName;
              vendorAuditGroupMasterBean.initColumns.push(master);
              vendorAuditGroupMasterBean.displayedColumns = vendorAuditGroupMasterBean.initColumns.map(col => col.id);
              vendorAuditGroupMasterBean.vendorAuditQuesDetails.forEach(vendorAuditQuesDetail => {
                if (vendorAuditQuesDetail.buyerMastersMap) {
                  vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, true);
                } else {
                  vendorAuditQuesDetail.buyerMastersMap = new Map<string, boolean>();
                  vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, true);
                }
              });
            });
          }
        } else {
          this.buyerMasters = [];
          this.buyerMasters.push(buyerMaster);
          this.vendorAuditGroupMasterBean.forEach(vendorAuditGroupMasterBean => {
            vendorAuditGroupMasterBean.displayedColumns = [];
            const master = new Master();
            master.id = buyerMaster.buyerCode;
            master.desc = buyerMaster.buyerName;
            master.extract = false;
            master.name = this.auditQuesBuyerMapping.vendorAuditQuesMaster.auditName;
            vendorAuditGroupMasterBean.initColumns.push(master);
            vendorAuditGroupMasterBean.displayedColumns = vendorAuditGroupMasterBean.initColumns.map(col => col.id);
            vendorAuditGroupMasterBean.vendorAuditQuesDetails.forEach(vendorAuditQuesDetail => {
              if (vendorAuditQuesDetail.buyerMastersMap) {
                vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, true);
              } else {
                vendorAuditQuesDetail.buyerMastersMap = new Map<string, boolean>();
                vendorAuditQuesDetail.buyerMastersMap.set(buyerMaster.buyerCode, true);
              }
            });
          });
        }
      });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const auctionQuestionBuyerMappingBean = new AuctionQuestionBuyerMappingBean();
        auctionQuestionBuyerMappingBean.vendorAuditQuesMaster = this.auditQuesBuyerMapping.vendorAuditQuesMaster;
        auctionQuestionBuyerMappingBean.vendorAuditGroupMasterBean = this.vendorAuditGroupMasterBean;
        this.subscribeToSaveResponse(this.auditQuesBuyerMappingService.create(auctionQuestionBuyerMappingBean));
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuditQuesBuyerMapping>>) {
        result.subscribe(
            (res: HttpResponse<IAuditQuesBuyerMapping>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackVendorAuditQuesMasterById(index: number, item: IVendorAuditQuesMaster) {
        return item.id;
    }

    changeAudit() {
      if (this.auditQuesBuyerMapping.vendorAuditQuesMaster !== undefined && this.auditQuesBuyerMapping.vendorAuditQuesMaster !== null) {
        this.vendorAuditQuesMasterService.findDetails(this.auditQuesBuyerMapping.vendorAuditQuesMaster.id).subscribe(vendorAuditGroupMasterBean => {
          this.vendorAuditGroupMasterBean = vendorAuditGroupMasterBean.body;
          if (this.vendorAuditGroupMasterBean) {
            this.vendorAuditGroupMasterBean.forEach(auditGroupMasterBean => {
              auditGroupMasterBean.initColumns = [
                {id: 'Ques', desc: 'Questions'}
              ];
              auditGroupMasterBean.displayedColumns = auditGroupMasterBean.initColumns.map(col => col.id);
            });
          }
        });
      }
    }

    searchContent() {
      this.ngbModalRef = this.modalService.open(BuyerMasterSearchComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'xlModal'
      });
    }

    showDetails(displayedColumn, extract) {
        displayedColumn.extract = extract;
    }

    changeValue(vendorAuditQuesDetails, buyerCode) {
      vendorAuditQuesDetails.buyerMastersMap.set(buyerCode, !vendorAuditQuesDetails.buyerMastersMap.get(buyerCode));
    }

    changeValueAll(buyerCode, auditName) {
      this.vendorAuditGroupMasterBean.forEach(auditGroupMasterBean => {
        auditGroupMasterBean.initColumns.forEach(buyerColumn => {
          if (buyerColumn.id === buyerCode) {
            buyerColumn.name = auditName;
          }
        });
      });
    }
}

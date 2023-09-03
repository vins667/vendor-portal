import { AfterViewChecked, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { IProfileWorkFlow, ProfileWorkFlow } from 'app/shared/model/profile-work-flow.model';
import { AccountService } from 'app/core/auth/account.service';
import { SnotifyService } from 'ng-snotify';
import { JhiEventManager } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { VendorsService } from './vendors.service';
import { VendorsBean } from 'app/shared/model/vendors-bean.model';
import { IDeliveryTermMaster } from 'app/shared/model/delivery-term-master.model';
import { DeliveryTermMasterService } from 'app/vendorportal/delivery-term-master';
import { IPayTermMaster } from 'app/shared/model/pay-term-master.model';
import { PayTermMasterService } from 'app/vendorportal/pay-term-master';
import { ShipmentTermMasterService } from 'app/vendorportal/shipment_term_master/shipment-term-master.service';
import { CurrencyMasterService } from 'app/vendorportal/currency-master';
import { IShipmentTermMaster } from 'app/shared/model/shipment-term-master.model';
import { ICurrencyMaster } from 'app/shared/model/currency-master.model';

@Component({
  selector: 'jhi-vendor-workflow',
  templateUrl: './vendor-workflow.component.html',
  styleUrls: ['./vendors.scss']
})
export class VendorWorkflowComponent implements OnInit, AfterViewChecked {
  isSaving: boolean;
  @Input() vendorsBean: VendorsBean;
  profileWorkFlow: IProfileWorkFlow;
  deliveryTermMasters: IDeliveryTermMaster[];
  payTermMasters: IPayTermMaster[];
  shipmentTermMasters: IShipmentTermMaster[];
  currencyMasters: ICurrencyMaster[];

  @ViewChild('scrollMe', { static: false }) private scrollMe: ElementRef;

  constructor(
    private accountService: AccountService,
    private vendorsService: VendorsService,
    private snotifyService: SnotifyService,
    private deliveryTermMasterService: DeliveryTermMasterService,
    private payTermMasterService: PayTermMasterService,
    private shipmentTermMasterService: ShipmentTermMasterService,
    private currencyMasterService: CurrencyMasterService,
    protected eventManager: JhiEventManager
  ) {
    this.profileWorkFlow = new ProfileWorkFlow();
    this.profileWorkFlow.userType = 'V';
    this.profileWorkFlow.forwardFlag = 'Q';
  }

  ngOnInit() {
    this.loadAll();
    this.profileWorkFlow.vendorId = this.vendorsBean.id;
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    this.scrollMe.nativeElement.scrollTop = this.scrollMe.nativeElement.scrollHeight;
  }

  save() {
    this.isSaving = true;
    this.profileWorkFlow.vendorCode = this.vendorsBean.vendorCode;
    this.profileWorkFlow.vendorShortName = this.vendorsBean.vendorShortName;
    this.profileWorkFlow.deliveryTermMasterId = this.vendorsBean.deliveryTermMasterId;
    this.profileWorkFlow.payTermMasterId = this.vendorsBean.payTermMasterId;
    this.profileWorkFlow.shipmentTermMasterId = this.vendorsBean.shipmentTermMasterId;
    this.profileWorkFlow.currencyMasterId = this.vendorsBean.currencyMasterId;
    this.profileWorkFlow.orderAllowed = this.vendorsBean.orderAllowed;
    this.subscribeToSaveResponse(this.vendorsService.createWorkflow(this.profileWorkFlow));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfileWorkFlow>>) {
    result.subscribe((res: HttpResponse<IProfileWorkFlow>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.profileWorkFlow = new ProfileWorkFlow();
    this.profileWorkFlow.userType = 'V';
    this.profileWorkFlow.forwardFlag = 'Q';
    this.profileWorkFlow.vendorId = this.vendorsBean.id;
    this.scrollToBottom();
    this.eventManager.broadcast({ name: 'tabModification', content: '' });
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  loadAll() {
    this.deliveryTermMasterService.query().subscribe((res: HttpResponse<IDeliveryTermMaster[]>) => {
      this.deliveryTermMasters = res.body;
    });
    this.payTermMasterService.query().subscribe((res: HttpResponse<IPayTermMaster[]>) => {
      this.payTermMasters = res.body;
    });
    this.shipmentTermMasterService.query().subscribe((res: HttpResponse<IShipmentTermMaster[]>) => {
      this.shipmentTermMasters = res.body;
    });
    this.currencyMasterService.query().subscribe((res: HttpResponse<ICurrencyMaster[]>) => {
      this.currencyMasters = res.body;
    });
  }
}

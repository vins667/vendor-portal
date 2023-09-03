import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { DateTimeAdapter, OWL_DATE_TIME_FORMATS, OWL_DATE_TIME_LOCALE } from 'ng-pick-datetime';
import { MomentDateTimeAdapter } from 'ng-pick-datetime-moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BillRegisterMaster, IBillRegisterMaster } from 'app/finance/bill-register/bill-register-master.model';
import { BillRegisterDetails, IBillRegisterDetails } from 'app/finance/bill-register/bill-register-details.model';
import { BillRegisterMasterService } from 'app/finance/bill-register/bill-register-master.service';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { MasterSearch } from 'app/shared/model/master-search.model';
import { ICompany } from 'app/shared/db2/model/company.model';
import { IDivision } from 'app/shared/db2/model/division.model';
import { IFinbusinessunit } from 'app/shared/db2/model/finbusinessunit.model';
import { CompanyService } from 'app/shared/db2/service/company.service';
import { DivisionService } from 'app/shared/db2/service/division.service';
import { FinbusinessunitService } from 'app/shared/db2/service/finbusinessunit.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PlantInvoiceSelectionComponent } from './plant-invoice-selection.component';
import { JhiEventManager } from 'ng-jhipster';
import { IPlantinvoice } from 'app/finance/bill-register/plantinvoice.model';

export const MY_MOMENT_FORMATS = {
  parseInput: 'DD-MM-YYYY LT',
  fullPickerInput: 'DD-MM-YYYY LT',
  datePickerInput: 'DD-MM-YYYY',
  timePickerInput: 'HH:mm',
  monthYearLabel: 'MMM YYYY',
  dateA11yLabel: 'LL',
  monthYearA11yLabel: 'MMMM YYYY'
};

@Component({
  selector: 'jhi-bill-register-update',
  templateUrl: './bill-register-update.component.html',
  providers: [
    // `MomentDateTimeAdapter` and `OWL_MOMENT_DATE_TIME_FORMATS` can be automatically provided by importing
    // `OwlMomentDateTimeModule` in your applications root module. We provide it at the component level
    // here, due to limitations of our example generation script.
    { provide: DateTimeAdapter, useClass: MomentDateTimeAdapter, deps: [OWL_DATE_TIME_LOCALE] },
    { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ]
})
export class BillRegisterUpdateComponent implements OnInit {
  isSaving = false;
  companies?: ICompany[];
  divisions?: IDivision[];
  finbusinessunits?: IFinbusinessunit[];
  public dataRemotePlantInvoice: RemoteData;
  public dataRemoteSupplier: RemoteData;
  billRegisterDetails: IBillRegisterDetails[] = [];
  currentBillRegisterDetail: IBillRegisterDetails;
  protected ngbModalRef: NgbModalRef;
  eventSubscriber?: Subscription;

  editForm = this.fb.group({
    id: [],
    company: [null, [Validators.required, Validators.maxLength(3)]],
    division: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcompanycode: [null, [Validators.required, Validators.maxLength(3)]],
    businessunitcode: [null, [Validators.required, Validators.maxLength(10)]],
    billtype: [null, [Validators.required, Validators.maxLength(20)]],
    billnumber: [null, [Validators.required, Validators.maxLength(50)]],
    billdate: [null, [Validators.required]],
    customersuppliertype: [null, [Validators.required, Validators.maxLength(1)]],
    customersuppliercode: [null, [Validators.required, Validators.maxLength(20)]],
    customersuppliername: [null, [Validators.maxLength(100)]],
    remarks: [null, [Validators.maxLength(2000)]],
    totalQuantity: [null, [Validators.required]],
    totalValue: [null, [Validators.required]],
    submitDate: [null],
    receiveDate: [],
    queryFlag: [],
    queryRemarks: [null, [Validators.maxLength(2000)]],
    createdby: [],
    createddate: [],
    updatedby: [],
    updateddate: [],
    billRegisterDetailsBeans: []
  });

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected billRegisterMasterService: BillRegisterMasterService,
    protected companyService: CompanyService,
    protected divisionService: DivisionService,
    protected finbusinessunitService: FinbusinessunitService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    protected snotifyService: SnotifyService,
    private cdRef: ChangeDetectorRef,
    public completerService: CompleterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.dataRemoteSupplier = this.completerService.remote(
      this.billRegisterMasterService.resourceUrlSupplier + 2 + '/',
      'addressee',
      'addressee'
    );
    this.dataRemoteSupplier.dataField('');
    this.dataRemotePlantInvoice = this.completerService.remote(
      this.billRegisterMasterService.resourceUrlPlantinvoices,
      'challanno',
      'challanno'
    );
    this.dataRemotePlantInvoice.dataField('');
  }

  ngOnInit(): void {
    this.registerChangeInOrderFilter();
    this.activatedRoute.data.subscribe(({ billRegisterMaster }) => {
      if (billRegisterMaster.id === undefined) {
        billRegisterMaster.billtype = 'CHA';
        billRegisterMaster.customersuppliertype = '2';
        billRegisterMaster.queryFlag = false;
        const billRegisterDetail = new BillRegisterDetails();
        billRegisterDetail.billtype = 'CHA';
        billRegisterDetail.status = 'N';
        billRegisterDetail.shipmentMode = 'SEA';
        this.billRegisterDetails.push(billRegisterDetail);
      } else {
        this.billRegisterDetails = billRegisterMaster.billRegisterDetailsBeans;
      }

      this.updateForm(billRegisterMaster);
      this.companyService.query().subscribe((companies: HttpResponse<ICompany[]>) => {
        this.companies = companies.body;
        if (billRegisterMaster.id === undefined) {
          this.editForm.controls['company'].setValue(this.companies[0].code);
          this.editForm.controls['businessunitcompanycode'].setValue(this.companies[0].code);
        }
        this.fetchDivision();
        this.fetchBusinessUnit();
      });
    });
  }

  fetchDivision(): void {
    if (this.editForm.controls['company'] && this.editForm.controls['company'].value) {
      this.divisionService.query(this.editForm.controls['company'].value).subscribe((divisions: HttpResponse<IDivision[]>) => {
        this.divisions = divisions.body;
        if (this.divisions && this.divisions.length > 0) {
          this.editForm.controls['division'].setValue(this.divisions[0].id.code);
        }
      });
    } else {
      this.divisions = [];
    }
  }

  fetchBusinessUnit(): void {
    if (this.editForm.controls['company'] && this.editForm.controls['company'].value) {
      this.finbusinessunitService
        .query(this.editForm.controls['company'].value)
        .subscribe((businessunits: HttpResponse<IFinbusinessunit[]>) => {
          this.finbusinessunits = businessunits.body;
        });
    } else {
      this.finbusinessunits = [];
    }
  }

  addDetails(): void {
    const billRegisterDetail = new BillRegisterDetails();
    billRegisterDetail.billtype = this.editForm.get('billtype').value;
    billRegisterDetail.status = 'N';
    billRegisterDetail.shipmentMode = 'SEA';
    this.billRegisterDetails.push(billRegisterDetail);
  }

  removeDetals(index, billRegisterDetail: IBillRegisterDetails): any {
    if (billRegisterDetail.id && billRegisterDetail.id !== undefined) {
      this.snotifyService.confirm('Are you sure to delete?', 'Confirm', {
        timeout: 25000,
        showProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        position: SnotifyPosition.centerTop,
        buttons: [
          { text: 'Yes', action: toast => this.delete(toast, billRegisterDetail, index), bold: false },
          { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
        ]
      });
    } else {
      this.billRegisterDetails.splice(index, 1);
    }
  }

  delete(toast, billRegisterDetail: IBillRegisterDetails, index?: number): void {
    this.billRegisterMasterService.deleteDetails(billRegisterDetail.id).subscribe(response => {
      this.snotifyService.remove(toast.id);
      this.billRegisterDetails.splice(index, 1);
      this.cdRef.detectChanges();
    });
  }

  updateForm(billRegisterMaster: IBillRegisterMaster) {
    this.editForm.patchValue({
      id: billRegisterMaster.id,
      company: billRegisterMaster.company,
      division: billRegisterMaster.division,
      businessunitcompanycode: billRegisterMaster.businessunitcompanycode,
      businessunitcode: billRegisterMaster.businessunitcode,
      billtype: billRegisterMaster.billtype,
      billnumber: billRegisterMaster.billnumber,
      billdate: billRegisterMaster.billdate,
      customersuppliertype: billRegisterMaster.customersuppliertype,
      customersuppliercode: billRegisterMaster.customersuppliercode,
      customersuppliername: billRegisterMaster.customersuppliername,
      remarks: billRegisterMaster.remarks,
      totalQuantity: billRegisterMaster.totalQuantity,
      totalValue: billRegisterMaster.totalValue,
      submitDate: billRegisterMaster.submitDate,
      receiveDate: billRegisterMaster.receiveDate,
      queryFlag: billRegisterMaster.queryFlag,
      queryRemarks: billRegisterMaster.queryRemarks,
      createdby: billRegisterMaster.createdby,
      createddate: billRegisterMaster.createddate != null ? billRegisterMaster.createddate.format(DATE_TIME_FORMAT) : null,
      updatedby: billRegisterMaster.updatedby,
      updateddate: billRegisterMaster.updateddate != null ? billRegisterMaster.updateddate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = false;
    const billRegisterMaster = this.createFromForm();
    if (billRegisterMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.billRegisterMasterService.update(billRegisterMaster.id, billRegisterMaster));
    } else {
      this.subscribeToSaveResponse(this.billRegisterMasterService.create(billRegisterMaster));
    }
  }

  changeValue(): void {
    let index = 0;
    let totalValue = 0;
    let totalQuantity = 0;
    this.billRegisterDetails.forEach(billRegisterDetail => {
      if (billRegisterDetail.grossvalue) {
        totalValue = Number(totalValue) + Number(billRegisterDetail.grossvalue);
        totalQuantity = Number(totalQuantity) + Number(billRegisterDetail.quantity);
      }
      ++index;
    });
    if (index === this.billRegisterDetails.length) {
      this.editForm.controls['totalValue'].setValue(Number(totalValue.toFixed(2)));
      this.editForm.controls['totalQuantity'].setValue(Number(totalQuantity.toFixed(2)));
    }
  }

  private createFromForm(): IBillRegisterMaster {
    return {
      ...new BillRegisterMaster(),
      id: this.editForm.get(['id']).value,
      company: this.editForm.get(['company'])!.value,
      division: this.editForm.get(['division'])!.value,
      businessunitcompanycode: this.editForm.get(['businessunitcompanycode'])!.value,
      businessunitcode: this.editForm.get(['businessunitcode'])!.value,
      billtype: this.editForm.get(['billtype']).value,
      billnumber: this.editForm.get(['billnumber']).value,
      billdate: this.editForm.get(['billdate']).value,
      customersuppliertype: this.editForm.get(['customersuppliertype']).value,
      customersuppliercode: this.editForm.get(['customersuppliercode']).value,
      customersuppliername: this.editForm.get(['customersuppliername']).value,
      remarks: this.editForm.get(['remarks']).value,
      totalQuantity: this.editForm.get(['totalQuantity']).value,
      totalValue: this.editForm.get(['totalValue']).value,
      submitDate: this.editForm.get(['submitDate']).value,
      receiveDate: this.editForm.get(['receiveDate']).value,
      queryFlag: this.editForm.get(['queryFlag']).value,
      queryRemarks: this.editForm.get(['queryRemarks']).value,
      createdby: this.editForm.get(['createdby']).value,
      createddate:
        this.editForm.get(['createddate']).value != null ? moment(this.editForm.get(['createddate']).value, DATE_TIME_FORMAT) : undefined,
      updatedby: this.editForm.get(['updatedby']).value,
      updateddate:
        this.editForm.get(['updateddate']).value != null ? moment(this.editForm.get(['updateddate']).value, DATE_TIME_FORMAT) : undefined,
      billRegisterDetailsBeans: this.billRegisterDetails
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillRegisterMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  onSupplierSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.editForm.controls['customersuppliertype'].setValue(selected.originalObject.customersuppliertype);
      this.editForm.controls['customersuppliercode'].setValue(selected.originalObject.customersuppliercode);
      this.editForm.controls['customersuppliername'].setValue(selected.originalObject.addressee);
    }
  }

  resetURL(type: number): void {
    this.dataRemoteSupplier = this.completerService.remote(
      this.billRegisterMasterService.resourceUrlSupplier + type + '/',
      'addressee',
      'addressee'
    );
    this.dataRemoteSupplier.dataField('');
  }

  searchInvoices(billRegisterDetail: IBillRegisterDetails) {
    this.currentBillRegisterDetail = billRegisterDetail;
    this.ngbModalRef = this.modalService.open(PlantInvoiceSelectionComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  onPlantInvoiceSelected(selected: CompleterItem, billRegisterDetail: IBillRegisterDetails): void {
    if (selected && !!this.remoteData && !billRegisterDetail.id) {
      billRegisterDetail.companycode = selected.originalObject.id.companycode;
      billRegisterDetail.divisioncode = selected.originalObject.id.divisioncode;
      billRegisterDetail.invoicetypecode = selected.originalObject.invoicetypecode;
      billRegisterDetail.invoicedate = moment(selected.originalObject.challandate, 'YYYY-MM-DD', true);
      const search = new MasterSearch();
      search.code = selected.originalObject.challanno;
      this.billRegisterMasterService.findByCode(search).subscribe(value => {
        billRegisterDetail.style = value.body.style;
        billRegisterDetail.customercode = value.body.customercode;
        billRegisterDetail.customername = value.body.customername;
        billRegisterDetail.quantity = value.body.quantity;
        this.calculateValue();

        const billRegisterDetailTemp = new BillRegisterDetails();
        billRegisterDetailTemp.billtype = this.editForm.get('billtype').value;
        billRegisterDetailTemp.status = 'N';
        billRegisterDetailTemp.shipmentMode = 'SEA';
        this.billRegisterDetails.push(billRegisterDetailTemp);
      });
    } else if (!billRegisterDetail.id) {
      this.editForm.controls['companycode'].setValue(undefined);
      this.editForm.controls['divisioncode'].setValue(undefined);
      this.editForm.controls['invoicetypecode'].setValue(undefined);
      this.editForm.controls['code'].setValue(undefined);
      this.editForm.controls['invoicedate'].setValue(undefined);
      this.editForm.controls['customercode'].setValue(undefined);
      this.editForm.controls['customername'].setValue(undefined);
    }
  }

  selectAllSubmitDate(): void {
    if (this.editForm.controls['submitDate'].value) {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.submitdate = this.editForm.controls['submitDate'].value;
      });
    } else {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.submitdate = undefined;
      });
    }
  }

  selectAllReceiveDate(): void {
    if (this.editForm.controls['receiveDate'].value) {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.receiveDate = this.editForm.controls['receiveDate'].value;
      });
    } else {
      this.billRegisterDetails.forEach(billRegisterDetail => {
        billRegisterDetail.receiveDate = undefined;
      });
    }
  }

  calculateValue(): void {
    if (this.editForm.controls['totalValue'].value) {
      let totalQuantity = 0;
      let index = 0;
      this.billRegisterDetails.forEach(billRegisterDetail => {
        if (billRegisterDetail.quantity) {
          totalQuantity = Number(totalQuantity) + Number(billRegisterDetail.quantity);
        }
        ++index;
      });
      if (index === this.billRegisterDetails.length) {
        this.editForm.controls['totalQuantity'].setValue(Number(totalQuantity.toFixed(2)));
        const rate = (Number(this.editForm.controls['totalValue'].value) / Number(totalQuantity)).toFixed(3);
        let totalIndex = 0;
        this.billRegisterDetails.forEach(billRegisterDetail => {
          if (billRegisterDetail.quantity) {
            billRegisterDetail.price = Number(rate);
            const value = Number((Number(billRegisterDetail.quantity) * Number(rate)).toFixed(3));
            billRegisterDetail.grossvalue = value;
          }
          ++totalIndex;
        });
        if (totalIndex === this.billRegisterDetails.length) {
          let totalValue = 0;
          let indexLevel1 = 0;
          let lastIndex = 0;
          this.billRegisterDetails.forEach(billRegisterDetail => {
            if (billRegisterDetail.grossvalue) {
              totalValue = Number(totalValue) + Number(billRegisterDetail.grossvalue);
              lastIndex = indexLevel1;
            }
            ++indexLevel1;
          });
          if (indexLevel1 === this.billRegisterDetails.length) {
            const adjustmentValue = Number(this.editForm.controls['totalValue'].value) - Number(totalValue);
            this.billRegisterDetails[lastIndex].grossvalue = Number(
              (Number(this.billRegisterDetails[lastIndex].grossvalue) + adjustmentValue).toFixed(3)
            );
          }
        }
      }
    }
  }

  registerChangeInOrderFilter(): void {
    this.eventSubscriber = this.eventManager.subscribe('billRegisterPlantinvoiceFilter', data => {
      const plantinvoice: IPlantinvoice = data.content;
      this.currentBillRegisterDetail.companycode = plantinvoice.id.companycode;
      this.currentBillRegisterDetail.divisioncode = plantinvoice.id.divisioncode;
      this.currentBillRegisterDetail.invoicetypecode = plantinvoice.invoicetypecode;
      this.currentBillRegisterDetail.invoicedate = moment(plantinvoice.challandate, 'YYYY-MM-DD', true);
      this.currentBillRegisterDetail.code = plantinvoice.challanno;
      const search = new MasterSearch();
      search.code = plantinvoice.challanno;
      this.billRegisterMasterService.findByCode(search).subscribe(value => {
        this.currentBillRegisterDetail.style = value.body.style;
        this.currentBillRegisterDetail.customercode = value.body.customercode;
        this.currentBillRegisterDetail.customername = value.body.customername;
        this.currentBillRegisterDetail.quantity = value.body.quantity;
        this.calculateValue();

        const billRegisterDetailTemp = new BillRegisterDetails();
        billRegisterDetailTemp.billtype = this.editForm.get('billtype').value;
        billRegisterDetailTemp.status = 'N';
        billRegisterDetailTemp.shipmentMode = 'SEA';
        this.billRegisterDetails.push(billRegisterDetailTemp);
      });
    });
  }

  detailsChange(): void {
    this.billRegisterDetails.forEach(billRegisterDetail => {
      billRegisterDetail.billtype = this.editForm.get('billtype').value;
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IVendorBranchDetails, VendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';
import { VendorBranchDetailsService } from './vendor-branch-details.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ICountry } from 'app/shared/model/country.model';
import { IState } from 'app/shared/model/state.model';
import { CountryService } from 'app/vendorportal/country';
import { StateService } from 'app/vendorportal/state';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';
import { VendTypeMasterService } from 'app/vendorportal/vend-type-master';
import { VendSubTypeMasterService } from 'app/vendorportal/vend-sub-type-master';
import { JhiEventManager } from 'ng-jhipster';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-vendor-branch-details-update',
  templateUrl: './vendor-branch-details-update.component.html'
})
export class VendorBranchDetailsUpdateComponent implements OnInit {
  vendorBranchDetails: IVendorBranchDetails;
  vendorBranchDetailsCompare: IVendorBranchDetails;
  approvalStatus: string;
  approved: boolean;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  countries: ICountry[];
  states: IState[];
  selectedCountry: ICountry;
  selectedState: IState;
  selectedVendorTypeMaster?: IVendTypeMaster;
  vendTypeMasters: IVendTypeMaster[];
  vendSubTypeMasters: IVendSubTypeMaster[];

  constructor(
    protected vendorBranchDetailsService: VendorBranchDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected activeModal: NgbActiveModal,
    protected countryService: CountryService,
    protected stateService: StateService,
    protected vendTypeMasterService: VendTypeMasterService,
    protected vendSubTypeMasterService: VendSubTypeMasterService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {
    this.vendorBranchDetails = new VendorBranchDetails();
  }

  ngOnInit() {
    this.isSaving = false;

    if (this.vendorBranchDetails && this.vendorBranchDetails.id) {
      this.vendorBranchDetailsService.transaction(this.vendorBranchDetails.id).subscribe(vendorBranchDetails => {
        this.vendorBranchDetailsCompare = vendorBranchDetails.body;
        this.getStates();
      });
    }
    this.countryService.query().subscribe(countries => {
      this.countries = countries.body;
      this.getStates();
    });

    /* if (this.vendorBranchDetails && this.vendorBranchDetails.id) {
      this.vendorBranchDetailsService.transaction(this.vendorBranchDetails.id).subscribe(vendorBranchDetails => {
        this.vendorBranchDetailsCompare = vendorBranchDetails.body;
        if (this.vendorBranchDetailsCompare.gstNumber != null) {
          this.vendorBranchDetailsCompare.gstNumber1 = this.vendorBranchDetailsCompare.gstNumber.split(' ')[0];
          this.vendorBranchDetailsCompare.gstNumber2 = this.vendorBranchDetailsCompare.gstNumber.split(' ')[1];
          this.vendorBranchDetailsCompare.gstNumber3 = this.vendorBranchDetailsCompare.gstNumber.split(' ')[2];
          this.vendorBranchDetailsCompare.gstNumber4 = this.vendorBranchDetailsCompare.gstNumber.split(' ')[3];
          this.vendorBranchDetailsCompare.gstNumber5 = this.vendorBranchDetailsCompare.gstNumber.split(' ')[4];
        }
        this.getStates();
        this.getVendSubType();
      });
    }
    this.countryService.query().subscribe(countries => {
      this.countries = countries.body;
      if (this.vendorBranchDetails && this.vendorBranchDetails.id) {
        this.getStates();
        this.getVendSubType();
        if (this.vendorBranchDetails.gstNumber != null) {
          this.vendorBranchDetails.gstNumber1 = this.vendorBranchDetails.gstNumber.split(' ')[0];
          this.vendorBranchDetails.gstNumber2 = this.vendorBranchDetails.gstNumber.split(' ')[1];
          this.vendorBranchDetails.gstNumber3 = this.vendorBranchDetails.gstNumber.split(' ')[2];
          this.vendorBranchDetails.gstNumber4 = this.vendorBranchDetails.gstNumber.split(' ')[3];
          this.vendorBranchDetails.gstNumber5 = this.vendorBranchDetails.gstNumber.split(' ')[4];
        }
      }
    });*/
  }

  getStates() {
    if (this.vendorBranchDetails.countryId) {
      this.stateService.findByCountry(this.vendorBranchDetails.countryId).subscribe(states => {
        this.states = states.body;
        this.countries.forEach(country => {
          if (Number(this.vendorBranchDetails.countryId) === Number(country.id)) {
            this.selectedCountry = country;
            if (this.selectedCountry && this.selectedCountry.countryCode === 'IN') {
              this.vendorBranchDetails.gstNumber4 = 'Z';
              this.vendTypeMasterService.query().subscribe((res: HttpResponse<IVendTypeMaster[]>) => {
                this.vendTypeMasters = res.body;
                this.getVendSubType();
              });
            } else {
              this.vendTypeMasters = [];
            }
          }
        });
      });
    } else {
      this.states = [];
      this.selectedCountry = undefined;
    }
  }

  getVendSubType() {
    if (this.vendorBranchDetails.vendTypeMasterId) {
      if (this.vendTypeMasters) {
        this.vendTypeMasters.forEach(vendTypeMaster => {
          if (Number(vendTypeMaster.id) === Number(this.vendorBranchDetailsCompare.vendTypeMasterId)) {
            this.selectedVendorTypeMaster = vendTypeMaster;
          }
        });
      }
      this.vendSubTypeMasterService
        .findByVendType(this.vendorBranchDetails.vendTypeMasterId)
        .subscribe((res: HttpResponse<IVendSubTypeMaster[]>) => {
          this.vendSubTypeMasters = res.body;
        });
    }
  }

  selectStates() {
    if (this.selectedCountry && this.selectedCountry.countryCode === 'IN') {
      if (this.vendorBranchDetails.stateId) {
        this.states.forEach(state => {
          if (Number(this.vendorBranchDetails.stateId) === Number(state.id)) {
            this.selectedState = state;
            this.vendorBranchDetails.gstNumber1 = this.selectedState.gstStateCode;
          }
        });
      }
    } else {
      this.selectedState = undefined;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (
      this.vendorBranchDetails.gstNumber1 !== undefined &&
      this.vendorBranchDetails.gstNumber2 !== undefined &&
      this.vendorBranchDetails.gstNumber3 !== undefined &&
      this.vendorBranchDetails.gstNumber4 !== undefined &&
      this.vendorBranchDetails.gstNumber5 !== undefined
    ) {
      this.vendorBranchDetails.gstNumber =
        this.vendorBranchDetails.gstNumber1 +
        ' ' +
        this.vendorBranchDetails.gstNumber2 +
        ' ' +
        this.vendorBranchDetails.gstNumber3 +
        ' ' +
        this.vendorBranchDetails.gstNumber4 +
        ' ' +
        this.vendorBranchDetails.gstNumber5;
    }
    this.vendorBranchDetails.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.vendorBranchDetails.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.vendorBranchDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.vendorBranchDetailsService.update(this.vendorBranchDetails));
    } else {
      this.subscribeToSaveResponse(this.vendorBranchDetailsService.create(this.vendorBranchDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorBranchDetails>>) {
    result.subscribe((res: HttpResponse<IVendorBranchDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.eventManager.broadcast({
      name: 'vendorBranchDetailsListModification',
      content: ''
    });
    this.eventManager.broadcast({
      name: 'tabModification',
      content: ''
    });

    this.activeModal.dismiss('close');
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  close() {
    this.activeModal.dismiss('close');
  }
}

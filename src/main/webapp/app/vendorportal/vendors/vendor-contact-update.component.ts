import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/vendorportal/country';
import { IState } from 'app/shared/model/state.model';
import { StateService } from 'app/vendorportal/state';
import { IOrganizationType } from 'app/shared/model/organization-type.model';
import { OrganizationTypeService } from 'app/vendorportal/organization-type';
import { ITransactionNature } from 'app/shared/model/transaction-nature.model';
import { TransactionNatureService } from 'app/vendorportal/transaction-nature';
import { IVendTypeMaster } from 'app/shared/model/vend-type-master.model';
import { VendTypeMasterService } from 'app/vendorportal/vend-type-master';
import { IVendSubTypeMaster } from 'app/shared/model/vend-sub-type-master.model';
import { VendSubTypeMasterService } from 'app/vendorportal/vend-sub-type-master';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';
import { IVendorContact, VendorContact } from 'app/shared/model/vendor-contact.model';
@Component({
  selector: 'jhi-vendor-contact-update',
  templateUrl: './vendor-contact-update.component.html'
})
export class VendorContactUpdateComponent implements OnInit {
  @Input() vendorContact: VendorContact;
  @Input() vendorContactCompare: VendorContact;
  @Input() branchPresent: string;
  @Input() approvalStatus: string;
  @Input() approved: boolean;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  countries: ICountry[];
  states: IState[];
  selectedCountry: ICountry;
  selectedState: IState;
  selectedVendorTypeMaster?: IVendTypeMaster;

  // Masters
  organizationTypes: IOrganizationType[];
  transactionNatures: ITransactionNature[];
  vendTypeMasters: IVendTypeMaster[];
  vendSubTypeMasters: IVendSubTypeMaster[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected countryService: CountryService,
    protected stateService: StateService,
    protected organizationTypeService: OrganizationTypeService,
    protected transactionNatureService: TransactionNatureService,
    protected vendTypeMasterService: VendTypeMasterService,
    protected vendSubTypeMasterService: VendSubTypeMasterService,
    private snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.organizationTypeService.query().subscribe((res: HttpResponse<IOrganizationType[]>) => {
      this.organizationTypes = res.body;
    });
    this.transactionNatureService.query().subscribe((res: HttpResponse<ITransactionNature[]>) => {
      this.transactionNatures = res.body;
    });
    this.countryService.query().subscribe(countries => {
      this.countries = countries.body;
      if (this.vendorContact && this.vendorContact.countryId) {
        this.getStates();
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save() {}

  getStates() {
    if (this.vendorContact.countryId) {
      this.stateService.findByCountry(this.vendorContact.countryId).subscribe(states => {
        this.states = states.body;
        this.countries.forEach(country => {
          if (Number(this.vendorContact.countryId) === Number(country.id)) {
            this.selectedCountry = country;
            if (this.selectedCountry && this.selectedCountry.countryCode === 'IN') {
              this.vendTypeMasterService.query().subscribe((res: HttpResponse<IVendTypeMaster[]>) => {
                this.vendTypeMasters = res.body;
                if (this.vendorContact && this.vendorContact.vendTypeMasterId) {
                  this.getVendSubType();
                }
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
    if (this.vendorContact.vendTypeMasterId) {
      if (this.vendTypeMasters) {
        this.vendTypeMasters.forEach(vendTypeMaster => {
          if (Number(vendTypeMaster.id) === Number(this.vendorContactCompare.vendTypeMasterId)) {
            this.selectedVendorTypeMaster = vendTypeMaster;
          }
        });
      }
      this.vendSubTypeMasterService
        .findByVendType(this.vendorContact.vendTypeMasterId)
        .subscribe((res: HttpResponse<IVendSubTypeMaster[]>) => {
          this.vendSubTypeMasters = res.body;
        });
    }
  }

  selectStates() {
    if (this.selectedCountry && this.selectedCountry.countryCode === 'IN') {
      if (this.vendorContact.stateId) {
        this.states.forEach(state => {
          if (Number(this.vendorContact.stateId) === Number(state.id)) {
            this.selectedState = state;
          }
        });
      }
    } else {
      this.selectedState = undefined;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorContact>>) {
    result.subscribe((res: HttpResponse<IVendorContact>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Contact Details save successfully', '', toastConfig);
    this.eventManager.broadcast({ name: 'tabModification', content: '' });
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}

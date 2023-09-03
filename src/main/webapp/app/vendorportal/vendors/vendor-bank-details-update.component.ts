import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICountry } from 'app/shared/model/country.model';
import { IState } from 'app/shared/model/state.model';
import { CountryService } from 'app/vendorportal/country';
import { StateService } from 'app/vendorportal/state';
import { SnotifyService } from 'ng-snotify';
import { JhiEventManager } from 'ng-jhipster';
import { VendorBankDetails } from 'app/shared/model/vendor-bank-details.model';
@Component({
  selector: 'jhi-vendor-bank-details-update',
  templateUrl: './vendor-bank-details-update.component.html'
})
export class VendorBankDetailsUpdateComponent implements OnInit {
  @Input() vendorBankDetails: VendorBankDetails;
  @Input() vendorBankDetailsCompare: VendorBankDetails;
  @Input() approvalStatus: string;
  @Input() approved: boolean;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  countries: ICountry[];
  states: IState[];
  selectedCountry: ICountry;
  selectedState: IState;

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected countryService: CountryService,
    protected stateService: StateService,
    private snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.countryService.query().subscribe(countries => {
      this.countries = countries.body;
      if (this.vendorBankDetailsCompare != null && this.vendorBankDetailsCompare.countryId) {
        this.getStates();
      }
    });
  }

  getStates() {
    if (this.vendorBankDetailsCompare.countryId) {
      this.stateService.findByCountry(this.vendorBankDetailsCompare.countryId).subscribe(states => {
        this.states = states.body;
        this.countries.forEach(country => {
          if (Number(this.vendorBankDetailsCompare.countryId) === Number(country.id)) {
            this.selectedCountry = country;
          }
        });
      });
    } else {
      this.states = [];
      this.selectedCountry = undefined;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {}
}

import { Moment } from 'moment';

export interface IVendorTaxRegistration {
  id?: number;
  vendorId?: number;
  addressLine1?: string;
  addressLine2?: string;
  addressLine3?: string;
  addressLine4?: string;
  countryId?: number;
  stateId?: number;
  pincode?: number;
  panTaxNumber?: string;
  registeredForSaleTax?: boolean;
  salesTaxVatTinNumber?: string;
  registeredServiceTax?: boolean;
  serviceTaxNumber?: string;
  exciseApplicable?: boolean;
  exciseRegistrationNumber?: string;
  exciseRange?: string;
  exciseDivision?: string;
  exciseAddressLine1?: string;
  exciseAddressLine2?: string;
  divisionAddressLine1?: string;
  divisionAddressLine2?: string;
  addressForTax?: boolean;
  sisterConcernNo?: boolean;
  vendorCode?: string;
  gstNumber?: string;
  gstApplicable?: boolean;
  vendTypeMasterId?: number;
  vendSubTypeMasterId?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VendorTaxRegistration implements IVendorTaxRegistration {
  constructor(
    public id?: number,
    public vendorId?: number,
    public addressLine1?: string,
    public addressLine2?: string,
    public addressLine3?: string,
    public addressLine4?: string,
    public countryId?: number,
    public stateId?: number,
    public pincode?: number,
    public panTaxNumber?: string,
    public registeredForSaleTax?: boolean,
    public salesTaxVatTinNumber?: string,
    public registeredServiceTax?: boolean,
    public serviceTaxNumber?: string,
    public exciseApplicable?: boolean,
    public exciseRegistrationNumber?: string,
    public exciseRange?: string,
    public exciseDivision?: string,
    public exciseAddressLine1?: string,
    public exciseAddressLine2?: string,
    public divisionAddressLine1?: string,
    public divisionAddressLine2?: string,
    public addressForTax?: boolean,
    public sisterConcernNo?: boolean,
    public vendorCode?: string,
    public gstNumber?: string,
    public gstApplicable?: boolean,
    public vendTypeMasterId?: number,
    public vendSubTypeMasterId?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {
    this.registeredForSaleTax = this.registeredForSaleTax || false;
    this.registeredServiceTax = this.registeredServiceTax || false;
    this.exciseApplicable = this.exciseApplicable || false;
    this.addressForTax = this.addressForTax || false;
    this.sisterConcernNo = this.sisterConcernNo || false;
    this.gstApplicable = this.gstApplicable || false;
  }
}

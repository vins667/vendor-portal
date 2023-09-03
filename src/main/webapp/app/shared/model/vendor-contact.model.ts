import { Moment } from 'moment';

export interface IVendorContact {
  id?: number;
  vendorId?: number;
  vendorName?: string;
  organizationTypeId?: number;
  transactionNature?: string;
  otherTransactionNature?: string;
  contactPerson?: string;
  contactDesignation?: string;
  mobileNumber?: string;
  telephoneNumber?: string;
  faxNumber?: string;
  email?: string;
  website?: string;
  branchesPresent?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  cinno?: string;
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
  gstNumber1?: string;
  gstNumber2?: string;
  gstNumber3?: string;
  gstNumber4?: string;
  gstNumber5?: string;
  gstApplicable?: boolean;
  vendTypeMasterId?: number;
  vendSubTypeMasterId?: number;
}

export class VendorContact implements IVendorContact {
  constructor(
    public id?: number,
    public vendorId?: number,
    public vendorName?: string,
    public organizationTypeId?: number,
    public transactionNature?: string,
    public otherTransactionNature?: string,
    public contactPerson?: string,
    public contactDesignation?: string,
    public mobileNumber?: string,
    public telephoneNumber?: string,
    public faxNumber?: string,
    public email?: string,
    public website?: string,
    public branchesPresent?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public cinno?: string,
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
    public gstNumber1?: string,
    public gstNumber2?: string,
    public gstNumber3?: string,
    public gstNumber4?: string,
    public gstNumber5?: string,
    public gstApplicable?: boolean,
    public vendTypeMasterId?: number,
    public vendSubTypeMasterId?: number
  ) {
    this.branchesPresent = this.branchesPresent || false;
    this.registeredForSaleTax = this.registeredForSaleTax || false;
    this.registeredServiceTax = this.registeredServiceTax || false;
    this.exciseApplicable = this.exciseApplicable || false;
    this.addressForTax = this.addressForTax || false;
    this.sisterConcernNo = this.sisterConcernNo || false;
    this.gstApplicable = this.gstApplicable || false;
  }
}

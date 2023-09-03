import { Moment } from 'moment';

export interface IVendorBranchDetails {
  id?: number;
  vendorId?: number;
  addressLine1?: string;
  addressLine2?: string;
  addressLine3?: string;
  addressLine4?: string;
  countryId?: number;
  stateId?: number;
  pincode?: number;
  addressForTax?: boolean;
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
  gstNumber?: string;
  gstNumber1?: string;
  gstNumber2?: string;
  gstNumber3?: string;
  gstNumber4?: string;
  gstNumber5?: string;
  gstApplicable?: boolean;
  vendTypeMasterId?: number;
  vendTypeMasterDesc?: string;
  vendSubTypeMasterId?: number;
  vendSubTypeMasterDesc?: string;
  addressId?: string;
  branchContactNo?: string;
  branchFaxNo?: string;
  branchWebsite?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  dataFlag?: string;
}

export class VendorBranchDetails implements IVendorBranchDetails {
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
    public addressForTax?: boolean,
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
    public gstNumber?: string,
    public gstNumber1?: string,
    public gstNumber2?: string,
    public gstNumber3?: string,
    public gstNumber4?: string,
    public gstNumber5?: string,
    public gstApplicable?: boolean,
    public vendTypeMasterId?: number,
    public vendSubTypeMasterId?: number,
    public addressId?: string,
    public branchContactNo?: string,
    public branchFaxNo?: string,
    public branchWebsite?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public dataFlag?: string
  ) {
    this.addressForTax = this.addressForTax || false;
    this.registeredForSaleTax = this.registeredForSaleTax || false;
    this.registeredServiceTax = this.registeredServiceTax || false;
    this.exciseApplicable = this.exciseApplicable || false;
    this.gstApplicable = this.gstApplicable || false;
  }
}

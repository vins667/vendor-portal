import { IVendorContact } from './vendor-contact.model';
import { IVendorBankDetails } from './vendor-bank-details.model';
import { IVendorNomination } from './vendor-nomination.model';
import { IVendorAdditionalInfo } from './vendor-additional-info.model';
import { IVendorUsers } from './vendor-users.model';
import { IVendorDocument } from './vendor-document.model';
import { IVendorTerms } from './vendor-terms.model';
import { Moment } from 'moment';
import { IProfileWorkFlow } from './profile-work-flow.model';

export interface IVendorsBean {
  id?: number;
  vendorCode?: string;
  vendorName?: string;
  vendorShortName?: string;
  approvalStatus?: string;
  requestedBy?: string;
  requestedDate?: Moment;
  approvedBy?: string;
  approvedDate?: Moment;
  approved?: boolean;
  deliveryTermMasterId?: number;
  payTermMasterId?: number;
  shipmentTermMasterId?: number;
  currencyMasterId?: number;
  orderAllowed?: boolean;
  vendorContactStatus?: string;
  bankStatus?: string;
  branchStatus?: string;
  nominationStatus?: string;
  addInfoStatus?: string;
  peopleStatus?: string;
  documentStatus?: string;
  termsStatus?: string;
  vendorContactTaxRegistration?: IVendorContact;
  vendorContactTaxRegistrationCompare?: IVendorContact;
  vendorBankDetails?: IVendorBankDetails;
  vendorBankDetailsCompare?: IVendorBankDetails;
  vendorNomination?: IVendorNomination;
  vendorNominationTransaction?: IVendorNomination;
  vendorAdditionalInfo?: IVendorAdditionalInfo;
  vendorAdditionalInfoCompare?: IVendorAdditionalInfo;
  vendorUsers?: IVendorUsers[];
  vendorDocuments?: IVendorDocument[];
  vendorTerms?: IVendorTerms;
  profileWorkFlows?: IProfileWorkFlow[];
}

export class VendorsBean implements IVendorsBean {
  constructor(
    public id?: number,
    public vendorCode?: string,
    public vendorName?: string,
    public vendorShortName?: string,
    public approvalStatus?: string,
    public requestedBy?: string,
    public requestedDate?: Moment,
    public approvedBy?: string,
    public approvedDate?: Moment,
    public approved?: boolean,
    public deliveryTermMasterId?: number,
    public payTermMasterId?: number,
    public shipmentTermMasterId?: number,
    public currencyMasterId?: number,
    public orderAllowed?: boolean,
    public vendorContactStatus?: string,
    public bankStatus?: string,
    public branchStatus?: string,
    public nominationStatus?: string,
    public addInfoStatus?: string,
    public peopleStatus?: string,
    public documentStatus?: string,
    public termsStatus?: string,
    public vendorContactTaxRegistration?: IVendorContact,
    public vendorContactTaxRegistrationCompare?: IVendorContact,
    public vendorBankDetails?: IVendorBankDetails,
    public vendorNomination?: IVendorNomination,
    public vendorNominationTransaction?: IVendorNomination,
    public vendorAdditionalInfo?: IVendorAdditionalInfo,
    public vendorAdditionalInfoCompare?: IVendorAdditionalInfo,
    public vendorUsers?: IVendorUsers[],
    public vendorDocuments?: IVendorDocument[],
    public vendorTerms?: IVendorTerms,
    public profileWorkFlows?: IProfileWorkFlow[]
  ) {}
}

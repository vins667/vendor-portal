import { IVendorBranchDetails } from './vendor-branch-details.model';

export interface IVendorBranchBean {
  vendorBranchDetails?: IVendorBranchDetails;
  vendorBranchDetailsCompare?: IVendorBranchDetails;
}

export class VendorBranchBean implements IVendorBranchBean {
  constructor(public vendorBranchDetails?: IVendorBranchDetails, public vendorBranchDetailsCompare?: IVendorBranchDetails) {}
}

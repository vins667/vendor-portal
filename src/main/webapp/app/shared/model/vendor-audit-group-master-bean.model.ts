import {IVendorAuditQuesDetails} from "app/shared/model/vendor-audit-ques-details.model";
import {Master} from "app/shared/model/master.modal";

export interface IVendorAuditGroupMasterBean {
  id?: number;
  groupName?: string;
  initColumns?: Master[];
  displayedColumns?: any[];
  vendorAuditQuesDetails?: IVendorAuditQuesDetails[];
}

export class VendorAuditGroupMasterBean implements IVendorAuditQuesDetails {
  constructor(
    public id?: number,
    public groupName?: string,
    public initColumns?: Master[],
    public displayedColumns?: any[],
    public vendorAuditQuesDetails?: IVendorAuditQuesDetails[]
  ) {}
}

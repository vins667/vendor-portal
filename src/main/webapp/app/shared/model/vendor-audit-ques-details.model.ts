import { Moment } from 'moment';
import { IVendorAuditQuesMaster } from './vendor-audit-ques-master.model';
import { IAuditGroupMaster } from './audit-group-master.model';
import {IBuyerMap} from './buyer-map.model';

export interface IVendorAuditQuesDetails {
  id?: number;
  auditQuestion?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  buyerMastersMap?: Map<string, boolean>;
  buyerMasters?: IBuyerMap[];
  vendorAuditQuesMaster?: IVendorAuditQuesMaster;
  auditGroupMaster?: IAuditGroupMaster;
}

export class VendorAuditQuesDetails implements IVendorAuditQuesDetails {
  constructor(
    public id?: number,
    public auditQuestion?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public buyerMastersMap?: Map<string, boolean>,
    public vendorAuditQuesMaster?: IVendorAuditQuesMaster,
    public auditGroupMaster?: IAuditGroupMaster
  ) {}
}

import { IVendorAuditQuesMaster } from 'app/shared/model//vendor-audit-ques-master.model';
import { IBuyerMaster } from 'app/shared/model/buyer-master.model';
import { IBuyerMap } from 'app/shared/model/buyer-map.model';
import { IVendorAuditGroupMasterBean } from 'app/shared/model/vendor-audit-group-master-bean.model';

export interface IAuditQuesBuyerMapping {
  id?: number;
  vendorAuditQuesMaster?: IVendorAuditQuesMaster;
  buyerMasters?: IBuyerMaster[];
  buyerMaps?: IBuyerMap[];
  vendorAuditGroupMasterBeans?: IVendorAuditGroupMasterBean[];
}

export class AuditQuesBuyerMapping implements IAuditQuesBuyerMapping {
  constructor(
    public id?: number,
    public vendorAuditQuesMaster?: IVendorAuditQuesMaster,
    public buyerMasters?: IBuyerMaster[],
    public buyerMaps?: IBuyerMap[],
    public vendorAuditGroupMasterBeans?: IVendorAuditGroupMasterBean[]
  ) {}
}

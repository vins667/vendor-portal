import {IVendorAuditQuesMaster} from 'app/shared/model/vendor-audit-ques-master.model';
import {IVendorAuditGroupMasterBean} from 'app/shared/model/vendor-audit-group-master-bean.model';

export interface IAuctionQuestionBuyerMappingBean {
  vendorAuditQuesMaster?: IVendorAuditQuesMaster;
  vendorAuditGroupMasterBean?: IVendorAuditGroupMasterBean[];
}

export class AuctionQuestionBuyerMappingBean implements IAuctionQuestionBuyerMappingBean {
  constructor(public vendorAuditQuesMaster?: IVendorAuditQuesMaster, public vendorAuditGroupMasterBean?: IVendorAuditGroupMasterBean[]) {
  }
}

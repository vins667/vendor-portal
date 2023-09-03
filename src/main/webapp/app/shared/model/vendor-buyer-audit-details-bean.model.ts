import { IVendorMaster } from './vendor-master.model';
import { IBuyerMaster } from './buyer-master.model';
import { IMaster } from './master.modal';

export interface IVendorBuyerAuditDetailsBean {
  id?: number;
  vendorMaster?: IVendorMaster;
  buyerMaster?: IBuyerMaster;
  masters?: IMaster[];
}

export class VendorBuyerAuditDetailsBean implements IVendorBuyerAuditDetailsBean {
  constructor(public id?: number, public vendorMaster?: IVendorMaster, public buyerMaster?: IBuyerMaster, public masters?: IMaster[]) {}
}

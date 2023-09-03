export interface IVendorBuyerAudit {
  id?: number;
}

export class VendorBuyerAudit implements IVendorBuyerAudit {
  constructor(public id?: number) {}
}

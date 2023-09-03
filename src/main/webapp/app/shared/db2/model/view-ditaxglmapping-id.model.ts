export interface IViewDitaxglmappingId {
  companycode?: string;
  code?: string;
  taxcode?: string;
}

export class ViewDitaxglmappingId implements IViewDitaxglmappingId {
  constructor(public companycode?: string, public code?: string, public taxcode?: string) {}
}

export interface IOrderpartnerbankId {
  ordprncsmsuppliercompanycode?: string;
  ordprncustomersuppliertype?: string;
  ordprncustomersuppliercode?: string;
  identifier?: number;
}

export class OrderpartnerbankId implements IOrderpartnerbankId {
  constructor(
    public ordprncsmsuppliercompanycode?: string,
    public ordprncustomersuppliertype?: string,
    public ordprncustomersuppliercode?: string,
    public identifier?: number
  ) {}
}

export interface IBuyerRegisterPurchaseLineBeanModel {
  companycode?: string;
  countercode?: string;
  code?: string;
  orderdate?: any;
  projectcode?: string;
  itemtypeaficode?: string;
  subcode01?: string;
  subcode02?: string;
  subcode03?: string;
  subcode04?: string;
  subcode05?: string;
  subcode06?: string;
  subcode07?: string;
  subcode08?: string;
  subcode09?: string;
  subcode10?: string;
  summarizeddescription?: string;
  userprimaryuomcode?: string;
  userprimaryquantity?: number;
  price?: number;
  value?: number;
}

export class BuyerRegisterPurchaseLineBeanModel implements IBuyerRegisterPurchaseLineBeanModel {
  constructor(
    public companycode?: string,
    public countercode?: string,
    public code?: string,
    public orderdate?: any,
    public projectcode?: string,
    public itemtypeaficode?: string,
    public subcode01?: string,
    public subcode02?: string,
    public subcode03?: string,
    public subcode04?: string,
    public subcode05?: string,
    public subcode06?: string,
    public subcode07?: string,
    public subcode08?: string,
    public subcode09?: string,
    public subcode10?: string,
    public summarizeddescription?: string,
    public userprimaryuomcode?: string,
    public userprimaryquantity?: number,
    public price?: number,
    public value?: number
  ) {}
}

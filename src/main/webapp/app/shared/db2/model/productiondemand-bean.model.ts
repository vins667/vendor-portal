export interface IProductiondemandBean {
  companycode?: string;
  countercode?: string;
  code?: string;
  stepnumber?: string;
  groupstepnumber?: string;
  previousoperation?: string;
  previousoperationdesc?: string;
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
  product?: string;
  productdescription?: string;
  baseprimaryuomcode?: string;
  previousprogressqty?: number;
  progressqty?: number;
  balanceqty?: number;
  selectedquantity?: number;
}

export class ProductiondemandBean implements IProductiondemandBean {
  constructor(
    public companycode?: string,
    public countercode?: string,
    public code?: string,
    public stepnumber?: string,
    public groupstepnumber?: string,
    public previousoperation?: string,
    public previousoperationdesc?: string,
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
    public product?: string,
    public productdescription?: string,
    public baseprimaryuomcode?: string,
    public previousprogressqty?: number,
    public progressqty?: number,
    public balanceqty?: number,
    public selectedquantity?: number
  ) {}
}

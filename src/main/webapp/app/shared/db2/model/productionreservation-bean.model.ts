import { IBalance } from 'app/shared/db2/model/balance.model';

export interface IProductionreservationBean {
  id?: number;
  itemtypeaficompanycode?: string;
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
  groupline?: string;
  product?: string;
  userprimaryuomcode?: string;
  qualitycode?: string;
  warehousecode?: string;
  summarizeddescription?: string;
  longdescription?: string;
  demandnumber?: string;
  reservationquantity?: number;
  stockquantity?: number;
  issuedquantity?: number;
  balancequantity?: number;
  selectedquantity?: number;
  selectedBalances?: IBalance[];
}

export class ProductionreservationBean implements IProductionreservationBean {
  constructor(
    public id?: number,
    public itemtypeaficompanycode?: string,
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
    public groupline?: string,
    public product?: string,
    public userprimaryuomcode?: string,
    public qualitycode?: string,
    public warehousecode?: string,
    public summarizeddescription?: string,
    public longdescription?: string,
    public demandnumber?: string,
    public reservationquantity?: number,
    public stockquantity?: number,
    public issuedquantity?: number,
    public balancequantity?: number,
    public selectedquantity?: number,
    public selectedBalances?: IBalance[]
  ) {}
}

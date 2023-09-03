export interface IProductionOrderBreakup {
  id?: number;
  code?: string;
  codetemp?: string;
  color?: string;
  destination?: string;
  qty?: number;
  balanceqty?: number;
  markerratio?: number;
  markerqty?: number;
  passedquantity?: number;
  rejectedquantity?: number;
}

export class ProductionOrderBreakup implements IProductionOrderBreakup {
  constructor(
    public id?: number,
    public code?: string,
    public codetemp?: string,
    public color?: string,
    public destination?: string,
    public qty?: number,
    public balanceqty?: number,
    public markerratio?: number,
    public markerqty?: number,
    public passedquantity?: number,
    public rejectedquantity?: number
  ) {}
}

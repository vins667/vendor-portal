export interface IBuyerCosting {
  id?: number;
  orderQty?: number;
  smv?: number;
  subTotal?: number;
  margin?: number;
  sellingPrice1?: number;
  sellingPrice2?: number;
  wastage?: number;
  currency?: string;
  convRate?: number;
}

export class BuyerCosting implements IBuyerCosting {
  constructor(
    public id?: number,
    public orderQty?: number,
    public smv?: number,
    public subTotal?: number,
    public margin?: number,
    public sellingPrice1?: number,
    public sellingPrice2?: number,
    public wastage?: number,
    public currency?: string,
    public convRate?: number
  ) {}
}

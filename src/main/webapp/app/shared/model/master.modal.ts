export interface IMaster {
  id?: string;
  desc?: string;
  name?: string;
  code?: string;
  btnType?: string;
  plantCode?: string;
  destination?: string;
  destinationDesc?: string;
  buyer?: string;
  buyerName?: string;
  itemType?: string;
  itemName?: string;
  key?: number;
  extract?: boolean;
  size?: number;
  pageNo?: number;
}

export class Master implements IMaster {
  constructor(
    public id?: string,
    public desc?: string,
    public name?: string,
    public code?: string,
    public btnType?: string,
    public plantCode?: string,
    public destination?: string,
    public destinationDesc?: string,
    public buyer?: string,
    public buyerName?: string,
    public itemType?: string,
    public itemName?: string,
    public key?: number,
    public extract?: boolean,
    public size?: number,
    public pageNo?: number
  ) {}
}

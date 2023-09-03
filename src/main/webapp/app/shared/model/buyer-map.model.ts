export interface IBuyerMap {
  key?: string;
  value?: boolean;
}

export class BuyerMap implements IBuyerMap {
  constructor(
    public key?: string,
    public value?: boolean) {
  }
}

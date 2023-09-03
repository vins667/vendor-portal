import { IBalance } from './balance.model';

export interface ILotBean {
  lotcode?: string;
  lotquantity?: number;
  selecedquantity?: number;
  noPlies?: number;
  endBits?: number;
  collapse?: boolean;
  balances?: IBalance[];
}

export class LotBean implements ILotBean {
  constructor(
    public lotcode?: string,
    public lotquantity?: number,
    public selecedquantity?: number,
    public noPlies?: number,
    public endBits?: number,
    public collapse?: boolean,
    public balances?: IBalance[]
  ) {}
}

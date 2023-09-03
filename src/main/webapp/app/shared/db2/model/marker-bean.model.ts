import { IBalance } from './balance.model';
import { ILotBean } from './lot-bean.model';

export interface IMarkerBean {
  markerId?: number;
  markercode?: string;
  markerLength?: number;
  lotcode?: string;
  lotquantity?: number;
  selecedquantity?: number;
  noRolls?: number;
  noPlies?: number;
  endBits?: number;
  collapse?: boolean;
  suggested?: boolean;
  highlight?: boolean;
  lotBeans?: ILotBean[];
  balances?: IBalance[];
}

export class MarkerBean implements IMarkerBean {
  constructor(
    public markerId?: number,
    public markercode?: string,
    public markerLength?: number,
    public lotcode?: string,
    public lotquantity?: number,
    public selecedquantity?: number,
    public noRolls?: number,
    public noPlies?: number,
    public endBits?: number,
    public collapse?: boolean,
    public suggested?: boolean,
    public highlight?: boolean,
    public lotBeans?: ILotBean[],
    public balances?: IBalance[]
  ) {}
}

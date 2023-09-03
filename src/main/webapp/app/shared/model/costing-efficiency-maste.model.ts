import { Moment } from 'moment';

export interface ICostingEfficiencyMaste {
  id?: number;
  fromQuantity?: number;
  toQuantity?: number;
  efficiencyPerc?: string;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
}

export class CostingEfficiencyMaste implements ICostingEfficiencyMaste {
  constructor(
    public id?: number,
    public fromQuantity?: number,
    public toQuantity?: number,
    public efficiencyPerc?: string,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment
  ) {}
}

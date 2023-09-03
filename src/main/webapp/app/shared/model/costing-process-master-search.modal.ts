import { Moment } from 'moment';

export interface ICostingProcessMasterSearch {
  id?: number;
  processcode?: string;
  processdesc?: string;
  fromQuantity?: number;
  toQuantity?: number;
  size?: number;
  pageNo?: number;
}

export class CostingProcessMasterSearch implements ICostingProcessMasterSearch {
  constructor(
    public id?: number,
    public processcode?: string,
    public processdesc?: string,
    public fromQuantity?: number,
    public toQuantity?: number,
    public size?: number,
    public pageNo?: number
  ) {}
}

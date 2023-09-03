import { Moment } from 'moment';

export interface ICostingFabricItemDetails {
  id?: number;
  itemType?: string;
  code?: string;
  descrption?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class CostingFabricItemDetails implements ICostingFabricItemDetails {
  constructor(
    public id?: number,
    public itemType?: string,
    public code?: string,
    public descrption?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {}
}

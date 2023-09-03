import { Moment } from 'moment';

export interface ISewingProductionEntry {
  id?: number;
  companycode?: string;
  countercode?: string;
  productionordercode?: string;
  plantCode?: string;
  plantDesc?: string;
  projectcode?: string;
  style?: string;
  color?: string;
  colorDesc?: string;
  destination?: string;
  destinationDesc?: string;
  orderQty?: number;
  tolerance?: number;
  netOrderQty?: number;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
  progressPostedBy?: string;
  progressPostedDate?: Moment;
}

export class SewingProductionEntry implements ISewingProductionEntry {
  constructor(
    public id?: number,
    public companycode?: string,
    public countercode?: string,
    public productionordercode?: string,
    public plantCode?: string,
    public plantDesc?: string,
    public projectcode?: string,
    public style?: string,
    public color?: string,
    public colorDesc?: string,
    public destination?: string,
    public destinationDesc?: string,
    public orderQty?: number,
    public tolerance?: number,
    public netOrderQty?: number,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment,
    public progressPostedBy?: string,
    public progressPostedDate?: Moment
  ) {}
}

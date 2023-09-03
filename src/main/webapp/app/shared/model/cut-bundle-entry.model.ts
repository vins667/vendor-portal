import { Moment } from 'moment';

export interface ICutBundleEntry {
  id?: number;
  porductionCounterCode?: string;
  productionCode?: string;
  plantCode?: string;
  style?: string;
  color?: string;
  size?: string;
  bundle_size?: number;
  bundle_pcs?: number;
  createdby?: string;
  createddate?: Moment;
  lastupdatedby?: string;
  lastupdateddate?: Moment;
}

export class CutBundleEntry implements ICutBundleEntry {
  constructor(
    public id?: number,
    public porductionCounterCode?: string,
    public productionCode?: string,
    public plantCode?: string,
    public style?: string,
    public color?: string,
    public size?: string,
    public bundle_size?: number,
    public bundle_pcs?: number,
    public createdby?: string,
    public createddate?: Moment,
    public lastupdatedby?: string,
    public lastupdateddate?: Moment
  ) {}
}

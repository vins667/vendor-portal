import { Moment } from 'moment';

export interface IAssetSupplierMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  createdDate?: Moment;
}

export class AssetSupplierMaster implements IAssetSupplierMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public createdDate?: Moment
  ) {}
}

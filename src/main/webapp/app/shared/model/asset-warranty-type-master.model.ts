import { Moment } from 'moment';

export interface IAssetWarrantyTypeMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
}

export class AssetWarrantyTypeMaster implements IAssetWarrantyTypeMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string
  ) {}
}

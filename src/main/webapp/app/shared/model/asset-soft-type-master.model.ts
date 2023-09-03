import { Moment } from 'moment';

export interface IAssetSoftTypeMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
}

export class AssetSoftTypeMaster implements IAssetSoftTypeMaster {
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

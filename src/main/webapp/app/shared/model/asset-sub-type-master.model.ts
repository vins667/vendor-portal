import { Moment } from 'moment';
import { IAssetTypeMaster } from 'app/shared/model//asset-type-master.model';

export interface IAssetSubTypeMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  assetTypeMaster?: IAssetTypeMaster;
}

export class AssetSubTypeMaster implements IAssetSubTypeMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public assetTypeMaster?: IAssetTypeMaster
  ) {}
}

import { Moment } from 'moment';
import { IAssetSubTypeMaster } from './asset-sub-type-master.model';

export interface IAssetSubTypeDetailMaster {
  id?: number;
  code?: string;
  description?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  assetSubTypeMaster?: IAssetSubTypeMaster;
}

export class AssetSubTypeDetailMaster implements IAssetSubTypeDetailMaster {
  constructor(
    public id?: number,
    public code?: string,
    public description?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public assetSubTypeMaster?: IAssetSubTypeMaster
  ) {}
}

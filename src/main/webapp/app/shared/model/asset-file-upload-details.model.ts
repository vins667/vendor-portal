import { Moment } from 'moment';
import { IAssetFileUploadMaster } from 'app/shared/model//asset-file-upload-master.model';
import { IAssetMaster } from 'app/shared/model//asset-master.model';

export interface IAssetFileUploadDetails {
  id?: number;
  createdBy?: string;
  createdDate?: Moment;
  assetFileUploadMaster?: IAssetFileUploadMaster;
  assetMaster?: IAssetMaster;
}

export class AssetFileUploadDetails implements IAssetFileUploadDetails {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public assetFileUploadMaster?: IAssetFileUploadMaster,
    public assetMaster?: IAssetMaster
  ) {}
}

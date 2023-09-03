import { IAssetMaster } from './asset-master.model';
import { IAssetFileUploadMaster } from './asset-file-upload-master.model';
import { IAssetFileUploadDetails } from './asset-file-upload-details.model';

export interface IAssetFileUploadBean {
  assetMaster?: IAssetMaster;
  assetFileUploadMasters?: IAssetFileUploadMaster[];
  assetFileUploadDetails?: IAssetFileUploadDetails[];
}

export class AssetFileUploadBean implements IAssetFileUploadBean {
  constructor(
    public assetMaster?: IAssetMaster,
    public assetFileUploadMasters?: IAssetFileUploadMaster[],
    public assetFileUploadDetails?: IAssetFileUploadDetails[]
  ) {}
}

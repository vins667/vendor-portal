import { Moment } from 'moment';
import { IAssetSupplierMaster } from 'app/shared/model//asset-supplier-master.model';
import { IAssetDocumentMaster } from 'app/shared/model//asset-document-master.model';

export interface IAssetFileUploadMaster {
  id?: number;
  fileName?: string;
  displayFileName?: string;
  createdBy?: string;
  createdDate?: Moment;
  invoiceNumber?: string;
  detailExist?: boolean;
  assetSupplierMaster?: IAssetSupplierMaster;
  assetDocumentMaster?: IAssetDocumentMaster;
}

export class AssetFileUploadMaster implements IAssetFileUploadMaster {
  constructor(
    public id?: number,
    public fileName?: string,
    public displayFileName?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public invoiceNumber?: string,
    public detailExist?: boolean,
    public assetSupplierMaster?: IAssetSupplierMaster,
    public assetDocumentMaster?: IAssetDocumentMaster
  ) {}
}

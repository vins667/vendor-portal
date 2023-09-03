import { Moment } from 'moment';
import { IAssetOwnershipMaster } from 'app/shared/model//asset-ownership-master.model';
import { IAssetTypeMaster } from 'app/shared/model//asset-type-master.model';
import { IAssetSubTypeMaster } from 'app/shared/model//asset-sub-type-master.model';
import { IAssetSubTypeDetailMaster } from 'app/shared/model//asset-sub-type-detail-master.model';
import { IAssetCompanyMaster } from 'app/shared/model//asset-company-master.model';
import { IAssetSupplierMaster } from 'app/shared/model//asset-supplier-master.model';
import { IAssetLocationMaster } from 'app/shared/model//asset-location-master.model';
import { IAssetAuditDetails } from 'app/shared/model//asset-audit-details.model';

export interface IAssetMaster {
  id?: number;
  assetCode?: string;
  model?: string;
  assetTag?: string;
  warrantyDate?: Moment;
  warrantyEndDate?: Moment;
  poNumber?: string;
  invoiceNumber?: string;
  invoiceDate?: Moment;
  quantity?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  assetOwnershipMaster?: IAssetOwnershipMaster;
  assetTypeMaster?: IAssetTypeMaster;
  assetSubTypeMaster?: IAssetSubTypeMaster;
  assetSubTypeDetailMaster?: IAssetSubTypeDetailMaster;
  assetCompanyMaster?: IAssetCompanyMaster;
  assetSupplierMaster?: IAssetSupplierMaster;
  assetLocationMaster?: IAssetLocationMaster;
  assetAuditDetails?: IAssetAuditDetails[];
}

export class AssetMaster implements IAssetMaster {
  constructor(
    public id?: number,
    public assetCode?: string,
    public model?: string,
    public assetTag?: string,
    public warrantyDate?: Moment,
    public warrantyEndDate?: Moment,
    public poNumber?: string,
    public invoiceNumber?: string,
    public invoiceDate?: Moment,
    public quantity?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public assetOwnershipMaster?: IAssetOwnershipMaster,
    public assetTypeMaster?: IAssetTypeMaster,
    public assetSubTypeMaster?: IAssetSubTypeMaster,
    public assetSubTypeDetailMaster?: IAssetSubTypeDetailMaster,
    public assetCompanyMaster?: IAssetCompanyMaster,
    public assetSupplierMaster?: IAssetSupplierMaster,
    public assetLocationMaster?: IAssetLocationMaster,
    public assetAuditDetails?: IAssetAuditDetails[]
  ) {}
}

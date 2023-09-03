import { Moment } from 'moment';
import { IAssetAuditSoftwareDetails } from './asset-audit-software-details.model';
import { IAssetAuditSoftwareKeyDetails } from './asset-audit-software-key-details.model';

export interface IAssetAuditDetails {
  id?: number;
  systemId?: number;
  uuid?: string;
  name?: string;
  ip?: string;
  hostname?: string;
  manufacturer?: string;
  model?: string;
  serial?: string;
  storageCount?: number;
  memoryCount?: number;
  type?: string;
  osInstallationDate?: Moment;
  osGroup?: string;
  osFamily?: string;
  osName?: string;
  osVersion?: string;
  assetCode?: string;
  createdBy?: string;
  createdDate?: Moment;
  runNumber?: number;
  assetAuditSoftwareDetailsList?: IAssetAuditSoftwareDetails[];
  assetAuditSoftwareKeyDetails?: IAssetAuditSoftwareKeyDetails[];
}

export class AssetAuditDetails implements IAssetAuditDetails {
  constructor(
    public id?: number,
    public systemId?: number,
    public uuid?: string,
    public name?: string,
    public ip?: string,
    public hostname?: string,
    public manufacturer?: string,
    public model?: string,
    public serial?: string,
    public storageCount?: number,
    public memoryCount?: number,
    public type?: string,
    public osInstallationDate?: Moment,
    public osGroup?: string,
    public osFamily?: string,
    public osName?: string,
    public osVersion?: string,
    public assetCode?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public runNumber?: number,
    public assetAuditSoftwareDetailsList?: IAssetAuditSoftwareDetails[],
    public assetAuditSoftwareKeyDetails?: IAssetAuditSoftwareKeyDetails[]
  ) {}
}

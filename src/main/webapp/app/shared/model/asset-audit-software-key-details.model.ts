import { Moment } from 'moment';

export interface IAssetAuditSoftwareKeyDetails {
  id?: number;
  name?: string;
  key?: string;
  systemId?: number;
  createdBy?: string;
  createdDate?: Moment;
}

export class AssetAuditSoftwareKeyDetails implements IAssetAuditSoftwareKeyDetails {
  constructor(
    public id?: number,
    public name?: string,
    public key?: string,
    public systemId?: number,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}

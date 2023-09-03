import { Moment } from 'moment';

export interface IAssetAuditSoftwareDetails {
  id?: number;
  name?: string;
  publisher?: string;
  installedOn?: Moment;
  systemId?: number;
  createdBy?: string;
  createdDate?: Moment;
}

export class AssetAuditSoftwareDetails implements IAssetAuditSoftwareDetails {
  constructor(
    public id?: number,
    public name?: string,
    public publisher?: string,
    public installedOn?: Moment,
    public systemId?: number,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}

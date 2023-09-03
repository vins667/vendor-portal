export interface IAssetAuditSoftwareCompare {
  id?: number;
  uuid?: string;
  name?: string;
  hostname?: string;
  ip?: string;
  assetCode?: string;
  storageCount?: number;
  memoryCount?: number;
  systemId?: number;
  color?: string;
  type?: string;
}

export class AssetAuditSoftwareCompare implements IAssetAuditSoftwareCompare {
  constructor(
    public id?: number,
    public uuid?: string,
    public name?: string,
    public hostname?: string,
    public ip?: string,
    public assetCode?: string,
    public storageCount?: number,
    public memoryCount?: number,
    public systemId?: number,
    public color?: string,
    public type?: string
  ) {}
}

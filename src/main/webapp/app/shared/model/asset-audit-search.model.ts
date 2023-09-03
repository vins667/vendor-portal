export interface IAssetAuditSearch {
  uuid?: string;
  name?: string;
  ip?: string;
  serial?: string;
  assetCode?: string;
  size?: number;
  pageNo?: number;
}

export class AssetAuditSearch implements IAssetAuditSearch {
  constructor(
    public uuid?: string,
    public name?: string,
    public ip?: string,
    public serial?: string,
    public assetCode?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

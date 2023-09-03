export interface IAssetSoftwareCompareBean {
  uuid?: string;
  name?: string;
  publisher?: string;
  installedOn?: string;
  type?: string;
  color?: number;
}

export class AssetSoftwareCompareBean implements IAssetSoftwareCompareBean {
  constructor(
    public uuid?: string,
    public name?: string,
    public publisher?: string,
    public installedOn?: string,
    public type?: string,
    public color?: number
  ) {}
}

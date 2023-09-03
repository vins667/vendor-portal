export interface IAssetSoftwareKeyCompareBean {
  uuid?: string;
  name?: string;
  jhiKey?: string;
  type?: string;
  color?: number;
}

export class AssetSoftwareKeyCompareBean implements IAssetSoftwareKeyCompareBean {
  constructor(public uuid?: string, public name?: string, public jhiKey?: string, public type?: string, public color?: number) {}
}

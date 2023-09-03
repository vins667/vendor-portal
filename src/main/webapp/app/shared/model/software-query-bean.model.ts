export interface ISoftwareQueryBean {
  id?: number;
  assetCode?: string;
  assetName?: string;
  assetIp?: string;
  publisher?: string;
  publisherName?: string;
}

export class SoftwareQueryBean implements ISoftwareQueryBean {
  constructor(
    public id?: number,
    public assetCode?: string,
    public assetName?: string,
    public assetIp?: string,
    public publisher?: string,
    public publisherName?: string
  ) {}
}

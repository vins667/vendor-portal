export interface ISoftwareSerachQuery {
  assetCode?: string;
  publisher?: string;
  software?: string;
  size?: number;
  pageNo?: number;
}

export class SoftwareSearchQuery implements ISoftwareSerachQuery {
  constructor(
    public assetCode?: string,
    public publisher?: string,
    public software?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

export interface IBankRealizationCertificateUploadSearch {
  brcDate?: any;
  brcDateTo?: any;
  brcNo?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}
export class BankRealizationCertificateUploadSearch implements IBankRealizationCertificateUploadSearch {
  constructor(
    public brcDate?: string,
    public brcDateTo?: string,
    public brcNo?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

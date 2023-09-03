export interface IBankRealisationCertificateUpload {
  id?: number;
  sbNo?: string;
  sbDate?: any;
  brcNo?: string;
  brcDate?: any;
  portCode?: string;
  fob?: number;
  currency?: string;
  realisationDate?: any;
  status?: string;
  createddate?: any;
  createdby?: string;
}
export class BankRealisationCertificateUpload implements IBankRealisationCertificateUpload {
  constructor(
    public id?: number,
    public sbNo?: string,
    public sbDate?: any,
    public brcNo?: string,
    public brcDate?: any,
    public portCode?: string,
    public fob?: number,
    public currency?: string,
    public realisationDate?: any,
    public status?: string,
    public createddate?: any,
    public createdby?: string
  ) {}
}

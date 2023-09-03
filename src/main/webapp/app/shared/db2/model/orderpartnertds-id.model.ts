export interface IOrderpartnertdsId {
  companycode?: string;
  csmsupcustomersuppliertype?: string;
  csmsupcustomersuppliercode?: string;
  tdsteusergenericgrouptypecode?: string;
  tdstypecode?: string;
  tdscode?: string;
  tdsitaxcode?: string;
}

export class OrderpartnertdsId implements IOrderpartnertdsId {
  constructor(
    public companycode?: string,
    public csmsupcustomersuppliertype?: string,
    public csmsupcustomersuppliercode?: string,
    public tdsteusergenericgrouptypecode?: string,
    public tdstypecode?: string,
    public tdscode?: string,
    public tdsitaxcode?: string
  ) {}
}

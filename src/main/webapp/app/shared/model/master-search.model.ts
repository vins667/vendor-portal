export interface IMasterSearch {
  id?: number;
  companyCode?: string;
  businessunitcode?: string;
  finyearcode?: string;
  code?: string;
  description?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
  reportType?: string;
  paraString1?: string;
  paraNumber1?: number;
  parameters1?: string[];
  parameters2?: string[];
  templateType?: string;
}

export class MasterSearch implements IMasterSearch {
  constructor(
    public id?: number,
    public companyCode?: string,
    public businessunitcode?: string,
    public finyearcode?: string,
    public code?: string,
    public description?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number,
    public reportType?: string,
    public paraString1?: string,
    public paraNumber1?: number,
    public parameters1?: string[],
    public parameters2?: string[],
    public templateType?: string
  ) {}
}

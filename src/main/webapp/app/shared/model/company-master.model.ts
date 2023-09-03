import { Moment } from 'moment';

export interface ICompanyMaster {
  id?: number;
  companyCode?: string;
  companyName?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class CompanyMaster implements ICompanyMaster {
  constructor(
    public id?: number,
    public companyCode?: string,
    public companyName?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}

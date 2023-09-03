import { Moment } from 'moment';
import { ICompanyMaster } from 'app/shared/model//company-master.model';

export interface IFactoryMaster {
  id?: number;
  factoryCode?: string;
  nowFactoryCode?: string;
  factoryName?: string;
  flag?: string;
  createdBy?: string;
  createdDate?: Moment;
  companyMaster?: ICompanyMaster;
}

export class FactoryMaster implements IFactoryMaster {
  constructor(
    public id?: number,
    public factoryCode?: string,
    public nowFactoryCode?: string,
    public factoryName?: string,
    public flag?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public companyMaster?: ICompanyMaster
  ) {}
}

import { IManpowerBean } from 'app/shared/model/manpower-bean.model';

export interface IManpowerBudgeting {
  factoryCode?: string;
  factoryName?: string | null;
  nowFactoryName?: string | null;
  departmentCode?: string;
  departmentName?: string | null;
  type?: string | null;
  dateFrom?: any | null;
  manpowerBeans?: IManpowerBean[] | [];
}

export class ManpowerBudgeting implements IManpowerBudgeting {
  constructor(
    public factoryCode?: string,
    public factoryName?: string | null,
    public nowFactoryName?: string | null,
    public departmentCode?: string,
    public departmentName?: string | null,
    public type?: string | null,
    public dateFrom?: any | null,
    public manpowerBeans?: IManpowerBean[] | []
  ) {}
}

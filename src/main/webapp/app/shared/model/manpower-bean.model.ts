import { IResources } from 'app/shared/model/resources.model';

export interface IManpowerBean {
  desCode?: string;
  desCodeDesc?: string;
  sdepCode?: string;
  subDeptDesc?: string;
  catCode?: string;
  catName?: string;
  employeeCount?: number;
  balanceCount?: number;
  resources?: IResources[];
}

export class ManpowerBean implements IManpowerBean {
  constructor(
    public desCode?: string,
    public desCodeDesc?: string,
    public sdepCode?: string,
    public subDeptDesc?: string,
    public catCode?: string,
    public catName?: string,
    public employeeCount?: number,
    public balanceCount?: number,
    public resources?: IResources[]
  ) {}
}

import { IResources } from 'app/shared/model/resources.model';

export interface IManpowerSearch {
  deptCode?: string;
  factoryCode?: string;
  dateFrom?: string;
  type?: string;
  resourcesBeans?: IResources[];
}

export class ManpowerSearch implements IManpowerSearch {
  constructor(
    public deptCode?: string,
    public factoryCode?: string,
    public dateFrom?: string,
    public type?: string,
    public resourcesBeans?: IResources[]
  ) {}
}

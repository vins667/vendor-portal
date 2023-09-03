import { MMRDesignationBean } from './mmr-designation-bean.model';

export interface IMMDepartmentBean {
  id?: number;
  department?: string;
  departmentDesc?: string;
  expend?: boolean;
  totalSal?: number;
  totalPcs?: number;
  mmrDesignationBean?: MMRDesignationBean[];
}

export class MMDepartmentBean implements IMMDepartmentBean {
  constructor(
    public id?: number,
    public department?: string,
    public departmentDesc?: string,
    public expend?: boolean,
    public totalSal?: number,
    public totalPcs?: number,
    public mmrDesignationBean?: MMRDesignationBean[]
  ) {}
}

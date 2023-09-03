export interface IMMRDesignationBean {
  id?: number;
  designation?: string;
  designationDesc?: string;
  swCode?: string;
  salary?: number;
  pcsRate?: number;
}

export class MMRDesignationBean implements IMMRDesignationBean {
  constructor(
    public id?: number,
    public designation?: string,
    public designationDesc?: string,
    public swCode?: string,
    public salary?: number,
    public pcsRate?: number
  ) {}
}

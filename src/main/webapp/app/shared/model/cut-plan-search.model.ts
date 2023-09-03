export interface ICutPlanSearch {
  id?: number;
  pono?: string;
  plantCode?: string;
  style?: string;
  color?: string;
  destination?: string;
  itemtypecode?: string;
  subcode01?: string;
  subcode02?: string;
  subcode03?: string;
  subcode04?: string;
  subcode05?: string;
  subcode06?: string;
  subcode07?: string;
  subcode08?: string;
  subcode09?: string;
  subcode10?: string;
  status?: string;
  tolPer?: number;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class CutPlanSearch implements ICutPlanSearch {
  constructor(
    public id?: number,
    public pono?: string,
    public style?: string,
    public color?: string,
    public plantCode?: string,
    public destination?: string,
    public itemtypecode?: string,
    public subcode01?: string,
    public subcode02?: string,
    public subcode03?: string,
    public subcode04?: string,
    public subcode05?: string,
    public subcode06?: string,
    public subcode07?: string,
    public subcode08?: string,
    public subcode09?: string,
    public subcode10?: string,
    public status?: string,
    public tolPer?: number,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

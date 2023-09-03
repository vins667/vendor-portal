export interface IVcutStylePlanUploadSearch {
  planDate?: string;
  planDateTo?: string;
  style?: string;
  poNo?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class VcutStylePlanUploadSearch implements IVcutStylePlanUploadSearch {
  constructor(
    public planDate?: string,
    public planDateTo?: string,
    public style?: string,
    public poNo?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

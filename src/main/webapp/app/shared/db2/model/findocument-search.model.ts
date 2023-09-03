export interface IFindocumentSearch {
  code?: string;
  status?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class FindocumentSearch implements IFindocumentSearch {
  constructor(
    public code?: string,
    public status?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

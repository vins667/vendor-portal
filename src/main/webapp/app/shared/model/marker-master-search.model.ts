export interface IMarkerMasterSearch {
  style?: string;
  color?: string;
  projectcode?: string;
  status?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class MarkerMasterSearch implements IMarkerMasterSearch {
  constructor(
    public style?: string,
    public color?: string,
    public projectcode?: string,
    public status?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

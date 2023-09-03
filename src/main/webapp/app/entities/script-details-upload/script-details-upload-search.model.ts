export interface IScriptDetailsUploadSearch {
  date?: any;
  dateFrom?: any;
  sNo?: string;
  invoiceNo?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}
export class ScriptDetailsUploadSearch implements IScriptDetailsUploadSearch {
  constructor(
    public date?: any,
    public dateFrom?: any,
    public sNo?: string,
    public invoiceNo?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

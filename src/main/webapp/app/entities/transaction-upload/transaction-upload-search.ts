export interface ITransactionUploadSearch {
  chequeNo?: string;
  page?: any;
  sort?: string;
  sortType?: string;
  size?: number;
  pageNo?: number;
}

export class TransactionUploadSearch implements ITransactionUploadSearch {
  constructor(
    public chequeNo?: string,
    public page?: any,
    public sort?: string,
    public sortType?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

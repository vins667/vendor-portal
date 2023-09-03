export interface ITransactionUpload {
  id?: number;
  transactionPostedDate?: any;
  chequeNo?: number;
  description?: string;
  mode?: string;
  transactionAmount?: number;
}

export class TransactionUpload implements ITransactionUpload {
  constructor(
    public id?: number,
    public transactionPostedDate?: any,
    public chequeNo?: number,
    public description?: string,
    public mode?: string,
    public transactionAmount?: number
  ) {}
}

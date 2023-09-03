import { Moment } from 'moment';

export interface IBankReconciliationdetail {
  id?: number;
  rowcount?: string;
  companycode?: string;
  businessunitcode?: string;
  finyearcode?: string;
  code?: string;
  linenumber?: string;
  glcode?: string;
  glname?: string;
  chequeno?: string;
  chequedate?: any;
  chequeclearingdate?: string;
  chequeamount?: number;
  debitamit?: number;
  creditamt?: number;
  documentype?: string;
  documentdate?: any;
  profitcentercode?: string;
  checkdepositnotclear?: number;
  checkissuenotclear?: number;
  exist?: boolean;
  narration?: string;
  reconciliationdate?: any;
}

export class BankReconciliationdetail implements IBankReconciliationdetail {
  constructor(
    public id?: number,
    public rowcount?: string,
    public companycode?: string,
    public businessunitcode?: string,
    public finyearcode?: string,
    public code?: string,
    public linenumber?: string,
    public glcode?: string,
    public glname?: string,
    public chequeno?: string,
    public chequedate?: any,
    public chequeclearingdate?: string,
    public chequeamount?: number,
    public debitamit?: number,
    public creditamt?: number,
    public documentype?: string,
    public documentdate?: any,
    public profitcentercode?: string,
    public checkdepositnotclear?: number,
    public checkissuenotclear?: number,
    public exist?: boolean,
    public narration?: string,
    public reconciliationdate?: any
  ) {}
}

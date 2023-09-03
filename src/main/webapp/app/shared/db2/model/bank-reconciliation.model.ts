import { IBankReconciliationdetail } from 'app/shared/db2/model/bank-reconciliationdetail.model';
import { Moment } from 'moment';

export interface IBankReconciliation {
  id?: number;
  reconciliationdate?: Moment;
  documentdate?: Moment;
  documentno?: string;
  bankcode?: string;
  bankname?: string;
  balance?: number;
  checkdepositnotclear?: number;
  checkissuenotclear?: number;
  ledgerbalance?: number;
  bankbalance?: number;
  balancedifference?: number;
  entryby?: string;
  entrydate?: Moment;
  bankReconciliationdetails?: IBankReconciliationdetail[];
}

export class BankReconciliation implements IBankReconciliation {
  constructor(
    public id?: number,
    public reconciliationdate?: Moment,
    public documentdate?: Moment,
    public documentno?: string,
    public bankcode?: string,
    public bankname?: string,
    public balance?: number,
    public entryby?: string,
    public entrydate?: Moment,
    public checkdepositnotclear?: number,
    public checkissuenotclear?: number,
    public ledgerbalance?: number,
    public bankbalance?: number,
    public balancedifference?: number,
    public bankReconciliationdetails?: IBankReconciliationdetail[]
  ) {}
}

import { Moment } from 'moment';

export interface ITdsSlabMaster {
  id?: number;
  finYear?: string;
  gender?: string;
  ageStart?: number;
  ageEnd?: number;
  amountStart?: number;
  amountEnd?: number;
  taxPercentage?: number;
  taxSurcharge?: number;
  exemptionLimit?: number;
  createdBy?: string;
  createdDate?: Moment;
}

export class TdsSlabMaster implements ITdsSlabMaster {
  constructor(
    public id?: number,
    public finYear?: string,
    public gender?: string,
    public ageStart?: number,
    public ageEnd?: number,
    public amountStart?: number,
    public amountEnd?: number,
    public taxPercentage?: number,
    public taxSurcharge?: number,
    public exemptionLimit?: number,
    public createdBy?: string,
    public createdDate?: Moment
  ) {}
}

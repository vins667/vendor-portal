import { Moment } from 'moment';
import { ITdsYearMaster } from './tds-year-master.model';

export interface IPreviousEmploymentQry {
  id?: number;
  financeYear?: string;
  cardNo?: string;
  name?: string;
  dateFrom?: Moment;
  dateTo?: Moment;
  previousEmployer?: string;
  landLordName?: string;
  landLordPan?: string;
  landLordAddress?: string;
  monthRent?: number;
  basic?: number;
  hra?: number;
  cta?: number;
  others?: number;
  spa?: number;
  mda?: number;
  epf?: number;
  vpf?: number;
  tds?: number;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  tdsYearMaster?: ITdsYearMaster;
}

export class PreviousEmploymentQry implements IPreviousEmploymentQry {
  constructor(
    public id?: number,
    public financeYear?: string,
    public cardNo?: string,
    public name?: string,
    public dateFrom?: Moment,
    public dateTo?: Moment,
    public previousEmployer?: string,
    public landLordName?: string,
    public landLordPan?: string,
    public landLordAddress?: string,
    public monthRent?: number,
    public basic?: number,
    public hra?: number,
    public cta?: number,
    public others?: number,
    public spa?: number,
    public mda?: number,
    public epf?: number,
    public vpf?: number,
    public tds?: number,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public tdsYearMaster?: ITdsYearMaster
  ) {}
}

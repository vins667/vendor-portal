import { Moment } from 'moment';

export interface ITdsYearMaster {
  id?: number;
  code?: string;
  financeYear?: string;
  active?: boolean;
  expired?: boolean;
  tempLock?: boolean;
  uploadDoc?: boolean;
  regime?: boolean;
  createdBy?: string;
  createdDate?: Moment;
}

export class TdsYearMaster implements ITdsYearMaster {
  constructor(
    public id?: number,
    public code?: string,
    public financeYear?: string,
    public active?: boolean,
    public expired?: boolean,
    public tempLock?: boolean,
    public uploadDoc?: boolean,
    public regime?: boolean,
    public createdBy?: string,
    public createdDate?: Moment
  ) {
    this.active = this.active || false;
    this.expired = this.expired || false;
    this.tempLock = this.tempLock || false;
    this.uploadDoc = this.uploadDoc || false;
    this.regime = this.regime || false;
  }
}

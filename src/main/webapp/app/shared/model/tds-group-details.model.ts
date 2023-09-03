import { Moment } from 'moment';
import { ITdsGroupMaster } from './tds-group-master.model';
import { TdsDeclarationBreakupBean } from './tds-declaration-breakup-bean.model';

export interface ITdsGroupDetails {
  id?: number;
  perkCode?: string;
  perkDescription?: string;
  perkLimit?: string;
  perkType?: string;
  perkMode?: string;
  calType?: string;
  forComp?: string;
  printOrder?: number;
  remarks?: string;
  amount?: number;
  createdBy?: string;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
  createdDate?: Moment;
  displayFlag?: string;
  tdsGroupMaster?: ITdsGroupMaster;
  tdsDeclarationBreakupBeans?: TdsDeclarationBreakupBean[];
}

export class TdsGroupDetails implements ITdsGroupDetails {
  constructor(
    public id?: number,
    public perkCode?: string,
    public perkDescription?: string,
    public perkLimit?: string,
    public perkType?: string,
    public perkMode?: string,
    public calType?: string,
    public forComp?: string,
    public printOrder?: number,
    public remarks?: string,
    public amount?: number,
    public createdBy?: string,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment,
    public createdDate?: Moment,
    public tdsGroupMaster?: ITdsGroupMaster,
    public displayFlag?: string,
    public tdsDeclarationBreakupBeans?: TdsDeclarationBreakupBean[]
  ) {}
}

import { Moment } from 'moment';
import { IVcutPlanChangeMaster } from 'app/shared/model/vcut-plan-change-master.model';

export interface IVcutStylePlanUpload {
  id?: number;
  planDate?: Moment;
  plantCode?: string;
  plantDescription?: string;
  floor?: string;
  lineNo?: string;
  lineDesc?: string;
  poNoCounter?: string;
  poNo?: string;
  projectcode?: string;
  style?: string;
  color?: string;
  colorName?: string;
  destination?: string;
  destinationDesc?: string;
  buyer?: string;
  buyerName?: string;
  quantity?: number;
  kickOff?: number;
  smv?: number;
  days?: number;
  operators?: number;
  helpers?: number;
  itemType?: string;
  itemName?: string;
  workingHours?: number;
  merchantName?: string;
  merchant?: string;
  factory?: string;
  createBy?: string;
  createdDate?: Moment;
  vcutSessionMasterId?: any;
  activePlan?: boolean;
  vcutPlanChangeMaster?: IVcutPlanChangeMaster;
}

export class VcutStylePlanUpload implements IVcutStylePlanUpload {
  constructor(
    public id?: number,
    public planDate?: Moment,
    public plantCode?: string,
    public plantDescription?: string,
    public floor?: string,
    public lineNo?: string,
    public lineDesc?: string,
    public poNo?: string,
    public poNoCounter?: string,
    public projectcode?: string,
    public style?: string,
    public color?: string,
    public colorName?: string,
    public destination?: string,
    public destinationDesc?: string,
    public buyer?: string,
    public buyerName?: string,
    public quantity?: number,
    public kickOff?: number,
    public smv?: number,
    public days?: number,
    public operators?: number,
    public helpers?: number,
    public itemType?: string,
    public itemName?: string,
    public workingHours?: number,
    public merchantName?: string,
    public merchant?: string,
    public factory?: string,
    public createBy?: string,
    public createdDate?: Moment,
    public vcutSessionMasterId?: number,
    public activePlan?: boolean,
    public vcutPlanChangeMaster?: IVcutPlanChangeMaster
  ) {}
}

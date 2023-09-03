import { Moment } from 'moment';
import { Fullitemkeydecoder } from '../db2/model/fulltemkeydecoder.model';
import { ICutPlanEntryDetails } from './cut-plan-entry-details.model';
import { IMarkerMasterEntry } from './marker-master-entry.model';
import { IMarkerBean } from 'app/shared/db2/model/marker-bean.model';
import { ICutPlanProgressEntry } from 'app/shared/model/cut-plan-progress-entry.model';

export interface ICutPlanEntry {
  id?: number;
  plantCode?: string;
  plantDescription?: string;
  porductionCounterCode?: string;
  productionCode?: string;
  style?: string;
  color?: string;
  colorDesc?: string;
  destination?: string;
  destinationDesc?: string;
  orderQty?: number;
  tolerance?: number;
  netOrderQty?: number;
  itemtypecode?: string;
  subcode01?: string;
  subcode02?: string;
  subcode03?: string;
  subcode04?: string;
  subcode05?: string;
  subcode06?: string;
  subcode07?: string;
  subcode08?: string;
  subcode09?: string;
  subcode10?: string;
  summerizedDescription?: string;
  fabricRequired?: number;
  noPlies?: number;
  actualNoPlies?: number;
  noMarkers?: number;
  markerLength?: number;
  LotNo?: string;
  noRolls?: number;
  endBits?: number;
  status?: string;
  releaseBy?: string;
  releaseDate?: any;
  startDate?: Moment;
  endDate?: Moment;
  totalHour?: any;
  noCutters?: number;
  plannedAvg?: number;
  actualAvg?: number;
  createdby?: string;
  createddate?: Moment;
  lastupdatedby?: string;
  lastupdateddate?: Moment;
  markerMasterEntry?: IMarkerMasterEntry;
  markerMasterEntryId?: number;
  markerBean?: IMarkerBean;
  itemcode?: Fullitemkeydecoder;
  resourceCode?: string;
  resourceDescription?: string;
  progressEntryDate?: any;
  progressPostedDate?: any;
  progressPostedBy?: string;
  cutPlanEntryDetailsBeans?: ICutPlanEntryDetails[];
  cutPlanProgressEntryBeans?: ICutPlanProgressEntry[];
  cutPlanProgressEntry?: ICutPlanProgressEntry;
}

export class CutPlanEntry implements ICutPlanEntry {
  constructor(
    public id?: number,
    public plantCode?: string,
    public plantDescription?: string,
    public porductionCounterCode?: string,
    public productionCode?: string,
    public style?: string,
    public color?: string,
    public colorDesc?: string,
    public destination?: string,
    public destinationDesc?: string,
    public orderQty?: number,
    public tolerance?: number,
    public netOrderQty?: number,
    public itemtypecode?: string,
    public subcode01?: string,
    public subcode02?: string,
    public subcode03?: string,
    public subcode04?: string,
    public subcode05?: string,
    public subcode06?: string,
    public subcode07?: string,
    public subcode08?: string,
    public subcode09?: string,
    public subcode10?: string,
    public summerizedDescription?: string,
    public fabricRequired?: number,
    public noPlies?: number,
    public actualNoPlies?: number,
    public noMarkers?: number,
    public markerLength?: number,
    public LotNo?: string,
    public noRolls?: number,
    public endBits?: number,
    public status?: string,
    public releaseBy?: string,
    public releaseDate?: any,
    public startDate?: Moment,
    public endDate?: Moment,
    public totalHour?: number,
    public noCutters?: number,
    public createdby?: string,
    public createddate?: Moment,
    public lastupdatedby?: string,
    public lastupdateddate?: Moment,
    public markerMasterEntry?: IMarkerMasterEntry,
    public markerMasterEntryId?: number,
    public markerBean?: IMarkerBean,
    public itemcode?: Fullitemkeydecoder,
    public resourceCode?: string,
    public resourceDescription?: string,
    public progressEntryDate?: any,
    public progressPostedDate?: any,
    public progressPostedBy?: string,
    public plannedAvg?: number,
    public actualAvg?: number,
    public cutPlanEntryDetailsBeans?: ICutPlanEntryDetails[],
    public cutPlanProgressEntryBeans?: ICutPlanProgressEntry[],
    public cutPlanProgressEntry?: ICutPlanProgressEntry
  ) {}
}

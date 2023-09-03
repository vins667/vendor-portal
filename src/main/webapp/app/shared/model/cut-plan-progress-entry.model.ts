import { Moment } from 'moment';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';

export interface ICutPlanProgressEntry {
  id?: number;
  operationCode?: string;
  operationDescription?: string;
  startDate?: Moment;
  endDate?: Moment;
  noCutters?: number;
  totalHour?: any;
  progressEntryBy?: string;
  progressEntryDate?: Moment;
  progressimportid?: number;
  progressPostedBy?: string;
  progressPostedDate?: Moment;
  cutPlanEntry?: ICutPlanEntry;
  lastProgress?: string;
}

export class CutPlanProgressEntry implements ICutPlanProgressEntry {
  constructor(
    public id?: number,
    public operationCode?: string,
    public operationDescription?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public noCutters?: number,
    public totalHour?: any,
    public progressEntryBy?: string,
    public progressEntryDate?: Moment,
    public progressimportid?: number,
    public progressPostedBy?: string,
    public progressPostedDate?: Moment,
    public cutPlanEntry?: ICutPlanEntry,
    public lastProgress?: string
  ) {}
}

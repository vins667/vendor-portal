import { Moment } from 'moment';
import { ICutPlanEntry } from 'app/shared/model/cut-plan-entry.model';
import { IPackingProgressEntry } from 'app/shared/model/packing-progress-entry.model';
import { IPackingProductionEntry } from 'app/shared/model/packing-production-entry.model';
import { IStitchIssuePackDetails } from 'app/shared/model/stitch-issue-pack-details.model';

export interface IPackingProgressEntry {
  id?: number;
  scannedBy?: string;
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
  packingProductionEntry?: IPackingProductionEntry;
  stitchIssuePackDetails?: IStitchIssuePackDetails[];
  lastProgress?: string;
}

export class PackingProgressEntry implements IPackingProgressEntry {
  constructor(
    public id?: number,
    public scannedBy?: string,
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
    public packingProductionEntry?: IPackingProductionEntry,
    public stitchIssuePackDetails?: IStitchIssuePackDetails[],
    public lastProgress?: string
  ) {}
}

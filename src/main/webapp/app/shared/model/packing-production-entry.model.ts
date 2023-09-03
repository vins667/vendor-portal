import { Moment } from 'moment';
import { IPackingProgressEntry } from 'app/shared/model/packing-progress-entry.model';

export interface IPackingProductionEntry {
  id?: number;
  companycode?: string;
  countercode?: string;
  productionordercode?: string;
  plantCode?: string;
  plantDesc?: string;
  projectcode?: string;
  style?: string;
  color?: string;
  colorDesc?: string;
  destination?: string;
  destinationDesc?: string;
  orderQty?: number;
  tolerance?: number;
  netOrderQty?: number;
  createdby?: string;
  createddate?: Moment;
  updatedby?: string;
  updateddate?: Moment;
  scannedBy?: string;
  progressPostedBy?: string;
  progressPostedDate?: Moment;
  packingProgressEntry?: IPackingProgressEntry;
  packingProgressEntries?: IPackingProgressEntry[];
}

export class PackingProductionEntry implements IPackingProductionEntry {
  constructor(
    public id?: number,
    public companycode?: string,
    public countercode?: string,
    public productionordercode?: string,
    public plantCode?: string,
    public plantDesc?: string,
    public projectcode?: string,
    public style?: string,
    public color?: string,
    public colorDesc?: string,
    public destination?: string,
    public destinationDesc?: string,
    public orderQty?: number,
    public tolerance?: number,
    public netOrderQty?: number,
    public createdby?: string,
    public createddate?: Moment,
    public updatedby?: string,
    public updateddate?: Moment,
    public scannedBy?: string,
    public progressPostedBy?: string,
    public progressPostedDate?: Moment,
    public packingProgressEntry?: IPackingProgressEntry,
    public packingProgressEntries?: IPackingProgressEntry[]
  ) {}
}

import { IMarkerEntryDetails } from './marker-entry-details.model';
import { Fullitemkeydecoder } from '../db2/model/fulltemkeydecoder.model';

export interface IMarkerMasterEntry {
  id?: number;
  markerCode?: string;
  plantCode?: string;
  plantDescription?: string;
  bodyFabric?: boolean;
  style?: string;
  color?: string;
  colorDesc?: string;
  width?: string;
  length?: number;
  orderQty?: number;
  itemCode?: Fullitemkeydecoder;
  orderStatus?: string;
  createdBy?: string;
  createdDate?: any;
  lastUpdatedBy?: string;
  lastUpdatedDate?: any;
  saveDisabled?: boolean;
  itemType?: string;
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
  plannedAvg?: number;
  actualAvg?: number;
  approvalFlag?: string;
  approvedBy?: string;
  approvedDate?: any;
  exist?: boolean;
  markerEntryDetails?: IMarkerEntryDetails[];
}

export class MarkerMasterEntry implements IMarkerMasterEntry {
  constructor(
    public id?: number,
    public markerCode?: string,
    public plantCode?: string,
    public plantDescription?: string,
    public bodyFabric?: boolean,
    public style?: string,
    public color?: string,
    public colorDesc?: string,
    public width?: string,
    public length?: number,
    public orderQty?: number,
    public itemCode?: Fullitemkeydecoder,
    public orderStatus?: string,
    public createdBy?: string,
    public createdDate?: any,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: any,
    public saveDisabled?: boolean,
    public itemType?: string,
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
    public plannedAvg?: number,
    public actualAvg?: number,
    public approvalFlag?: string,
    public approvedBy?: string,
    public approvedDate?: any,
    public exist?: boolean,
    public markerEntryDetails?: IMarkerEntryDetails[]
  ) {}
}

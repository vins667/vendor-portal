export interface IBalanceSuggestionSearch {
  id?: number;
  plantCode?: string;
  productionorder?: string;
  style?: string;
  destinationcode?: string;
  color?: string;
  projectcode?: string;
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
  pliesLength?: number;
  noPlies?: number;
  markerMasterEntryId?: number;
}

export class BalanceSuggestionSearch implements IBalanceSuggestionSearch {
  constructor(
    public id?: number,
    public plantCode?: string,
    public productionorder?: string,
    public style?: string,
    public destinationcode?: string,
    public color?: string,
    public projectcode?: string,
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
    public pliesLength?: number,
    public noPlies?: number,
    public markerMasterEntryId?: number
  ) {}
}

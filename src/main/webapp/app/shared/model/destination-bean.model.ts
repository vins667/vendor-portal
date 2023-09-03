import { IMarkerEntryDetails } from 'app/shared/model/marker-entry-details.model';

export interface IDestinationBean {
  exist?: boolean;
  destination?: string;
  totalQty?: number;
  markerEntryDetailsBeans?: IMarkerEntryDetails[];
}

export class DestinationBean implements IDestinationBean {
  constructor(
    public exist?: boolean,
    public destination?: string,
    public totalQty?: number,
    public markerEntryDetailsBeans?: IMarkerEntryDetails[]
  ) {}
}

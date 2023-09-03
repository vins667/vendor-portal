import { IDirectBookingEntry } from 'app/shared/model/direct-booking-entry.model';

export interface IDirectBookingTdsDetails {
  id?: number;
  tdsDesc?: string;
  tdsCode?: string;
  tdsTaxCode?: string;
  tdsPercDesc?: string;
  tdsPerc?: number;
  tdsApplicable?: boolean;
  directBookingEntry?: IDirectBookingEntry;
}

export class DirectBookingTdsDetails implements IDirectBookingTdsDetails {
  constructor(
    public id?: number,
    public tdsDesc?: string,
    public tdsCode?: string,
    public tdsTaxCode?: string,
    public tdsPercDesc?: string,
    public tdsPerc?: number,
    public tdsApplicable?: boolean,
    public directBookingEntry?: IDirectBookingEntry
  ) {
    this.tdsApplicable = this.tdsApplicable || false;
  }
}

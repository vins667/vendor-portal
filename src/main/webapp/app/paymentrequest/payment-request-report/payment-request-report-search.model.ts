import { Moment } from 'moment';

export interface IPaymentRequestReportSearch {
  dateFrom?: Moment;
  dateTo?: Moment;
}

export class PaymentRequestReportSearch {
  constructor(public dateFrom?: Moment, public dateTo?: Moment) {}
}

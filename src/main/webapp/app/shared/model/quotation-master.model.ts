export interface IQuotationMaster {
  id?: number;
  eventType?: string;
  rfq?: boolean;
  bidding?: boolean;
  projectTitle?: string;
  category?: string;
  sourcingTeam?: string;
  projectRegion?: string;
  remarks?: string;
  template?: string;
}

export class QuotationMaster implements IQuotationMaster {
  constructor(
    public id?: number,
    public eventType?: string,
    public rfq?: boolean,
    public bidding?: boolean,
    public projectTitle?: string,
    public category?: string,
    public sourcingTeam?: string,
    public projectRegion?: string,
    public remarks?: string,
    public template?: string
  ) {
    this.rfq = this.rfq || false;
    this.bidding = this.bidding || false;
  }
}

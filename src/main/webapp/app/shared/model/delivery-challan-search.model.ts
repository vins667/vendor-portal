export interface IDeliveryChallanSearch {
  factCode?: string;
  factDescription?: string;
  status?: string;
  size?: number;
  pageNo?: number;
}

export class DeliveryChallanSearch implements IDeliveryChallanSearch {
  constructor(
    public factCode?: string,
    public factDescription?: string,
    public status?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

export interface ISewingProductionEntrySearch {
  shiftcode?: string;
  productionordercode?: string;
  style?: string;
  page?: any;
  size?: number;
  pageNo?: number;
}

export class SewingProductionEntrySearch implements ISewingProductionEntrySearch {
  constructor(
    public shiftcode?: string,
    public productionordercode?: string,
    public style?: string,
    public page?: any,
    public size?: number,
    public pageNo?: number
  ) {}
}

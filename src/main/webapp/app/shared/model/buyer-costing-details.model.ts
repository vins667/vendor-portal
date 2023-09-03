import { IBuyerCostingSubDetails } from './buyer-costing-sub-details.model';

export interface IBuyerCostingDetails {
  id?: number;
  groupCode?: string;
  expend?: boolean;
  totalAmount?: number;
  totalAmtwast?: number;
  totalCurrAmt?: number;
  buyerCostingSubDetails?: IBuyerCostingSubDetails[];
}

export class BuyerCostingDetails implements IBuyerCostingDetails {
  constructor(
    public id?: number,
    public groupCode?: string,
    public expend?: boolean,
    public totalAmount?: number,
    public totalAmtwast?: number,
    public totalCurrAmt?: number,
    public buyerCostingSubDetails?: IBuyerCostingSubDetails[]
  ) {}
}

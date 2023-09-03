import { ICostingGroupDetails } from './costing-group-details.model';
import { IUnitOfMeasure } from './unit-of-measure.model';

export interface IBuyerCostingSubDetails {
  itemType?: string;
  description?: string;
  umo?: string;
  avg?: number;
  rate?: number;
  shrinkage?: number;
  processrate?: number;
  amount?: number;
  amtwast?: number;
  curramt?: number;
  costingGroupDetails?: ICostingGroupDetails[];
  unitOfMeasures?: IUnitOfMeasure[];
}

export class BuyerCostingSubDetails implements IBuyerCostingSubDetails {
  constructor(
    public itemType?: string,
    public description?: string,
    public umo?: string,
    public avg?: number,
    public rate?: number,
    public shrinkage?: number,
    public processrate?: number,
    public amount?: number,
    public amtwast?: number,
    public curramt?: number,
    public costingGroupDetails?: ICostingGroupDetails[],
    public unitOfMeasures?: IUnitOfMeasure[]
  ) {}
}

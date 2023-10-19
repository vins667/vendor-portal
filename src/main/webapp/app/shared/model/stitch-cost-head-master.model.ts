import { IStitchCostSubHeadMaster } from "./stitch-cost-sub-head-master.model";
export interface IStitchCostHeadMaster {
  id?: number;
  headName?: string;
  headType?: string;
  totalCtc?: number;
  expend?: boolean;
  totalCost?: number;
  stitchCostSubHeadMaster?: IStitchCostSubHeadMaster[];
}

export class StitchCostHeadMaster implements IStitchCostHeadMaster {
  constructor(
    public id?: number,
    public headName?: string,
    public headType?: string,
    public totalCtc?: number,
    public expend?: boolean,
    public totalCost?: number,
    public stitchCostSubHeadMaster?: IStitchCostSubHeadMaster[]
  ) { }
}

import { IStitchCostSubHeadMaster } from "./stitch-cost-sub-head-master.model";
export interface IStitchCostHeadMaster {
  id?: number;
  headName?: string;
  expend?: boolean;
  stitchCostSubHeadMaster?: IStitchCostSubHeadMaster[];
}

export class StitchCostHeadMaster implements IStitchCostHeadMaster {
  constructor(
    public id?: number,
    public headName?: string,
    public expend?: boolean,
    public stitchCostSubHeadMaster?: IStitchCostSubHeadMaster[]
  ) { }
}

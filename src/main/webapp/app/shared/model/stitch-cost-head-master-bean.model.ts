import { IStitchCostHeadMaster } from "./stitch-cost-head-master.model";
export interface IStitchCostHeadMasterBean {
    factory?: string;
    stitchCostHeadMasters?: IStitchCostHeadMaster[];
}

export class StitchCostHeadMasterBean implements IStitchCostHeadMasterBean {
    constructor(
        public factory?: string,
        public stitchCostHeadMasters?: IStitchCostHeadMaster[]
    ) { }
}

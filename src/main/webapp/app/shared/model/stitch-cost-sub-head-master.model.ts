import { IStitchCostHeadMaster } from "./stitch-cost-head-master.model";
export interface IStitchCostSubHeadMaster {
    id?: number;
    subHeadName?: string;
    companyCost?: number;
    stitchCostHeadMaster?: IStitchCostHeadMaster;
}

export class StitchCostSubHeadMaster implements IStitchCostSubHeadMaster {
    constructor(
        public id?: number,
        public subHeadName?: string,
        public companyCost?: number,
        public stitchCostHeadMaster?: IStitchCostHeadMaster
    ) { }
}

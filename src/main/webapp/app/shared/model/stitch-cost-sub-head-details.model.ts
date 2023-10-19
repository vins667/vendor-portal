import { IStitchCostSubHeadMaster } from "./stitch-cost-sub-head-master.model";

export interface IStitchCostSubHeadDetails {
    id?: number;
    factory?: string;
    companyCost?: number;
    stitchCostSubHeadMaster?: IStitchCostSubHeadMaster;
}

export class StitchCostSubHeadDetails implements IStitchCostSubHeadDetails {
    constructor(
        public id?: number,
        public factory?: string,
        public companyCost?: number,
        public stitchCostSubHeadMaster?: IStitchCostSubHeadMaster
    ) { }
}

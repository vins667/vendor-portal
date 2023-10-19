import { IStitchCostHeadMaster } from "./stitch-cost-head-master.model";
import { IStitchCostSubHeadDetails } from "./stitch-cost-sub-head-details.model";
export interface IStitchCostSubHeadMaster {
    id?: number;
    subHeadName?: string;
    companyCost?: number;
    stitchCostHeadMaster?: IStitchCostHeadMaster;
    stitchCostSubHeadDetails?: IStitchCostSubHeadDetails;
}

export class StitchCostSubHeadMaster implements IStitchCostSubHeadMaster {
    constructor(
        public id?: number,
        public subHeadName?: string,
        public companyCost?: number,
        public stitchCostHeadMaster?: IStitchCostHeadMaster,
        public stitchCostSubHeadDetails?: IStitchCostSubHeadDetails
    ) { }
}

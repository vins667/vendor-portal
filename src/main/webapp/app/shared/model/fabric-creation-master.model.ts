import { IFabricSubstractMaster } from './fabric-substract-master.model';
import { IFabricSubstractDetails } from './fabric-substract-details.model';
import { IFabricSplFinishMaster } from './fabric-spl-finish-master.model';
import { IFabricOthersMaster } from './fabric-others-master.model';
import { IFabricCreationWarpDetails } from './fabric-creation-warp-details.model';
import { IFabricCreationWeftDetails } from './fabric-creation-weft-details.model';
import { IFabricCreationContentDetails } from './fabric-creation-content-details.model';

export interface IFabricCreationMaster {
  id?: number;
  description?: string;
  code?: string;
  epi?: number;
  ppi?: number;
  oth?: string;
  fabricSubstractMaster?: IFabricSubstractMaster;
  fabricSubstractDetails?: IFabricSubstractDetails;
  fabricSplFinishMaster?: IFabricSplFinishMaster;
  fabricOthersMaster?: IFabricOthersMaster;
  fabricCreationWarpDetails?: IFabricCreationWarpDetails[];
  fabricCreationWeftDetails?: IFabricCreationWeftDetails[];
  fabricCreationContentDetails?: IFabricCreationContentDetails[];
}

export class FabricCreationMaster implements IFabricCreationMaster {
  constructor(
    public id?: number,
    public description?: string,
    public epi?: number,
    public ppi?: number,
    public oth?: string,
    public fabricSubstractMaster?: IFabricSubstractMaster,
    public fabricSubstractDetails?: IFabricSubstractDetails,
    public fabricSplFinishMaster?: IFabricSplFinishMaster,
    public fabricOthersMaster?: IFabricOthersMaster,
    public fabricCreationWarpDetails?: IFabricCreationWarpDetails[],
    public fabricCreationWeftDetails?: IFabricCreationWeftDetails[],
    public fabricCreationContentDetails?: IFabricCreationContentDetails[]
  ) {}
}

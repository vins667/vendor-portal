import { IFabricUomMaster } from 'app/shared/model//fabric-uom-master.model';

export interface IFabricCreationWarpDetails {
  id?: number;
  warp1?: string;
  warp2?: string;
  fabricUomMaster?: IFabricUomMaster;
}

export class FabricCreationWarpDetails implements IFabricCreationWarpDetails {
  constructor(public id?: number, public warp1?: string, public warp2?: string, public fabricUomMaster?: IFabricUomMaster) {}
}

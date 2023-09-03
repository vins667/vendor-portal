import { IFabricUomMaster } from 'app/shared/model//fabric-uom-master.model';

export interface IFabricCreationWeftDetails {
  id?: number;
  weft1?: string;
  weft2?: string;
  fabricUomMaster?: IFabricUomMaster;
}

export class FabricCreationWeftDetails implements IFabricCreationWeftDetails {
  constructor(public id?: number, public weft1?: string, public weft2?: string, public fabricUomMaster?: IFabricUomMaster) {}
}

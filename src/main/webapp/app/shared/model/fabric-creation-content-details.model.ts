import { IFabricContentMaster } from 'app/shared/model//fabric-content-master.model';

export interface IFabricCreationContentDetails {
  id?: number;
  percentage?: number;
  fabricContentMaster?: IFabricContentMaster;
}

export class FabricCreationContentDetails implements IFabricCreationContentDetails {
  constructor(public id?: number, public percentage?: number, public fabricContentMaster?: IFabricContentMaster) {}
}

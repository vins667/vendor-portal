import { IVcutFactoryAccessId } from 'app/shared/model/vcut-factory-access-id.model';

export interface IVcutFactoryAccess {
  id?: IVcutFactoryAccessId;
  appType?: string;
}

export class VcutFactoryAccess implements IVcutFactoryAccess {
  constructor(public id?: IVcutFactoryAccessId, public appType?: string) {}
}

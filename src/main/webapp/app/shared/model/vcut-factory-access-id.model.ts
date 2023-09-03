export interface IVcutFactoryAccessId {
  cardNo?: string;
  factoryCode?: string;
}
export class VcutFactoryAccessId implements IVcutFactoryAccessId {
  constructor(public cardNo?: string, public factoryCode?: string) {}
}

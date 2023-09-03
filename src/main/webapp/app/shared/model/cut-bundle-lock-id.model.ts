export interface ICutBundleLockId {
  productionCode?: string;
  plantCode?: string;
}
export class CutBundleLockId implements ICutBundleLockId {
  constructor(public productionCode?: string, public plantCode?: string) {}
}

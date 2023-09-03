import { ICutBundleLockId } from 'app/shared/model/cut-bundle-lock-id.model';

export interface ICutBundleLock {
  id?: ICutBundleLockId;
  lockedDate?: any;
  lockedBy?: string;
}
export class CutBundleLock implements ICutBundleLock {
  constructor(public id?: ICutBundleLockId, public lockedDate?: any, lockedBy?: string) {}
}

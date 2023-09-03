import { Moment } from 'moment';

export interface IAssetAuditRunTimes {
  runTime?: Moment;
}

export class AssetAuditRunTimes implements IAssetAuditRunTimes {
  constructor(public runTime?: Moment) {}
}

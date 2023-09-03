export interface IIgnoreSoftwareSearchMaster {
  swName?: string;
  swPublisher?: string;
  size?: number;
  pageNo?: number;
}

export class IgnoreSoftwareSearchMaster implements IIgnoreSoftwareSearchMaster {
  constructor(public swName?: string, public swPublisher?: string, public size?: number, public pageNo?: number) {}
}

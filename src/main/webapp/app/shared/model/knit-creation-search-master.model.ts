export interface IKnitCreationSearchMaster {
  code?: string;
  name?: string;
  size?: number;
  pageNo?: number;
}

export class KnitCreationSearchMaster implements IKnitCreationSearchMaster {
  constructor(public code?: string, public name?: string, public size?: number, public pageNo?: number) {}
}

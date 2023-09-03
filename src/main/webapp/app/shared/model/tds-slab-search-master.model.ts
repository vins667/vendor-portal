export interface ITdsSlabSearchMaster {
  finYear?: string;
  gender?: string;
}

export class TdsSlabSearchMaster implements ITdsSlabSearchMaster {
  constructor(public finYear?: string, public gender?: string) {}
}

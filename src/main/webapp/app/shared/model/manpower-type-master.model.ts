export interface IManpowerTypeMaster {
  code?: string;
  description?: string;
}

export class ManpowerTypeMaster implements IManpowerTypeMaster {
  constructor(public code?: string, public description?: string) {}
}

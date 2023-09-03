export interface IGlmasterId {
  companycode?: string;
  code?: string;
}

export class GlmasterId implements IGlmasterId {
  constructor(public companycode?: string, public code?: string) {}
}

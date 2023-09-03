export interface IMasterBean {
  code?: string;
  name?: string;
}

export class MasterBean implements IMasterBean {
  constructor(public code?: string, public desc?: string) {}
}

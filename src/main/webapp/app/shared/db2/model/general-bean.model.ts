export interface IGeneralBean {
  code?: string;
  description?: string;
}

export class GeneralBean implements IGeneralBean {
  constructor(public code?: string, public description?: string) {}
}

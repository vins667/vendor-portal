export interface ISoftwareKeyDetailsBean {
  id?: number;
  assetCode?: string;
  name?: string;
  jhiKey?: string;
}

export class SoftwareKeyDetailsBean implements ISoftwareKeyDetailsBean {
  constructor(public id?: number, public assetCode?: string, public name?: string, public jhiKey?: string) {}
}

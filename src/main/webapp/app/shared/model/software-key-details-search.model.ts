export interface ISoftwareKeyDetailsSearch {
  name?: string;
  jhiKey?: string;
  size?: number;
  pageNo?: number;
}

export class SoftwareKeyDetailsSearch implements ISoftwareKeyDetailsSearch {
  constructor(public name?: string, public jhiKey?: string, public size?: number, public pageNo?: number) {}
}

export interface ITdsDetail {
  tdsCode?: string;
  tdsiTexCode?: string;
  value?: number;
}

export class TdsDetail implements ITdsDetail {
  constructor(public tdsCode?: string, public tdsiTexCode?: string, public value?: number) {}
}

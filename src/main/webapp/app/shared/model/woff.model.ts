export interface IWoff {
  wCode?: number;
  desc1?: string;
}

export class Woff implements IWoff {
  constructor(public wCode?: number, public desc1?: string) {}
}

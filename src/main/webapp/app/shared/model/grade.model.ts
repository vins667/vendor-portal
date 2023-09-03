export interface IGrade {
  gCode?: number;
  desc1?: string;
}
export class Grade implements IGrade {
  constructor(public gCode?: number, public desc1?: string) {}
}

export interface ISection {
  secCode?: number;
  desc1?: string;
}
export class Section implements ISection {
  constructor(public secCode?: number, public desc1?: string) {}
}

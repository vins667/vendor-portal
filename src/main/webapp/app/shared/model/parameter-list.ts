export interface IParameterList {
  parameter1?: number;
  parameter2?: string;
  parameter3?: string;
  parameter4?: string;
  parameter5?: string;
  parameter6?: string;
  parameter7?: string;
  parameter8?: string;
}

export class ParameterList implements IParameterList {
  constructor(
    public parameter1?: number,
    public parameter2?: string,
    public parameter3?: string,
    public parameter4?: string,
    public parameter5?: string,
    public parameter6?: string,
    public parameter7?: string,
    public parameter8?: string
  ) {}
}

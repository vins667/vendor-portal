export interface IMasterParameters {
  parastring01?: string;
  parastring02?: string;
  parastring03?: string;
  parastring04?: string;
  parastring05?: string;
  parastring06?: string;
  parastring07?: string;
  parastring08?: string;
  parastring09?: string;
  parastring10?: string;
  paraint01?: number;
}

export class MasterParameters implements IMasterParameters {
  constructor(
    public parastring01?: string,
    public parastring02?: string,
    public parastring03?: string,
    public parastring04?: string,
    public parastring05?: string,
    public parastring06?: string,
    public parastring07?: string,
    public parastring08?: string,
    public parastring09?: string,
    public parastring10?: string,
    public paraint01?: number
  ) {}
}

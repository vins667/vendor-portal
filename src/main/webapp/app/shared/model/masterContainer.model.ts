export interface IMasterContainer {
  paraCode1?: string;
  paraCode2?: string;
  paraCode3?: string;
  paraCode4?: string;
  paraCode5?: string;
  paraQty1?: number;
  paraQty2?: number;
  paraQty3?: number;
  paraQty4?: number;
  paraQty5?: number;
}

export class MasterContainer implements IMasterContainer {
  constructor(
    public paraCode1?: string,
    public paraCode2?: string,
    public paraCode3?: string,
    public paraCode4?: string,
    public paraCode5?: string,
    public paraQty1?: number,
    public paraQty2?: number,
    public paraQty3?: number,
    public paraQty4?: number,
    public paraQty5?: number
  ) {}
}

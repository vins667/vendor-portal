export interface IShift {
  sftCode?: number;
  sftUcode?: string;
  intime?: string;
  outtime?: string;
  grace?: number;
  br1?: string;
  end1?: string;
  br2?: string;
  end2?: string;
  br3?: string;
  end3?: string;
  ostart?: number;
  bstart?: number;
  hfday?: number;
  fulday?: number;
  hfAfterHrs?: number;
  otRoundMin?: number;
  ntAfterDed?: number;
  minDeduct?: number;
  lunch?: number;
  foodAfterMin?: number;
  sftMisBefMin?: number;
  sftMisAftMin?: number;
  foodAllow?: number;
}
export class Shift implements IShift {
  constructor(
    public sftCode?: number,
    public sftUcode?: string,
    public intime?: string,
    public outtime?: string,
    public grace?: number,
    public br1?: string,
    public end1?: string,
    public br2?: string,
    public end2?: string,
    public br3?: string,
    public end3?: string,
    public ostart?: number,
    public bstart?: number,
    public hfday?: number,
    public fulday?: number,
    public hfAfterHrs?: number,
    public otRoundMin?: number,
    public ntAfterDed?: number,
    public minDeduct?: number,
    public lunch?: number,
    public foodAfterMin?: number,
    public sftMisBefMin?: number,
    public sftMisAftMin?: number,
    public foodAllow?: number
  ) {}
}

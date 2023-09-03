import { IMonthlyId } from 'app/shared/model/monthly-id.model';

export interface IMonthly {
  id?: IMonthlyId;
  basic?: number;
  hra?: number;
  da?: number;
  ca?: number;
  oa?: number;
  emplEpf?: number;
  emplFpf?: number;
  emprEpf?: number;
  emprFpf?: number;
  emplEsi?: number;
  emprEsi?: number;
  loan?: number;
  totSal?: number;
  totDed?: number;
  netSal?: number;
  basic1?: number;
  basic2?: number;
  dayNo?: number;
  othDed?: number;
  freze?: number;
  bCl?: number;
  bEl?: number;
  bPl?: number;
  bMl?: number;
  bOl?: number;
  bSl?: number;
  oBada?: number;
  oHra?: number;
  oCa?: number;
  oOa?: number;
  tds?: number;
  arrAmt?: number;
  incentive?: number;
  arrAmta?: number;
  incentivea?: number;
  sno?: number;
  esicut?: number;
  otamt?: number;
  arrDay?: number;
  arrmDay?: number;
  pfcut?: number;
  fpfcut?: number;
  basic3?: number;
  adsal?: number;
  otBasic?: number;
  otHr?: number;
  otAmt?: number;
  otEsi1?: number;
  otEsi2?: number;
  elAmt?: number;
  clAmt?: number;
  othDed1?: number;
  ptotSal?: number;
  loanbal?: number;
  oDa?: number;
  oEx?: number;
  ex?: number;
  incP?: number;
  incAm?: number;
  all1?: string;
  all2?: string;
  all3?: string;
  all4?: string;
  all5?: string;
  all6?: string;
  all7?: string;
  all8?: string;
  all9?: string;
  all10?: string;
  all11?: string;
  all12?: string;
  rat1?: number;
  rat2?: number;
  rat3?: number;
  rat4?: number;
  rat5?: number;
  rat6?: number;
  rat7?: number;
  rat8?: number;
  rat9?: number;
  rat10?: number;
  rat11?: number;
  rat12?: number;
  earn1?: number;
  earn2?: number;
  earn3?: number;
  earn4?: number;
  earn5?: number;
  earn6?: number;
  earn7?: number;
  earn8?: number;
  earn9?: number;
  earn10?: number;
  earn11?: number;
  earn12?: number;
  arr1?: number;
  arr2?: number;
  arr3?: number;
  arr4?: number;
  arr5?: number;
  arr6?: number;
  arr7?: number;
  arr8?: number;
  arr9?: number;
  arr10?: number;
  arr11?: number;
  arr12?: number;
  dall1?: string;
  dall2?: string;
  dall3?: string;
  dall4?: string;
  dall5?: string;
  dall6?: string;
  dall7?: string;
  dall8?: string;
  ded1?: number;
  ded2?: number;
  ded3?: number;
  ded4?: number;
  ded5?: number;
  ded6?: number;
  ded7?: number;
  ded8?: number;
  rem1?: number;
  rem2?: number;
  rem3?: number;
  rem4?: number;
  rem5?: number;
  iotAmt?: number;
  iotEsi1?: number;
  iotEsi2?: number;
  bus?: number;
  otHra?: number;
  otAmta?: number;
  loanAdjust?: number;
  dedAdj?: number;
  hall1?: string;
  hall2?: string;
  hall3?: string;
  hall4?: string;
  hall5?: string;
  hall6?: string;
  hall7?: string;
  hall8?: string;
  hall9?: string;
  hall10?: string;
  hdall1?: string;
  hdall2?: string;
  hdall3?: string;
  hdall4?: string;
  hdall5?: string;
  hdall6?: string;
  hdall7?: string;
  hdall8?: string;
  hdall9?: string;
  grs15Day?: number;
  grs30Day?: number;
  pf15Day?: number;
  pf30Day?: number;
  esi15Day?: number;
  esi30Day?: number;
  ot1ArrHrs?: number;
  ot1ArrAmt?: number;
  ot2ArrHrs?: number;
  ot2ArrAmt?: number;
  ot3ArrHrs?: number;
  ot3ArrAmt?: number;
  sunHrs?: number;
  sunAmt?: number;
  hr2?: number;
  amt2?: number;
  hr2Bal?: number;
  amt2Bal?: number;
  esiHr2?: number;
  otHr4462HrArr?: number;
  otHr4462HrArrAmt?: number;
  dedExport1?: number;
  dedExport2?: number;
  dedExport3?: number;
  dedExport4?: number;
  dedExport5?: number;
  dedExport6?: number;
  dedExport7?: number;
  dedExport8?: number;
  dedExport9?: number;
  dedExport10?: number;
  dedExport11?: number;
  dedExport12?: number;
  dedExport13?: number;
  dedExport14?: number;
  dedExport15?: number;
  dedExport16?: number;
  dedExport17?: number;
  dedExport18?: number;
  dedExport19?: number;
  dedExport20?: number;
  gwr1Esi1?: number;
  gwr2Esi1?: number;
}

export class Monthly implements IMonthly {
  constructor(
    public id?: IMonthlyId,
    public basic?: number,
    public hra?: number,
    public da?: number,
    public ca?: number,
    public oa?: number,
    public emplEpf?: number,
    public emplFpf?: number,
    public emprEpf?: number,
    public emprFpf?: number,
    public emplEsi?: number,
    public emprEsi?: number,
    public loan?: number,
    public totSal?: number,
    public totDed?: number,
    public netSal?: number,
    public basic1?: number,
    public basic2?: number,
    public dayNo?: number,
    public othDed?: number,
    public freze?: number,
    public bCl?: number,
    public bEl?: number,
    public bPl?: number,
    public bMl?: number,
    public bOl?: number,
    public bSl?: number,
    public oBada?: number,
    public oHra?: number,
    public oCa?: number,
    public oOa?: number,
    public tds?: number,
    public arrAmt?: number,
    public incentive?: number,
    public arrAmta?: number,
    public incentivea?: number,
    public sno?: number,
    public esicut?: number,
    public otamt?: number,
    public arrDay?: number,
    public arrmDay?: number,
    public pfcut?: number,
    public fpfcut?: number,
    public basic3?: number,
    public adsal?: number,
    public otBasic?: number,
    public otHr?: number,
    public otAmt?: number,
    public otEsi1?: number,
    public otEsi2?: number,
    public elAmt?: number,
    public clAmt?: number,
    public othDed1?: number,
    public ptotSal?: number,
    public loanbal?: number,
    public oDa?: number,
    public oEx?: number,
    public ex?: number,
    public incP?: number,
    public incAm?: number,
    public all1?: string,
    public all2?: string,
    public all3?: string,
    public all4?: string,
    public all5?: string,
    public all6?: string,
    public all7?: string,
    public all8?: string,
    public all9?: string,
    public all10?: string,
    public all11?: string,
    public all12?: string,
    public rat1?: number,
    public rat2?: number,
    public rat3?: number,
    public rat4?: number,
    public rat5?: number,
    public rat6?: number,
    public rat7?: number,
    public rat8?: number,
    public rat9?: number,
    public rat10?: number,
    public rat11?: number,
    public rat12?: number,
    public earn1?: number,
    public earn2?: number,
    public earn3?: number,
    public earn4?: number,
    public earn5?: number,
    public earn6?: number,
    public earn7?: number,
    public earn8?: number,
    public earn9?: number,
    public earn10?: number,
    public earn11?: number,
    public earn12?: number,
    public arr1?: number,
    public arr2?: number,
    public arr3?: number,
    public arr4?: number,
    public arr5?: number,
    public arr6?: number,
    public arr7?: number,
    public arr8?: number,
    public arr9?: number,
    public arr10?: number,
    public arr11?: number,
    public arr12?: number,
    public dall1?: string,
    public dall2?: string,
    public dall3?: string,
    public dall4?: string,
    public dall5?: string,
    public dall6?: string,
    public dall7?: string,
    public dall8?: string,
    public ded1?: number,
    public ded2?: number,
    public ded3?: number,
    public ded4?: number,
    public ded5?: number,
    public ded6?: number,
    public ded7?: number,
    public ded8?: number,
    public rem1?: number,
    public rem2?: number,
    public rem3?: number,
    public rem4?: number,
    public rem5?: number,
    public iotAmt?: number,
    public iotEsi1?: number,
    public iotEsi2?: number,
    public bus?: number,
    public otHra?: number,
    public otAmta?: number,
    public loanAdjust?: number,
    public dedAdj?: number,
    public hall1?: string,
    public hall2?: string,
    public hall3?: string,
    public hall4?: string,
    public hall5?: string,
    public hall6?: string,
    public hall7?: string,
    public hall8?: string,
    public hall9?: string,
    public hall10?: string,
    public hdall1?: string,
    public hdall2?: string,
    public hdall3?: string,
    public hdall4?: string,
    public hdall5?: string,
    public hdall6?: string,
    public hdall7?: string,
    public hdall8?: string,
    public hdall9?: string,
    public grs15Day?: number,
    public grs30Day?: number,
    public pf15Day?: number,
    public pf30Day?: number,
    public esi15Day?: number,
    public esi30Day?: number,
    public ot1ArrHrs?: number,
    public ot1ArrAmt?: number,
    public ot2ArrHrs?: number,
    public ot2ArrAmt?: number,
    public ot3ArrHrs?: number,
    public ot3ArrAmt?: number,
    public sunHrs?: number,
    public sunAmt?: number,
    public hr2?: number,
    public amt2?: number,
    public hr2Bal?: number,
    public amt2Bal?: number,
    public esiHr2?: number,
    public otHr4462HrArr?: number,
    public otHr4462HrArrAmt?: number,
    public dedExport1?: number,
    public dedExport2?: number,
    public dedExport3?: number,
    public dedExport4?: number,
    public dedExport5?: number,
    public dedExport6?: number,
    public dedExport7?: number,
    public dedExport8?: number,
    public dedExport9?: number,
    public dedExport10?: number,
    public dedExport11?: number,
    public dedExport12?: number,
    public dedExport13?: number,
    public dedExport14?: number,
    public dedExport15?: number,
    public dedExport16?: number,
    public dedExport17?: number,
    public dedExport18?: number,
    public dedExport19?: number,
    public dedExport20?: number,
    public gwr1Esi1?: number,
    public gwr2Esi1?: number
  ) {}
}

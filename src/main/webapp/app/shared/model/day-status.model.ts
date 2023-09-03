export interface IDayStatus {
  inTm?: string;
  outTm?: string;
}
export class DayStatus implements IDayStatus {
  constructor(public inTm?: string, public outTm?: string) {}
}

export interface IVcutFactoryLineBreakup {
  id?: number;
  hourBreakup?: string;
  displayFlag?: string;
  hourPlan?: number;
  hourActual?: number;
  cumPlan?: number;
  cumActual?: number;
  varianceHour?: number;
  varianceCum?: number;
  efficiencyPlan?: number;
  efficiencyActual?: number;
  fttRatePlan?: number;
  fttRateActual?: number;
  activeHour?: string;
  dhuRatePlan?: number;
  dhuRateActual?: number;
}

export class VcutFactoryLineBreakup implements IVcutFactoryLineBreakup {
  constructor(
    public id?: number,
    public hourBreakup?: string,
    public displayFlag?: string,
    public hourPlan?: number,
    public hourActual?: number,
    public cumPlan?: number,
    public cumActual?: number,
    public varianceHour?: number,
    public varianceCum?: number,
    public efficiencyPlan?: number,
    public efficiencyActual?: number,
    public fttRatePlan?: number,
    public fttRateActual?: number,
    public activeHour?: string,
    public dhuRatePlan?: number,
    public dhuRateActual?: number
  ) {}
}

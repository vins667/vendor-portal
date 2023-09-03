export interface IMonthlyId {
  empCode?: string;
  monthNo?: number;
  monthYear?: string;
}

export class MonthlyId implements IMonthlyId {
  constructor(public empCode?: string, public monthNo?: number, public monthYear?: string) {}
}

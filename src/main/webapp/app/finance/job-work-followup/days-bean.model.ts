export interface IDaysBean {
  day?: number;
  date?: string;
  selectDay?: boolean;
}

export class DaysBean implements IDaysBean {
  constructor(public day?: number, public date?: string, public selectDay?: boolean) {}
}

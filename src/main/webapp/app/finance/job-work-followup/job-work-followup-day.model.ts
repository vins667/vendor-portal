export interface IDay {
  number?: number;
  year?: number;

  month?: string;
  monthIndex?: number;

  weekDayName?: string;
  weekDayNumber?: number;
}
export class Day implements IDay {
  public number?: number;
  public year?: number;
  public month?: string;
  public monthIndex?: number;
  public weekDayName?: string;
  public weekDayNumber?: number;
}

export interface ISalaryBean {
  empCode?: string;
  monthNo?: number;
  monthYear?: string;
  displayMonthYear?: string;
  dayNo?: number;
  totSal?: number;
  totDed?: number;
  netSal?: number;
  arrAmt?: number;
}
export class SalaryBean implements ISalaryBean {
  constructor(
    public empCode?: string,
    public monthNo?: number,
    public monthYear?: string,
    public displayMonthYear?: string,
    public dayNo?: number,
    public totSal?: number,
    public totDed?: number,
    public netSal?: number,
    public arrAmt?: number
  ) {}
}

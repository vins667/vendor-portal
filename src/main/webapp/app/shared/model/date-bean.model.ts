export interface IDateBean {
  date?: any;
}

export class DateBean implements IDateBean {
  constructor(public date?: any) {}
}

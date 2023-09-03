export interface IPreviousEmploymentDetailBean {
  id?: number;
  employerName?: string;
  amount?: number;
}

export class PreviousEmploymentDetailBean implements IPreviousEmploymentDetailBean {
  constructor(public id?: number, public employerName?: string, public amount?: number) {}
}

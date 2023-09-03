export interface ILeavePendingReport {
  status?: string;
  flag?: string;
}

export class LeavePendingReport implements ILeavePendingReport {
  constructor(public status?: string, public flag?: string) {}
}

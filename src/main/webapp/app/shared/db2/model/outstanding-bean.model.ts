export interface IOutstandingBean {
  supplierCode?: string;
  amount?: number;
}

export class OutstandingBean implements IOutstandingBean {
  constructor(public supplierCode?: string, public amount?: number) {}
}

export interface IChequeBean {
  businessunitcode?: string;
  financialyearcode?: string;
  code?: string;
}
export class ChequeBean implements IChequeBean {
  constructor(public businessunitcode?: string, public financialyearcode?: string, public code?: string) {}
}

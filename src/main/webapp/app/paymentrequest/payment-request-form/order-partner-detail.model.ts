export interface IOrderPartnerDetail {
  msmeNo?: string;
  gstr1?: string;
  gstr3B?: string;
}
export class orderPartnerDetail implements IOrderPartnerDetail {
  constructor(public msmeNo?: string, public gstr1?: string, public gstr3B?: string) {}
}

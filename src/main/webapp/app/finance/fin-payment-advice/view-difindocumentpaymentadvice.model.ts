import { IViewDifindocumentpaymentadviceId } from './view-difindocumentpaymentadvice-id.model';

export interface IViewDifindocumentpaymentadvice {
  id?: IViewDifindocumentpaymentadviceId;
  postingdate?: any;
  proposalrefno?: string;
  glcode?: string;
  gldescription?: string;
  customersuppliercode?: string;
  customersuppliername?: string;
  gstinnumber?: string;
  amountincc?: number;
  chequenumber?: string;
  utrnumber?: string;
  utrdate?: any;
  emailaddress?: string;
  advicesent?: boolean;
  flag?: boolean;
}

export class ViewDifindocumentpaymentadvice implements IViewDifindocumentpaymentadvice {
  constructor(
    public id?: IViewDifindocumentpaymentadviceId,
    public postingdate?: any,
    public proposalrefno?: string,
    public glcode?: string,
    public gldescription?: string,
    public customersuppliercode?: string,
    public customersuppliername?: string,
    public gstinnumber?: string,
    public amountincc?: number,
    public chequenumber?: string,
    public utrnumber?: string,
    public utrdate?: any,
    public emailaddress?: string,
    public advicesent?: boolean,
    public flag?: boolean
  ) {}
}

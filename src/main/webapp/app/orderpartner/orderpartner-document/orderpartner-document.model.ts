import { IOrderpartnerbank } from 'app/shared/db2/model/orderpartnerbank.model';
import { IOrderpartnerUpload } from 'app/orderpartner/orderpartner-document/orderpartner-upload.model';

export interface IOrderpartnerDocument {
  customersuppliercompanycode?: string;
  customersuppliertype?: string;
  customersuppliercode?: string;
  legalname1?: string;
  commissionerate?: string;
  eccno?: string;
  eccnoAllow?: boolean;
  gstinnumber?: string;
  glcode?: string;
  glname?: string;
  gst3b?: string;
  gst1?: string;
  gst2a?: string;
  gst2aRemark?: string;
  email?: string;
  emailAllow?: boolean;
  phone?: string;
  phoneAllow?: boolean;
  orderpartnerbanks?: IOrderpartnerbank[];
  orderpartnerUploads?: IOrderpartnerUpload[];
}

export class OrderpartnerDocument implements IOrderpartnerDocument {
  constructor(
    public customersuppliercompanycode?: string,
    public customersuppliertype?: string,
    public customersuppliercode?: string,
    public legalname1?: string,
    public commissionerate?: string,
    public eccno?: string,
    public eccnoAllow?: boolean,
    public gstinnumber?: string,
    public glcode?: string,
    public glname?: string,
    public gst3b?: string,
    public gst1?: string,
    public gst2a?: string,
    public gst2aRemark?: string,
    public email?: string,
    public emailAllow?: boolean,
    public phone?: string,
    public phoneAllow?: boolean,
    public orderpartnerbanks?: IOrderpartnerbank[],
    public orderpartnerUploads?: IOrderpartnerUpload[]
  ) {}
}

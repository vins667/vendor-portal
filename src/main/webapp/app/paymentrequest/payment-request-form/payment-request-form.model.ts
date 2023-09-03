import { IPaymentRequestFormDetails } from 'app/paymentrequest/payment-request-form/payment-request-form-details.model';
import { IMasterSearch } from 'app/shared/model/master-search.model';
import { IPaymentRequestInvoice } from 'app/paymentrequest/payment-request-form/payment-request-invoice.model';

export interface IPaymentRequestForm {
  id?: number;
  company?: string;
  division?: string;
  businessunitcode?: string;
  paymentType?: string;
  requestNo?: string;
  requestDate?: any;
  supplierType?: string;
  supplierCode?: string;
  supplierName?: string;
  supplierGstName?: string;
  gstin?: string;
  gstrBFilling?: string;
  gstrFillingMonth?: string;
  gstr2a?: string;
  countrycode?: string;
  currencycode?: string;
  msmeNo?: string;
  requestType?: string;
  poNo?: string;
  poDate?: any;
  piNo?: string;
  piDate?: any;
  invoiceNo?: string;
  invoiceDate?: any;
  paymenttermcode?: string;
  paymenttermdesc?: string;
  conversionRate?: number;
  piAmount?: number;
  outstandingAmount?: number;
  paymentRelease?: number;
  poBasic?: number;
  poGst?: number;
  totalPoValue?: number;
  piBasic?: number;
  piGstPerc?: number;
  piGst?: number;
  totalPiValue?: number;
  freightRequired?: string;
  freightValue?: number;
  requestAmount?: number;
  requestGst?: number;
  tdsType?: string;
  tdsValue?: number;
  totalReleaseAmount?: number;
  chequeNo?: string;
  utrNo?: string;
  utrDate?: any;
  findocbusinessunitcode?: string;
  findocfinancialyearcode?: string;
  findoccode?: string;
  forwardCode?: string;
  forwardName?: string;
  poFile?: string;
  piFile?: string;
  remarks?: string;
  status?: string;
  createdBy?: string;
  createdDate?: any;
  approvedBy?: string;
  approvedDate?: any;
  forwards?: IMasterSearch[];
  invoices?: IPaymentRequestInvoice[];
  paymentRequestFormDetails?: IPaymentRequestFormDetails[];
  paymentRequestFormDetail?: IPaymentRequestFormDetails;
}

export class PaymentRequestForm implements IPaymentRequestForm {
  constructor(
    public id?: number,
    public company?: string,
    public division?: string,
    public businessunitcode?: string,
    public paymentType?: string,
    public requestNo?: string,
    public requestDate?: any,
    public supplierType?: string,
    public supplierCode?: string,
    public supplierName?: string,
    public supplierGstName?: string,
    public gstin?: string,
    public gstrBFilling?: string,
    public gstrFillingMonth?: string,
    public gstr2a?: string,
    public countrycode?: string,
    public currencycode?: string,
    public msmeNo?: string,
    public requestType?: string,
    public poNo?: string,
    public poDate?: any,
    public piNo?: string,
    public piDate?: any,
    public invoiceNo?: string,
    public invoiceDate?: any,
    public paymenttermcode?: string,
    public paymenttermdesc?: string,
    public conversionRate?: number,
    public piAmount?: number,
    public outstandingAmount?: number,
    public paymentRelease?: number,
    public poBasic?: number,
    public poGst?: number,
    public totalPoValue?: number,
    public piBasic?: number,
    public piGstPerc?: number,
    public piGst?: number,
    public totalPiValue?: number,
    public freightRequired?: string,
    public freightValue?: number,
    public requestAmount?: number,
    public requestGst?: number,
    public tdsType?: string,
    public tdsValue?: number,
    public totalReleaseAmount?: number,
    public chequeNo?: string,
    public utrNo?: string,
    public utrDate?: any,
    public findocbusinessunitcode?: string,
    public findocfinancialyearcode?: string,
    public findoccode?: string,
    public forwardCode?: string,
    public forwardName?: string,
    public poFile?: string,
    public piFile?: string,
    public remarks?: string,
    public status?: string,
    public createdBy?: string,
    public createdDate?: any,
    public approvedBy?: string,
    public approvedDate?: any,
    public forwards?: IMasterSearch[],
    public invoices?: IPaymentRequestInvoice[],
    public paymentRequestFormDetails?: IPaymentRequestFormDetails[],
    public paymentRequestFormDetail?: IPaymentRequestFormDetails
  ) {}
}

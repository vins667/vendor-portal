import { IFindocumentId } from './findocument-id.model';
import { IFindocumentline } from 'app/shared/db2/model/findocumentline.model';
import { IViewfindocumentId } from 'app/shared/db2/model/viewfindocument-id.model';

export interface IViewfindocument {
  id?: IViewfindocumentId;
  findocstatisticalgroupcode?: string;
  documentdate?: any;
  glcode?: string;
  documenttypecode?: string;
  documenttypedescription?: string;
  postingdate?: any;
  commercialinvoicecode?: string;
  commercialinvoicedivisioncode?: string;
  plantinvoicedivisioncode?: string;
  plantinvoicecode?: string;
  poadvancepurordercountercode?: string;
  poadvancepurchaseordercode?: string;
  poadvancelineno?: number;
  mrndivisioncode?: string;
  mrncode?: number;
  referencetext1?: string;
  headerremark?: string;
  headerreport?: string;
  mrnmrnprefixcode?: string;
  purchaseinvoicedivisioncode?: string;
  purchaseinvoicecode?: string;
  purchaseinvoiceinvoicedate?: any;
  duedate?: any;
  absuniqueid?: number;
  chequedate?: any;
  chequenumber?: string;
  directinvoicedivisioncode?: string;
  directinvoicecountercode?: string;
  directinvoicecode?: string;
  sdcreditprovisionalcountercode?: string;
  sdcreditprovisionalcode?: string;
  employeecode?: string;
  expenseinvoicedivisioncode?: string;
  expenseinvoicecode?: string;
  expenseinvoiceinvoicedate?: any;
  slcustomersuppliertype?: string;
  slcustomersuppliercode?: string;
  amountincc?: number;
  amountindc?: number;
  comments?: string;
  profitcentercode?: string;
  costcentercode?: string;
  companycurrencycode?: string;
  documentcurrencycode?: string;
  reconciletranno?: string;
  firstusergenericgrouptypecode?: string;
  firstcode?: string;
  secusergenericgrouptypecode?: string;
  seccode?: string;
  thirdusergenericgrouptypecode?: string;
  thirdcode?: string;
  frtusergenericgrouptypecode?: string;
  frtcode?: string;
  fifthusergenericgrouptypecode?: string;
  fifthcode?: string;
  sixthusergenericgrouptypecode?: string;
  sixthcode?: string;
  svnusergenericgrouptypecode?: string;
  svncode?: string;
  assetnocountercode?: string;
  assetnocode?: string;
  fdlreferencetext1?: string;
  gldescription?: string;
  bsplflag?: string;
  faclassificationtype?: string;
  legalname1?: string;
  businessunitdescription?: string;
  reffindoccode?: string;
  narration?: string;
}

export class Viewfindocument implements IViewfindocument {
  constructor(
    public id?: IViewfindocumentId,
    public findocstatisticalgroupcode?: string,
    public documentdate?: any,
    public glcode?: string,
    public documenttypecode?: string,
    public documenttypedescription?: string,
    public postingdate?: any,
    public commercialinvoicecode?: string,
    public commercialinvoicedivisioncode?: string,
    public plantinvoicedivisioncode?: string,
    public plantinvoicecode?: string,
    public poadvancepurordercountercode?: string,
    public poadvancepurchaseordercode?: string,
    public poadvancelineno?: number,
    public mrndivisioncode?: string,
    public mrncode?: number,
    public referencetext1?: string,
    public headerremark?: string,
    public headerreport?: string,
    public mrnmrnprefixcode?: string,
    public purchaseinvoicedivisioncode?: string,
    public purchaseinvoicecode?: string,
    public purchaseinvoiceinvoicedate?: any,
    public duedate?: any,
    public absuniqueid?: number,
    public chequedate?: any,
    public chequenumber?: string,
    public directinvoicedivisioncode?: string,
    public directinvoicecountercode?: string,
    public directinvoicecode?: string,
    public sdcreditprovisionalcountercode?: string,
    public sdcreditprovisionalcode?: string,
    public employeecode?: string,
    public expenseinvoicedivisioncode?: string,
    public expenseinvoicecode?: string,
    public expenseinvoiceinvoicedate?: any,
    public slcustomersuppliertype?: string,
    public slcustomersuppliercode?: string,
    public amountincc?: number,
    public amountindc?: number,
    public comments?: string,
    public profitcentercode?: string,
    public costcentercode?: string,
    public companycurrencycode?: string,
    public documentcurrencycode?: string,
    public reconciletranno?: string,
    public firstusergenericgrouptypecode?: string,
    public firstcode?: string,
    public secusergenericgrouptypecode?: string,
    public seccode?: string,
    public thirdusergenericgrouptypecode?: string,
    public thirdcode?: string,
    public frtusergenericgrouptypecode?: string,
    public frtcode?: string,
    public fifthusergenericgrouptypecode?: string,
    public fifthcode?: string,
    public sixthusergenericgrouptypecode?: string,
    public sixthcode?: string,
    public svnusergenericgrouptypecode?: string,
    public svncode?: string,
    public assetnocountercode?: string,
    public assetnocode?: string,
    public fdlreferencetext1?: string,
    public gldescription?: string,
    public bsplflag?: string,
    public faclassificationtype?: string,
    public legalname1?: string,
    public businessunitdescription?: string,
    public reffindoccode?: string,
    public narration?: string
  ) {}
}

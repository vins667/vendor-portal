import { IGeneralBean } from './general-bean.model';
import { IProductionOrderBreakup } from './production-order-breakup.model';
import { IProductionreservationBean } from './productionreservation-bean.model';
import { IQualitylevel } from './qualitylevel.model';
import { IProductiondemandBean } from './productiondemand-bean.model';
import { Moment } from 'moment';

export interface IProductionOrderBean {
  companycode?: string;
  countercode?: string;
  productionordercode?: string;
  projectcode?: string;
  product?: string;
  subcode01?: string;
  subcode02?: string;
  subcode03?: string;
  subcode04?: string;
  subcode05?: string;
  subcode06?: string;
  subcode07?: string;
  subcode08?: string;
  subcode09?: string;
  subcode10?: string;
  buyerDescription?: string;
  brandcode?: string;
  brandname?: string;
  batchno?: string;
  productlongdescription?: string;
  markerqty?: number;
  markerqtytill?: number;
  markerqtybalance?: number;
  logicalwarehousecode?: string;
  logicalwarehousedescription?: string;
  userprimaryuomcode?: string;
  operation?: string;
  operationdesc?: string;
  prvoperation?: string;
  prvoperationdesc?: string;
  prvStepQuantity?: number;
  warehousezonecode?: string;
  warehouselocation?: string;
  shadeCode?: string;
  shadeDescription?: string;
  chemicalCode?: string;
  chemicalDescription?: string;
  maxdate?: Moment;
  colorsList?: IGeneralBean[];
  sizewiseList?: IProductionOrderBreakup[];
  destinationList?: IGeneralBean[];
  qualityList?: IQualitylevel[];
  costcenterList?: IGeneralBean[];
  workcenterList?: IGeneralBean[];
  operationList?: IGeneralBean[];
  productionreservations?: IProductionreservationBean[];
  productiondemandBeans?: IProductiondemandBean[];
  tollerance?: Number;
}

export class ProductionOrderBean implements IProductionOrderBean {
  constructor(
    public companycode?: string,
    public countercode?: string,
    public productionordercode?: string,
    public projectcode?: string,
    public product?: string,
    public subcode01?: string,
    public subcode02?: string,
    public subcode03?: string,
    public subcode04?: string,
    public subcode05?: string,
    public subcode06?: string,
    public subcode07?: string,
    public subcode08?: string,
    public subcode09?: string,
    public subcode10?: string,
    public buyerDescription?: string,
    public brandcode?: string,
    public brandname?: string,
    public batchno?: string,
    public productlongdescription?: string,
    public markerqty?: number,
    public markerqtytill?: number,
    public markerqtybalance?: number,
    public logicalwarehousecode?: string,
    public logicalwarehousedescription?: string,
    public userprimaryuomcode?: string,
    public operation?: string,
    public operationdesc?: string,
    public prvoperation?: string,
    public prvoperationdesc?: string,
    public prvStepQuantity?: number,
    public warehousezonecode?: string,
    public warehouselocation?: string,
    public shadeCode?: string,
    public shadeDescription?: string,
    public chemicalCode?: string,
    public chemicalDescription?: string,
    public maxdate?: Moment,
    public colorsList?: IGeneralBean[],
    public sizewiseList?: IProductionOrderBreakup[],
    public destinationList?: IGeneralBean[],
    public qualityList?: IQualitylevel[],
    public costcenterList?: IGeneralBean[],
    public workcenterList?: IGeneralBean[],
    public operationList?: IGeneralBean[],
    public productionreservations?: IProductionreservationBean[],
    public productiondemandBeans?: IProductiondemandBean[],
    public tollerance?: Number
  ) {}
}

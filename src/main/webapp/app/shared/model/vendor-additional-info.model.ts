import { Moment } from 'moment';

export interface IVendorAdditionalInfo {
  id?: number;
  vendorId?: number;
  esiCode?: string;
  pfCode?: string;
  isSsiUnit?: boolean;
  propPartnerDirector?: string;
  propPtrnDtrTeleNo?: string;
  associateSisterConcern?: string;
  introducedBy?: string;
  comp_ref?: string;
  productServiceItemsOfParty?: string;
  manufacturigUnitAddress?: string;
  manufacturingUnitCapicity?: string;
  numberOfMachines?: number;
  numberOfEmployees?: number;
  dealingWithOthExptParty?: string;
  turnoverId?: number;
  auditor?: string;
  auditorAddress?: string;
  personDealingWithCompany?: string;
  department?: string;
  remarks?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastUpdatedBy?: string;
  lastUpdatedDate?: Moment;
}

export class VendorAdditionalInfo implements IVendorAdditionalInfo {
  constructor(
    public id?: number,
    public vendorId?: number,
    public esiCode?: string,
    public pfCode?: string,
    public isSsiUnit?: boolean,
    public propPartnerDirector?: string,
    public propPtrnDtrTeleNo?: string,
    public associateSisterConcern?: string,
    public introducedBy?: string,
    public comp_ref?: string,
    public productServiceItemsOfParty?: string,
    public manufacturigUnitAddress?: string,
    public manufacturingUnitCapicity?: string,
    public numberOfMachines?: number,
    public numberOfEmployees?: number,
    public dealingWithOthExptParty?: string,
    public turnoverId?: number,
    public auditor?: string,
    public auditorAddress?: string,
    public personDealingWithCompany?: string,
    public department?: string,
    public remarks?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastUpdatedBy?: string,
    public lastUpdatedDate?: Moment
  ) {
    this.isSsiUnit = this.isSsiUnit || false;
  }
}

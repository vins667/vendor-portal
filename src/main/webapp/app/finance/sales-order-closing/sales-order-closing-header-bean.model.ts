import { ISalesOrderClosingDetailsBean } from './sales-order-closing-details-bean.model';

export interface ISalesOrderClosingHeaderBean {
  projectCode?: string;
  companycode?: string;
  divisioncode?: string;
  salesordercode?: string;
  salesorderdate?: string;
  customerCode?: string;
  customerName?: string;
  style?: string;
  orderQuantity?: number;
  tolerance?: number;
  totalQuantity?: number;
  shippedQuantity?: number;
  shippedPercentage?: number;
  status?: boolean;
  isChecked?: boolean;
  salesOrderClosingDetailsBeans?: ISalesOrderClosingDetailsBean[];
}
export class SalesOrderClosingHeaderBean implements ISalesOrderClosingHeaderBean {
  constructor(
    public projectCode?: string,
    public companycode?: string,
    public divisioncode?: string,
    public salesordercode?: string,
    public salesorderdate?: string,
    public customerCode?: string,
    public customerName?: string,
    public style?: string,
    public orderQuantity?: number,
    public tolerance?: number,
    public totalQuantity?: number,
    public shippedQuantity?: number,
    public shippedPercentage?: number,
    public status?: boolean,
    public isChecked?: boolean,
    public salesOrderClosingDetailsBeans?: ISalesOrderClosingDetailsBean[]
  ) {}
}

import { ISalesOrderClosing } from 'app/finance/sales-order-closing/sales-order-closing.model';

export interface ISalesOrderClosingDetailsBean {
  projectCode?: string;
  companycode?: string;
  divisioncode?: string;
  salesordercode?: string;
  orderline?: string;
  ordersubline?: string;
  salesorderdate?: string;
  customerCode?: string;
  customerName?: string;
  style?: string;
  styleColor?: string;
  styleSize?: string;
  orderQuantity?: number;
  tolerance?: number;
  totalQuantity?: number;
  shippedQuantity?: number;
  shippedPercentage?: number;
  status?: string;
}
export class SalesOrderClosingDetailsBean implements ISalesOrderClosingDetailsBean {
  constructor(
    public projectCode?: string,
    public companycode?: string,
    public divisioncode?: string,
    public salesordercode?: string,
    public orderline?: string,
    public ordersubline?: string,
    public salesorderdate?: string,
    public customerCode?: string,
    public customerName?: string,
    public style?: string,
    public styleColor?: string,
    public styleSize?: string,
    public orderQuantity?: number,
    public tolerance?: number,
    public totalQuantity?: number,
    public shippedQuantity?: number,
    public shippedPercentage?: number,
    public status?: string
  ) {}
}

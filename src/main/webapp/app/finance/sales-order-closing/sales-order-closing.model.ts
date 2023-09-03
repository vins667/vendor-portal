import { ISalesOrderClosingHeaderBean } from './sales-order-closing-header-bean.model';

export interface ISalesOrderClosing {
  isChecked: boolean;
  projectCode?: string;
  style?: string;
  customerCode?: string;
  customerName?: string;
  orderQuantity?: number;
  tolerance?: number;
  totalQuantity?: number;
  shippedQuantity?: number;
  shippedPercentage?: number;
  status?: string;
  salesOrderClosingHeaderBeans?: ISalesOrderClosingHeaderBean[];
}

// @ts-ignore
export class SalesOrderClosing implements ISalesOrderClosing {
  constructor(
    public isChecked?: boolean,
    public projectCode?: string,
    public style?: string,
    public customerCode?: string,
    public customerName?: string,
    public orderQuantity?: number,
    public tolerance?: number,
    public totalQuantity?: number,
    public shippedQuantity?: number,
    public shippedPercentage?: number,
    public status?: string,
    public salesOrderClosingHeaderBeans?: ISalesOrderClosingHeaderBean[]
  ) {}
}

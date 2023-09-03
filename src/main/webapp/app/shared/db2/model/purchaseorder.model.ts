import { IPurchaseorderId } from 'app/shared/db2/model/purchaseorder-id.model';

export interface IPurchaseorder {
  id?: IPurchaseorderId;
  orderdate?: any;
}

export class Purchaseorder implements IPurchaseorder {
  constructor(public id?: IPurchaseorderId, public orderdate?: any) {}
}

import { IProductionorderId } from './productionorder-id.model';

export interface IProductionorder {
  id?: IProductionorderId;
  productionordercountercode?: string;
  orderdate?: any;
  totalprimaryquantity?: number;
  primaryunitofmeasurecode?: string;
}

export class ProductionOrder implements IProductionorder {
  constructor(
    public id?: IProductionorderId,
    public productionordercountercode?: string,
    public orderdate?: any,
    public totalprimaryquantity?: number,
    public primaryunitofmeasurecode?: string
  ) {}
}

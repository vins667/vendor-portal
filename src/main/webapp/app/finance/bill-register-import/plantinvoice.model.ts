import { IPlantinvoiceId } from 'app/finance/bill-register-import/plantinvoice-id.model';
import { Moment } from 'moment';

export interface IPlantinvoice {
  id?: IPlantinvoiceId;
  invoicedate?: Moment;
  invoicetypecode?: string;
  challanno?: string;
  challandate?: Moment;
}

export class Plantinvoice implements IPlantinvoice {
  constructor(
    public id?: IPlantinvoiceId,
    public invoicedate?: Moment,
    public invoicetypecode?: string,
    public challanno?: string,
    public challandate?: Moment
  ) {}
}

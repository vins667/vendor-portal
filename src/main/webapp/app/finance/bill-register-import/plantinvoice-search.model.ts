export interface IPlantinvoiceSearch {
  code?: string;
  suppliercode?: string;
  page?: any;
  size?: number;
  pageNo?: number;
}

export class PlantinvoiceSearch implements IPlantinvoiceSearch {
  constructor(public code?: string, public suppliercode?: string, public page?: any, public size?: number, public pageNo?: number) {}
}

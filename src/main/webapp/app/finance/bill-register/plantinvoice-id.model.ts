export interface IPlantinvoiceId {
  companycode?: string;
  divisioncode?: string;
  code?: string;
}

export class PlantinvoiceId implements IPlantinvoiceId {
  constructor(public companycode?: string, public divisioncode?: string, public code?: string) {}
}

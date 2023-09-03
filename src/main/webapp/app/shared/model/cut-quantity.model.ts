export interface ICutQuantity {
  sizeCode?: string;
  orderQuantity?: number;
  toleranceQuantity?: number;
  netQuantity?: number;
}
export class CutQuantity implements ICutQuantity {
  constructor(public sizeCode?: string, public orderQuantity?: number, public toleranceQuantity?: number, public netQuantity?: number) {}
}

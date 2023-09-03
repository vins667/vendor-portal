export interface ICost {
  costCode?: string;
  desc1?: string;
  tmp?: number;
}
export class Cost implements ICost {
  constructor(public costCode?: string, public desc1?: string, public tmp?: number) {}
}

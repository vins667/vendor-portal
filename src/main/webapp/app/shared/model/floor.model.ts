export interface IFloor {
  flCode?: number;
  flDesc?: string;
}
export class Floor implements IFloor {
  constructor(public flCode?: number, public flDesc?: string) {}
}

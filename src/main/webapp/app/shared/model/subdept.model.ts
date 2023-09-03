export interface ISubdept {
  depCode?: number;
  sdepDesc?: string;
  tmp?: number;
  sdepCode?: string;
}
export class Subdept implements ISubdept {
  constructor(public depCode?: number, public sdepDesc?: string, public tmp?: number, public sdepCode?: string) {}
}

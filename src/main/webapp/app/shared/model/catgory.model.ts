export interface ICatgory {
  catCode?: number;
  catName?: string;
}
export class Catgory implements ICatgory {
  constructor(public catCode?: number, public catName?: string) {}
}

export interface ITdsDeclarationBreakupBean {
  id?: number;
  employerId?: number;
  groupDetailId?: number;
  amount?: number;
}

export class TdsDeclarationBreakupBean implements ITdsDeclarationBreakupBean {
  constructor(public id?: number, public employerId?: number, public groupDetailId?: number, public amount?: number) {}
}

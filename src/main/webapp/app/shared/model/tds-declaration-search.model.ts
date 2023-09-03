export interface ITdsDeclarationSearch {
  status?: string;
  name?: string;
  cardNo?: string;
  year?: string;
  size?: number;
  pageNo?: number;
}

export class TdsDeclarationSearch implements ITdsDeclarationSearch {
  constructor(
    public status?: string,
    public name?: string,
    public cardNo?: string,
    public year?: string,
    public size?: number,
    public pageNo?: number
  ) {}
}

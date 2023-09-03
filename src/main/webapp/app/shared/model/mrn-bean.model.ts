export interface IMrnBean {
  tariffcode?: string;
  itemtypeaficode?: string;
  itemcode?: string;
  itemdescription?: string;
  uom?: string;
}

export class MrnBean implements IMrnBean {
  constructor(
    public tariffcode?: string,
    public itemtypeaficode?: string,
    public itemcode?: string,
    public itemdescription?: string,
    public uom?: string
  ) {}
}

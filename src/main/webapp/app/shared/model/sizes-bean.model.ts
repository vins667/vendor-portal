export interface ISizesBean {
  sizeCode?: string;
  sequence?: number;
}
export class SizesBean implements ISizesBean {
  constructor(public sizeCode?: string, public sequence?: number) {}
}

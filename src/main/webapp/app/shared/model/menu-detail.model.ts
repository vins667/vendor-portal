export interface IMenuDetail {
  id?: number;
  link?: string;
  label?: string;
  icon?: string;
  toolTip?: string;
  type?: string[];
  subItem?: IMenuDetail[];
  isCollapsed?: boolean;
}

export class MenuDetail implements IMenuDetail {
  constructor(
    public id?: number,
    public link?: string,
    public label?: string,
    public icon?: string,
    public toolTip?: string,
    public type?: string[],
    public subItem?: IMenuDetail[],
    public isCollapsed?: boolean
  ) {
    this.isCollapsed = this.isCollapsed || false;
  }
}

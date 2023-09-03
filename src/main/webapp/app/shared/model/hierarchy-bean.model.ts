export interface IHierarchyBean {
  name?: string;
  imageUrl?: string;
  area?: string;
  profileUrl?: string;
  office?: string;
  tags?: string;
  loggedUser?: boolean;
  positionName?: string;
  unit?: any;
  children?: IHierarchyBean[];
}

export class HierarchyBean implements IHierarchyBean {
  constructor(
    public name?: string,
    public imageUrl?: string,
    public area?: string,
    public profileUrl?: string,
    public office?: string,
    public tags?: string,
    public loggedUser?: boolean,
    public positionName?: string,
    public unit?: any,
    public children?: IHierarchyBean[]
  ) {}
}

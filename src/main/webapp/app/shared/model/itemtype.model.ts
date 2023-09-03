import { IItemtypeId } from './itemtype-id.model';

export interface IItemtype {
  id?: IItemtypeId;
  defaultprimaryuomcode?: string;
  defaultsecondaryuomcode?: string;
}

export class Itemtype implements IItemtype {
  constructor(public id?: IItemtypeId, public defaultprimaryuomcode?: string, public defaultsecondaryuomcode?: string) {}
}

import { IViewDitaxglmappingId } from './view-ditaxglmapping-id.model';

export interface IViewDitaxglmapping {
  id?: IViewDitaxglmappingId;
  glcode?: string;
  longdescription?: string;
  value?: number;
}

export class ViewDitaxglmapping implements IViewDitaxglmapping {
  constructor(public id?: IViewDitaxglmappingId, public glcode?: string, public longdescription?: string, public value?: number) {}
}

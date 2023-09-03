import { Moment } from 'moment';
import { ISectionMatId } from './section-mat-id.model';

export interface ISectionMat {
  id?: ISectionMatId;
  desc1?: string;
}

export class SectionMat implements ISectionMat {
  constructor(public id?: ISectionMatId, public desc1?: string) {}
}

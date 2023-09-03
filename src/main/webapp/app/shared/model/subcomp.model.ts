import { Moment } from 'moment';
import { ISubcompId } from './subcomp-id.model';

export interface ISubcomp {
  id?: ISubcompId;
  subSname?: string;
  name?: string;
}

export class Subcomp implements ISubcomp {
  constructor(public id?: ISubcompId, public subSname?: string, public name?: string) {}
}

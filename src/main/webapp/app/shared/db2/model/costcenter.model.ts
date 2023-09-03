import { ICostcenterId } from './costcenter-id.model';

export interface ICostcenter {
  id?: ICostcenterId;
  longdescription?: string;
  shortdescription?: string;
  searchdescription?: string;
}

export class Costcenter implements ICostcenter {
  constructor(
    public id?: ICostcenterId,
    public longdescription?: string,
    public shortdescription?: string,
    public searchdescription?: string
  ) {}
}

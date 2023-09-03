import { Moment } from 'moment';

export interface IGstGovtUpload {
  id?: number;
  gGstin?: string;
  gSupplier?: string;
  gInvno?: string;
  gInvdate?: Moment;
  gInvtype?: string;
  gState?: string;
  gReverse?: string;
  gInvamt?: number;
  gInvnet?: number;
  gRate?: number;
  gCgst?: number;
  gSgst?: number;
  gIgst?: number;
  gCess?: number;
  gMonth?: Moment;
  gLocation?: string;
  gSrlno?: string;
  gStatus?: string;
  gConfirmdate?: Moment;
}

export class GstGovtUpload implements IGstGovtUpload {
  constructor(
    public id?: number,
    public gGstin?: string,
    public gSupplier?: string,
    public gInvno?: string,
    public gInvdate?: Moment,
    public gInvtype?: string,
    public gState?: string,
    public gReverse?: string,
    public gInvamt?: number,
    public gInvnet?: number,
    public gRate?: number,
    public gCgst?: number,
    public gSgst?: number,
    public gIgst?: number,
    public gCess?: number,
    public gMonth?: Moment,
    public gLocation?: string,
    public gSrlno?: string,
    public gStatus?: string,
    public gConfirmdate?: Moment
  ) {}
}

import { IPoDatatexApprovalLine } from './po-datatex-approval-line.model';

export interface IPoDatatexApproval {
  id?: string;
  currentstatus?: string;
  vngstnumber?: string;
  vnstatename?: string;
  templatecode?: string;
  projectcode?: string;
  countercode?: string;
  code?: string;
  orderdate?: string;
  vnname?: string;
  vnaddress?: string;
  warehousecode?: string;
  poDatatexApprovalLine?: IPoDatatexApprovalLine[];
}
export class PoDatatexApproval implements IPoDatatexApproval {
  constructor(
    public id?: string,
    public currentstatus?: string,
    public vngstnumber?: string,
    public vnstatename?: string,
    public templatecode?: string,
    public projectcode?: string,
    public countercode?: string,
    public code?: string,
    public orderdate?: string,
    public vnname?: string,
    public vnaddress?: string,
    public warehousecode?: string,
    public poDatatexApprovalLine?: IPoDatatexApprovalLine[]
  ) {}
}

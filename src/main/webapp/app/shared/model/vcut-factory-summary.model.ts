import { IVcutSessionMaster } from 'app/shared/model/vcut-session-master.model';
import { IVcutFactoryLineBreakup } from 'app/shared/model/vcut-factory-line-breakup.model';
import { IVcutFactoryAccess } from 'app/shared/model/vcut-factory-access.model';
import { IVcutTvDefectBreakup } from 'app/shared/model/vcut-tv-defect-breakup.model';

export interface IVcutFactorySummary {
  id?: number;
  factory?: string;
  line?: string;
  buyer?: string;
  style?: string;
  poNo?: string;
  smv?: number;
  operators?: number;
  helpers?: number;
  quantity?: number;
  planEff?: number;
  achieved?: number;
  achEff?: number;
  balance?: number;
  ftt?: number;
  rectified?: number;
  alter?: number;
  rejected?: number;
  extend?: boolean;
  activeFactory?: string;
  vcutSessionMaster?: IVcutSessionMaster;
  vcutFactorySummaries?: IVcutFactorySummary[];
  vcutFactoryLineBreakups?: IVcutFactoryLineBreakup[];
  hourlyFactoryBreakups?: IVcutFactoryLineBreakup[];
  vcutFactoryAccesses?: IVcutFactoryAccess[];
  vcutTvDefectBreakups?: IVcutTvDefectBreakup[];
  vcutTvDefectOBBreakups?: IVcutTvDefectBreakup[];
}

export class VcutFactorySummary implements IVcutFactorySummary {
  constructor(
    public id?: number,
    public factory?: string,
    public line?: string,
    public buyer?: string,
    public style?: string,
    public poNo?: string,
    public smv?: number,
    public operators?: number,
    public helpers?: number,
    public quantity?: number,
    public planEff?: number,
    public achieved?: number,
    public achEff?: number,
    public balance?: number,
    public ftt?: number,
    public rectified?: number,
    public alter?: number,
    public rejected?: number,
    public extend?: boolean,
    public activeFactory?: string,
    public vcut_session_master?: IVcutSessionMaster,
    public vcutFactorySummaries?: IVcutFactorySummary[],
    public hourlyFactoryBreakups?: IVcutFactoryLineBreakup[],
    public vcutFactoryLineBreakups?: IVcutFactoryLineBreakup[],
    public vcutFactoryAccesses?: IVcutFactoryAccess[],
    public vcutTvDefectBreakups?: IVcutTvDefectBreakup[],
    public vcutTvDefectOBBreakups?: IVcutTvDefectBreakup[]
  ) {}
}

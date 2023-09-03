import { Moment } from 'moment';
import { IAssetAuditSoftwareCompare } from './asset-audit-software-compare.model';
import { IAssetSoftwareCompareBean } from './asset-software-compare-bean.model';
import { IAssetSoftwareKeyCompareBean } from './asset-software-key-compare-bean.model';

export interface IAssetCompareBean {
  startTime?: Moment;
  endTime?: Moment;
  assetHardwareCompareBeans?: IAssetAuditSoftwareCompare[];
  assetSoftwareCompareBeans?: IAssetSoftwareCompareBean[];
  assetSoftwareKeyCompareBeans?: IAssetSoftwareKeyCompareBean[];
}

export class AssetCompareBean implements IAssetCompareBean {
  constructor(
    public startTime?: Moment,
    public endTime?: Moment,
    public assetHardwareCompareBeans?: IAssetAuditSoftwareCompare[],
    public assetSoftwareCompareBeans?: IAssetSoftwareCompareBean[],
    assetSoftwareKeyCompareBeans?: IAssetSoftwareKeyCompareBean[]
  ) {}
}

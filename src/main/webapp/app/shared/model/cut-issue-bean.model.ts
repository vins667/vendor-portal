import { IMaster } from 'app/shared/model/master.modal';

export interface ICutIssueBean {
  style?: string;
  colors?: IMaster[];
}
export class CutIssueBean implements ICutIssueBean {
  constructor(public style?: string, public colors?: IMaster[]) {}
}

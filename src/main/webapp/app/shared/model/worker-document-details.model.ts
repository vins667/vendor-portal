import { Moment } from 'moment';
import { IRecruitmentDocumentMaster } from 'app/shared/model//recruitment-document-master.model';
import { IWorkerJoining } from 'app/shared/model//worker-joining.model';

export interface IWorkerDocumentDetails {
  id?: number;
  documentType?: string;
  remarks?: string;
  attached?: boolean;
  fileName?: string;
  multipartFile?: any;
  createdBy?: string;
  createdDate?: Moment;
  recruitmentDocumentMaster?: IRecruitmentDocumentMaster;
  workerJoining?: IWorkerJoining;
}

export class WorkerDocumentDetails implements IWorkerDocumentDetails {
  constructor(
    public id?: number,
    public documentType?: string,
    public remarks?: string,
    public attached?: boolean,
    public fileName?: string,
    public multipartFile?: any,
    public createdBy?: string,
    public createdDate?: Moment,
    public recruitmentDocumentMaster?: IRecruitmentDocumentMaster,
    public workerJoining?: IWorkerJoining
  ) {
    this.attached = this.attached || false;
  }
}

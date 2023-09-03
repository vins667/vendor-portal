export interface IWorkerForwardTypeMaster {
  id?: number;
  code?: string;
  description?: string;
}

export class WorkerForwardTypeMaster implements IWorkerForwardTypeMaster {
  constructor(public id?: number, public code?: string, public description?: string) {}
}

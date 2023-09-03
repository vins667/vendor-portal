export interface IUserPlantId {
  login?: string;
  plantCode?: string;
}
export class UserPlantId implements IUserPlantId {
  constructor(public login?: string, public plantCode?: string) {}
}

export interface IUserPlantDetailsNew {
  plantCode?: string;
  plantDescription?: string;
}
export class UserPlantDetailsNew implements IUserPlantDetailsNew {
  constructor(public plantCode?: string, public plantDescription?: string) {}
}

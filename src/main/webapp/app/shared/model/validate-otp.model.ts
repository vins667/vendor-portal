export interface IValidateOtp {
  cardNo?: string;
  mobileNumber?: string;
  otp?: string;
}
export class ValidateOtp implements IValidateOtp {
  constructor(public cardNo?: string, public mobileNumber?: string, public otp?: string) {}
}

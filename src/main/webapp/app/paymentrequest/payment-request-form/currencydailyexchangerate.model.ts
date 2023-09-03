export interface ICurrencydailyexchangerate {
  purchaseexchangerate?: number;
}
export class Currencydailyexchangerate implements ICurrencydailyexchangerate {
  constructor(public purchaseexchangerate?: number) {}
}

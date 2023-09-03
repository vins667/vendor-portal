import { Moment } from 'moment';

export interface ISuggestion {
  id?: number;
  suggestionText?: string;
  createdBy?: string;
  createdDate?: Moment;
}

export class Suggestion implements ISuggestion {
  constructor(public id?: number, public suggestionText?: string, public createdBy?: string, public createdDate?: Moment) {}
}

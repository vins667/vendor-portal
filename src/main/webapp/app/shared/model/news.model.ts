import { INewsArticle } from 'app/shared/model/news-article.model';

export interface INews {
  articles?: INewsArticle[];
  status?: string;
  totalResults?: number;
}

export class News implements INews {
  constructor(public articles?: INewsArticle[], public status?: string, public totalResults?: number) {}
}

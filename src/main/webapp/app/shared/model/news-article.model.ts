import { INewsSource } from 'app/shared/model/news-source.model';

export interface INewsArticle {
  author?: string;
  content?: string;
  description?: string;
  publishedAt?: string;
  source?: INewsSource;
  title?: string;
  url?: string;
  urlToImage?: string;
}

export class NewsArticle implements INewsArticle {
  constructor(
    public author?: string,
    public content?: string,
    public description?: string,
    public publishedAt?: string,
    public source?: INewsSource,
    public title?: string,
    public url?: string,
    public urlToImage?: string
  ) {}
}

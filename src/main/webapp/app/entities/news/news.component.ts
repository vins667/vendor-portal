import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { AccountService } from 'app/core/auth/account.service';
import { INews } from 'app/shared/model/news.model';

import * as NewsAPI from 'newsapi';
const newsapi = new NewsAPI('7f8d0b3a16324a53b1ac80ad7f03ba69');
@Component({
  selector: 'jhi-news',
  templateUrl: './news.component.html'
})
export class NewsComponent implements OnInit {
  news: INews;
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    newsapi.v2
      .topHeadlines({
        sources: 'google-news-in'
      })
      .then(response => {
        this.news = response;
      });
  }

  trackId(index: number, item: IEmployeeView) {
    return item.cardNo;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}

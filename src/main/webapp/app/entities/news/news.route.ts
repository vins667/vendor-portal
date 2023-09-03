import { Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { NewsComponent } from './news.component';

export const NEWS_ROUTE: Routes = [
  {
    path: '',
    component: NewsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'News Headline'
    },
    canActivate: [UserRouteAccessService]
  }
];

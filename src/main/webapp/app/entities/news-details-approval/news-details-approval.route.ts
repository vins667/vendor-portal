import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NewsDetails } from 'app/shared/model/news-details.model';
import { NewsDetailsApprovalService } from './news-details-approval.service';
import { NewsDetailsApprovalComponent } from './news-details-approval.component';
import { NewsDetailsApprovalDetailComponent } from './news-details-approval-detail.component';
import { INewsDetails } from 'app/shared/model/news-details.model';

@Injectable({ providedIn: 'root' })
export class NewsDetailsResolve implements Resolve<INewsDetails> {
  constructor(private service: NewsDetailsApprovalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NewsDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<NewsDetails>) => response.ok),
        map((newsDetails: HttpResponse<NewsDetails>) => newsDetails.body)
      );
    }
    return of(new NewsDetails());
  }
}

export const newsDetailsApprovalRoute: Routes = [
  {
    path: ':type',
    component: NewsDetailsApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'NewsDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':type/:id/view',
    component: NewsDetailsApprovalDetailComponent,
    resolve: {
      newsDetails: NewsDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NewsDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const newsDetailsApprovalPopupRoute: Routes = [];

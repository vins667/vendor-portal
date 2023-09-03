import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NewsDetails } from 'app/shared/model/news-details.model';
import { NewsDetailsService } from './news-details.service';
import { NewsDetailsComponent } from './news-details.component';
import { NewsDetailsUpdateComponent } from './news-details-update.component';
import { NewsDetailsDeletePopupComponent } from './news-details-delete-dialog.component';
import { INewsDetails } from 'app/shared/model/news-details.model';

@Injectable({ providedIn: 'root' })
export class NewsDetailsResolve implements Resolve<INewsDetails> {
  constructor(private service: NewsDetailsService) {}

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

export const newsDetailsRoute: Routes = [
  {
    path: '',
    component: NewsDetailsComponent,
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
    path: 'new',
    component: NewsDetailsUpdateComponent,
    resolve: {
      newsDetails: NewsDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NewsDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NewsDetailsUpdateComponent,
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

export const newsDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: NewsDetailsDeletePopupComponent,
    resolve: {
      newsDetails: NewsDetailsResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NewsDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MonthlyNewsData } from 'app/shared/model/monthly-news-data.model';
import { MonthlyNewsDataService } from './monthly-news-data.service';
import { MonthlyNewsDataComponent } from './monthly-news-data.component';
import { MonthlyNewsDataUpdateComponent } from './monthly-news-data-update.component';
import { IMonthlyNewsData } from 'app/shared/model/monthly-news-data.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class MonthlyNewsDataResolve implements Resolve<IMonthlyNewsData> {
  constructor(private service: MonthlyNewsDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMonthlyNewsData> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MonthlyNewsData>) => response.ok),
        map((monthlyNewsData: HttpResponse<MonthlyNewsData>) => monthlyNewsData.body)
      );
    }
    return of(new MonthlyNewsData());
  }
}

export const monthlyNewsDataRoute: Routes = [
  {
    path: '',
    component: MonthlyNewsDataComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Monthly News Data'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MonthlyNewsDataUpdateComponent,
    resolve: {
      monthlyNewsData: MonthlyNewsDataResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Monthly News Data'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MonthlyNewsDataUpdateComponent,
    resolve: {
      monthlyNewsData: MonthlyNewsDataResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Monthly News Data'
    },
    canActivate: [UserRouteAccessService]
  }
];

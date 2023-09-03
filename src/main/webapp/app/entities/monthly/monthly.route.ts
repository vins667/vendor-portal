import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { MonthlyService } from './monthly.service';
import { MonthlyComponent } from './monthly.component';
import { MonthlyDetailComponent } from 'app/entities/monthly/monthly-detail.component';
import { IMonthly, Monthly } from 'app/shared/model/monthly.model';

@Injectable({ providedIn: 'root' })
export class MonthlyResolve implements Resolve<IMonthly> {
  constructor(private service: MonthlyService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Monthly> {
    return of(new Monthly());
  }
}

export const monthlyRoute: Routes = [
  {
    path: '',
    component: MonthlyComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Monthly Salary'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: '{monthYear}',
    component: MonthlyDetailComponent,
    resolve: {
      monthly: MonthlyResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Monthly Salary'
    },
    canActivate: [UserRouteAccessService]
  }
];

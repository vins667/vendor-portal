import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutFactorySummary } from 'app/shared/model/vcut-factory-summary.model';
import { VcutLineSummaryService } from './vcut-line-summary.service';
import { VcutLineSummaryComponent } from './vcut-line-summary.component';
import { IVcutFactorySummary } from 'app/shared/model/vcut-factory-summary.model';

@Injectable({ providedIn: 'root' })
export class VcutLineSummaryResolve implements Resolve<IVcutFactorySummary> {
  constructor(private service: VcutLineSummaryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutFactorySummary> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutFactorySummary>) => response.ok),
        map((vcutFactorySummary: HttpResponse<VcutFactorySummary>) => vcutFactorySummary.body)
      );
    }
    return of(new VcutFactorySummary());
  }
}

export const vcutLineSummaryRoute: Routes = [
  {
    path: '',
    component: VcutLineSummaryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Production Dashboard'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutLineSummaryPopupRoute: Routes = [];

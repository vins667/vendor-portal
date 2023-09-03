import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TdsComputation } from 'app/shared/model/tds-computation.model';
import { TdsComputationEntryService } from './tds-computation-entry.service';
import { TdsComputationEntryComponent } from './tds-computation-entry.component';
import { TdsComputationEntryUpdateComponent } from './tds-computation-entry-update.component';
import { ITdsComputation } from 'app/shared/model/tds-computation.model';

@Injectable({ providedIn: 'root' })
export class TdsComputationResolve implements Resolve<ITdsComputation> {
  constructor(private service: TdsComputationEntryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TdsComputation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TdsComputation>) => response.ok),
        map((tdsComputation: HttpResponse<TdsComputation>) => tdsComputation.body)
      );
    }
    return of(new TdsComputation());
  }
}

export const tdsComputationEntryRoute: Routes = [
  {
    path: '',
    component: TdsComputationEntryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'TdsComputations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TdsComputationEntryUpdateComponent,
    resolve: {
      tdsComputation: TdsComputationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'TdsComputations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tdsComputationEntryPopupRoute: Routes = [];

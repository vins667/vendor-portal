import { Routes, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { PreviousEmploymentQryComponent } from './previous-employment-qry.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Injectable } from '@angular/core';
import { IPreviousEmploymentQry, PreviousEmploymentQry } from 'app/shared/model/previous-employment-qry.model';
import { PreviousEmploymentQryService, PreviousEmploymentQryDetailComponent } from '.';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class PreviousEmploymentQryResolve implements Resolve<IPreviousEmploymentQry> {
  constructor(private service: PreviousEmploymentQryService) {}
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PreviousEmploymentQry> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PreviousEmploymentQry>) => response.ok),
        map((previousEmploymentQry: HttpResponse<PreviousEmploymentQry>) => previousEmploymentQry.body)
      );
    }
    return of(new PreviousEmploymentQry());
  }
}
export const previousEmploymentQryRoute: Routes = [
  {
    path: '',
    component: PreviousEmploymentQryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Previous Employment Qries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PreviousEmploymentQryDetailComponent,
    resolve: {
      previousEmploymentQry: PreviousEmploymentQryResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Previous Employment Qries'
    },
    canActivate: [UserRouteAccessService]
  }
];

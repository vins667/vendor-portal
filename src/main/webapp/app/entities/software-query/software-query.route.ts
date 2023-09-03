import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SoftwareQueryService } from './software-query.service';
import { SoftwareQueryComponent } from './software-query.component';
import { ISoftwareQueryBean, SoftwareQueryBean } from 'app/shared/model/software-query-bean.model';

@Injectable({ providedIn: 'root' })
export class SoftwareQueryResolve implements Resolve<ISoftwareQueryBean> {
  constructor(private service: SoftwareQueryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SoftwareQueryBean> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SoftwareQueryBean>) => response.ok),
        map((softwareQuery: HttpResponse<SoftwareQueryBean>) => softwareQuery.body)
      );
    }
    return of(new SoftwareQueryBean());
  }
}

export const softwareQueryRoute: Routes = [
  {
    path: '',
    component: SoftwareQueryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Software Queries'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const softwareQueryPopupRoute: Routes = [{}];

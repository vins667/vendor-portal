import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HardwareQueryService } from './hardware-query.service';
import { HardwareQueryComponent } from './hardware-query.component';
import { IHardwareQueryBean, HardwareQueryBean } from 'app/shared/model/hardware-query.bean.model';

@Injectable({ providedIn: 'root' })
export class HardwareQueryResolve implements Resolve<IHardwareQueryBean> {
  constructor(private service: HardwareQueryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<HardwareQueryBean> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<HardwareQueryBean>) => response.ok),
        map((hardwareQuery: HttpResponse<HardwareQueryBean>) => hardwareQuery.body)
      );
    }
    return of(new HardwareQueryBean());
  }
}

export const hardwareQueryRoute: Routes = [
  {
    path: '',
    component: HardwareQueryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Hardware Query'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const hardwareQueryPopupRoute: Routes = [{}];

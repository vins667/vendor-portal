import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RateMaster } from 'app/shared/model/rate-master.model';
import { RateMasterService } from './rate-master.service';
import { RateMasterComponent } from './rate-master.component';
import { RateMasterDetailComponent } from './rate-master-detail.component';
import { RateMasterUpdateComponent } from './rate-master-update.component';
import { RateMasterDeletePopupComponent } from './rate-master-delete-dialog.component';
import { IRateMaster } from 'app/shared/model/rate-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class RateMasterResolve implements Resolve<IRateMaster> {
  constructor(private service: RateMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRateMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RateMaster>) => response.ok),
        map((rateMaster: HttpResponse<RateMaster>) => rateMaster.body)
      );
    }
    return of(new RateMaster());
  }
}
export const rateMasterRoute: Routes = [
  {
    path: '',
    component: RateMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Rate Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RateMasterDetailComponent,
    resolve: {
      rateMaster: RateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Rate Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RateMasterUpdateComponent,
    resolve: {
      rateMaster: RateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Rate Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RateMasterUpdateComponent,
    resolve: {
      rateMaster: RateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Rate Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const rateMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RateMasterDeletePopupComponent,
    resolve: {
      rateMaster: RateMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'Rate Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

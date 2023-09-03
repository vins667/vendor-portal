import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';
import { IgnoreSoftwareMasterService } from './ignore-software-master.service';
import { IgnoreSoftwareMasterComponent } from './ignore-software-master.component';
import { IgnoreSoftwareMasterDetailComponent } from './ignore-software-master-detail.component';
import { IgnoreSoftwareMasterUpdateComponent } from './ignore-software-master-update.component';
import { IgnoreSoftwareMasterDeletePopupComponent } from './ignore-software-master-delete-dialog.component';
import { IIgnoreSoftwareMaster } from 'app/shared/model/ignore-software-master.model';

@Injectable({ providedIn: 'root' })
export class IgnoreSoftwareMasterResolve implements Resolve<IIgnoreSoftwareMaster> {
  constructor(private service: IgnoreSoftwareMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IgnoreSoftwareMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<IgnoreSoftwareMaster>) => response.ok),
        map((ignoreSoftwareMaster: HttpResponse<IgnoreSoftwareMaster>) => ignoreSoftwareMaster.body)
      );
    }
    return of(new IgnoreSoftwareMaster());
  }
}

export const ignoreSoftwareMasterRoute: Routes = [
  {
    path: '',
    component: IgnoreSoftwareMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'IgnoreSoftwareMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: IgnoreSoftwareMasterDetailComponent,
    resolve: {
      ignoreSoftwareMaster: IgnoreSoftwareMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'IgnoreSoftwareMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: IgnoreSoftwareMasterUpdateComponent,
    resolve: {
      ignoreSoftwareMaster: IgnoreSoftwareMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'IgnoreSoftwareMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: IgnoreSoftwareMasterUpdateComponent,
    resolve: {
      ignoreSoftwareMaster: IgnoreSoftwareMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'IgnoreSoftwareMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const ignoreSoftwareMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: IgnoreSoftwareMasterDeletePopupComponent,
    resolve: {
      ignoreSoftwareMaster: IgnoreSoftwareMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'IgnoreSoftwareMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

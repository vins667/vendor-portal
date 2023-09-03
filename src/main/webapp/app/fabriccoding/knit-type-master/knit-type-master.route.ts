import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KnitTypeMaster } from 'app/shared/model/knit-type-master.model';
import { KnitTypeMasterService } from './knit-type-master.service';
import { KnitTypeMasterComponent } from './knit-type-master.component';
import { KnitTypeMasterDetailComponent } from './knit-type-master-detail.component';
import { KnitTypeMasterUpdateComponent } from './knit-type-master-update.component';
import { KnitTypeMasterDeletePopupComponent } from './knit-type-master-delete-dialog.component';
import { IKnitTypeMaster } from 'app/shared/model/knit-type-master.model';

@Injectable({ providedIn: 'root' })
export class KnitTypeMasterResolve implements Resolve<IKnitTypeMaster> {
  constructor(private service: KnitTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IKnitTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<KnitTypeMaster>) => response.ok),
        map((knitTypeMaster: HttpResponse<KnitTypeMaster>) => knitTypeMaster.body)
      );
    }
    return of(new KnitTypeMaster());
  }
}

export const knitTypeMasterRoute: Routes = [
  {
    path: '',
    component: KnitTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'KnitTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: KnitTypeMasterDetailComponent,
    resolve: {
      knitTypeMaster: KnitTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: KnitTypeMasterUpdateComponent,
    resolve: {
      knitTypeMaster: KnitTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: KnitTypeMasterUpdateComponent,
    resolve: {
      knitTypeMaster: KnitTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const knitTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: KnitTypeMasterDeletePopupComponent,
    resolve: {
      knitTypeMaster: KnitTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'KnitTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

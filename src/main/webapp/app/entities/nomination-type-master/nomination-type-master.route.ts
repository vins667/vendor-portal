import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NominationTypeMaster } from 'app/shared/model/nomination-type-master.model';
import { NominationTypeMasterService } from './nomination-type-master.service';
import { NominationTypeMasterComponent } from './nomination-type-master.component';
import { NominationTypeMasterDetailComponent } from './nomination-type-master-detail.component';
import { NominationTypeMasterUpdateComponent } from './nomination-type-master-update.component';
import { NominationTypeMasterDeletePopupComponent } from './nomination-type-master-delete-dialog.component';
import { INominationTypeMaster } from 'app/shared/model/nomination-type-master.model';

@Injectable({ providedIn: 'root' })
export class NominationTypeMasterResolve implements Resolve<INominationTypeMaster> {
  constructor(private service: NominationTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<INominationTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<NominationTypeMaster>) => response.ok),
        map((nominationTypeMaster: HttpResponse<NominationTypeMaster>) => nominationTypeMaster.body)
      );
    }
    return of(new NominationTypeMaster());
  }
}

export const nominationTypeMasterRoute: Routes = [
  {
    path: '',
    component: NominationTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'NominationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NominationTypeMasterDetailComponent,
    resolve: {
      nominationTypeMaster: NominationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NominationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NominationTypeMasterUpdateComponent,
    resolve: {
      nominationTypeMaster: NominationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NominationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NominationTypeMasterUpdateComponent,
    resolve: {
      nominationTypeMaster: NominationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NominationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const nominationTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: NominationTypeMasterDeletePopupComponent,
    resolve: {
      nominationTypeMaster: NominationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'NominationTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

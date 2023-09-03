import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InstituteMaster } from 'app/shared/model/institute-master.model';
import { InstituteMasterService } from './institute-master.service';
import { InstituteMasterComponent } from './institute-master.component';
import { InstituteMasterDetailComponent } from './institute-master-detail.component';
import { InstituteMasterUpdateComponent } from './institute-master-update.component';
import { InstituteMasterDeletePopupComponent } from './institute-master-delete-dialog.component';
import { IInstituteMaster } from 'app/shared/model/institute-master.model';

@Injectable({ providedIn: 'root' })
export class InstituteMasterResolve implements Resolve<IInstituteMaster> {
  constructor(private service: InstituteMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInstituteMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<InstituteMaster>) => response.ok),
        map((instituteMaster: HttpResponse<InstituteMaster>) => instituteMaster.body)
      );
    }
    return of(new InstituteMaster());
  }
}

export const instituteMasterRoute: Routes = [
  {
    path: '',
    component: InstituteMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'InstituteMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InstituteMasterDetailComponent,
    resolve: {
      instituteMaster: InstituteMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'InstituteMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InstituteMasterUpdateComponent,
    resolve: {
      instituteMaster: InstituteMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'InstituteMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InstituteMasterUpdateComponent,
    resolve: {
      instituteMaster: InstituteMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'InstituteMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const instituteMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: InstituteMasterDeletePopupComponent,
    resolve: {
      instituteMaster: InstituteMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'InstituteMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

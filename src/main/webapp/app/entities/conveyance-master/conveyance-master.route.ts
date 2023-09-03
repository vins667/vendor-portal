import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { ConveyanceMasterService } from './conveyance-master.service';
import { ConveyanceMasterComponent } from './conveyance-master.component';
import { ConveyanceMasterDetailComponent } from './conveyance-master-detail.component';
import { ConveyanceMasterUpdateComponent } from './conveyance-master-update.component';
import { ConveyanceMasterDeletePopupComponent } from './conveyance-master-delete-dialog.component';
import { IConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class ConveyanceMasterResolve implements Resolve<IConveyanceMaster> {
  constructor(private service: ConveyanceMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConveyanceMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConveyanceMaster>) => response.ok),
        map((conveyanceMaster: HttpResponse<ConveyanceMaster>) => conveyanceMaster.body)
      );
    }
    return of(new ConveyanceMaster());
  }
}

export const conveyanceMasterRoute: Routes = [
  {
    path: '',
    component: ConveyanceMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Conveyance Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ConveyanceMasterDetailComponent,
    resolve: {
      conveyanceMaster: ConveyanceMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Conveyance Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ConveyanceMasterUpdateComponent,
    resolve: {
      conveyanceMaster: ConveyanceMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'ConveyanceMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConveyanceMasterUpdateComponent,
    resolve: {
      conveyanceMaster: ConveyanceMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Conveyance Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const conveyanceMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ConveyanceMasterDeletePopupComponent,
    resolve: {
      conveyanceMaster: ConveyanceMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Conveyance Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

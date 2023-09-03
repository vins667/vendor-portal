import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TrailMockOperation } from 'app/shared/model/trail-mock-operation.model';
import { TrailMockOperationService } from './trail-mock-operation.service';
import { TrailMockOperationComponent } from './trail-mock-operation.component';
import { TrailMockOperationDetailComponent } from './trail-mock-operation-detail.component';
import { TrailMockOperationUpdateComponent } from './trail-mock-operation-update.component';
import { TrailMockOperationDeletePopupComponent } from './trail-mock-operation-delete-dialog.component';
import { ITrailMockOperation } from 'app/shared/model/trail-mock-operation.model';

@Injectable({ providedIn: 'root' })
export class TrailMockOperationResolve implements Resolve<ITrailMockOperation> {
  constructor(private service: TrailMockOperationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITrailMockOperation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TrailMockOperation>) => response.ok),
        map((trailMockOperation: HttpResponse<TrailMockOperation>) => trailMockOperation.body)
      );
    }
    return of(new TrailMockOperation());
  }
}

export const trailMockOperationRoute: Routes = [
  {
    path: '',
    component: TrailMockOperationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Operator Profiles'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrailMockOperationDetailComponent,
    resolve: {
      trailMockOperation: TrailMockOperationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operator Profile'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrailMockOperationUpdateComponent,
    resolve: {
      trailMockOperation: TrailMockOperationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operator Profile'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrailMockOperationUpdateComponent,
    resolve: {
      trailMockOperation: TrailMockOperationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operator Profile'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const trailMockOperationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TrailMockOperationDeletePopupComponent,
    resolve: {
      trailMockOperation: TrailMockOperationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Operator Profile'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

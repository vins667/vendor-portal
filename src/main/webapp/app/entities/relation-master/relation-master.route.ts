import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RelationMaster } from 'app/shared/model/relation-master.model';
import { RelationMasterService } from './relation-master.service';
import { RelationMasterComponent } from './relation-master.component';
import { RelationMasterDetailComponent } from './relation-master-detail.component';
import { RelationMasterUpdateComponent } from './relation-master-update.component';
import { RelationMasterDeletePopupComponent } from './relation-master-delete-dialog.component';
import { IRelationMaster } from 'app/shared/model/relation-master.model';

@Injectable({ providedIn: 'root' })
export class RelationMasterResolve implements Resolve<IRelationMaster> {
  constructor(private service: RelationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRelationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RelationMaster>) => response.ok),
        map((relationMaster: HttpResponse<RelationMaster>) => relationMaster.body)
      );
    }
    return of(new RelationMaster());
  }
}

export const relationMasterRoute: Routes = [
  {
    path: '',
    component: RelationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Relation Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RelationMasterDetailComponent,
    resolve: {
      relationMaster: RelationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Relation Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RelationMasterUpdateComponent,
    resolve: {
      relationMaster: RelationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Relation Masters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RelationMasterUpdateComponent,
    resolve: {
      relationMaster: RelationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Relation Masters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const relationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: RelationMasterDeletePopupComponent,
    resolve: {
      relationMaster: RelationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Relation Masters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

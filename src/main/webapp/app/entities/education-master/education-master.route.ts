import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationMaster } from 'app/shared/model/education-master.model';
import { EducationMasterService } from './education-master.service';
import { EducationMasterComponent } from './education-master.component';
import { EducationMasterDetailComponent } from './education-master-detail.component';
import { EducationMasterUpdateComponent } from './education-master-update.component';
import { EducationMasterDeletePopupComponent } from './education-master-delete-dialog.component';
import { IEducationMaster } from 'app/shared/model/education-master.model';

@Injectable({ providedIn: 'root' })
export class EducationMasterResolve implements Resolve<IEducationMaster> {
  constructor(private service: EducationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEducationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EducationMaster>) => response.ok),
        map((educationMaster: HttpResponse<EducationMaster>) => educationMaster.body)
      );
    }
    return of(new EducationMaster());
  }
}

export const educationMasterRoute: Routes = [
  {
    path: '',
    component: EducationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'EducationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EducationMasterDetailComponent,
    resolve: {
      educationMaster: EducationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EducationMasterUpdateComponent,
    resolve: {
      educationMaster: EducationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EducationMasterUpdateComponent,
    resolve: {
      educationMaster: EducationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const educationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EducationMasterDeletePopupComponent,
    resolve: {
      educationMaster: EducationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

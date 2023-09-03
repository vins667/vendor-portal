import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationTypeMaster } from 'app/shared/model/education-type-master.model';
import { EducationTypeMasterService } from './education-type-master.service';
import { EducationTypeMasterComponent } from './education-type-master.component';
import { EducationTypeMasterDetailComponent } from './education-type-master-detail.component';
import { EducationTypeMasterUpdateComponent } from './education-type-master-update.component';
import { EducationTypeMasterDeletePopupComponent } from './education-type-master-delete-dialog.component';
import { IEducationTypeMaster } from 'app/shared/model/education-type-master.model';

@Injectable({ providedIn: 'root' })
export class EducationTypeMasterResolve implements Resolve<IEducationTypeMaster> {
  constructor(private service: EducationTypeMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEducationTypeMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EducationTypeMaster>) => response.ok),
        map((educationTypeMaster: HttpResponse<EducationTypeMaster>) => educationTypeMaster.body)
      );
    }
    return of(new EducationTypeMaster());
  }
}

export const educationTypeMasterRoute: Routes = [
  {
    path: '',
    component: EducationTypeMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'EducationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EducationTypeMasterDetailComponent,
    resolve: {
      educationTypeMaster: EducationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EducationTypeMasterUpdateComponent,
    resolve: {
      educationTypeMaster: EducationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EducationTypeMasterUpdateComponent,
    resolve: {
      educationTypeMaster: EducationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationTypeMasters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const educationTypeMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EducationTypeMasterDeletePopupComponent,
    resolve: {
      educationTypeMaster: EducationTypeMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EducationTypeMasters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

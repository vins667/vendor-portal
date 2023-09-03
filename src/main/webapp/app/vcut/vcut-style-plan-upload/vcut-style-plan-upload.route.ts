import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';
import { VcutStylePlanUploadService } from './vcut-style-plan-upload.service';
import { VcutStylePlanUploadComponent } from './vcut-style-plan-upload.component';
import { VcutStylePlanUploadDetailComponent } from './vcut-style-plan-upload-detail.component';
import { VcutStylePlanUploadUpdateComponent } from './vcut-style-plan-upload-update.component';
import { VcutStylePlanUploadDeletePopupComponent } from './vcut-style-plan-upload-delete-dialog.component';
import { IVcutStylePlanUpload } from 'app/shared/model/vcut-style-plan-upload.model';

@Injectable({ providedIn: 'root' })
export class VcutStylePlanUploadResolve implements Resolve<IVcutStylePlanUpload> {
  constructor(private service: VcutStylePlanUploadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVcutStylePlanUpload> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VcutStylePlanUpload>) => response.ok),
        map((vcutStylePlanUpload: HttpResponse<VcutStylePlanUpload>) => vcutStylePlanUpload.body)
      );
    }
    return of(new VcutStylePlanUpload());
  }
}

export const vcutStylePlanUploadRoute: Routes = [
  {
    path: '',
    component: VcutStylePlanUploadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: VcutStylePlanUploadDetailComponent,
    resolve: {
      vcutStylePlanUpload: VcutStylePlanUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VcutStylePlanUploadUpdateComponent,
    resolve: {
      vcutStylePlanUpload: VcutStylePlanUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VcutStylePlanUploadUpdateComponent,
    resolve: {
      vcutStylePlanUpload: VcutStylePlanUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vcutStylePlanUploadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VcutStylePlanUploadDeletePopupComponent,
    resolve: {
      vcutStylePlanUpload: VcutStylePlanUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

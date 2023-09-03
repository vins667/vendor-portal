import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IScriptDetailsUpload, ScriptDetailsUpload } from 'app/entities/script-details-upload/script-details-upload.model';
import { ScriptDetailsUploadService } from 'app/entities/script-details-upload/script-details-upload.service';
import { ScriptDetailsUploadComponent } from 'app/entities/script-details-upload/script-details-upload-component';
import { ScriptDetailsUploadDetailComponent } from 'app/entities/script-details-upload/script-details-upload-detail.component';
import { ScriptDetailsUploadUpdateComponent } from 'app/entities/script-details-upload/script-details-upload-update.component';
import { ScriptDetailsUploadPopupComponent } from 'app/entities/script-details-upload/script-details-upload-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class ScriptDetailsUploadUploadResolve implements Resolve<IScriptDetailsUpload> {
  constructor(private service: ScriptDetailsUploadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IScriptDetailsUpload> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ScriptDetailsUpload>) => response.ok),
        map((scriptDetailsUpload: HttpResponse<ScriptDetailsUpload>) => scriptDetailsUpload.body)
      );
    }
    return of(new ScriptDetailsUpload());
  }
}

export const scriptDetailsUploadRoute: Routes = [
  {
    path: '',
    component: ScriptDetailsUploadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'Script Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: ScriptDetailsUploadDetailComponent,
    resolve: {
      scriptDetailsUpload: ScriptDetailsUploadUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ScriptDetailsUploadUpdateComponent,
    resolve: {
      scriptDetailsUpload: ScriptDetailsUploadUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Script Upload'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const scriptDetailsUploadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ScriptDetailsUploadPopupComponent,
    resolve: {
      scriptDetailsUpload: ScriptDetailsUploadUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Script Upload'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

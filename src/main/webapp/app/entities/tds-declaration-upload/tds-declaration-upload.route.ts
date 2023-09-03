import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { Observable, of } from 'rxjs';
import { TdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { TdsDeclarationUploadUpdateComponent } from './tds-declaration-upload-update.component';
import { ITdsDeclarationUpload } from 'app/shared/model/tds-declaration-upload.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class TdsDeclarationUploadResolve implements Resolve<ITdsDeclarationUpload> {
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITdsDeclarationUpload> {
    return of(new TdsDeclarationUpload());
  }
}
export const tdsDeclarationUploadRoute: Routes = [
  {
    path: '',
    component: TdsDeclarationUploadUpdateComponent,
    resolve: {
      tdsDeclarationUpload: TdsDeclarationUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_USER'],
      pageTitle: 'TdsDeclarationUploads'
    },
    canActivate: [UserRouteAccessService]
  }
];
export const tdsDeclarationUploadPopupRoute: Routes = [{}];

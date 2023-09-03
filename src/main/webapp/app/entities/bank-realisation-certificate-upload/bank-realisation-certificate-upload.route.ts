import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import {
  BankRealisationCertificateUpload,
  IBankRealisationCertificateUpload
} from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.model';
import { BankRealisationCertificateUploadService } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.service';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { BankRealisationCertificateUploadComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload.component';
import { BankRealisationCertificateUploadDetailComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-detail.component';
import { BankRealisationCertificateUploadPopupComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-delete-dialog.component';
import { BankRealisationCertificateUploadUpdateComponent } from 'app/entities/bank-realisation-certificate-upload/bank-realisation-certificate-upload-update.component';

@Injectable({ providedIn: 'root' })
export class BankRealisationCertificateUploadResolve implements Resolve<IBankRealisationCertificateUpload> {
  constructor(private service: BankRealisationCertificateUploadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBankRealisationCertificateUpload> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BankRealisationCertificateUpload>) => response.ok),
        map((bankRealisationCertificateUpload: HttpResponse<BankRealisationCertificateUpload>) => bankRealisationCertificateUpload.body)
      );
    }
    return of(new BankRealisationCertificateUpload());
  }
}
export const bankRealisationCertificateUploadRoute: Routes = [
  {
    path: '',
    component: BankRealisationCertificateUploadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,desc',
      pageTitle: 'BRC Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: BankRealisationCertificateUploadDetailComponent,
    resolve: {
      bankRealisationCertificateResolve: BankRealisationCertificateUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Plan Upload'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BankRealisationCertificateUploadUpdateComponent,
    resolve: {
      bankRealisationCertificateUpload: BankRealisationCertificateUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BRC Upload'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bankRealisationCertificateUploadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BankRealisationCertificateUploadPopupComponent,
    resolve: {
      vcutStylePlanUpload: BankRealisationCertificateUploadResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'BRC Upload'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { QuotationMaster } from 'app/shared/model/quotation-master.model';
import { QuotationMasterService } from './quotation-master.service';
import { QuotationMasterComponent } from './quotation-master.component';
import { QuotationMasterDetailComponent } from './quotation-master-detail.component';
import { QuotationMasterUpdateComponent } from './quotation-master-update.component';
import { QuotationMasterDeletePopupComponent } from './quotation-master-delete-dialog.component';
import { IQuotationMaster } from 'app/shared/model/quotation-master.model';

@Injectable({ providedIn: 'root' })
export class QuotationMasterResolve implements Resolve<IQuotationMaster> {
  constructor(private service: QuotationMasterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<QuotationMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<QuotationMaster>) => response.ok),
        map((quotationMaster: HttpResponse<QuotationMaster>) => quotationMaster.body)
      );
    }
    return of(new QuotationMaster());
  }
}

export const quotationMasterRoute: Routes = [
  {
    path: '',
    component: QuotationMasterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Quotation Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QuotationMasterDetailComponent,
    resolve: {
      quotationMaster: QuotationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Quotation Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QuotationMasterUpdateComponent,
    resolve: {
      quotationMaster: QuotationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Quotation Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QuotationMasterUpdateComponent,
    resolve: {
      quotationMaster: QuotationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Quotation Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const quotationMasterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: QuotationMasterDeletePopupComponent,
    resolve: {
      quotationMaster: QuotationMasterResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Quotation Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

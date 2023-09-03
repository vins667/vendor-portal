import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendorBuyerAudit } from 'app/shared/model/vendor-buyer-audit.model';
import { VendorBuyerAuditService } from './vendor-buyer-audit.service';
import { VendorBuyerAuditComponent } from './vendor-buyer-audit.component';
import { VendorBuyerAuditDetailComponent } from './vendor-buyer-audit-detail.component';
import { VendorBuyerAuditUpdateComponent } from './vendor-buyer-audit-update.component';
import { VendorBuyerAuditDeletePopupComponent } from './vendor-buyer-audit-delete-dialog.component';
import { IVendorBuyerAudit } from 'app/shared/model/vendor-buyer-audit.model';
import { IVendorBuyerAuditDetailsBean } from 'app/shared/model/vendor-buyer-audit-details-bean.model';

@Injectable({ providedIn: 'root' })
export class VendorBuyerAuditResolve implements Resolve<IVendorBuyerAudit> {
  constructor(private service: VendorBuyerAuditService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVendorBuyerAudit> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VendorBuyerAudit>) => response.ok),
        map((vendorBuyerAudit: HttpResponse<VendorBuyerAudit>) => vendorBuyerAudit.body)
      );
    }
    return of(new VendorBuyerAudit());
  }
}

@Injectable({ providedIn: 'root' })
export class VendorBuyerAuditBuyerResolve implements Resolve<IVendorBuyerAuditDetailsBean> {
  constructor(private service: VendorBuyerAuditService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVendorBuyerAuditDetailsBean> {
    const buyerCode = route.params['buyerCode'];
    const vendorCode = route.params['vendorCode'];
    const auditCode = route.params['auditCode'];
    // console.log('vivek', buyerCode, ' ', vendorCode, ' ', auditCode);
    if (buyerCode && vendorCode) {
      return this.service.findByBuyer(buyerCode, vendorCode).pipe(
        filter((response: HttpResponse<IVendorBuyerAuditDetailsBean>) => response.ok),
        map((vendorBuyerAudit: HttpResponse<IVendorBuyerAuditDetailsBean>) => vendorBuyerAudit.body)
      );
    }
  }
}

export const vendorBuyerAuditRoute: Routes = [
  {
    path: '',
    component: VendorBuyerAuditComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'VendorBuyerAudits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VendorBuyerAuditDetailComponent,
    resolve: {
      vendorBuyerAudit: VendorBuyerAuditResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendorBuyerAudits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VendorBuyerAuditUpdateComponent,
    resolve: {
      vendorBuyerAuditDetail: VendorBuyerAuditBuyerResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendorBuyerAudits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VendorBuyerAuditUpdateComponent,
    resolve: {
      vendorBuyerAudit: VendorBuyerAuditResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendorBuyerAudits'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vendorBuyerAuditPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VendorBuyerAuditDeletePopupComponent,
    resolve: {
      vendorBuyerAudit: VendorBuyerAuditResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'VendorBuyerAudits'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

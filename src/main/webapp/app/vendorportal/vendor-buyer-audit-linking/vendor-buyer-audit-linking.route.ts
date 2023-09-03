import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendorBuyerAuditLinking } from 'app/shared/model/vendor-buyer-audit-linking.model';
import { VendorBuyerAuditLinkingService } from './vendor-buyer-audit-linking.service';
import { VendorBuyerAuditLinkingComponent } from './vendor-buyer-audit-linking.component';
import { VendorBuyerAuditLinkingDetailComponent } from './vendor-buyer-audit-linking-detail.component';
import { VendorBuyerAuditLinkingUpdateComponent } from './vendor-buyer-audit-linking-update.component';
import { VendorBuyerAuditLinkingDeletePopupComponent } from './vendor-buyer-audit-linking-delete-dialog.component';
import { IVendorBuyerAuditLinking } from 'app/shared/model/vendor-buyer-audit-linking.model';

@Injectable({ providedIn: 'root' })
export class VendorBuyerAuditLinkingResolve implements Resolve<IVendorBuyerAuditLinking> {
    constructor(private service: VendorBuyerAuditLinkingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VendorBuyerAuditLinking> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VendorBuyerAuditLinking>) => response.ok),
                map((vendorBuyerAuditLinking: HttpResponse<VendorBuyerAuditLinking>) => vendorBuyerAuditLinking.body)
            );
        }
        return of(new VendorBuyerAuditLinking());
    }
}

export const vendorBuyerAuditLinkingRoute: Routes = [
    {
        path: '',
        component: VendorBuyerAuditLinkingComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            defaultSort: 'id,asc',
            pageTitle: 'VendorBuyerAuditLinkings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VendorBuyerAuditLinkingDetailComponent,
        resolve: {
            vendorBuyerAuditLinking: VendorBuyerAuditLinkingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorBuyerAuditLinkings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VendorBuyerAuditLinkingUpdateComponent,
        resolve: {
            vendorBuyerAuditLinking: VendorBuyerAuditLinkingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorBuyerAuditLinkings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VendorBuyerAuditLinkingUpdateComponent,
        resolve: {
            vendorBuyerAuditLinking: VendorBuyerAuditLinkingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorBuyerAuditLinkings'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vendorBuyerAuditLinkingPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VendorBuyerAuditLinkingDeletePopupComponent,
        resolve: {
            vendorBuyerAuditLinking: VendorBuyerAuditLinkingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorBuyerAuditLinkings'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

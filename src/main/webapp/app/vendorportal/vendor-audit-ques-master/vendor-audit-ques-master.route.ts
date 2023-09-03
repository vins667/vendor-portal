import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';
import { VendorAuditQuesMasterService } from './vendor-audit-ques-master.service';
import { VendorAuditQuesMasterComponent } from './vendor-audit-ques-master.component';
import { VendorAuditQuesMasterDetailComponent } from './vendor-audit-ques-master-detail.component';
import { VendorAuditQuesMasterUpdateComponent } from './vendor-audit-ques-master-update.component';
import { VendorAuditQuesMasterDeletePopupComponent } from './vendor-audit-ques-master-delete-dialog.component';
import { IVendorAuditQuesMaster } from 'app/shared/model/vendor-audit-ques-master.model';

@Injectable({ providedIn: 'root' })
export class VendorAuditQuesMasterResolve implements Resolve<IVendorAuditQuesMaster> {
    constructor(private service: VendorAuditQuesMasterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VendorAuditQuesMaster> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VendorAuditQuesMaster>) => response.ok),
                map((vendorAuditQuesMaster: HttpResponse<VendorAuditQuesMaster>) => vendorAuditQuesMaster.body)
            );
        }
        return of(new VendorAuditQuesMaster());
    }
}

export const vendorAuditQuesMasterRoute: Routes = [
    {
        path: '',
        component: VendorAuditQuesMasterComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            defaultSort: 'id,asc',
            pageTitle: 'VendorAuditQuesMasters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VendorAuditQuesMasterDetailComponent,
        resolve: {
            vendorAuditQuesMaster: VendorAuditQuesMasterResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorAuditQuesMasters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VendorAuditQuesMasterUpdateComponent,
        resolve: {
            vendorAuditQuesMaster: VendorAuditQuesMasterResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorAuditQuesMasters'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VendorAuditQuesMasterUpdateComponent,
        resolve: {
            vendorAuditQuesMaster: VendorAuditQuesMasterResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorAuditQuesMasters'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vendorAuditQuesMasterPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VendorAuditQuesMasterDeletePopupComponent,
        resolve: {
            vendorAuditQuesMaster: VendorAuditQuesMasterResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'VendorAuditQuesMasters'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

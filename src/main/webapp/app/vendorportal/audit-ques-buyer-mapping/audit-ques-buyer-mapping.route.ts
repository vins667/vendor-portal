import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';
import { AuditQuesBuyerMappingService } from './audit-ques-buyer-mapping.service';
import { AuditQuesBuyerMappingComponent } from './audit-ques-buyer-mapping.component';
import { AuditQuesBuyerMappingDetailComponent } from './audit-ques-buyer-mapping-detail.component';
import { AuditQuesBuyerMappingUpdateComponent } from './audit-ques-buyer-mapping-update.component';
import { AuditQuesBuyerMappingDeletePopupComponent } from './audit-ques-buyer-mapping-delete-dialog.component';
import { IAuditQuesBuyerMapping } from 'app/shared/model/audit-ques-buyer-mapping.model';

@Injectable({ providedIn: 'root' })
export class AuditQuesBuyerMappingResolve implements Resolve<IAuditQuesBuyerMapping> {
    constructor(private service: AuditQuesBuyerMappingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AuditQuesBuyerMapping> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AuditQuesBuyerMapping>) => response.ok),
                map((auditQuesBuyerMapping: HttpResponse<AuditQuesBuyerMapping>) => auditQuesBuyerMapping.body)
            );
        }
        return of(new AuditQuesBuyerMapping());
    }
}

export const auditQuesBuyerMappingRoute: Routes = [
    {
        path: '',
        component: AuditQuesBuyerMappingComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            defaultSort: 'id,asc',
            pageTitle: 'AuditQuesBuyerMappings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AuditQuesBuyerMappingDetailComponent,
        resolve: {
            auditQuesBuyerMapping: AuditQuesBuyerMappingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'AuditQuesBuyerMappings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AuditQuesBuyerMappingUpdateComponent,
        resolve: {
            auditQuesBuyerMapping: AuditQuesBuyerMappingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'AuditQuesBuyerMappings'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AuditQuesBuyerMappingUpdateComponent,
        resolve: {
            auditQuesBuyerMapping: AuditQuesBuyerMappingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'AuditQuesBuyerMappings'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditQuesBuyerMappingPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AuditQuesBuyerMappingDeletePopupComponent,
        resolve: {
            auditQuesBuyerMapping: AuditQuesBuyerMappingResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMIN'],
            pageTitle: 'AuditQuesBuyerMappings'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

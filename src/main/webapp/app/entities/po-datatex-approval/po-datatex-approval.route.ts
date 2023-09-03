import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PoDatatexApproval } from 'app/shared/model/po-datatex-approval.model';
import { PoDatatexApprovalService } from './po-datatex-approval.service';
import { PoDatatexApprovalComponent } from './po-datatex-approval.component';
import { PoDatatexApprovalDetailComponent } from './po-datatex-approval-detail.component';
import { PoDatatexApprovalUpdateComponent } from './po-datatex-approval-update.component';
import { IPoDatatexApproval } from 'app/shared/model/po-datatex-approval.model';

@Injectable({ providedIn: 'root' })
export class PoDatatexApprovalResolve implements Resolve<IPoDatatexApproval> {
  constructor(private service: PoDatatexApprovalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPoDatatexApproval> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PoDatatexApproval>) => response.ok),
        map((poDatatexApproval: HttpResponse<PoDatatexApproval>) => poDatatexApproval.body)
      );
    }
    return of(new PoDatatexApproval());
  }
}

export const poDatatexApprovalRoute: Routes = [
  {
    path: '',
    component: PoDatatexApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'PoDatatexApprovals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PoDatatexApprovalDetailComponent,
    resolve: {
      poDatatexApproval: PoDatatexApprovalResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PoDatatexApprovals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PoDatatexApprovalUpdateComponent,
    resolve: {
      poDatatexApproval: PoDatatexApprovalResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PoDatatexApprovals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PoDatatexApprovalUpdateComponent,
    resolve: {
      poDatatexApproval: PoDatatexApprovalResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'PoDatatexApprovals'
    },
    canActivate: [UserRouteAccessService]
  }
];

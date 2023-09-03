import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConveyanceMasterApprovalService } from './conveyance-master-approval.service';
import { ConveyanceMasterApprovalComponent } from './conveyance-master-approval.component';
import { ConveyanceMasterApprovalUpdateComponent } from './conveyance-master-approval-update.component';
import { IConveyanceMaster, ConveyanceMaster } from 'app/shared/model/conveyance-master.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

@Injectable({ providedIn: 'root' })
export class ConveyanceMasterApprovalResolve implements Resolve<IConveyanceMaster> {
  constructor(private service: ConveyanceMasterApprovalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConveyanceMaster> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConveyanceMaster>) => response.ok),
        map((conveyanceMaster: HttpResponse<ConveyanceMaster>) => conveyanceMaster.body)
      );
    }
    return of(new ConveyanceMaster());
  }
}

export const conveyanceMasterApprovalRoute: Routes = [
  {
    path: '',
    component: ConveyanceMasterApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ConveyanceMaster Approval'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConveyanceMasterApprovalUpdateComponent,
    resolve: {
      conveyanceMaster: ConveyanceMasterApprovalResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'ConveyanceMaster Approval'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const conveyanceMasterApprovalPopupRoute: Routes = [];

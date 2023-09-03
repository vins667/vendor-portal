import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeliveryChallan, IDeliveryChallan } from 'app/shared/model/delivery-challan.model';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { DeliverChallanApprovalService } from './deliver-challan-approval.service';
import { DeliverChallanApprovalComponent } from './deliver-challan-approval.component';
import { DeliverChallanApprovalUpdateComponent } from './deliver-challan-approval-update.component';

@Injectable({ providedIn: 'root' })
export class DeliverChallanApprovalResolve implements Resolve<IDeliveryChallan> {
  constructor(private service: DeliverChallanApprovalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DeliveryChallan> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<DeliveryChallan>) => response.ok),
        map((deliveryChallan: HttpResponse<DeliveryChallan>) => deliveryChallan.body)
      );
    }
    return of(new DeliveryChallan());
  }
}

export const deliverChallanApprovalRoute: Routes = [
  {
    path: '',
    component: DeliverChallanApprovalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'DeliverChallanApproval'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliverChallanApprovalUpdateComponent,
    resolve: {
      deliveryChallan: DeliverChallanApprovalResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ADMIN_USER'],
      pageTitle: 'DeliveryChallan'
    },
    canActivate: [UserRouteAccessService]
  }
];

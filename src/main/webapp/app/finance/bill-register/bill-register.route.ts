import { Injectable, NgModule } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterModule, Routes } from '@angular/router';

import { BillRegisterComponent } from './bill-register.component';
import { BillRegisterUpdateComponent } from './bill-register-update.component';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { BillRegisterMaster, IBillRegisterMaster } from 'app/finance/bill-register/bill-register-master.model';
import { BillRegisterMasterService } from 'app/finance/bill-register/bill-register-master.service';

@Injectable({ providedIn: 'root' })
export class BillRegisterRoutingResolveService implements Resolve<IBillRegisterMaster> {
  constructor(protected service: BillRegisterMasterService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillRegisterMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((billRegisterMaster: HttpResponse<IBillRegisterMaster>) => {
          if (billRegisterMaster.body) {
            return of(billRegisterMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillRegisterMaster());
  }
}

export const billRegisterRoute: Routes = [
  {
    path: '',
    component: BillRegisterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Bill Register'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BillRegisterUpdateComponent,
    resolve: {
      billRegisterMaster: BillRegisterRoutingResolveService
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Bill Register'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BillRegisterUpdateComponent,
    resolve: {
      billRegisterMaster: BillRegisterRoutingResolveService
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Bill Register'
    },
    canActivate: [UserRouteAccessService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(billRegisterRoute)],
  exports: [RouterModule]
})
export class BillRegisterRoute {}

import { Injectable, NgModule } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterModule, Routes } from '@angular/router';

import { BillRegisterImportComponent } from './bill-register-import.component';
import { BillRegisterImportUpdateComponent } from './bill-register-import-update.component';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { BillRegisterImportMaster, IBillRegisterImportMaster } from 'app/finance/bill-register-import/bill-register-import-master.model';
import { BillRegisterImportMasterService } from 'app/finance/bill-register-import/bill-register-import-master.service';

@Injectable({ providedIn: 'root' })
export class BillRegisterImportRoutingResolveService implements Resolve<IBillRegisterImportMaster> {
  constructor(protected service: BillRegisterImportMasterService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillRegisterImportMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((billRegisterMaster: HttpResponse<IBillRegisterImportMaster>) => {
          if (billRegisterMaster.body) {
            return of(billRegisterMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillRegisterImportMaster());
  }
}

export const billRegisterRoute: Routes = [
  {
    path: '',
    component: BillRegisterImportComponent,
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
    component: BillRegisterImportUpdateComponent,
    resolve: {
      billRegisterMaster: BillRegisterImportRoutingResolveService
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Bill Register'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BillRegisterImportUpdateComponent,
    resolve: {
      billRegisterMaster: BillRegisterImportRoutingResolveService
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
export class BillRegisterImportRoute {}

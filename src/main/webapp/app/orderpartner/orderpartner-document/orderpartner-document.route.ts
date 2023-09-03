import { Injectable, NgModule } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterModule, RouterStateSnapshot, Routes } from '@angular/router';

import { OrderpartnerDocumentComponent } from './orderpartner-document.component';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { OrderpartnerDocumentUpdateComponent } from './orderpartner-document-update.component';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { IOrderpartnerDocument, OrderpartnerDocument } from './orderpartner-document.model';
import { OrderpartnerDocumentService } from './orderpartner-document.service';
import { OrderpartnerDocumentDetailComponent } from './orderpartner-document-detail.component';

@Injectable({ providedIn: 'root' })
export class OrderpartnerDocumentResolve implements Resolve<IOrderpartnerDocument> {
  constructor(private service: OrderpartnerDocumentService) {}
  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<IOrderpartnerDocument> | Promise<IOrderpartnerDocument> | IOrderpartnerDocument {
    const customersuppliertype = route.params['customersuppliertype'] ? route.params['customersuppliertype'] : null;
    const customersuppliercode = route.params['customersuppliercode'] ? route.params['customersuppliercode'] : null;
    if (customersuppliertype && customersuppliercode) {
      return this.service.find(customersuppliertype, customersuppliercode).pipe(
        filter((response: HttpResponse<OrderpartnerDocument>) => response.ok),
        map((paymentRequestForm: HttpResponse<OrderpartnerDocument>) => paymentRequestForm.body)
      );
    }
    return of(new OrderpartnerDocument());
  }
}
export const orderpartnerDocumentRoute: Routes = [
  {
    path: '',
    component: OrderpartnerDocumentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'Order Partner'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':customersuppliertype/:customersuppliercode/edit',
    component: OrderpartnerDocumentUpdateComponent,
    resolve: {
      orderpartnerDocument: OrderpartnerDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Order Partner'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'upload',
    component: OrderpartnerDocumentDetailComponent,
    resolve: {
      orderpartnerDocument: OrderpartnerDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Order Partner'
    },
    canActivate: [UserRouteAccessService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(orderpartnerDocumentRoute)],
  exports: [RouterModule]
})
export class OrderpartnerDocumentRoute {}

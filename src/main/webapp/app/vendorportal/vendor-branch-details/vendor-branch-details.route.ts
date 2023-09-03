import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';
import { VendorBranchDetailsService } from './vendor-branch-details.service';
import { VendorBranchDetailsDetailComponent } from './vendor-branch-details-detail.component';
import { VendorBranchDetailsUpdateComponent } from './vendor-branch-details-update.component';
import { VendorBranchDetailsDeletePopupComponent } from './vendor-branch-details-delete-dialog.component';
import { IVendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';

@Injectable({ providedIn: 'root' })
export class VendorBranchDetailsResolve implements Resolve<IVendorBranchDetails> {
  constructor(private service: VendorBranchDetailsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VendorBranchDetails> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VendorBranchDetails>) => response.ok),
        map((vendorBranchDetails: HttpResponse<VendorBranchDetails>) => vendorBranchDetails.body)
      );
    }
    return of(new VendorBranchDetails());
  }
}

export const vendorBranchDetailsRoute: Routes = [
  {
    path: ':id/view',
    component: VendorBranchDetailsDetailComponent,
    resolve: {
      vendorBranchDetails: VendorBranchDetailsResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'VendorBranchDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VendorBranchDetailsUpdateComponent,
    resolve: {
      vendorBranchDetails: VendorBranchDetailsResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'VendorBranchDetails'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VendorBranchDetailsUpdateComponent,
    resolve: {
      vendorBranchDetails: VendorBranchDetailsResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'VendorBranchDetails'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const vendorBranchDetailsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: VendorBranchDetailsDeletePopupComponent,
    resolve: {
      vendorBranchDetails: VendorBranchDetailsResolve
    },
    data: {
      authorities: ['ROLE_ADMIN', 'ROLE_USER'],
      pageTitle: 'VendorBranchDetails'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

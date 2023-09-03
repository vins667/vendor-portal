import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FollowupBuyer } from './followup-buyer.model';
import { FollowupBuyerService } from './followup-buyer.service';
import { FollowupBuyerComponent } from './followup-buyer.component';
import { FollowupBuyerDetailComponent } from './followup-buyer-detail.component';
import { FollowupBuyerUpdateComponent } from './followup-buyer-update.component';
import { FollowupBuyerDeletePopupComponent } from './followup-buyer-delete-dialog.component';
import { IFollowupBuyer } from './followup-buyer.model';

@Injectable({ providedIn: 'root' })
export class FollowupBuyerResolve implements Resolve<IFollowupBuyer> {
  constructor(private service: FollowupBuyerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFollowupBuyer> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<FollowupBuyer>) => response.ok),
        map((followupBuyer: HttpResponse<FollowupBuyer>) => followupBuyer.body)
      );
    }
    return of(new FollowupBuyer());
  }
}

export const followupBuyerRoute: Routes = [
  {
    path: '',
    component: FollowupBuyerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FollowupBuyerDetailComponent,
    resolve: {
      followupBuyer: FollowupBuyerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FollowupBuyerUpdateComponent,
    resolve: {
      followupBuyer: FollowupBuyerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FollowupBuyerUpdateComponent,
    resolve: {
      followupBuyer: FollowupBuyerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const followupBuyerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FollowupBuyerDeletePopupComponent,
    resolve: {
      followupBuyer: FollowupBuyerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Buyer Master'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];

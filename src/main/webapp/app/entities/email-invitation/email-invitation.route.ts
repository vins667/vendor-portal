import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EmailInvitation } from 'app/shared/model/email-invitation.model';
import { EmailInvitationService } from './email-invitation.service';
import { EmailInvitationComponent } from './email-invitation.component';
import { EmailInvitationUpdateComponent } from './email-invitation-update.component';
import { IEmailInvitation } from 'app/shared/model/email-invitation.model';

@Injectable({ providedIn: 'root' })
export class EmailInvitationResolve implements Resolve<IEmailInvitation> {
  constructor(private service: EmailInvitationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EmailInvitation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EmailInvitation>) => response.ok),
        map((emailInvitation: HttpResponse<EmailInvitation>) => emailInvitation.body)
      );
    }
    return of(new EmailInvitation());
  }
}

export const emailInvitationRoute: Routes = [
  {
    path: '',
    component: EmailInvitationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'id,dsc',
      pageTitle: 'EmailInvitations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmailInvitationUpdateComponent,
    resolve: {
      emailInvitation: EmailInvitationResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'EmailInvitations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const emailInvitationPopupRoute: Routes = [];

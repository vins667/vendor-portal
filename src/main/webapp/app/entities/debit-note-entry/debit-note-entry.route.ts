import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DebitNoteEntryService } from './debit-note-entry.service';
import { Findocument, IFindocument } from 'app/shared/db2/model/findocument.model';
import { DebitNoteEntryComponent } from 'app/entities/debit-note-entry/debit-note-entry.component';

@Injectable({ providedIn: 'root' })
export class DebitNoteEntryResolve implements Resolve<IFindocument> {
  constructor(private service: DebitNoteEntryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFindocument> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Findocument>) => response.ok),
        map((findocument: HttpResponse<Findocument>) => findocument.body)
      );
    }
    return of(new Findocument());
  }
}

export const debitNoteEntryRoute: Routes = [
  {
    path: '',
    component: DebitNoteEntryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'style,asc',
      pageTitle: 'DebitNoteEntries'
    },
    canActivate: [UserRouteAccessService]
  }
];

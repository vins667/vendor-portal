import { Findocument, IFindocument } from '../../shared/db2/model/findocument.model';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { DebitNoteEntryService } from '../debit-note-entry/debit-note-entry.service';
import { FinDocumentGlChangeService } from './fin-document-gl-change.service';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { DebitNoteEntryComponent } from '../debit-note-entry/debit-note-entry.component';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../core/auth/user-route-access-service';
import { FinDocumentGlChangeComponent } from './fin-document-gl-change.component';
import { Injectable } from '@angular/core';
@Injectable({ providedIn: 'root' })
export class FinDocumentGlChangeResolve implements Resolve<IFindocument> {
  constructor(private service: FinDocumentGlChangeService) {}

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
export const finDocumentGlChangeRoute: Routes = [
  {
    path: '',
    component: FinDocumentGlChangeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      defaultSort: 'style,asc',
      pageTitle: 'Fin Document GL Change'
    },
    canActivate: [UserRouteAccessService]
  }
];

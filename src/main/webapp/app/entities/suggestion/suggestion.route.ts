import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Suggestion } from 'app/shared/model/suggestion.model';
import { SuggestionService } from './suggestion.service';
import { SuggestionUpdateComponent } from './suggestion-update.component';
import { ISuggestion } from 'app/shared/model/suggestion.model';

@Injectable({ providedIn: 'root' })
export class SuggestionResolve implements Resolve<ISuggestion> {
  constructor(private service: SuggestionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Suggestion> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Suggestion>) => response.ok),
        map((suggestion: HttpResponse<Suggestion>) => suggestion.body)
      );
    }
    return of(new Suggestion());
  }
}

export const suggestionRoute: Routes = [
  {
    path: 'new',
    component: SuggestionUpdateComponent,
    resolve: {
      suggestion: SuggestionResolve
    },
    data: {
      authorities: ['ROLE_USER', 'ROLE_ADMIN'],
      pageTitle: 'Suggestions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const suggestionPopupRoute: Routes = [];

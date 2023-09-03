import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { Account } from 'app/core/user/account.model';
import { JhiTrackerService } from '../tracker/tracker.service';
import { IMaster, Master } from 'app/shared/model/master.modal';

@Injectable({ providedIn: 'root' })
export class AccountService {
  private userIdentity: any;
  private authenticated = false;
  private activeTheme: any;
  private authenticationState = new Subject<any>();

  constructor(private http: HttpClient, private trackerService: JhiTrackerService) {}

  fetch(): Observable<HttpResponse<Account>> {
    return this.http.get<Account>(SERVER_API_URL + 'api/account', { observe: 'response' });
  }

  save(account: any): Observable<HttpResponse<any>> {
    return this.http.post(SERVER_API_URL + 'api/account', account, { observe: 'response' });
  }

  update(theme: any): Observable<HttpResponse<IMaster>> {
    const master = new Master();
    master.id = theme;
    return this.http.post(SERVER_API_URL + 'api/account-theme', master, { observe: 'response' });
  }

  authenticate(identity) {
    this.userIdentity = identity;
    this.authenticated = identity !== null;
    this.authenticationState.next(this.userIdentity);
  }

  hasAnyAuthority(authorities: string[]): boolean {
    if (!this.authenticated || !this.userIdentity || !this.userIdentity.authorities) {
      return false;
    }

    return authorities.some((authority: string) => this.userIdentity.authorities.includes(authority));
  }

  hasAuthority(authority: string): Promise<boolean> {
    if (!this.authenticated) {
      return Promise.resolve(false);
    }

    return this.identity().then(
      id => {
        return Promise.resolve(id.authorities && id.authorities.includes(authority));
      },
      () => {
        return Promise.resolve(false);
      }
    );
  }

  identity(force?: boolean): Promise<Account> {
    if (force) {
      this.userIdentity = undefined;
    }

    // check and see if we have retrieved the userIdentity data from the server.
    // if we have, reuse it by immediately resolving
    if (this.userIdentity) {
      return Promise.resolve(this.userIdentity);
    }

    // retrieve the userIdentity data from the server, update the identity object, and then resolve.
    return this.fetch()
      .toPromise()
      .then(response => {
        const account: Account = response.body;
        if (account) {
          this.userIdentity = account;
          this.authenticated = true;
          this.activeTheme = account.theme;
          this.trackerService.connect();
        } else {
          this.userIdentity = null;
          this.authenticated = false;
        }
        this.authenticationState.next(this.userIdentity);
        return this.userIdentity;
      })
      .catch(err => {
        if (this.trackerService.stompClient && this.trackerService.stompClient.connected) {
          this.trackerService.disconnect();
        }
        this.userIdentity = null;
        this.authenticated = false;
        this.authenticationState.next(this.userIdentity);
        return null;
      });
  }

  isAuthenticated(): boolean {
    return this.authenticated;
  }

  fetchTheme(): string {
    return this.activeTheme;
  }

  setTheme(theme: string) {
    this.activeTheme = theme;
  }

  isIdentityResolved(): boolean {
    return this.userIdentity !== undefined;
  }

  getAuthenticationState(): Observable<any> {
    return this.authenticationState.asObservable();
  }

  getImageUrl(): string {
    return this.isIdentityResolved() ? this.userIdentity.imageUrl : null;
  }
}

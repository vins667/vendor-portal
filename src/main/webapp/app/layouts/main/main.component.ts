import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd, NavigationError } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AccountService } from 'app/core/auth/account.service';
import { CommonService } from 'app/common.service';

@Component({
  selector: 'jhi-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.scss']
})
export class JhiMainComponent implements OnInit {
  commonService: any;
  constructor(private titleService: Title, private router: Router, private cs: CommonService, private accountService: AccountService) {
    this.commonService = cs;
  }

  private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
    let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : 'vamaniportalApp';
    if (routeSnapshot.firstChild) {
      title = this.getPageTitle(routeSnapshot.firstChild) || title;
    }
    return title;
  }

  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.titleService.setTitle(this.getPageTitle(this.router.routerState.snapshot.root));
        this.cs.navbarToggleValue = false;
        this.cs.sidebarToggleValue = true;
      }
      if (event instanceof NavigationError && event.error.status === 404) {
        this.router.navigate(['/404']);
      }
    });
  }

  onActivate(event) {
    window.scroll(0, 0);
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  activeTheme() {
    return this.accountService.fetchTheme();
  }
}

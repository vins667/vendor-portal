import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Account } from 'app/core/user/account.model';
import { AccountService } from 'app/core/auth/account.service';
import { IMenuMaster } from 'app/shared/model/menu-master.model';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'jhi-qlik-dashboard',
  templateUrl: './qlik-dashboard.component.html',
  encapsulation: ViewEncapsulation.None
})
export class QlikDashboardComponent implements OnInit, AfterViewInit {
  account: Account;
  menuMaster: IMenuMaster;
  map: any;
  qlikUrlTemp: any;
  qlikUrl2Temp: any;
  qlikUrl3Temp: any;
  qlikUrl4Temp: any;
  qlikUrl5Temp: any;

  constructor(
    private accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected sanitizer: DomSanitizer,
    private ref: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.account = account;
    });
    this.activatedRoute.data.subscribe(({ menuMaster }) => {
      this.menuMaster = menuMaster;
      this.map = new Map();
      if (this.menuMaster) {
        if (this.menuMaster.qlikLabel) {
          this.qlikUrlTemp = this.sanitizer.bypassSecurityTrustResourceUrl(this.menuMaster.qlikUrl);
        }
        if (this.menuMaster.qlikLabelTwo) {
          this.qlikUrl2Temp = this.sanitizer.bypassSecurityTrustResourceUrl(this.menuMaster.qlikUrlTwo);
        }
        if (this.menuMaster.qlikLabelThree) {
          this.qlikUrl3Temp = this.sanitizer.bypassSecurityTrustResourceUrl(this.menuMaster.qlikUrlThree);
        }
        if (this.menuMaster.qlikLabelFour) {
          this.qlikUrl4Temp = this.sanitizer.bypassSecurityTrustResourceUrl(this.menuMaster.qlikUrlFour);
        }
        if (this.menuMaster.qlikLabelFive) {
          this.qlikUrl5Temp = this.sanitizer.bypassSecurityTrustResourceUrl(this.menuMaster.qlikUrlFive);
        }
      }
      this.menuMaster.qlikUrlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.menuMaster.qlikUrl);
    });
  }

  ngAfterViewInit() {
    this.ref.detach();
  }
}

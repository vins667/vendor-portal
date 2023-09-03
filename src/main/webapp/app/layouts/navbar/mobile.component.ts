import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-mobile-verify',
  templateUrl: './mobile.component.html',
  styleUrls: ['navbar.scss']
})
export class MobileComponent implements OnInit {
  currentAccount: any;

  constructor(private accountService: AccountService) {}

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
  }
}

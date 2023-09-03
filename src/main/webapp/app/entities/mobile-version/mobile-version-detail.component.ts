import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMobileVersion } from 'app/shared/model/mobile-version.model';

@Component({
  selector: 'jhi-mobile-version-detail',
  templateUrl: './mobile-version-detail.component.html'
})
export class MobileVersionDetailComponent implements OnInit {
  mobileVersion: IMobileVersion;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mobileVersion }) => {
      this.mobileVersion = mobileVersion;
    });
  }

  previousState() {
    window.history.back();
  }
}

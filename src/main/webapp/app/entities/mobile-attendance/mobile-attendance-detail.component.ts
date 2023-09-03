import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';

@Component({
  selector: 'jhi-mobile-attendance-detail',
  templateUrl: './mobile-attendance-detail.component.html'
})
export class MobileAttendanceDetailComponent implements OnInit {
  mobileAttendance: IMobileAttendance;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mobileAttendance }) => {
      this.mobileAttendance = mobileAttendance;
    });
  }

  previousState() {
    window.history.back();
  }
}

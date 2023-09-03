import { Component, OnInit } from '@angular/core';
import { IDoctorSchedule } from 'app/shared/model/doctor-schedule.model';
import { DoctorScheduleService } from './doctor-schedule.service';

@Component({
  selector: 'jhi-doctor-schedule',
  templateUrl: './doctor-schedule.component.html',
  styleUrls: ['navbar.scss']
})
export class DoctorScheduleComponent implements OnInit {
  doctorSchedule: IDoctorSchedule;

  constructor(public doctorScheduleService: DoctorScheduleService) {}

  ngOnInit() {
    this.doctorScheduleService.byUnit().subscribe(doctorSchedule => {
      this.doctorSchedule = doctorSchedule.body;
    });
  }
}

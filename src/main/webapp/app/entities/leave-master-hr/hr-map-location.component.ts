import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';

@Component({
  selector: 'jhi-hr-map-location',
  templateUrl: 'hr-map-location.component.html',
  styleUrls: ['hr-map-location.component.scss']
})
export class HrMapLocationComponent implements OnInit {
  title = 'My first AGM project';
  lat: number;
  lng: number;
  imagePath: string;
  name: string;
  mobileAttendance: IMobileAttendance;

  @ViewChild('agmMap', { static: true })
  agmMap: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    this.agmMap.zoom = 18;
    this.lat = Number(this.mobileAttendance.latitude);
    this.lng = Number(this.mobileAttendance.longitude);
    this.imagePath = this.mobileAttendance.fileName;
    this.name = 'Vivek Jaiswal';
  }

  clear() {
    this.activeModal.dismiss('cancel');
  }

  onMouseOver(infoWindow, gm) {
    if (gm.lastOpen != null) {
      gm.lastOpen.close();
    }

    gm.lastOpen = infoWindow;

    infoWindow.open();
  }
}

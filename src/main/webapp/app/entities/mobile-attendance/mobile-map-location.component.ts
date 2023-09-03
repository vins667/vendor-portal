import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';

@Component({
  selector: 'jhi-mobile-map-location',
  templateUrl: 'mobile-map-location.component.html',
  styleUrls: ['mobile-map-location.component.scss']
})
export class MobileMapLocationComponent implements OnInit {
  title = 'My first AGM project';
  mobileAttendance: IMobileAttendance;
  lat: number;
  lng: number;
  imagePath: string;
  name: string;

  @ViewChild('agmMap', { static: true })
  agmMap: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    if (this.mobileAttendance !== null && this.mobileAttendance.id !== undefined) {
      this.agmMap.zoom = 18;
      this.lat = Number(this.mobileAttendance.latitude);
      this.lng = Number(this.mobileAttendance.longitude);
      this.imagePath = this.mobileAttendance.fileName;
    }
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

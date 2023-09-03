import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IMobileAttendance } from 'app/shared/model/mobile-attendance.model';
import { AccountService } from 'app/core/auth/account.service';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LeaveMobileMapLocationComponent } from './leave-mobile-map-location.component';

@Component({
  selector: 'jhi-leave-mobile-attendance',
  templateUrl: './leave-mobile-attendance.component.html',
  styleUrls: ['./leave-mobile-map-location.component.scss']
})
export class LeaveMobileAttendanceComponent implements OnInit {
  currentAccount: any;
  mobileAttendances: IMobileAttendance[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  protected ngbModalRef: NgbModalRef;

  lat: number;
  lng: number;
  imagePath: string;
  name: string;

  constructor(
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    private modalService: NgbModal,
    public activeModal: NgbActiveModal
  ) {}

  ngOnInit() {}

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

  maps(mobileAttendance) {
    this.ngbModalRef = this.modalService.open(LeaveMobileMapLocationComponent as Component, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ngbModalRef.componentInstance.mobileAttendance = mobileAttendance;
  }

  addData(mobileAttendance) {
    this.eventManager.broadcast({ name: 'selectedLeaveMobileAttendance', content: mobileAttendance });
  }
}

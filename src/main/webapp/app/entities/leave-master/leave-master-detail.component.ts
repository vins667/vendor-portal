import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ILeaveMaster } from 'app/shared/model/leave-master.model';
import { HttpResponse } from '@angular/common/http';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { EmployeeViewService } from 'app/entities/employee-view';
import { LeaveMobileMapLocationComponent } from './leave-mobile-map-location.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-leave-master-detail',
  templateUrl: './leave-master-detail.component.html'
})
export class LeaveMasterDetailComponent implements OnInit {
  leaveMaster: ILeaveMaster;
  hodEmployeeView: IEmployeeView;
  protected ngbModalRef: NgbModalRef;

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected employeeViewService: EmployeeViewService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ leaveMaster }) => {
      this.leaveMaster = leaveMaster;
      this.employeeViewService.find(this.leaveMaster.hodApprovedBy).subscribe((res2: HttpResponse<IEmployeeView>) => {
        this.hodEmployeeView = res2.body;
      });
    });
  }

  previousState() {
    window.history.back();
  }

  maps(mobileAttendance) {
    this.ngbModalRef = this.modalService.open(LeaveMobileMapLocationComponent as Component, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ngbModalRef.componentInstance.mobileAttendance = mobileAttendance;
  }
}

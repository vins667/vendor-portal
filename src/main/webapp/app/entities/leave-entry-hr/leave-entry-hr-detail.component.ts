import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ILeaveEntryHr } from 'app/shared/model/leave-entry-hr.model';
import { IEmployeeView } from 'app/shared/model/employee-view.model';
import { HttpResponse } from '@angular/common/http';
import { EmployeeViewService } from '../employee-view/employee-view.service';

@Component({
  selector: 'jhi-leave-entry-hr-detail',
  templateUrl: './leave-entry-hr-detail.component.html'
})
export class LeaveEntryHrDetailComponent implements OnInit {
  leaveEntryHr: ILeaveEntryHr;

  hodEmployeeView: IEmployeeView;

  constructor(protected activatedRoute: ActivatedRoute, protected employeeViewService: EmployeeViewService) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ leaveEntryHr }) => {
      this.leaveEntryHr = leaveEntryHr;
      this.employeeViewService.find(this.leaveEntryHr.hodApprovedBy).subscribe((res2: HttpResponse<IEmployeeView>) => {
        this.hodEmployeeView = res2.body;
      });
    });
  }

  previousState() {
    window.history.back();
  }
}

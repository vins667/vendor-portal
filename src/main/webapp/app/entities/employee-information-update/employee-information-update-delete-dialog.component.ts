import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IEmployeeInformationUpdate } from 'app/shared/model/employee-information-update.model';
import { EmployeeInformationUpdateService } from './employee-information-update.service';

@Component({
  selector: 'jhi-employee-information-update-delete-dialog',
  templateUrl: './employee-information-update-delete-dialog.component.html'
})
export class EmployeeInformationUpdateDeleteDialogComponent {
  employeeInformationUpdate: IEmployeeInformationUpdate;

  constructor(
    protected employeeInformationUpdateService: EmployeeInformationUpdateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.employeeInformationUpdateService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'employeeInformationUpdateListModification',
        content: 'Deleted an employeeInformationUpdate'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-employee-information-update-delete-popup',
  template: ''
})
export class EmployeeInformationUpdateDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ employeeInformationUpdate }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EmployeeInformationUpdateDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.employeeInformationUpdate = employeeInformationUpdate;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/employee-information-update', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/employee-information-update', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}

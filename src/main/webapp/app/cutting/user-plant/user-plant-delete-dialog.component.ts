import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserPlant } from 'app/shared/model/user-plant.model';
import { UserPlantService } from './user-plant.service';

@Component({
  selector: 'jhi-user-plant-delete-dialog',
  templateUrl: './user-plant-delete-dialog.component.html'
})
export class UserPlantDeleteDialogComponent {
  userPlant: IUserPlant;

  constructor(protected userPlantService: UserPlantService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.userPlantService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'userPlantListModification',
        content: 'Deleted an userPlant'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-user-plant-delete-popup',
  template: ''
})
export class UserPlantDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userPlant }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(UserPlantDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.userPlant = userPlant;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/user-plant', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/user-plant', { outlets: { popup: null } }]);
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

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBillRegister } from './bill-register.model';
import { BillRegisterService } from './bill-register.service';
import { JhiEventManager } from 'ng-jhipster';
import { BillRegisterMasterService } from 'app/finance/bill-register/bill-register-master.service';

@Component({
  templateUrl: './bill-register-delete-dialog.component.html'
})
export class BillRegisterDeleteDialogComponent {
  billRegister?: IBillRegister;

  constructor(
    protected billRegisterService: BillRegisterMasterService,
    protected activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billRegisterService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'billRegisterEntryListModification',
        content: 'Deleted an assetMaster'
      });
      this.activeModal.close('deleted');
    });
  }
}

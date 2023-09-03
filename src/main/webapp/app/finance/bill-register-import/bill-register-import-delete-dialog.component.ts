import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBillRegisterImport } from './bill-register-import.model';
import { BillRegisterImportService } from './bill-register-import.service';
import { JhiEventManager } from 'ng-jhipster';
import { BillRegisterImportMasterService } from 'app/finance/bill-register-import/bill-register-import-master.service';

@Component({
  templateUrl: './bill-register-import-delete-dialog.component.html'
})
export class BillRegisterImportDeleteDialogComponent {
  billRegister?: IBillRegisterImport;

  constructor(
    protected billRegisterService: BillRegisterImportMasterService,
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

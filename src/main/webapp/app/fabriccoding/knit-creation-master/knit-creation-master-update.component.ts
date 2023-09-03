import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IKnitCreationMaster } from 'app/shared/model/knit-creation-master.model';
import { KnitCreationMasterService } from './knit-creation-master.service';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { KnitCreationSearchMasterComponent } from './knit-creation-search-master.component';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-knit-creation-master-update',
  templateUrl: './knit-creation-master-update.component.html'
})
export class KnitCreationMasterUpdateComponent implements OnInit, OnDestroy {
  isSaving: boolean;
  knitCreationMaster: IKnitCreationMaster;
  protected ngbModalRef: NgbModalRef;
  data: string;

  constructor(
    protected knitCreationMasterService: KnitCreationMasterService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    private eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {}

  searchUUID(data: string) {
    this.ngbModalRef = this.modalService.open(KnitCreationSearchMasterComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
    this.ngbModalRef.componentInstance.data = data;
    this.data = data;
  }

  ngOnInit() {
    this.registerChangeInKnitCreationMasters();
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ knitCreationMaster }) => {
      this.knitCreationMaster = knitCreationMaster;
      if (this.knitCreationMaster && this.knitCreationMaster.id !== undefined) {
        this.getKeyPressValue();
      }
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.knitCreationMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.knitCreationMasterService.update(this.knitCreationMaster));
    } else {
      this.subscribeToSaveResponse(this.knitCreationMasterService.create(this.knitCreationMaster));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKnitCreationMaster>>) {
    result.subscribe(() => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res));
  }

  clearField(data) {
    if (data === 'A') {
      this.knitCreationMaster.yarnCountMaster = undefined;
    } else if (data === 'B') {
      this.knitCreationMaster.yarnTypeMaster = undefined;
    } else if (data === 'C') {
      this.knitCreationMaster.knitTypeMaster = undefined;
    } else if (data === 'D') {
      this.knitCreationMaster.knitProcessMaster = undefined;
    }
    this.getKeyPressValue();
  }

  getKeyPressValue() {
    let tempCode = '';
    let tempDesc = '';
    if (this.knitCreationMaster && this.knitCreationMaster.yarnCountMaster && this.knitCreationMaster.yarnCountMaster.code !== undefined) {
      tempCode += this.knitCreationMaster.yarnCountMaster.code;
      tempDesc += this.knitCreationMaster.yarnCountMaster.description;
    }
    if (this.knitCreationMaster && this.knitCreationMaster.yarnTypeMaster && this.knitCreationMaster.yarnTypeMaster.code !== undefined) {
      tempCode += this.knitCreationMaster.yarnTypeMaster.code;
      if (tempDesc.length > 0) {
        tempDesc += '..' + this.knitCreationMaster.yarnTypeMaster.description;
      } else {
        tempDesc += this.knitCreationMaster.yarnTypeMaster.description;
      }
    }
    if (this.knitCreationMaster && this.knitCreationMaster.knitTypeMaster && this.knitCreationMaster.knitTypeMaster.code !== undefined) {
      tempCode += this.knitCreationMaster.knitTypeMaster.code;
      if (tempDesc.length > 0) {
        tempDesc += '..' + this.knitCreationMaster.knitTypeMaster.description;
      } else {
        tempDesc += this.knitCreationMaster.knitTypeMaster.description;
      }
    }
    if (
      this.knitCreationMaster &&
      this.knitCreationMaster.knitProcessMaster &&
      this.knitCreationMaster.knitProcessMaster.code !== undefined
    ) {
      tempCode += this.knitCreationMaster.knitProcessMaster.code;
      if (tempDesc.length > 0) {
        tempDesc += '..' + this.knitCreationMaster.knitProcessMaster.description;
      } else {
        tempDesc += this.knitCreationMaster.knitProcessMaster.description;
      }
    }
    this.knitCreationMaster.code = tempCode;
    this.knitCreationMaster.description = tempDesc;
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res?: HttpErrorResponse) {
    this.isSaving = false;
    this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }

  registerChangeInKnitCreationMasters() {
    this.eventManager.subscribe('selectedknitCreationMaster', message => {
      const knitCreationMasters = message.content;
      if (this.data === 'A') {
        this.knitCreationMaster.yarnCountMaster = knitCreationMasters;
      } else if (this.data === 'B') {
        this.knitCreationMaster.yarnTypeMaster = knitCreationMasters;
      } else if (this.data === 'C') {
        this.knitCreationMaster.knitTypeMaster = knitCreationMasters;
      } else if (this.data === 'D') {
        this.knitCreationMaster.knitProcessMaster = knitCreationMasters;
      }
      this.getKeyPressValue();
    });
  }
}

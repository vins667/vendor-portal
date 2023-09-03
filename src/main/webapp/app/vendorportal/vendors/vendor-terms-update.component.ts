import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { JhiEventManager } from 'ng-jhipster';
import { IVendorTerms, VendorTerms } from 'app/shared/model/vendor-terms.model';
@Component({
  selector: 'jhi-vendor-terms-update',
  templateUrl: './vendor-terms-update.component.html'
})
export class VendorTermsUpdateComponent implements OnInit {
  @Input() vendorTerms: VendorTerms;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;
  toast: any;

  constructor(protected activatedRoute: ActivatedRoute, private snotifyService: SnotifyService, protected eventManager: JhiEventManager) {}

  ngOnInit() {
    if (this.vendorTerms && this.vendorTerms.id) {
      this.isSaving = true;
    } else {
      this.isSaving = false;
    }
  }

  previousState() {
    window.history.back();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorTerms>>) {
    result.subscribe((res: HttpResponse<IVendorTerms>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res) {
    this.isSaving = true;
    this.snotifyService.remove(this.toast.id);
    this.snotifyService.success('Terms agree successfully', '', toastConfig);
    this.eventManager.broadcast({ name: 'tabModification', content: '' });
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}

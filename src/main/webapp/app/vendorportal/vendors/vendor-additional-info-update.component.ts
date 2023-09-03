import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { ITurnoverMaster } from 'app/shared/model/turnover-master.model';
import { TurnoverMasterService } from 'app/vendorportal/turnover-master';
import { SnotifyService } from 'ng-snotify';
import { JhiEventManager } from 'ng-jhipster';
import { VendorAdditionalInfo } from 'app/shared/model/vendor-additional-info.model';

@Component({
  selector: 'jhi-vendor-additional-info-update',
  templateUrl: './vendor-additional-info-update.component.html'
})
export class VendorAdditionalInfoUpdateComponent implements OnInit {
  @Input() vendorAdditionalInfo: VendorAdditionalInfo;
  @Input() vendorAdditionalInfoCompare: VendorAdditionalInfo;
  @Input() approvalStatus: string;
  @Input() approved: boolean;
  isSaving: boolean;
  createdDate: string;
  lastUpdatedDate: string;

  turnoverMasters: ITurnoverMaster[];

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected turnoverMasterService: TurnoverMasterService,
    private snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.turnoverMasterService.query().subscribe((res: HttpResponse<ITurnoverMaster[]>) => {
      this.turnoverMasters = res.body;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {}
}

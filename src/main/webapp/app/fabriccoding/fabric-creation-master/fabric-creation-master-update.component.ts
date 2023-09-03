import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IFabricCreationMaster } from 'app/shared/model/fabric-creation-master.model';
import { FabricCreationMasterService } from './fabric-creation-master.service';
import { IFabricSubstractMaster } from 'app/shared/model/fabric-substract-master.model';
import { FabricSubstractMasterService } from 'app/fabriccoding/fabric-substract-master';
import { IFabricSubstractDetails } from 'app/shared/model/fabric-substract-details.model';
import { FabricSubstractDetailsService } from 'app/fabriccoding/fabric-substract-details';
import { IFabricSplFinishMaster } from 'app/shared/model/fabric-spl-finish-master.model';
import { FabricSplFinishMasterService } from 'app/fabriccoding/fabric-spl-finish-master';
import { FabricOthersMasterService } from 'app/fabriccoding/fabric-others-master';
import { IFabricOthersMaster } from 'app/shared/model/fabric-others-master.model';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { FabricCreationWarpDetails } from 'app/shared/model/fabric-creation-warp-details.model';
import { FabricCreationWeftDetails } from 'app/shared/model/fabric-creation-weft-details.model';
import { IFabricUomMaster } from 'app/shared/model/fabric-uom-master.model';
import { FabricUomMasterService } from 'app/fabriccoding/fabric-uom-master';
import { FabricCreationContentDetails } from 'app/shared/model/fabric-creation-content-details.model';
import { FabricContentSearchComponent } from 'app/fabriccoding/fabric-creation-master/fabric-content-search.component';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-fabric-creation-master-update',
  templateUrl: './fabric-creation-master-update.component.html'
})
export class FabricCreationMasterUpdateComponent implements OnInit {
  fabricCreationMaster: IFabricCreationMaster;
  isSaving: boolean;
  fabricsubstractmasters: IFabricSubstractMaster[];
  fabricsubstractdetails: IFabricSubstractDetails[];
  fabricsplfinishmasters: IFabricSplFinishMaster[];
  fabricothersmasters: IFabricOthersMaster[];
  fabricuommasters: IFabricUomMaster[];
  protected ngbModalRef: NgbModalRef;
  contentIndex: number;

  constructor(
    protected fabricCreationMasterService: FabricCreationMasterService,
    protected activatedRoute: ActivatedRoute,
    protected jhiAlertService: JhiAlertService,
    protected fabricSubstractMasterService: FabricSubstractMasterService,
    protected fabricSubstractDetailsService: FabricSubstractDetailsService,
    protected fabricSplFinishMasterService: FabricSplFinishMasterService,
    protected fabricOthersMasterService: FabricOthersMasterService,
    protected fabricUomMasterService: FabricUomMasterService,
    protected modalService: NgbModal,
    private eventManager: JhiEventManager,
    protected snotifyService: SnotifyService
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.registerChangeInSelectedFabricContentDetails();
    this.activatedRoute.data.subscribe(({ fabricCreationMaster }) => {
      this.fabricCreationMaster = fabricCreationMaster;
      if (this.fabricCreationMaster.fabricSubstractMaster !== undefined && this.fabricCreationMaster.fabricSubstractMaster.id > 0) {
        this.fetchSubDetails();
      }
      if (this.fabricCreationMaster.fabricCreationWarpDetails === undefined || this.fabricCreationMaster.fabricCreationWarpDetails === null) {
        this.fabricCreationMaster.fabricCreationWarpDetails = [];
        this.fabricCreationMaster.fabricCreationWarpDetails.push(new FabricCreationWarpDetails());
        this.fabricCreationMaster.fabricCreationWarpDetails.push(new FabricCreationWarpDetails());
      }

      if (this.fabricCreationMaster.fabricCreationWeftDetails === undefined || this.fabricCreationMaster.fabricCreationWeftDetails === null) {
        this.fabricCreationMaster.fabricCreationWeftDetails = [];
        this.fabricCreationMaster.fabricCreationWeftDetails.push(new FabricCreationWeftDetails());
        this.fabricCreationMaster.fabricCreationWeftDetails.push(new FabricCreationWeftDetails());
      }
      if (this.fabricCreationMaster.fabricCreationContentDetails === undefined || this.fabricCreationMaster.fabricCreationContentDetails === null) {
        this.fabricCreationMaster.fabricCreationContentDetails = [];
        this.fabricCreationMaster.fabricCreationContentDetails.push(new FabricCreationContentDetails());
        this.fabricCreationMaster.fabricCreationContentDetails.push(new FabricCreationContentDetails());
      }
    });
    this.fabricSubstractMasterService.query().subscribe(
      (res: HttpResponse<IFabricSubstractMaster[]>) => {
        this.fabricsubstractmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.fabricSplFinishMasterService.query().subscribe(
      (res: HttpResponse<IFabricSplFinishMaster[]>) => {
        this.fabricsplfinishmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.fabricOthersMasterService.query().subscribe(
      (res: HttpResponse<IFabricOthersMaster[]>) => {
        this.fabricothersmasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.fabricUomMasterService.query().subscribe(
      (res: HttpResponse<IFabricUomMaster[]>) => {
        this.fabricuommasters = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    let ctr = 0;
    let pctr = 0;
    if (this.fabricCreationMaster.fabricCreationContentDetails) {
      let contentPercentage = 0;
      this.fabricCreationMaster.fabricCreationContentDetails.forEach(fabricCreationContentDetail => {
        if (
          fabricCreationContentDetail.fabricContentMaster !== undefined &&
          fabricCreationContentDetail.fabricContentMaster.id !== undefined
        ) {
          if (fabricCreationContentDetail.percentage === undefined || fabricCreationContentDetail.percentage === null) {
            this.snotifyService.error('Percentage must not be empty', '', toastConfig);
            this.isSaving = false;
            ++pctr;
            return false;
          } else {
            contentPercentage += fabricCreationContentDetail.percentage;
          }
        }
      });
      if (pctr === 0 && contentPercentage === 100) {
        ++ctr;
      } else if (pctr === 0) {
        this.snotifyService.error("Percentage can't be greater/less than 100.", '', toastConfig);
        this.isSaving = false;
        return false;
      }
    }
    if (ctr > 0) {
      if (this.fabricCreationMaster.id !== undefined) {
        this.subscribeToSaveResponse(this.fabricCreationMasterService.update(this.fabricCreationMaster));
      } else {
        this.subscribeToSaveResponse(this.fabricCreationMasterService.create(this.fabricCreationMaster));
      }
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFabricCreationMaster>>) {
    result.subscribe((res: HttpResponse<IFabricCreationMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res));
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(res?: HttpErrorResponse) {
    this.isSaving = false;
    this.snotifyService.error(res.headers.get('x-vamaniportalapp-error'), '', toastConfig);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  fabricCoding() {
    let coding = '';
    if (this.fabricCreationMaster.fabricSubstractMaster !== undefined) {
      coding += this.fabricCreationMaster.fabricSubstractMaster.description;
    }
    if (this.fabricCreationMaster.fabricSubstractDetails !== undefined) {
      if (coding.length > 0) {
        coding += '..' + this.fabricCreationMaster.fabricSubstractDetails.description;
      } else {
        coding += this.fabricCreationMaster.fabricSubstractDetails.description;
      }
    }
    let wrap = '';
    if (this.fabricCreationMaster.fabricCreationWarpDetails) {
      this.fabricCreationMaster.fabricCreationWarpDetails.forEach(fabricCreationWarpDetail => {
        if (wrap.length > 0) {
          if (
            fabricCreationWarpDetail.warp1 !== undefined &&
            fabricCreationWarpDetail.warp2 !== undefined &&
            fabricCreationWarpDetail.fabricUomMaster !== undefined
          ) {
            wrap +=
              '+' +
              fabricCreationWarpDetail.warp1 +
              '/' +
              fabricCreationWarpDetail.warp2 +
              fabricCreationWarpDetail.fabricUomMaster.description;
          }
        } else {
          if (
            fabricCreationWarpDetail.warp1 !== undefined &&
            fabricCreationWarpDetail.warp2 !== undefined &&
            fabricCreationWarpDetail.fabricUomMaster !== undefined
          ) {
            wrap +=
              fabricCreationWarpDetail.warp1 + '/' + fabricCreationWarpDetail.warp2 + fabricCreationWarpDetail.fabricUomMaster.description;
          }
        }
      });
    }
    let weft = '';
    if (this.fabricCreationMaster.fabricCreationWeftDetails) {
      this.fabricCreationMaster.fabricCreationWeftDetails.forEach(fabricCreationWeftDetail => {
        if (weft.length > 0) {
          if (
            fabricCreationWeftDetail.weft1 !== undefined &&
            fabricCreationWeftDetail.weft2 !== undefined &&
            fabricCreationWeftDetail.fabricUomMaster !== undefined
          ) {
            weft +=
              '+' +
              fabricCreationWeftDetail.weft1 +
              '/' +
              fabricCreationWeftDetail.weft2 +
              fabricCreationWeftDetail.fabricUomMaster.description;
          }
        } else {
          if (
            fabricCreationWeftDetail.weft1 !== undefined &&
            fabricCreationWeftDetail.weft2 !== undefined &&
            fabricCreationWeftDetail.fabricUomMaster !== undefined
          ) {
            weft +=
              fabricCreationWeftDetail.weft1 + '/' + fabricCreationWeftDetail.weft2 + fabricCreationWeftDetail.fabricUomMaster.description;
          }
        }
      });
    }
    if (wrap.length > 0 && weft.length > 0) {
      if (coding.length > 0) {
        coding += '..Count=' + wrap + '×' + weft;
      } else {
        coding += 'Count=' + wrap + '×' + weft;
      }
    } else if (wrap.length > 0 || weft.length > 0) {
      if (coding.length > 0) {
        coding += '..Count=' + wrap + weft;
      } else {
        coding += 'Count=' + wrap + weft;
      }
    }
    if (
      this.fabricCreationMaster.epi !== undefined &&
      this.fabricCreationMaster.epi !== null &&
      this.fabricCreationMaster.ppi !== undefined &&
      this.fabricCreationMaster.ppi !== null
    ) {
      if (coding.length > 0) {
        coding += '..Const=' + this.fabricCreationMaster.epi + '×' + this.fabricCreationMaster.ppi;
      } else {
        coding += 'Const=' + this.fabricCreationMaster.epi + '×' + this.fabricCreationMaster.ppi;
      }
    }
    if (this.fabricCreationMaster.fabricCreationContentDetails) {
      let content = '';
      this.fabricCreationMaster.fabricCreationContentDetails.forEach(fabricCreationContentDetail => {
        if (
          fabricCreationContentDetail.fabricContentMaster !== undefined &&
          fabricCreationContentDetail.fabricContentMaster !== null &&
          fabricCreationContentDetail.percentage !== null &&
          fabricCreationContentDetail.percentage > 0
        ) {
          if (content.length > 0) {
            content +=
              ' ' + fabricCreationContentDetail.fabricContentMaster.description + '=' + fabricCreationContentDetail.percentage + '%';
          } else {
            content += fabricCreationContentDetail.fabricContentMaster.description + '=' + fabricCreationContentDetail.percentage + '%';
          }
        }
      });
      if (content !== null && content.length > 0) {
        coding += '..' + content;
      }
    }
    if (
      this.fabricCreationMaster.oth !== undefined &&
      this.fabricCreationMaster.oth !== null &&
      this.fabricCreationMaster.fabricOthersMaster !== undefined &&
      this.fabricCreationMaster.fabricOthersMaster !== null
    ) {
      if (coding.length > 0) {
        coding += '..' + this.fabricCreationMaster.oth + ' ' + this.fabricCreationMaster.fabricOthersMaster.description;
      } else {
        coding += this.fabricCreationMaster.oth + ' ' + this.fabricCreationMaster.fabricOthersMaster.description;
      }
    }
    if (this.fabricCreationMaster.fabricSplFinishMaster !== undefined) {
      if (coding.length > 0) {
        coding += '..(Spl.Finish:' + this.fabricCreationMaster.fabricSplFinishMaster.description + ')';
      } else {
        coding += '(Spl.Finish:' + this.fabricCreationMaster.fabricSplFinishMaster.description + ')';
      }
    }
    this.fabricCreationMaster.description = coding;
  }

  fetchSubDetails() {
    if (this.fabricCreationMaster.fabricSubstractMaster !== undefined && this.fabricCreationMaster.fabricSubstractMaster.id > 0) {
      this.fabricSubstractDetailsService
        .findByMaster(this.fabricCreationMaster.fabricSubstractMaster.id)
        .subscribe(fabricSubstractDetails => {
          this.fabricsubstractdetails = fabricSubstractDetails.body;
        });
    } else {
      this.fabricsubstractdetails = [];
      this.fabricCreationMaster.fabricSubstractDetails = undefined;
      this.fabricCoding();
    }
  }
  addWrapWeft() {
    if (this.fabricCreationMaster.fabricCreationWarpDetails) {
      this.fabricCreationMaster.fabricCreationWarpDetails.push(new FabricCreationWarpDetails());
    } else {
      this.fabricCreationMaster.fabricCreationWarpDetails = [];
      this.fabricCreationMaster.fabricCreationWarpDetails.push(new FabricCreationWarpDetails());
    }

    if (this.fabricCreationMaster.fabricCreationWeftDetails) {
      this.fabricCreationMaster.fabricCreationWeftDetails.push(new FabricCreationWeftDetails());
    } else {
      this.fabricCreationMaster.fabricCreationWeftDetails = [];
      this.fabricCreationMaster.fabricCreationWeftDetails.push(new FabricCreationWeftDetails());
    }
  }

  removeWrapWeft(index) {
    this.fabricCreationMaster.fabricCreationWarpDetails.splice(index, 1);
    this.fabricCreationMaster.fabricCreationWeftDetails.splice(index, 1);
    this.fabricCoding();
  }

  addContent() {
    if (this.fabricCreationMaster.fabricCreationContentDetails) {
      this.fabricCreationMaster.fabricCreationContentDetails.push(new FabricCreationContentDetails());
    } else {
      this.fabricCreationMaster.fabricCreationContentDetails = [];
      this.fabricCreationMaster.fabricCreationContentDetails.push(new FabricCreationContentDetails());
    }
  }

  removeContent(index) {
    this.fabricCreationMaster.fabricCreationContentDetails.splice(index, 1);
    this.fabricCoding();
  }

  searchContent(index: number) {
    this.contentIndex = index;
    this.ngbModalRef = this.modalService.open(FabricContentSearchComponent as Component, {
      size: 'lg',
      backdrop: 'static',
      windowClass: 'xlModal'
    });
  }

  registerChangeInSelectedFabricContentDetails() {
    this.eventManager.subscribe('selectedFabricContentDetails', message => {
      const fabricContMaster = message.content;
      this.fabricCreationMaster.fabricCreationContentDetails[this.contentIndex].fabricContentMaster = fabricContMaster;
      this.fabricCoding();
    });
  }
}

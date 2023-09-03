import { Component, Input, OnInit, ViewChild } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { MarkerMasterEntryService } from './marker-master-entry.service';
import { IMaster, Master } from 'app/shared/model/master.modal';
import { IMasterContainer, MasterContainer } from 'app/shared/model/MasterContainer.model';
import { IMarkerEntryDetails, MarkerEntryDetails } from 'app/shared/model/marker-entry-details.model';
import { IFullitemkeydecoder } from 'app/shared/db2/model/fulltemkeydecoder.model';
import { IUserPlant } from 'app/shared/model/user-plant.model';
import { IMarkerDestinationBean } from 'app/shared/model/marker-destination-bean.model';
import { CompleterCmp, CompleterItem, CompleterService, RemoteData } from 'ng2-completer';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { MarkerMasterEntry } from 'app/shared/model/marker-master-entry.model';

@Component({
  selector: 'jhi-marker-master-entry-update',
  templateUrl: './marker-master-entry-update.component.html'
})
export class MarkerMasterEntryUpdateComponent implements OnInit {
  isProcess = false;
  isSaving: boolean;
  search?: IMaster;
  @Input() markerMasterEntry: MarkerMasterEntry;
  markerMasterEntries: MarkerMasterEntry[];
  masterContainer?: IMasterContainer;
  markerEntryDetails?: IMarkerEntryDetails[];
  fullitemkeydecoders: IFullitemkeydecoder[];
  colors?: IMaster[];
  plantcode?: IMaster;
  userPlants?: IUserPlant[] = [];
  markerDestinationBean?: IMarkerDestinationBean;
  public dataRemoteProject: RemoteData;

  @ViewChild('remoteData', { static: false }) private remoteData: CompleterCmp | undefined;

  constructor(
    protected markerMasterEntryService: MarkerMasterEntryService,
    protected activatedRoute: ActivatedRoute,
    public completerService: CompleterService,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {
    this.search = new Master();
    this.masterContainer = new MasterContainer();
    this.markerEntryDetails = [];
    this.fullitemkeydecoders = [];
    this.dataRemoteProject = this.completerService.remote(this.markerMasterEntryService.resourceUrlProject, 'id.code', 'id.code');
  }

  ngOnInit() {
    this.isSaving = false;
    if (this.markerMasterEntryService.markerMasterEntry) {
      this.markerMasterEntry = Object.assign({}, this.markerMasterEntryService.markerMasterEntry);
      this.markerMasterEntryService.markerMasterEntry = undefined;
      this.fetchSizes();
      this.viewMarkers();
    } else {
      this.markerMasterEntry = new MarkerMasterEntry();
    }
    this.markerMasterEntryService.plants().subscribe(userPlants => {
      this.userPlants = userPlants.body;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.markerMasterEntries && this.markerMasterEntries.length > 0) {
      this.subscribeToSaveResponse(this.markerMasterEntryService.create(this.markerMasterEntries));
    }
  }

  fetchColors(): void {
    if (this.markerMasterEntry.style && this.markerMasterEntry.style !== undefined) {
      this.isProcess = true;
      this.search.name = this.markerMasterEntry.style;
      this.markerMasterEntryService.queryColorByCountry(this.search).subscribe((country: HttpResponse<IMaster[]>) => {
        this.colors = country.body;
        this.isProcess = false;
      });
    } else {
      this.colors = [];
    }
  }

  fetchSizes(): void {
    if (this.markerMasterEntry.style && this.markerMasterEntry.style !== undefined) {
      if (this.colors) {
        this.colors.forEach(color => {
          if (this.markerMasterEntry.color === color.name) {
            this.markerMasterEntry.colorDesc = color.desc;
          }
        });
      }
      this.isProcess = true;
      this.search.code = this.markerMasterEntry.style;
      this.search.desc = this.markerMasterEntry.color;
      this.markerMasterEntryService.querySizeByStyle(this.search).subscribe((response: HttpResponse<IMarkerDestinationBean>) => {
        this.markerDestinationBean = response.body;
        this.isProcess = false;
      });
    } else {
      this.markerMasterEntry.markerEntryDetails = [];
    }
  }

  addMarkerEntry(): void {
    const markerMasterEntry = new MarkerMasterEntry();
    markerMasterEntry.plantCode = this.markerMasterEntry.plantCode;
    markerMasterEntry.plantDescription = this.markerMasterEntry.plantDescription;
    markerMasterEntry.style = this.markerMasterEntry.style;
    markerMasterEntry.color = this.markerMasterEntry.color;
    markerMasterEntry.colorDesc = this.markerMasterEntry.colorDesc;
    markerMasterEntry.width = this.markerMasterEntry.width;
    markerMasterEntry.itemCode = this.markerMasterEntry.itemCode;
    markerMasterEntry.subcode01 = this.markerMasterEntry.subcode01;
    markerMasterEntry.subcode02 = this.markerMasterEntry.subcode02;
    markerMasterEntry.subcode03 = this.markerMasterEntry.subcode03;
    markerMasterEntry.subcode04 = this.markerMasterEntry.subcode04;
    markerMasterEntry.subcode05 = this.markerMasterEntry.subcode05;
    markerMasterEntry.subcode06 = this.markerMasterEntry.subcode06;
    markerMasterEntry.subcode07 = this.markerMasterEntry.subcode07;
    markerMasterEntry.subcode08 = this.markerMasterEntry.subcode08;
    markerMasterEntry.subcode09 = this.markerMasterEntry.subcode09;
    markerMasterEntry.subcode10 = this.markerMasterEntry.subcode10;
    markerMasterEntry.bodyFabric = this.markerMasterEntry.bodyFabric;
    markerMasterEntry.plannedAvg = this.markerMasterEntry.plannedAvg;

    let index = 0;
    const markerEntryDetails: IMarkerEntryDetails[] = [];
    this.markerDestinationBean.sizeCodes.forEach(sizeMaster => {
      const markerEntryDetail = new MarkerEntryDetails();
      markerEntryDetail.sizeCode = sizeMaster.sizeCode;
      markerEntryDetail.sizeQty = 0;
      markerEntryDetails.push(markerEntryDetail);
      ++index;
    });
    if (index === this.markerDestinationBean.sizeCodes.length) {
      markerMasterEntry.markerEntryDetails = markerEntryDetails;
      if (this.markerMasterEntries) {
        this.markerMasterEntries.push(markerMasterEntry);
      } else {
        this.markerMasterEntries = [];
        this.markerMasterEntries.push(markerMasterEntry);
      }
    }
  }

  fetchReservationItem(): void {
    if (
      this.markerMasterEntry.style &&
      this.markerMasterEntry.style !== undefined &&
      this.markerMasterEntry.color &&
      this.markerMasterEntry.color !== undefined
    ) {
      this.search.id = this.markerMasterEntry.style;
      this.search.desc = this.markerMasterEntry.color;
      this.markerMasterEntryService.getAllReservationItemByPo(this.search).subscribe((item: HttpResponse<IFullitemkeydecoder[]>) => {
        this.fullitemkeydecoders = item.body;
      });
    } else {
      this.fullitemkeydecoders = [];
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<MarkerMasterEntry>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.isProcess = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
    this.isProcess = false;
  }

  trackId(index: number, item: IMarkerEntryDetails) {
    return item.id;
  }

  trackNew(index: number, item: IMarkerEntryDetails) {
    return item.id;
  }

  widthSet(): void {
    if (this.markerMasterEntry.itemCode) {
      this.markerMasterEntry.width = this.markerMasterEntry.itemCode.ordersubcode07.trim();
      this.viewMarkers();
    } else {
      this.markerMasterEntry.width = undefined;
      this.viewMarkers();
    }
  }

  selectPlant(): void {
    if (this.markerMasterEntry.plantCode) {
      this.userPlants.forEach(userPlant => {
        if (userPlant.id.plantCode === this.markerMasterEntry.plantCode) {
          this.markerMasterEntry.plantDescription = userPlant.plantDescription;
        }
      });
    } else {
      this.markerMasterEntry.plantDescription = undefined;
    }
  }

  viewMarkers(): void {
    if (
      this.markerMasterEntry &&
      this.markerMasterEntry.plantCode &&
      this.markerMasterEntry.style &&
      this.markerMasterEntry.color &&
      this.markerMasterEntry.itemCode
    ) {
      this.isProcess = true;
      this.markerMasterEntryService.view(this.markerMasterEntry).subscribe(
        markerMasterEntries => {
          this.isProcess = false;
          if (markerMasterEntries.body && markerMasterEntries.body.length > 0) {
            this.markerMasterEntries = markerMasterEntries.body;
            this.markerMasterEntry.bodyFabric = this.markerMasterEntries[0].bodyFabric;
            this.markerMasterEntry.plannedAvg = this.markerMasterEntries[0].plannedAvg;
          } else {
            this.isProcess = true;
            this.markerMasterEntryService.viewAvg(this.markerMasterEntry).subscribe(
              markerMasterEntry => {
                this.isProcess = false;
                this.markerMasterEntry.plannedAvg = markerMasterEntry.body.plannedAvg;
              },
              () => {
                this.isProcess = false;
                this.snotifyService.error('Planned Average not found!');
              }
            );
          }
        },
        () => {
          this.isProcess = false;
        }
      );
    }
  }

  onProjectSelected(selected?: CompleterItem): void {
    if (selected && !!this.remoteData) {
      this.markerMasterEntry.style = selected.originalObject.id.code;
      this.fetchColors();
    } else {
      this.markerMasterEntry.style = undefined;
    }
  }

  avgCalculation(markerMasterEntry: MarkerMasterEntry) {
    let totalPcs = 0;
    let index = 0;
    markerMasterEntry.markerEntryDetails.forEach(markerEntryDetail => {
      if (markerEntryDetail.sizeQty) {
        totalPcs += Number(markerEntryDetail.sizeQty);
      }
      ++index;
    });
    if (index === markerMasterEntry.markerEntryDetails.length && markerMasterEntry.length) {
      if (totalPcs > 0 && markerMasterEntry.length > 0) {
        markerMasterEntry.actualAvg = Number((Number(markerMasterEntry.length) / Number(totalPcs)).toFixed(3));
      } else {
        markerMasterEntry.actualAvg = 0.0;
      }
    }
  }

  forwardPopup(markerMasterEntry: MarkerMasterEntry): void {
    this.snotifyService.confirm('Are you sure to forward for approval?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.forward(markerMasterEntry, toast), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  forward(markerMasterEntry: MarkerMasterEntry, toast: any): void {
    this.isSaving = true;
    this.isProcess = true;
    this.snotifyService.remove(toast.id);
    this.subscribeToSaveResponse(this.markerMasterEntryService.forward(markerMasterEntry.id));
  }

  updateInLine(): void {
    this.markerMasterEntries.forEach(markerMasterEntry => {
      markerMasterEntry.plannedAvg = this.markerMasterEntry.plannedAvg;
    });
  }
}

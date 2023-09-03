import { Component, Input, OnInit, Output, EventEmitter, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { ITrailMockOperation } from 'app/shared/model/trail-mock-operation.model';
import { TrailMockOperationService } from './trail-mock-operation.service';
import { IWorkerRecruitment } from 'app/shared/model/worker-recruitment.model';
import { WorkerRecruitmentService } from 'app/entities/worker-recruitment';
import { IOperationMaster } from 'app/shared/model/operation-master.model';
import { OperationMasterService } from 'app/entities/operation-master';
import { IMachineMaster, MachineMaster } from 'app/shared/model/machine-master.model';
import { MachineMasterService } from 'app/entities/machine-master';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import 'rxjs/add/operator/debounceTime';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { WorkerWorkFlowUpdateComponent } from './worker-work-flow-update.component';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { IItemsMovedEvent } from 'app/shared/model/items-moved-event';
import { intersectionwith } from 'lodash/intersectionWith';
import { differenceWith } from 'lodash/differenceWith';
@Component({
  selector: 'jhi-trail-mock-operation-update',
  templateUrl: './trail-mock-operation-update.component.html'
})
export class TrailMockOperationUpdateComponent implements OnInit, OnDestroy {
  trailMockOperation: ITrailMockOperation;
  isSaving: boolean;
  eventSubscriber: Subscription;
  workerrecruitments: IWorkerRecruitment[];
  operationmasters: IOperationMaster[];
  machinemasters: IMachineMaster[];
  createdDate: string;
  lastUpdatedDate: string;
  ratingSchedule: string;
  color: string;
  protected ngbModalRef: NgbModalRef;
  myStyles = {
    'font-size': '20px',
    'font-weight': 'bold',
    'text.color': 'white',
    'text-align': 'center'
  };
  public mask = [/[0-9]/, /\d/, ':', /\d/, /\d/];
  // private variables to manage class
  searchTermAvailable = '';
  searchTermSelected = '';
  availableItems: Array<IOperationMaster> = [];
  selectedItems: Array<IOperationMaster> = [];
  listBoxForm: FormGroup;
  availableListBoxControl: FormControl = new FormControl();
  selectedListBoxControl: FormControl = new FormControl();
  availableSearchInputControl: FormControl = new FormControl();
  selectedSearchInputControl: FormControl = new FormControl();
  // field to use for value of option
  @Input() valueField = 'id';
  // text to display as title above component
  @Input() title: string;
  // time to debounce search output in ms
  @Input() debounceTime = 500;
  // show/hide button to move all items between boxes
  @Input() moveAllButton = true;
  // text displayed over the available items list box
  @Input() availableText = 'Available items';
  // text displayed over the selected items list box
  @Input() selectedText = 'Selected items';
  // set placeholder text in available items list box
  @Input() availableFilterPlaceholder = 'Filter...';
  // set placeholder text in selected items list box
  @Input() selectedFilterPlaceholder = 'Filter...';
  // event called when item or items from available items(left box) is selected
  @Output() onAvailableItemSelected: EventEmitter<{} | Array<{}>> = new EventEmitter<{} | Array<{}>>();
  // event called when item or items from selected items(right box) is selected
  @Output() onSelectedItemsSelected: EventEmitter<{} | Array<{}>> = new EventEmitter<{} | Array<{}>>();
  // event called when items are moved between boxes, returns state of both boxes and item moved
  @Output() onItemsMoved: EventEmitter<IItemsMovedEvent> = new EventEmitter<IItemsMovedEvent>();
  @ViewChild('myckeditor', { static: false }) ckeditor: any;
  constructor(
    protected jhiAlertService: JhiAlertService,
    protected trailMockOperationService: TrailMockOperationService,
    protected workerRecruitmentService: WorkerRecruitmentService,
    protected operationMasterService: OperationMasterService,
    protected machineMasterService: MachineMasterService,
    protected activatedRoute: ActivatedRoute,
    public fb: FormBuilder,
    protected modalService: NgbModal,
    protected snotifyService: SnotifyService,
    protected eventManager: JhiEventManager
  ) {
    this.listBoxForm = this.fb.group({
      availableListBox: this.availableListBoxControl,
      selectedListBox: this.selectedListBoxControl,
      availableSearchInput: this.availableSearchInputControl,
      selectedSearchInput: this.selectedSearchInputControl
    });
  }

  ngOnInit() {
    this.isSaving = false;
    this.availableItems = [];
    this.registerChangeInWorkFlow();
    this.availableText = 'Available Operations';
    this.selectedText = 'Selected Operations';
    this.activatedRoute.data.subscribe(({ trailMockOperation }) => {
      this.trailMockOperation = trailMockOperation;
      if (this.trailMockOperation && this.trailMockOperation.operationMasters) {
        this.selectedItems = this.trailMockOperation.operationMasters;
      }
      if (this.trailMockOperation && this.trailMockOperation.achiveRating) {
        if (this.trailMockOperation.achiveRating > 64) {
          this.trailMockOperation.gradeDescription = 'ABOVE 65%';
          this.trailMockOperation.grade = 'A+';
          this.myStyles['background-color'] = 'lime';
        } else if (this.trailMockOperation.achiveRating > 54 && this.trailMockOperation.achiveRating < 65) {
          this.trailMockOperation.gradeDescription = '55 TO 64%';
          this.trailMockOperation.grade = 'A';
          this.myStyles['background-color'] = 'limegreen';
        } else if (this.trailMockOperation.achiveRating > 49 && this.trailMockOperation.achiveRating < 55) {
          this.trailMockOperation.gradeDescription = '50 TO 54%';
          this.trailMockOperation.grade = 'B';
          this.myStyles['background-color'] = 'Orange';
        } else {
          this.trailMockOperation.gradeDescription = '40 TO 59%';
          this.trailMockOperation.grade = 'C';
          this.myStyles['background-color'] = 'red';
        }
      }
      this.createdDate = this.trailMockOperation.createdDate != null ? this.trailMockOperation.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.trailMockOperation.lastUpdatedDate != null ? this.trailMockOperation.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.operationMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOperationMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOperationMaster[]>) => response.body)
      )
      .subscribe(
        (res: IOperationMaster[]) => {
          this.operationmasters = res;
          this.availableItems = this.operationmasters;
          if (this.selectedItems && this.selectedItems.length > 0) {
            for (let i = this.availableItems.length - 1; i >= 0; i--) {
              for (let j = 0; j < this.selectedItems.length; j++) {
                if (this.availableItems[i] && this.availableItems[i].id === this.selectedItems[j].id) {
                  this.availableItems.splice(i, 1);
                }
              }
            }
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.machineMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMachineMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMachineMaster[]>) => response.body)
      )
      .subscribe((res: IMachineMaster[]) => (this.machinemasters = res), (res: HttpErrorResponse) => this.onError(res.message));
    //  This is code of dual box selection..............................
    this.availableListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onAvailableItemSelected.emit(items));
    this.selectedListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onSelectedItemsSelected.emit(items));
    this.availableSearchInputControl.valueChanges
      .debounceTime(this.debounceTime)
      .distinctUntilChanged()
      .subscribe((search: string) => (this.searchTermAvailable = search));
    this.selectedSearchInputControl.valueChanges
      .debounceTime(this.debounceTime)
      .distinctUntilChanged()
      .subscribe((search: string) => (this.searchTermSelected = search));
  }

  reCall() {
    this.isSaving = false;
    this.availableItems = [];
    this.availableText = 'Available Operations';
    this.selectedText = 'Selected Operations';
    this.trailMockOperationService.find(this.trailMockOperation.id).subscribe(trailMockOperation => {
      this.trailMockOperation = trailMockOperation.body;
      if (this.trailMockOperation && this.trailMockOperation.operationMasters) {
        this.selectedItems = this.trailMockOperation.operationMasters;
      }
      if (this.trailMockOperation && this.trailMockOperation.achiveRating) {
        if (this.trailMockOperation.achiveRating > 64) {
          this.trailMockOperation.gradeDescription = 'ABOVE 65%';
          this.trailMockOperation.grade = 'A+';
          this.myStyles['background-color'] = 'lime';
        } else if (this.trailMockOperation.achiveRating > 54 && this.trailMockOperation.achiveRating < 65) {
          this.trailMockOperation.gradeDescription = '55 TO 64%';
          this.trailMockOperation.grade = 'A';
          this.myStyles['background-color'] = 'limegreen';
        } else if (this.trailMockOperation.achiveRating > 49 && this.trailMockOperation.achiveRating < 55) {
          this.trailMockOperation.gradeDescription = '50 TO 54%';
          this.trailMockOperation.grade = 'B';
          this.myStyles['background-color'] = 'Orange';
        } else {
          this.trailMockOperation.gradeDescription = '40 TO 59%';
          this.trailMockOperation.grade = 'C';
          this.myStyles['background-color'] = 'red';
        }
      }
      this.createdDate = this.trailMockOperation.createdDate != null ? this.trailMockOperation.createdDate.format(DATE_TIME_FORMAT) : null;
      this.lastUpdatedDate =
        this.trailMockOperation.lastUpdatedDate != null ? this.trailMockOperation.lastUpdatedDate.format(DATE_TIME_FORMAT) : null;
    });
    this.operationMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOperationMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOperationMaster[]>) => response.body)
      )
      .subscribe(
        (res: IOperationMaster[]) => {
          this.operationmasters = res;
          this.availableItems = this.operationmasters;
          if (this.selectedItems && this.selectedItems.length > 0) {
            for (let i = this.availableItems.length - 1; i >= 0; i--) {
              for (let j = 0; j < this.selectedItems.length; j++) {
                if (this.availableItems[i] && this.availableItems[i].id === this.selectedItems[j].id) {
                  this.availableItems.splice(i, 1);
                }
              }
            }
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.machineMasterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMachineMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMachineMaster[]>) => response.body)
      )
      .subscribe((res: IMachineMaster[]) => (this.machinemasters = res), (res: HttpErrorResponse) => this.onError(res.message));
    //  This is code of dual box selection..............................
    this.availableListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onAvailableItemSelected.emit(items));
    this.selectedListBoxControl.valueChanges.subscribe((items: Array<{}>) => this.onSelectedItemsSelected.emit(items));
    this.availableSearchInputControl.valueChanges
      .debounceTime(this.debounceTime)
      .distinctUntilChanged()
      .subscribe((search: string) => (this.searchTermAvailable = search));
    this.selectedSearchInputControl.valueChanges
      .debounceTime(this.debounceTime)
      .distinctUntilChanged()
      .subscribe((search: string) => (this.searchTermSelected = search));
  }

  registerChangeInWorkFlow() {
    this.eventSubscriber = this.eventManager.subscribe('workFlowForwardStatusModification', response => this.reCall());
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.trailMockOperation.operationMasters = this.selectedItems;
    this.trailMockOperation.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
    this.trailMockOperation.lastUpdatedDate = this.lastUpdatedDate != null ? moment(this.lastUpdatedDate, DATE_TIME_FORMAT) : null;
    if (this.trailMockOperation && this.trailMockOperation.id && this.trailMockOperation.id !== undefined) {
      this.subscribeToSaveResponse(this.trailMockOperationService.update(this.trailMockOperation));
    } else {
      this.subscribeToSaveResponse(this.trailMockOperationService.create(this.trailMockOperation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrailMockOperation>>) {
    result.subscribe((res: HttpResponse<ITrailMockOperation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully!', '', toastConfig);
  }

  protected onSaveError() {
    this.isSaving = false;
    this.snotifyService.error('Record not save!', '', toastConfig);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackWorkerRecruitmentById(index: number, item: IWorkerRecruitment) {
    return item.id;
  }

  trackOperationMasterById(index: number, item: IOperationMaster) {
    return item.id;
  }

  trackMachineMasterById(index: number, item: IMachineMaster) {
    return item.id;
  }
  // input to set search term for available list box from the outside
  @Input()
  set availableSearch(searchTerm: string) {
    this.searchTermAvailable = searchTerm;
    this.availableSearchInputControl.setValue(searchTerm);
  }

  // input to set search term for selected list box from the outside
  @Input()
  set selectedSearch(searchTerm: string) {
    this.searchTermSelected = searchTerm;
    this.selectedSearchInputControl.setValue(searchTerm);
  }
  // control value accessors
  _onChange = (_: any) => {};
  _onTouched = () => {};
  /**
   * Move all items from available to selected
   */
  moveAllItemsToSelected(): void {
    if (!this.availableItems.length) {
      return;
    }
    this.selectedItems = [...this.selectedItems, ...this.availableItems];
    this.availableItems = [];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.availableListBoxControl.value,
      from: 'available',
      to: 'selected'
    });
    this.availableListBoxControl.setValue([]);
    this.writeValue(this.getValues());
  }
  /**
   * Move all items from selected to available
   */
  moveAllItemsToAvailable(): void {
    if (!this.selectedItems.length) {
      return;
    }
    this.availableItems = [...this.availableItems, ...this.selectedItems];
    this.selectedItems = [];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.selectedListBoxControl.value,
      from: 'selected',
      to: 'available'
    });
    this.selectedListBoxControl.setValue([]);
    this.writeValue([]);
  }
  /**
   * Move marked items from available items to selected items
   */
  moveMarkedAvailableItemsToSelected(): void {
    // first move items to selected
    this.selectedItems = [
      ...this.selectedItems,
      ...intersectionwith(
        this.availableItems,
        this.availableListBoxControl.value,
        (item: IOperationMaster, value: number) => item.id === value
      )
    ];
    // now filter available items to not include marked values
    this.availableItems = [
      ...differenceWith(
        this.availableItems,
        this.availableListBoxControl.value,
        (item: IOperationMaster, value: number) => item.id === value
      )
    ];
    // clear marked available items and emit event
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.availableListBoxControl.value,
      from: 'available',
      to: 'selected'
    });
    this.availableListBoxControl.setValue([]);
    this.availableSearchInputControl.setValue('');
    this.writeValue(this.getValues());
  }
  /**
   * Move marked items from selected items to available items
   */
  moveMarkedSelectedItemsToAvailable(): void {
    // first move items to available
    this.availableItems = [
      ...this.availableItems,
      ...intersectionwith(
        this.selectedItems,
        this.selectedListBoxControl.value,
        (item: IOperationMaster, value: number) => item.id === value
      )
    ];
    // now filter available items to not include marked values
    this.selectedItems = [
      ...differenceWith(this.selectedItems, this.selectedListBoxControl.value, (item: IOperationMaster, value: number) => item.id === value)
    ];
    // clear marked available items and emit event
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: this.selectedListBoxControl.value,
      from: 'selected',
      to: 'available'
    });
    this.selectedListBoxControl.setValue([]);
    this.selectedSearchInputControl.setValue('');
    this.writeValue(this.getValues());
  }

  /**
   * Move single item from available to selected
   * @param item
   */
  moveAvailableItemToSelected(item: IOperationMaster): void {
    this.availableItems = this.availableItems.filter((listItem: IOperationMaster) => listItem.id !== item.id);
    this.selectedItems = [...this.selectedItems, item];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: [item.id],
      from: 'available',
      to: 'selected'
    });
    this.availableSearchInputControl.setValue('');
    this.availableListBoxControl.setValue([]);
    this.writeValue(this.getValues());
  }

  /**
   * Move single item from selected to available
   * @param item
   */
  moveSelectedItemToAvailable(item: IOperationMaster): void {
    this.selectedItems = this.selectedItems.filter((listItem: IOperationMaster) => listItem.id !== item.id);
    this.availableItems = [...this.availableItems, item];
    this.onItemsMoved.emit({
      available: this.availableItems,
      selected: this.selectedItems,
      movedItems: [item.id],
      from: 'selected',
      to: 'available'
    });
    this.selectedSearchInputControl.setValue('');
    this.selectedListBoxControl.setValue([]);
    this.writeValue(this.getValues());
  }

  /**
   * Utility method to get values from
   * selected items
   */
  private getValues(): number[] {
    return (this.selectedItems || []).map((item: IOperationMaster) => item.id);
  }

  /**
   * Function to pass to ngFor to improve performance, tracks items
   * by the value field
   * @param index
   * @param item
   */
  trackByValue(index: number, item: {}): string {
    return item[this.valueField];
  }

  /* Methods from ControlValueAccessor interface, required for ngModel and formControlName - begin */
  writeValue(value: any): void {
    if (this.selectedItems && value && value.length > 0) {
      this.selectedItems = [
        ...this.selectedItems,
        ...intersectionwith(this.availableItems, value, (item: IOperationMaster, val: number) => item.id === val)
      ];
      this.availableItems = [...differenceWith(this.availableItems, value, (item: IOperationMaster, val: number) => item.id === val)];
    }
    this._onChange(value);
  }
  /* When Achive Rating Enter By User */
  public getRating() {
    const achiveRate = this.trailMockOperation.achiveRating;
    if (achiveRate !== undefined) {
      if (achiveRate == null) {
        this.ratingSchedule = null;
        this.trailMockOperation.grade = null;
      } else {
        if (achiveRate > 64) {
          this.trailMockOperation.gradeDescription = 'ABOVE 65%';
          this.trailMockOperation.grade = 'A+';
          this.myStyles['background-color'] = 'lime';
        } else if (achiveRate > 54 && achiveRate < 65) {
          this.trailMockOperation.gradeDescription = '55 TO 64%';
          this.trailMockOperation.grade = 'A';
          this.myStyles['background-color'] = 'limegreen';
        } else if (achiveRate > 49 && achiveRate < 55) {
          this.trailMockOperation.gradeDescription = '50 TO 54%';
          this.trailMockOperation.grade = 'B';
          this.myStyles['background-color'] = 'Orange';
        } else {
          this.trailMockOperation.gradeDescription = '40 TO 59%';
          this.trailMockOperation.grade = 'C';
          this.myStyles['background-color'] = 'red';
        }
      }
    }
  }

  compareFn(c1: MachineMaster, c2: MachineMaster): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  workFlow() {
    this.trailMockOperationService.workFlow(this.trailMockOperation.id).subscribe(workerWorkFlow => {
      this.ngbModalRef = this.modalService.open(WorkerWorkFlowUpdateComponent as Component, {
        size: 'lg',
        backdrop: 'static',
        windowClass: 'xlModal'
      });
      this.ngbModalRef.componentInstance.workerWorkFlow = workerWorkFlow.body;
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}

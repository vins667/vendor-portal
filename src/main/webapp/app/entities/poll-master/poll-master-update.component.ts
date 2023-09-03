import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPollMaster } from 'app/shared/model/poll-master.model';
import { PollMasterService } from './poll-master.service';
import { IFactoryMaster } from 'app/shared/model/factory-master.model';
import { FactoryMasterService } from 'app/entities/factory-master';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { PollDetails } from 'app/shared/model/poll-details.model';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { intersectionwith } from 'lodash/intersectionWith';
import { differenceWith } from 'lodash/differenceWith';
import 'rxjs/add/operator/debounceTime';
import { IItemsMovedEvent } from 'app/shared/model/items-moved-event';

@Component({
  selector: 'jhi-poll-master-update',
  templateUrl: './poll-master-update.component.html'
})
export class PollMasterUpdateComponent implements OnInit {
  pollMaster: IPollMaster;
  isSaving: boolean;
  factorymasters: IFactoryMaster[];
  endDate: string;
  createdDate: string;
  approvedDate: string;

  // private variables to manage class
  searchTermAvailable = '';
  searchTermSelected = '';
  availableItems: Array<IFactoryMaster> = [];
  selectedItems: Array<IFactoryMaster> = [];
  listBoxForm: FormGroup;
  availableListBoxControl: FormControl = new FormControl();
  selectedListBoxControl: FormControl = new FormControl();
  availableSearchInputControl: FormControl = new FormControl();
  selectedSearchInputControl: FormControl = new FormControl();

  // field to use for value of option
  @Input() valueField = 'id';
  // field to use for displaying option text
  @Input() textField = 'name';
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

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected pollMasterService: PollMasterService,
    protected factoryMasterService: FactoryMasterService,
    public fb: FormBuilder,
    protected activatedRoute: ActivatedRoute,
    private snotifyService: SnotifyService,
    private router: Router
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
    this.availableText = 'Available Factories';
    this.selectedText = 'Selected Factories';
    this.activatedRoute.data.subscribe(({ pollMaster }) => {
      this.pollMaster = pollMaster;
      this.selectedItems = this.pollMaster.factoryMasters;
      let ctr = -1;
      this.pollMaster.pollDetails.forEach(pollDetails => {
        pollDetails.index = ++ctr;
      });
      this.endDate = this.pollMaster.endDate != null ? this.pollMaster.endDate.format(DATE_TIME_FORMAT) : null;
      this.createdDate = this.pollMaster.createdDate != null ? this.pollMaster.createdDate.format(DATE_TIME_FORMAT) : null;
      this.approvedDate = this.pollMaster.approvedDate != null ? this.pollMaster.approvedDate.format(DATE_TIME_FORMAT) : null;
    });

    this.factoryMasterService.query().subscribe(
      (res: HttpResponse<IFactoryMaster[]>) => {
        this.factorymasters = res.body;
        this.availableItems = this.factorymasters;
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
    if ((this.pollMaster !== null && this.pollMaster.id === null) || this.pollMaster.id === undefined) {
      this.pollMaster.pollDetails = [];
      const pollDetail0 = new PollDetails();
      pollDetail0.index = this.pollMaster.pollDetails.length;
      this.pollMaster.pollDetails.push(pollDetail0);

      const pollDetail1 = new PollDetails();
      pollDetail1.index = this.pollMaster.pollDetails.length;
      this.pollMaster.pollDetails.push(pollDetail1);

      const pollDetail2 = new PollDetails();
      pollDetail2.index = this.pollMaster.pollDetails.length;
      this.pollMaster.pollDetails.push(pollDetail2);

      const pollDetail3 = new PollDetails();
      pollDetail3.index = this.pollMaster.pollDetails.length;
      this.pollMaster.pollDetails.push(pollDetail3);

      this.pollMaster.flag = 'E';
      this.pollMaster.mailFlag = true;
      this.pollMaster.notificationFlag = true;
    }

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

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.pollMaster.createdDate = moment(new Date());
    this.pollMaster.createdBy = 'temp';
    this.pollMaster.endDate = this.endDate != null ? moment(this.endDate, DATE_TIME_FORMAT) : null;
    this.pollMaster.approvedDate = this.approvedDate != null ? moment(this.approvedDate, DATE_TIME_FORMAT) : null;
    this.pollMaster.factoryMasters = this.selectedItems;
    if (this.pollMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.pollMasterService.update(this.pollMaster));
    } else {
      this.subscribeToSaveResponse(this.pollMasterService.create(this.pollMaster));
    }
  }

  publish() {
    if (this.pollMaster.id !== undefined) {
      if (confirm('Do you want to publish?')) {
        this.subscribeToSaveResponse(this.pollMasterService.publish(this.pollMaster.id));
      }
    }
  }

  addOptions() {
    const pollDetail = new PollDetails();
    pollDetail.index = this.pollMaster.pollDetails.length;
    this.pollMaster.pollDetails.push(pollDetail);
  }

  removeOption(index) {
    for (let i = 0; i < this.pollMaster.pollDetails.length; i++) {
      if (this.pollMaster.pollDetails[i] && index === this.pollMaster.pollDetails[i].index) {
        this.pollMaster.pollDetails.splice(i, 1);
      }
    }
    for (let i = 0; i < this.pollMaster.pollDetails.length; i++) {
      this.pollMaster.pollDetails[i].index = i;
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPollMaster>>) {
    result.subscribe((res: HttpResponse<IPollMaster>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess(res) {
    this.isSaving = false;
    this.snotifyService.success('Save successfully!!!', '', toastConfig);
    this.router.navigate(['poll-master/' + res.body.id + '/edit']);
    // this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackFactoryMasterById(index: number, item: IFactoryMaster) {
    return item.id;
  }

  getSelected(selectedVals: Array<any>, option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
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
    if (this.selectedItems === undefined) {
      this.selectedItems = [];
    }
    if (this.availableItems === undefined) {
      this.availableItems = [];
    }
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
    if (this.selectedItems === undefined) {
      this.selectedItems = [];
    }
    if (this.availableItems === undefined) {
      this.availableItems = [];
    }
    // first move items to selected
    this.selectedItems = [
      ...this.selectedItems,
      ...intersectionwith(
        this.availableItems,
        this.availableListBoxControl.value,
        (item: IFactoryMaster, value: number) => item.id === value
      )
    ];
    // now filter available items to not include marked values
    this.availableItems = [
      ...differenceWith(this.availableItems, this.availableListBoxControl.value, (item: IFactoryMaster, value: number) => item.id === value)
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
    if (this.selectedItems === undefined) {
      this.selectedItems = [];
    }
    if (this.availableItems === undefined) {
      this.availableItems = [];
    }
    // first move items to available
    this.availableItems = [
      ...this.availableItems,
      ...intersectionwith(this.selectedItems, this.selectedListBoxControl.value, (item: IFactoryMaster, value: number) => item.id === value)
    ];
    // now filter available items to not include marked values
    this.selectedItems = [
      ...differenceWith(this.selectedItems, this.selectedListBoxControl.value, (item: IFactoryMaster, value: number) => item.id === value)
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
  moveAvailableItemToSelected(item: IFactoryMaster): void {
    if (this.selectedItems === undefined) {
      this.selectedItems = [];
    }
    if (this.availableItems === undefined) {
      this.availableItems = [];
    }
    this.availableItems = this.availableItems.filter((listItem: IFactoryMaster) => listItem.id !== item.id);
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
  moveSelectedItemToAvailable(item: IFactoryMaster): void {
    this.selectedItems = this.selectedItems.filter((listItem: IFactoryMaster) => listItem.id !== item.id);
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
    return (this.selectedItems || []).map((item: IFactoryMaster) => item.id);
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
        ...intersectionwith(this.availableItems, value, (item: IFactoryMaster, val: number) => item.id === val)
      ];
      this.availableItems = [...differenceWith(this.availableItems, value, (item: IFactoryMaster, val: number) => item.id === val)];
    }
    this._onChange(value);
  }
}

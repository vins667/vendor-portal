import { Component, Input, OnInit } from '@angular/core';
import { TreeviewConfig, TreeviewItem } from 'ngx-treeview';
import { IMenuSave } from 'app/shared/model/menu-save.model';
import { CutPlanBundleService } from './cut-plan-bundle.service';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';
import { Master } from 'app/shared/model/master.modal';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-cut-plan-bundle-print',
  templateUrl: './cut-plan-bundle-print.component.html'
})
export class CutPlanBundlePrintComponent implements OnInit {
  items: TreeviewItem[];
  printedItems: TreeviewItem[];
  dropdownEnabled = true;
  values: number[];
  printedValues: number[];
  cardNo: string;
  name: string;
  isSaving: boolean;
  @Input() search = new Master();
  menuAccessMaster: IMenuSave;
  @Input() btnType?: string;

  config = TreeviewConfig.create({
    hasAllCheckBox: true,
    hasFilter: true,
    hasCollapseExpand: true,
    decoupleChildFromParent: false,
    maxHeight: 500
  });

  constructor(
    protected cutPlanBundleService: CutPlanBundleService,
    public activeModal: NgbActiveModal,
    protected snotifyService: SnotifyService
  ) {}

  clearDetails() {
    this.name = '';
    this.items = [];
    this.values = [];
    this.printedItems = [];
    this.printedValues = [];
  }

  getDetails() {
    this.items = [];
    this.values = [];
    this.printedItems = [];
    this.printedValues = [];
    if (this.search) {
      this.search.btnType = this.btnType;
      this.cutPlanBundleService.tree(this.search).subscribe(menu => {
        menu.body.forEach(value => {
          const menuItem: TreeviewItem = Object.assign({}, value);
          const menuItem1: TreeviewItem = Object.assign({}, value);
          if (menuItem1.children !== null && menuItem1.children.length > 0) {
            menuItem.children = [];
            menuItem1.children.forEach(level1Item => {
              const menuLevel1Item: TreeviewItem = Object.assign({}, level1Item);
              const menuLevel1Item1: TreeviewItem = Object.assign({}, level1Item);
              if (menuLevel1Item1.children !== null && menuLevel1Item1.children.length > 0) {
                menuLevel1Item.children = [];
                menuLevel1Item1.children.forEach(level2Item => {
                  const menuLevel2Item: TreeviewItem = Object.assign({}, level2Item);
                  menuLevel1Item.children.push(new TreeviewItem(menuLevel2Item));
                  if (menuLevel2Item.checked === true) {
                    this.values.push(menuLevel2Item.value);
                  }
                });
                menuItem.children.push(new TreeviewItem(menuLevel1Item));
              } else {
                menuItem.children.push(new TreeviewItem(menuLevel1Item));
                if (menuLevel1Item.checked === true) {
                  this.values.push(menuLevel1Item.value);
                }
              }
            });
            this.items.push(new TreeviewItem(menuItem));
          } else {
            this.items.push(new TreeviewItem(menuItem));
            if (menuItem.checked === true) {
              this.values.push(menuItem.value);
            }
          }
        });
      });

      this.cutPlanBundleService.treeExist(this.search).subscribe(menu => {
        menu.body.forEach(value => {
          const menuItem: TreeviewItem = Object.assign({}, value);
          const menuItem1: TreeviewItem = Object.assign({}, value);
          if (menuItem1.children !== null && menuItem1.children.length > 0) {
            menuItem.children = [];
            menuItem1.children.forEach(level1Item => {
              const menuLevel1Item: TreeviewItem = Object.assign({}, level1Item);
              const menuLevel1Item1: TreeviewItem = Object.assign({}, level1Item);
              if (menuLevel1Item1.children !== null && menuLevel1Item1.children.length > 0) {
                menuLevel1Item.children = [];
                menuLevel1Item1.children.forEach(level2Item => {
                  const menuLevel2Item: TreeviewItem = Object.assign({}, level2Item);
                  menuLevel1Item.children.push(new TreeviewItem(menuLevel2Item));
                  if (menuLevel2Item.checked === true) {
                    this.printedValues.push(menuLevel2Item.value);
                  }
                });
                menuItem.children.push(new TreeviewItem(menuLevel1Item));
              } else {
                menuItem.children.push(new TreeviewItem(menuLevel1Item));
                if (menuLevel1Item.checked === true) {
                  this.printedValues.push(menuLevel1Item.value);
                }
              }
            });
            this.printedItems.push(new TreeviewItem(menuItem));
          } else {
            this.printedItems.push(new TreeviewItem(menuItem));
            if (menuItem.checked === true) {
              this.printedValues.push(menuItem.value);
            }
          }
        });
      });
    }
  }

  ngOnInit() {
    this.items = [];
    this.printedItems = [];
    this.getDetails();
  }

  printBundles() {
    this.isSaving = true;
    this.cutPlanBundleService.downloadBundles(this.values).subscribe(
      res => {
        FileSaver.saveAs(res, 'bundles.pdf');
        this.isSaving = false;
        this.getDetails();
      },
      res => {
        this.isSaving = false;
      }
    );
  }

  reprintBundles() {
    this.isSaving = true;
    this.cutPlanBundleService.downloadBundles(this.printedValues).subscribe(
      res => {
        FileSaver.saveAs(res, 'bundles.pdf');
        this.isSaving = false;
        this.getDetails();
      },
      res => {
        this.isSaving = false;
      }
    );
  }

  printPieces() {
    this.isSaving = true;
    this.cutPlanBundleService.downloadPieces(this.values).subscribe(
      res => {
        FileSaver.saveAs(res, 'pieces.pdf');
        this.isSaving = false;
        this.getDetails();
      },
      res => {
        this.isSaving = false;
      }
    );
  }

  reprintPieces() {
    this.isSaving = true;
    this.cutPlanBundleService.downloadPieces(this.printedValues).subscribe(
      res => {
        FileSaver.saveAs(res, 'pieces.pdf');
        this.isSaving = false;
        this.getDetails();
      },
      res => {
        this.isSaving = false;
      }
    );
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<any>>) {
    result.subscribe((res: HttpResponse<any>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.snotifyService.success('Save Successfully!', '', toastConfig);
  }

  protected onSaveError() {
    this.isSaving = false;
    this.snotifyService.error('Record not save!', '', toastConfig);
  }

  onFilterChange(value: string) {}

  cancel(): void {
    this.activeModal.dismiss();
  }
}

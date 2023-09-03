import { Component, OnInit } from '@angular/core';
import { MenuMasterService } from 'app/entities/menu-master';
import { TreeviewConfig, TreeviewItem } from 'ngx-treeview';
import { EmployeeViewService } from 'app/entities/employee-view';
import { IMenuSave, MenuSave } from 'app/shared/model/menu-save.model';
import { MenuAccessMasterService } from './menu-access-master.service';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { SnotifyService } from 'ng-snotify';
import { toastConfig } from 'app/core/toast/toast-config';

@Component({
  selector: 'jhi-menu-access-master',
  templateUrl: './menu-access-master.component.html'
})
export class MenuAccessMasterComponent implements OnInit {
  items: TreeviewItem[];
  dropdownEnabled = true;
  values: number[];
  cardNo: string;
  name: string;
  isSaving: boolean;
  menuAccessMaster: IMenuSave;

  config = TreeviewConfig.create({
    hasAllCheckBox: true,
    hasFilter: true,
    hasCollapseExpand: true,
    decoupleChildFromParent: false,
    maxHeight: 500
  });

  constructor(
    protected employeeViewService: EmployeeViewService,
    protected menuMasterService: MenuMasterService,
    protected menuAccessMasterService: MenuAccessMasterService,
    protected snotifyService: SnotifyService
  ) {}

  clearDetails() {
    this.name = '';
    this.items = [];
    this.values = [];
  }

  getDetails() {
    this.items = [];
    this.values = [];
    if (this.cardNo !== null && this.cardNo.length > 0) {
      this.employeeViewService.findByCard(this.cardNo).subscribe(user => {
        this.name = user.body.name;
        if (this.name !== null && this.name.length > 0) {
          this.menuMasterService.tree(this.cardNo).subscribe(menu => {
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
        }
      });
    } else {
      this.name = '';
      this.items = [];
    }
  }

  ngOnInit() {
    this.items = [];
  }

  save() {
    this.isSaving = true;
    this.menuAccessMaster = new MenuSave();
    this.menuAccessMaster.cardNo = this.cardNo;
    this.menuAccessMaster.menus = this.values;
    this.subscribeToSaveResponse(this.menuAccessMasterService.create(this.menuAccessMaster));
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

  onFilterChange(value: string) {
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { IUserPlantDetailsNew, UserPlantDetailsNew } from 'app/shared/model/user-plant-details-new.model';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { UserPlantService } from './user-plant.service';
import { IUserPlantNew, UserPlantNew } from 'app/shared/model/user-plant-new.model';
import { SnotifyPosition, SnotifyService } from 'ng-snotify';
import { CutPlanBundleService } from '../cut-plan-bundle/cut-plan-bundle.service';
import { EmployeeViewService } from 'app/entities/employee-view';
import { IMasterSearch, MasterSearch } from 'app/shared/model/master-search.model';

@Component({
  selector: 'jhi-user-plant-update',
  templateUrl: './user-plant-update.component.html'
})
export class UserPlantUpdateComponent implements OnInit {
  search: IMasterSearch;
  isSaving: boolean;
  userPlantNew: IUserPlantNew;
  userPlantDetailsNews: IUserPlantDetailsNew[] = [];
  name: string;

  constructor(
    protected cutPlanEntryService: CutPlanBundleService,
    protected employeeViewService: EmployeeViewService,
    protected userPlantService: UserPlantService,
    protected activatedRoute: ActivatedRoute,
    protected snotifyService: SnotifyService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;

    this.activatedRoute.data.subscribe(({ userPlantNew }) => {
      this.search = new MasterSearch();
      this.userPlantNew = new UserPlantNew();
      this.userPlantNew.userPlantDetailsNew = [];
      this.userPlantDetailsNews = [];
      this.userPlantService.plants().subscribe(userPlantDetailsNew => {
        this.userPlantDetailsNews = userPlantDetailsNew.body;
      });
      this.defaultDetails();
      // this.loadNewPlant();
    });
  }

  defaultDetails(): void {
    for (let i = 0; i < 5; i++) {
      this.userPlantNew.userPlantDetailsNew.push(new UserPlantDetailsNew());
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.userPlantNew.login !== undefined) {
      this.subscribeToSaveResponse(this.userPlantService.create(this.userPlantNew));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserPlantNew>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }

  loadNewPlant() {
    this.userPlantNew.userPlantDetailsNew = new Array<UserPlantDetailsNew>();
    for (let i = 0; i < 1; i++) {
      this.userPlantNew.userPlantDetailsNew.push(new UserPlantDetailsNew());
    }
  }

  addRow(): void {
    if (this.userPlantNew.userPlantDetailsNew) {
      this.userPlantNew.userPlantDetailsNew.push(new UserPlantDetailsNew());
    } else {
      this.userPlantNew.userPlantDetailsNew = [];
      this.userPlantNew.userPlantDetailsNew.push(new UserPlantDetailsNew());
    }
  }

  removeRow(index: any): void {
    if (this.userPlantNew.userPlantDetailsNew[index].plantCode !== undefined) {
      this.deleteRowPassg(this.userPlantNew.userPlantDetailsNew[index].plantCode, this.userPlantNew.login, index);
    } else {
      this.userPlantNew.userPlantDetailsNew.splice(index, 1);
    }
  }

  deleteRowPassg(id, login, index): void {
    this.snotifyService.confirm('Are you sure to delete row?', 'Confirm', {
      timeout: 25000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true,
      position: SnotifyPosition.centerTop,
      buttons: [
        { text: 'Yes', action: toast => this.deletePassg(toast, id, login, index), bold: false },
        { text: 'No', action: toast => this.snotifyService.remove(toast.id) }
      ]
    });
  }

  deletePassg(toast, id, login, index): void {
    this.userPlantService.deleteDetailRow(id, login).subscribe(any => {
      this.snotifyService.remove(toast.id);
      this.userPlantNew.userPlantDetailsNew.splice(index, 1);
    });
  }

  getLoginDetail(): void {
    if (this.userPlantNew.login && this.userPlantNew.login !== undefined) {
      this.employeeViewService.findByCard(this.userPlantNew.login).subscribe(user => {
        this.userPlantNew.login = user.body.login;
        this.name = user.body.name;
        this.userPlantService.find(this.userPlantNew.login).subscribe(userPlantNew => {
          if (userPlantNew.body.userPlantDetailsNew && userPlantNew.body.userPlantDetailsNew.length > 0) {
            this.userPlantNew.userPlantDetailsNew = userPlantNew.body.userPlantDetailsNew;
          }
        });
      });
    }
  }

  selectPlantDesc(userPlantDetails: UserPlantDetailsNew): void {
    this.userPlantDetailsNews.forEach(value => {
      if (value.plantCode === userPlantDetails.plantCode) {
        userPlantDetails.plantDescription = value.plantDescription;
      }
    });
  }
}

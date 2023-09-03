import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IInstituteMaster } from 'app/shared/model/institute-master.model';

@Component({
  selector: 'jhi-institute-master-detail',
  templateUrl: './institute-master-detail.component.html'
})
export class InstituteMasterDetailComponent implements OnInit {
  instituteMaster: IInstituteMaster;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ instituteMaster }) => {
      this.instituteMaster = instituteMaster;
    });
  }

  previousState() {
    window.history.back();
  }
}

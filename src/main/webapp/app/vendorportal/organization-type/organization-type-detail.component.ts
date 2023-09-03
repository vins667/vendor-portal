import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IOrganizationType } from 'app/shared/model/organization-type.model';

@Component({
  selector: 'jhi-organization-type-detail',
  templateUrl: './organization-type-detail.component.html'
})
export class OrganizationTypeDetailComponent implements OnInit {
  organizationType: IOrganizationType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ organizationType }) => {
      this.organizationType = organizationType;
    });
  }

  previousState() {
    window.history.back();
  }
}

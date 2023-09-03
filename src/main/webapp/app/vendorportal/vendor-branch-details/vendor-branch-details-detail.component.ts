import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IVendorBranchDetails } from 'app/shared/model/vendor-branch-details.model';

@Component({
  selector: 'jhi-vendor-branch-details-detail',
  templateUrl: './vendor-branch-details-detail.component.html'
})
export class VendorBranchDetailsDetailComponent implements OnInit {
  vendorBranchDetails: IVendorBranchDetails;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vendorBranchDetails }) => {
      this.vendorBranchDetails = vendorBranchDetails;
    });
  }

  previousState() {
    window.history.back();
  }
}

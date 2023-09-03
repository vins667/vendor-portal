import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGstGovtUpload } from 'app/shared/model/gst-govt-upload.model';

@Component({
  selector: 'jhi-gst-govt-upload-detail',
  templateUrl: './gst-govt-upload-detail.component.html'
})
export class GstGovtUploadDetailComponent implements OnInit {
  gstGovtUpload: IGstGovtUpload;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gstGovtUpload }) => {
      this.gstGovtUpload = gstGovtUpload;
    });
  }

  previousState() {
    window.history.back();
  }
}

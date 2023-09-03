import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGstVoplUpload } from 'app/shared/model/gst-vopl-upload.model';

@Component({
  selector: 'jhi-gst-vopl-upload-detail',
  templateUrl: './gst-vopl-upload-detail.component.html'
})
export class GstVoplUploadDetailComponent implements OnInit {
  gstVoplUpload: IGstVoplUpload;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gstVoplUpload }) => {
      this.gstVoplUpload = gstVoplUpload;
    });
  }

  previousState() {
    window.history.back();
  }
}

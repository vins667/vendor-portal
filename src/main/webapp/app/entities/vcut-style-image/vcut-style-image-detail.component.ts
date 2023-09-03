import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVcutStyleImage } from 'app/shared/model/vcut-style-image.model';

@Component({
  selector: 'jhi-vcut-style-image-detail',
  templateUrl: './vcut-style-image-detail.component.html'
})
export class VcutStyleImageDetailComponent implements OnInit {
  vcutStyleImage: IVcutStyleImage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ vcutStyleImage }) => {
      this.vcutStyleImage = vcutStyleImage;
    });
  }

  previousState() {
    window.history.back();
  }
}

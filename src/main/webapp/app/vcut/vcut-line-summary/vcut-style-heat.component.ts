import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IVcutTvImageSummary } from 'app/shared/model/vcut-tv-image-summary.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-vcut-style-heat',
  templateUrl: './vcut-style-heat.component.html'
})
export class VcutStyleHeatComponent implements OnInit {
  vcutTvImageSummary?: IVcutTvImageSummary;
  pointSize = 7;
  element: Element;
  root: Element;
  ctx;
  canvas;
  frontImgWidth: number;
  frontImgHeight: number;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit() {
    if (this.vcutTvImageSummary) {
      const image = {
        url: this.vcutTvImageSummary.imageFront,
        context: 'Mahila self help group',
        width: [''],
        height: ['']
      };

      this.frontImgWidth = 460;
      this.frontImgHeight = 500;
      if (this.vcutTvImageSummary.frontCordinates && this.vcutTvImageSummary.frontCordinates.length > 0) {
        this.vcutTvImageSummary.frontCordinates.forEach(frontCordinate => {
          this.getPosition(
            'canvasFront',
            this.frontImgWidth * Number(frontCordinate.coordinateX),
            this.frontImgHeight * Number(frontCordinate.coordinateY)
          );
        });
      }

      if (this.vcutTvImageSummary.backCordinates && this.vcutTvImageSummary.backCordinates.length > 0) {
        this.vcutTvImageSummary.backCordinates.forEach(frontCordinate => {
          this.getPosition(
            'canvasBack',
            this.frontImgWidth * Number(frontCordinate.coordinateX),
            this.frontImgHeight * Number(frontCordinate.coordinateY)
          );
        });
      }
      /*this.getImageDimension(image).subscribe(response => {

      });*/
      /*img.src = this.vcutTvImageSummary.imageFront;
      this.frontImgWidth = img.naturalWidth;
      this.frontImgHeight = img.naturalHeight;
      console.log(this.frontImgWidth);
      console.log(this.frontImgHeight);*/
    }
  }

  getImageDimension(image): Observable<any> {
    return new Observable(observer => {
      const img = new Image();
      img.onload = function(event) {
        const loadedImage: any = event.currentTarget;
        image.width = loadedImage.width;
        image.height = loadedImage.height;
        observer.next(image);
        observer.complete();
      };
      img.src = image.url;
    });
  }

  getPosition(type, offsetX, offsetY) {
    console.log(type, offsetX, offsetY);
    this.element = <Element>this.root;
    this.canvas = <HTMLCanvasElement>document.getElementById(type);
    this.ctx = this.canvas.getContext('2d');
    let curleft = 0;
    let curtop = 0;

    curleft += offsetX;
    curtop += offsetY;
    this.drawCoordinates(curleft, curtop);
  }

  drawCoordinates(x, y) {
    const grd = this.ctx.createLinearGradient(0, 0, 170, 0);
    grd.addColorStop(0, 'black');
    grd.addColorStop(1, 'red');
    this.ctx.fillStyle = grd; // Red color

    this.ctx.beginPath();
    this.ctx.arc(Number(x), Number(y), this.pointSize, 0, Math.PI * 2, true);
    this.ctx.fill();

    const coord = 'x=' + x + ', y=' + y;
    const p = this.ctx.getImageData(x, y, 1, 1).data;
    /* const hex = '#FF0000';// + this.rgbToHex(p[0], p[1], p[2])).slice(-6);
    console.log(hex); */
  }

  close() {
    this.activeModal.dismiss('cancel');
  }
}

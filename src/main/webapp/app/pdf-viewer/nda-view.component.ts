import { Component } from '@angular/core';

@Component({
  selector: 'jhi-nda-view-details',
  templateUrl: './nda-view.component.html'
})
export class NdaViewComponent {
  page = 1;
  totalPages: number;
  isLoaded = false;
  isNavbarCollapsed: boolean;

  afterLoadComplete(pdfData: any) {
    this.totalPages = pdfData.numPages;
    this.isLoaded = true;
  }

  nextPage() {
    this.page++;
  }

  prevPage() {
    this.page--;
  }

  changePage() {
    this.page = 1;
  }
}

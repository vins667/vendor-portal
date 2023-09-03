import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbdModalComponent } from 'app/pdf-viewer/ngb-model-component';
import { User } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-nda-details',
  templateUrl: './nda.component.html'
})
export class NdaComponent implements OnInit {
  user: User;

  constructor(private userService: UserService, private modalService: NgbModal) {}

  ngOnInit() {
    this.userService.currentuser().subscribe(user => {
      this.user = user.body;
      if (this.user.ndaActivated !== true) {
        this.open();
      }
    });
  }

  open() {
    const modalRef = this.modalService.open(NgbdModalComponent, { size: 'lg' });
    modalRef.componentInstance.name = 'World';
  }
}

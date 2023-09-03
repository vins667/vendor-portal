import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { EmailInvitationComponent, EmailInvitationUpdateComponent, emailInvitationRoute, emailInvitationPopupRoute } from './';

const ENTITY_STATES = [...emailInvitationRoute, ...emailInvitationPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [EmailInvitationComponent, EmailInvitationUpdateComponent],
  entryComponents: [EmailInvitationComponent, EmailInvitationUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalEmailInvitationModule {}

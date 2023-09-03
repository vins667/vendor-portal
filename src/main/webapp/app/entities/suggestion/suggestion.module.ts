import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VamaniportalSharedModule } from 'app/shared/shared.module';
import { SuggestionUpdateComponent, suggestionRoute, suggestionPopupRoute } from './';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
const ENTITY_STATES = [...suggestionRoute, ...suggestionPopupRoute];

@NgModule({
  imports: [VamaniportalSharedModule, RouterModule.forChild(ENTITY_STATES), SnotifyModule],
  declarations: [SuggestionUpdateComponent],
  entryComponents: [SuggestionUpdateComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults }, SnotifyService]
})
export class VamaniportalSuggestionModule {}

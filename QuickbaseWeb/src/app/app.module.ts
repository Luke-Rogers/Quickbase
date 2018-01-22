import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from  '@angular/forms';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatInputModule,
  MatAutocompleteModule,
  MatMenuModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {ActionBlockComponent} from './action-block/action-block.component';
import {ActionSettingsComponent} from './action-settings/action-settings.component';
import { ActionTableComponent } from './action-table/action-table.component';

@NgModule({
  declarations: [
    AppComponent,
    ActionBlockComponent,
    ActionSettingsComponent,
    ActionTableComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, BrowserAnimationsModule, HttpClientModule,
    MatButtonModule, MatCheckboxModule, MatAutocompleteModule, MatInputModule, MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

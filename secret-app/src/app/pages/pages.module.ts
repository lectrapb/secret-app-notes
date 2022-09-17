import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SecretComponent } from './secret/secret.component';
import { SecretNotesComponent } from './secret-notes/secret-notes.component';



@NgModule({
  declarations: [
    DashboardComponent,
    SecretComponent,
    SecretNotesComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class PagesModule { }

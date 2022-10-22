import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../guard/auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SecretNotesComponent } from './secret-notes/secret-notes.component';
import { SecretComponent } from './secret/secret.component';

const routes: Routes = [
     
    {path: 'dashboard',
     component: DashboardComponent, 
     canActivate:[AuthGuard],
     children: [
        {path:'secret-password', component: SecretComponent},
        {path:'secret-notes', component: SecretNotesComponent},
     ]
    }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }

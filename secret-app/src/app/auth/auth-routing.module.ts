import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './signup/signup.component';


const routes: Routes = [

     {path:'login', component: LoginComponent},
     {path:'signUp', component: SignUpComponent},
     {path:'**', pathMatch:'full', redirectTo:'/login'}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }

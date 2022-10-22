import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { Constant } from 'src/app/model/constant.model';

import { UserService } from '../../service/user.service';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {

 
  public signInForm = this.fb.group({
        
       email: ['', [Validators.required, Validators.pattern(Constant.emailPattern)]],
       password: ['',[Validators.required, Validators.minLength(6)]]
  });

  constructor(private fb:FormBuilder,
              private userService:UserService,
              private router:Router) { }

  ngOnInit(): void {

       const cemail:string = localStorage.getItem(Constant.REMEMBER_USER ) || '';
       this.signInForm.setValue({'email' :cemail, 'password': ''});
       if(!cemail){
        this.loadEmailFromToken();
       }
       
  }

  loadEmailFromToken():void{
    const token: string = localStorage.getItem(Constant.TOKEN) || '';
    if(token){         
      this.userService.validateToken(token)
          .subscribe(resp =>{
             if(resp){
               const user: User = this.userService.currentUser;
               this.signInForm.setValue({'email' :user.email, 'password': ''});        
             }
          });            
     }
  }

  signIn(): void {

    this.userService.signIn(this.signInForm.value)
                    .subscribe(resp =>{
                        if(resp){
                           Swal.fire('Success', 'Welcome to secret app!', 'success');
                           this.router.navigateByUrl('/dashboard');
                        }else{
                           Swal.fire('Error', 'Error Sign-in try again!', 'error'); 
                        }
                    });

  }

  get email() {return this.signInForm.get('email')}; 
  get password() {return this.signInForm.get('password')}; 
  set email(email:any) {this.signInForm.setValue({'email' :email})}; 


}

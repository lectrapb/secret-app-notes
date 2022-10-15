import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {

  private emailPattern: any = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  public signInForm = this.fb.group({
        
       email: ['test@gmail.com', [Validators.required, Validators.pattern(this.emailPattern)]],
       password: ['123456',[Validators.required, Validators.minLength(6)]]
  });

  constructor(private fb:FormBuilder,
              private userService:UserService,
              private router:Router) { }

  ngOnInit(): void {
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

}

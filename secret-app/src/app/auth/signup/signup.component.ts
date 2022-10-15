import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { v4 as uuidv4 } from 'uuid';
import Swal from 'sweetalert2';

import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './signup.component.html',
  styles: [
  ]
})
export class SignUpComponent implements OnInit {

  private emailPattern: any = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  public signUpForm = this.fb.group({
     
      name: ['Giovanny', [Validators.required, Validators.minLength(2)]], 
      email:['test@gmail.com', [Validators.required, Validators.pattern(this.emailPattern)]], 
      password: ['123456',[Validators.required, Validators.minLength(6)]]
  });

  constructor(private fb:FormBuilder, 
              private userService: UserService, 
              private router: Router) { }

  ngOnInit(): void {
  }

  signUp(): void {
  
      this.userService.signUp(this.signUpForm.value, uuidv4())
                      .subscribe( resp =>{
                         if(resp){
                            Swal.fire('Succesfull', 'Get into secret App', 'success');
                            this.router.navigateByUrl('/login');
                         }else{
                            Swal.fire('Error', 'Error Sing-up please try again!', 'error');                          
                         }
                      });
      
  }

  get name():any{return this.signUpForm.get('name')};
  get email():any{return this.signUpForm.get('email')};
  get password():any{return this.signUpForm.get('password')};

}

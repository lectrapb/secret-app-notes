import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map, catchError } from "rxjs/operators";

import { SignUpForm } from '../interfaces/SignUpForm.interface';

import { environment } from '../../environments/environment.prod';
import { Response } from '../interfaces/response.interface';
import { SignInForm } from '../interfaces/SignInForm.interface';
import { Constant } from '../model/constant.model';


const url_base = environment.url_base;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private reponse!: Response;

  constructor(private http: HttpClient) { }

  get headers(){
    return {
      headers:{
        'content-type': 'application/json'
      }
    }
  } 

  signUp(userForm: SignUpForm, uid: string): Observable<boolean>{
                  
      const url = `${url_base}/signUp`;
      userForm.uid = uid;
      userForm.role = "USER";
      
      return this.http.post(url, userForm, this.headers)
                          .pipe(
                             map( (resp: any) =>true),
                             catchError(err => of(false))
                          );
      
  }

  signIn(userForm: SignInForm): Observable<boolean>{

     const url = `${url_base}/signIn`;

     return this.http.post(url, userForm, this.headers)
                          .pipe(
                             map( (resp: any) =>{
                               this.reponse = resp as Response;
                               localStorage.setItem(Constant.TOKEN, this.reponse.data[0].attributes.token);    
                               localStorage.setItem(Constant.REMEMBER_USER,this.reponse.data[0].attributes.name);
                               return true;
                             }),
                             catchError(err => of(false))
                          );
  }

}

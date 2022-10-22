import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import { tap} from 'rxjs/operators';
import { Constant } from '../model/constant.model';
import { UserService } from '../service/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private userService:UserService,
              private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) {

    const token:string = localStorage.getItem(Constant.TOKEN) || '';
    return this.userService.validateToken(token)
               .pipe(
                 tap( isValidate =>{
                      if(!isValidate){
                         this.router.navigateByUrl(`/${Constant.PATH_LOGIN}`);
                      }

                 })
               )
    
  }
  
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Constant } from 'src/app/model/constant.model';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styles: [
  ]
})
export class SidebarComponent implements OnInit {

  constructor( public userServide: UserService,
               private router:Router) { }

  ngOnInit(): void {
  }

  closeSession():void{
      localStorage.removeItem(Constant.REMEMBER_USER);
      this.router.navigateByUrl(Constant.PATH_LOGIN);
  }

}

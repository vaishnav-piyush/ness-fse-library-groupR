import { Component, OnInit } from '@angular/core';
import { User } from "./user.model";
import { UserService } from "./user.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-audit-list',
  templateUrl: './user-listall.component.html',
  styleUrls: ['./register-user.component.css']
})
export class ListAllUserComponent implements OnInit {

  pageTitle: string ;
  auditList: User[];
  errorMessage : string;
  successMessage : string;
  currentAudit : User;
  eventId:string;

  
  constructor(private _auditService : UserService) { 
    this.pageTitle = "All users";
    
  }


  ngOnInit() {
    this._auditService.getAllUsers().subscribe(audits=> {this.auditList = audits});
  }

}

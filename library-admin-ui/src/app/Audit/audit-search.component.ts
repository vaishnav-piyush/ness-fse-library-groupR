import { Component, OnInit } from '@angular/core';
import { Audit } from "./audit.model";
import { AuditService } from "./audit.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-audit-list',
  templateUrl: './audit-search.component.html',
  styleUrls: ['./available-books.component.css']
})
export class AuditComponent implements OnInit {

  pageTitle: string ;
  auditList: Audit[];
  errorMessage : string;
  successMessage : string;
  currentAudit : Audit;
  eventId:string;

  
  constructor(private _auditService : AuditService) { 
    this.pageTitle = "Audit";
    this.currentAudit = { 
      "id" : '59dca04e1046555723e739b3', 
      "event_id" : "T3", 
      "event_name" : "Prasanna", 
      "login_name" : "D3", 
      "notes" : "AVAILABLE",
      "updatedDate" : new Date("2017-10-10T10:26:17.897Z") 
    };
  }


  getAudit()
  {
    this._auditService.getAudits(this.eventId).subscribe(audits=> {this.auditList = audits});
  }

  ngOnInit() {
    this._auditService.getAudits(this.eventId).subscribe(audits=> {this.auditList = audits});
  }

}

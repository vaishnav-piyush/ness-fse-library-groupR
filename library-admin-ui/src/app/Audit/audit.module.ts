import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuditComponent } from "./audit-search.component";
import { AuditService } from "./audit.service";
import { AuditRouting } from "./audit.routing";

@NgModule({
  imports: [
    CommonModule, 
    AuditRouting
  ],
  declarations: [
    AuditComponent], 
  providers: [
    AuditService
  ]
})
export class AuditModule { }

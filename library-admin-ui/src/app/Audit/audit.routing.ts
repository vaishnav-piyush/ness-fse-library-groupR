import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuditComponent } from "./audit-search.component";


const auditRoutes : Routes = [    
    { path : 'audit', component : AuditComponent }
]

export const AuditRouting:ModuleWithProviders = RouterModule.forChild(auditRoutes);

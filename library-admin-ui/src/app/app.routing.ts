import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AvailableBooksComponent } from "./books/available-books.component";
import { IssuedBooksComponent } from "./books/issued-books.component";
import { NewBookComponent } from "./books/new-book.component";
import { RegisterUserComponent } from "./user/register-user.component";
import { AuditComponent } from "./audit/audit-search.component";


const appRoutings : Routes = [
    { path: '', redirectTo: 'available-books', pathMatch: 'full' },
    { path : 'available-books', component : AvailableBooksComponent },
    { path: 'issued-books', component: IssuedBooksComponent },
    { path: 'new-book', component: NewBookComponent },
    { path: 'register-user', component: RegisterUserComponent },
    { path: 'audit', component: AuditComponent }
]

export const AppRoutes:ModuleWithProviders = RouterModule.forRoot(appRoutings);

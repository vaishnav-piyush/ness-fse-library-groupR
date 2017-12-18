import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AvailableBooksComponent } from "./books/available-books.component";
import { IssuedBooksComponent } from "./books/issued-books.component";
import { NewBookComponent } from "./books/new-book.component";
import { RegisterUserComponent } from "./user/register-user.component";
import { ListAllUserComponent } from "./user/user-listall.component";
import { AuditComponent } from "./audit/audit-search.component";
import { AuthGuard } from "./guards/auth.guard";
import { LoginComponent } from "./login/login.component";


const appRoutings : Routes = [
    { path : '', redirectTo: 'available-books', pathMatch: 'full' },
    { path : 'login', component: LoginComponent},
    { path : 'available-books', component : AvailableBooksComponent, canActivate: [AuthGuard] },
    { path: 'issued-books', component: IssuedBooksComponent, canActivate: [AuthGuard] },
    { path: 'new-book', component: NewBookComponent, canActivate: [AuthGuard] },
    { path: 'register-user', component: RegisterUserComponent, canActivate: [AuthGuard] },
    { path: 'audit', component: AuditComponent, canActivate: [AuthGuard] },
    { path: 'users', component: ListAllUserComponent, canActivate: [AuthGuard] }
]

export const AppRoutes:ModuleWithProviders = RouterModule.forRoot(appRoutings);

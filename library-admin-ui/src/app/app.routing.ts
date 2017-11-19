import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AvailableBooksComponent } from "./books/available-books.component";
import { IssuedBooksComponent } from "./books/issued-books.component";
import { NewBookComponent } from "./books/new-book.component";
import { RegisterUserComponent } from "./user/register-user.component";


const appRoutings : Routes = [
    { path: '', redirectTo: 'available-books', pathMatch: 'full' },
    { path : 'available-books', component : AvailableBooksComponent },
    { path: 'issued-books', component: IssuedBooksComponent },
    { path: 'new-book', component: NewBookComponent },
    { path: 'register-user', component: RegisterUserComponent }
]

export const AppRoutes:ModuleWithProviders = RouterModule.forRoot(appRoutings);

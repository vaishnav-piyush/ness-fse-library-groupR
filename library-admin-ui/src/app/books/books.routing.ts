import { ModuleWithProviders, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AvailableBooksComponent } from "./available-books.component";


const booksRoutes : Routes = [    
    { path : 'available-books', component : AvailableBooksComponent }
]

export const BooksRouting:ModuleWithProviders = RouterModule.forChild(booksRoutes);

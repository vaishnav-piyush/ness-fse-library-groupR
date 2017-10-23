import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router'
import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http"
import { AppRoutes } from "./app.routing";
import { BookService } from "./books/book.service";
import { AvailableBooksComponent } from "./books/available-books.component";
import { IssuedBooksComponent } from "./books/issued-books.component";
import { UserFormComponent } from "./books/user-form.component";
import { FormsModule } from "@angular/forms";
import { NewBookComponent } from "./books/new-book.component";
@NgModule({
  declarations: [
    AppComponent,
    AvailableBooksComponent, 
    IssuedBooksComponent, 
    UserFormComponent,
    NewBookComponent
  ],
  imports: [
    BrowserModule, 
    HttpClientModule,
    AppRoutes, 
    FormsModule
  ],
  providers: [
    BookService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

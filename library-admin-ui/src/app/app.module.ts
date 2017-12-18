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
import {RegisterUserComponent} from "./user/register-user.component";
import { ListAllUserComponent } from "./user/user-listall.component";
import {UserService} from "./user/user.service";
import {AuditComponent} from "./audit/audit-search.component";
import {AuditService} from "./audit/audit.service";
import { AuthGuard } from "./guards/auth.guard";
import { LoginComponent } from './login/login.component';
import { AuthenticationService } from './login/authentication.service';

@NgModule({
  declarations: [
    AppComponent,
    AvailableBooksComponent,
    IssuedBooksComponent,
    UserFormComponent,
    NewBookComponent,
    RegisterUserComponent,
    AuditComponent,
    LoginComponent,
    ListAllUserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutes,
    FormsModule
  ],
  providers: [
    BookService,
    UserService,
    AuditService,
    AuthGuard,
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

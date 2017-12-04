import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { User } from './user.model';
import { UserService } from './user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  user : User;
  pageTitle: string;
  submitted: boolean = false;
  errorMessage: string;
  successMessage: string;
  @ViewChild('successAlert') successAlert : ElementRef;

  constructor(private _userService : UserService) {
    this.pageTitle = "Register User";
    this.user = new User();
  }

  ngOnInit() {
  }

  onReset() {
    this.successMessage = "";
    this.submitted = false;
  }

  onSubmit() {
    this._userService.createUser(this.user).subscribe(
      createdUser => {
        console.log("New user is created: " + createdUser);
        this.errorMessage = "";
        this.successMessage = "New User '"+ createdUser.userName + "' Created Successfully.   ";
        this.submitted = true;
      },
      (err : HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          this.successMessage = "";
          this.errorMessage = "Something went wrong, unable to complete operation.";
          this.submitted = true;
        } else if(err.status == 401) {
          this.successMessage = "";
          console.log(`Error: Http Status Code ${err.status}, Error Description: ${err.error.error_description}, Response Body: ` + JSON.stringify(err.error));
          this.errorMessage = "You are not authorized to do this operation.";
          this.submitted = true;
        } else if(err.status == 400){
          this.successMessage = "";
          this.errorMessage = "";
          for (var i=0; i< err.error.errors.length; i++) {
            this.errorMessage =  this.errorMessage + err.error.errors[i].defaultMessage + '.    \n';
          }
          this.submitted = true;
        } else {
          console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
          this.successMessage = "";
          this.errorMessage = "Coudn't publish update to the server. Please try again later.";
          this.submitted = true;
        }
      }
    );
  }

}

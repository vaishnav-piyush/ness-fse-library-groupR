import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthenticationService } from "./authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};
  loading = false;
  error = '';

  constructor(private _router : Router, 
    private _authService: AuthenticationService
  ) { }

  ngOnInit() {
    //logout here
    this._authService.logout();
  }

  login() {
    this.loading = true;
    this._authService.authenticate(this.model.username, this.model.password)
    .subscribe(result => {
      console.log("Result: " + result);
      if(result) {
        //login successful, navigate to home route
        this._router.navigate(['/']);
      } else {
        this.error = 'Username or password is incorrect';
        this.loading = false;
      }
    },
    err => {
      console.log(err);
      console.log("Error Status: " +  err.status);
      if(err.status == 400) {
        this.error = 'Username or password is incorrect';
      } else {
        this.error = "Something has gone wrong. Please try again later";
      }      
      this.loading = false;
    }
  );
  }
}

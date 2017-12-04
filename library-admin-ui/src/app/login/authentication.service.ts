import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { HttpHeaders } from "@angular/common/http";
import { JwtToken } from "./jwt-token.model";
import { HttpErrorResponse } from "@angular/common/http";

@Injectable()
export class AuthenticationService {

  public access_token : string;
  public refresh_token: string;
  private _clientId: string = 'trusted-app';
  private _clientSecret: string = 'secret';
  private _urlGetToken = "http://localhost:9090/oauth/token"

  constructor(private _http : HttpClient) {
    var currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.access_token = currentUser && currentUser.token;
   }

   authenticate(username: string, password: string) : Observable<boolean> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + btoa(this._clientId + ":" + this._clientSecret)); 
    headers = headers.set('Content-Type', 'application/x-www-form-urlencoded');
    let urlParams = "grant_type=password&client_id=trusted-app&username=" + username + "&password=" + password;
    console.log(urlParams)
    return this._http.post<JwtToken>(this._urlGetToken, urlParams, {headers: headers}).retry(3)
    .do((rspBody : JwtToken) => { 
      
      if(rspBody.access_token != null) {
        console.log('Login Successfully, Token Received: ' + rspBody.access_token);        
        this.access_token = rspBody.access_token;
        this.refresh_token = rspBody.refresh_token;
        //set the token in local storage
        localStorage.setItem('currentUser', JSON.stringify(
          { username: username, 
            access_token: rspBody.access_token,
            refresh_token: rspBody.refresh_token
          })
        );
        return true;
      } else {
        return false;
      }
    })
    .catch((errBody : HttpErrorResponse) => {
      return Observable.throw(errBody);
    });     
   }

   logout(): void {
    this.access_token = null;
    this.refresh_token = null;
    //remove currentUser from local storage
    localStorage.removeItem('currentUser');
   }

   private ErrorHandler(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import { HttpErrorResponse } from "@angular/common/http";
import 'rxjs/add/operator/retry';
import 'rxjs/add/observable/throw';
import {User} from './user.model';

@Injectable()
export class UserService {
  private _urlGetAllUsers = 'http://localhost:8765/fse/usr/user';
  private _urlcreateUser = 'http://localhost:8765/fse/usr/user';

  constructor(private _http : HttpClient) {

  }

  getAllUsers() : Observable<User[]> {
    return this._http.get<User[]>(this._urlGetAllUsers).retry(3)
    .do((data:User[]) => console.log('Number of users fetched: ' + data.length))
    .catch(this.ErrorHandler);
  }

  createUser(user : User) : Observable<User> {
    console.log('Creating new user: ' + JSON.stringify(user));
    return this._http.post(this._urlcreateUser, user).retry(3)
      .do(data => console.log('Created New User: ' + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  private ErrorHandler(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err);
  }

}

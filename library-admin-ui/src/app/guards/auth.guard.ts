import { CanActivate, Router } from "@angular/router";
import { Injectable } from '@angular/core';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private _router : Router) {}

    canActivate(): boolean  {
        if ( localStorage.getItem('currentUser')) {
            //logged in
            return true;
        } else {
            //return to login page
            this._router.navigate(['/login']);
        }
    }

}

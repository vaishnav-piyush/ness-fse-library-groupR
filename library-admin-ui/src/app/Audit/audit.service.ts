import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Audit } from "./audit.model";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import { HttpErrorResponse } from "@angular/common/http";
import 'rxjs/add/operator/retry';

@Injectable()
export class AuditService {
   private _urlGetAllAudits = 'http://localhost:8765/fse/aud/audit';

  constructor(private _http : HttpClient) { 

  }

  getAllAudits() : Observable<Audit[]> {
    return this._http.get<Audit[]>(this._urlGetAllAudits).retry(3)
    //.do((data:Audit[]) => console.log('Number of books fetched: ' + data.length))
    .catch(this.ErrorHandler);
  }

  getAudits(event_id : string) : Observable<Audit[]> {
    return this._http.get<Audit[]>(this._urlGetAllAudits +"/"+event_id).retry(3)
    .do((data:Audit[]) => console.log('Number of audits fetched: ' + data.length))
    .catch(this.ErrorHandler);
  }

  private ErrorHandler(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err);
  }

}

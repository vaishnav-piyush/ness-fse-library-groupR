import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Book } from "./book.model";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import { HttpErrorResponse } from "@angular/common/http";
import 'rxjs/add/operator/retry';
import { HttpHeaders } from "@angular/common/http";

@Injectable()
export class BookService {
  // private _urlGetAllBooks = '../testdata/all-books.json';
  private _urlGetAllBooks = 'http://localhost:8765/fse/lib/book';
  private _urlGetBookById = "../testdata/single-book.json";
  private _urlUpdateBook = "http://localhost:8765/fse/lib/book"
  private _urlCreateBook = "http://localhost:8765/fse/lib/book";
  private _urlDeleteBook = "http://localhost:8765/fse/lib/book";

  constructor(private _http : HttpClient) {

  }

  getAllBooks() : Observable<Book[]> {
    let currentUser : any = localStorage.getItem('currentUser');
    let access_token : string =  JSON.parse(currentUser).access_token; 
    let headers : HttpHeaders = new HttpHeaders();
    console.log(JSON.stringify(currentUser));
    console.log("Bearer " + JSON.parse(currentUser).access_token);
    headers = headers.set("Authorization", `Bearer ${access_token}`);
    return this._http.get<Book[]>(this._urlGetAllBooks, {headers: headers}).retry(3)
    .do((data:Book[]) => console.log('Number of books fetched: ' + data.length))
    .catch(this.ErrorHandler);
  }

  getBookById(isbn : string) : Observable<Book> {
    let currentUser : any = localStorage.getItem('currentUser');
    let access_token : string =  JSON.parse(currentUser).access_token;
    console.log(JSON.stringify(currentUser));
    console.log("Bearer " + JSON.parse(currentUser).access_token); 
    return this._http.get<Book>(this._urlGetBookById, 
      {headers: new HttpHeaders().set("Authorization", `Bearer ${access_token}`)}).retry(3)
    .do(data => console.log('Book fetched: ' + JSON.stringify(data)))
    .catch(this.ErrorHandler);
  }

  updateBook(book : Book) : Observable<Book> {
    let currentUser : any = localStorage.getItem('currentUser');
    let access_token : string =  JSON.parse(currentUser).access_token; 
    console.log(JSON.stringify(currentUser));
    console.log("Bearer " + JSON.parse(currentUser).access_token);
    console.log(JSON.stringify(book));
    return this._http.put(this._urlUpdateBook + "/" + book.id, book, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${access_token}`)
    }).retry(3)
      .do(data => console.log("Book Issued: " + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  createBook(book : Book) : Observable<Book> {
    let currentUser : any = localStorage.getItem('currentUser');
    let access_token : string =  JSON.parse(currentUser).access_token; 
    console.log(JSON.stringify(currentUser));
    console.log("Bearer " + JSON.parse(currentUser).access_token);
    console.log("Creating new book: " + JSON.stringify(book));
    return this._http.post(this._urlCreateBook, book, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${access_token}`)
    }).retry(3)
      .do(data => console.log("Created New Book: " + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  deleteBook(book : Book) : Observable<Book> {
    let currentUser : any = localStorage.getItem('currentUser');
    let access_token : string =  JSON.parse(currentUser).access_token; 
    console.log(JSON.stringify(currentUser));
    console.log("Bearer " + JSON.parse(currentUser).access_token);
    console.log("Deleting book: " + JSON.stringify(book));
    return this._http.delete(this._urlDeleteBook + "/" + book.id, {
      headers: new HttpHeaders().set("Authorization", `Bearer ${access_token}`)
    }).retry(3)
      .do(data => console.log("Deleted book: " + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  private ErrorHandler(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err);
  }

}

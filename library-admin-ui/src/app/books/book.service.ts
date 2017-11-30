import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Book } from "./book.model";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import { HttpErrorResponse } from "@angular/common/http";
import 'rxjs/add/operator/retry';

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
    return this._http.get<Book[]>(this._urlGetAllBooks).retry(3)
    .do((data:Book[]) => console.log('Number of books fetched: ' + data.length))
    .catch(this.ErrorHandler);
  }

  getBookById(isbn : string) : Observable<Book> {
    return this._http.get<Book>(this._urlGetBookById).retry(3)
    .do(data => console.log('Book fetched: ' + JSON.stringify(data)))
    .catch(this.ErrorHandler);
  }

  updateBook(book : Book) : Observable<Book> {
    console.log(JSON.stringify(book));
    return this._http.put(this._urlUpdateBook + "/" + book.id, book).retry(3)
      .do(data => console.log("Book Issued: " + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  createBook(book : Book) : Observable<Book> {
    console.log("Creating new book: " + JSON.stringify(book));
    return this._http.post(this._urlCreateBook, book).retry(3)
      .do(data => console.log("Created New Book: " + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  deleteBook(book : Book) : Observable<Book> {
    console.log("Deleting book: " + JSON.stringify(book));
    return this._http.delete(this._urlDeleteBook + "/" + book.id).retry(3)
      .do(data => console.log("Deleted book: " + JSON.stringify(data)))
      .catch(this.ErrorHandler);
  }

  private ErrorHandler(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err);
  }

}

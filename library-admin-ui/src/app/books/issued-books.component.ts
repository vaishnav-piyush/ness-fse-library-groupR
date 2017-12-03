import { Component, OnInit } from '@angular/core';
import { BookService } from "./book.service";
import { Book } from "./book.model";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-issued-books',
  templateUrl: './issued-books.component.html',
  styleUrls: ['./issued-books.component.css']
})
export class IssuedBooksComponent implements OnInit {

  pageTitle : string;
  errorMessage : string;
  successMessage : string;
  issuedBooks : Book[];
  currentBook : Book;


  constructor(private _bookService : BookService) { 
    this.pageTitle = "Issued Books Listing";
    this.currentBook = { 
      "id" : '59dca04e1046555723e739b3', 
      "title" : "T3", 
      "author" : "Prasanna", 
      "description" : "D3", 
      "issueStatus" : "AVAILABLE", 
      "issuedTo" : "", 
      "addedDate" : new Date("2017-10-10T10:26:17.897Z"), 
      "updatedDate" : new Date("2017-10-10T10:26:17.897Z") 
    };
  }

  toggleSuccessAlert() {
    this.successMessage = "";
  }

  toggleErrorAlert() {
    this.errorMessage = "";
  }

  setCurrentBook(id : string) {
    this.currentBook = this.issuedBooks.find(book => book.id == id);
  }

  ngOnInit() {
    this._bookService.getAllBooks().subscribe(
      books => {this.issuedBooks = books.filter(book => book.issueStatus == "ISSUED")},
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          this.successMessage = "";
          this.errorMessage = "Something went wrong, unable to complete operation.";
          console.log(`Error: Http Status Code ${err.status}, Error Description: ` + JSON.stringify(err.error));
        } else {
          this.successMessage = "";
          if(err.status == 401) {
            console.log(`Error: Http Status Code ${err.status}, Error Description: ${err.error.error_description}, Response Body: ` + JSON.stringify(err.error));
            this.errorMessage = "You are not authorized to do this operation."
          } else {
            console.log(`Error: Http Status Code ${err.status}, Response Body: ` + JSON.stringify(err.error));
            this.errorMessage = "Coudn't publish update to the server. Please try again later.";
          }         
        }
      }
    );
  }

  onBookRelease() {
    this.currentBook.issueStatus = "AVAILABLE";
    this._bookService.updateBook(this.currentBook).subscribe(
      releasedBook => {
        const index = this.issuedBooks.findIndex(book => book.id == releasedBook.id);
        this.issuedBooks.splice(index, 1);
        console.log(releasedBook.title + " is released.");
        this.errorMessage = "";
        this.successMessage = "'" + releasedBook.title + "' is released";
      }, 
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          this.successMessage = "";
          this.errorMessage = "Something went wrong, unable to complete operation.";
          console.log(`Error: Http Status Code ${err.status}, Error Description: ` + JSON.stringify(err.error));
        } else {
          this.successMessage = "";
          if(err.status == 401) {
            console.log(`Error: Http Status Code ${err.status}, Error Description: ${err.error.error_description}, Response Body: ` + JSON.stringify(err.error));
            this.errorMessage = "You are not authorized to do this operation."
          } else {
            console.log(`Error: Http Status Code ${err.status}, Response Body: ` + JSON.stringify(err.error));
            this.errorMessage = "Coudn't publish update to the server. Please try again later.";
          }         
        }
      }
    );
  }

}

import { Component, OnInit } from '@angular/core';
import { Book } from "./book.model";
import { BookService } from "./book.service";
import { User } from "./user.model";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-available-books',
  templateUrl: './available-books.component.html',
  styleUrls: ['./available-books.component.css']
})
export class AvailableBooksComponent implements OnInit {

  pageTitle: string ;
  availableBooks: Book[];
  errorMessage : string;
  successMessage : string;
  currentBook : Book;
  issueToUser : User;

  
  constructor(private _bookService : BookService) { 
    this.pageTitle = "Available Books Listing";
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

  setCurrentBook(id : string) {
    this.currentBook = this.availableBooks.find(book => book.id == id);
  }

  toggleSuccessAlert() {
    this.successMessage = "";
  }

  toggleErrorAlert() {
    this.errorMessage = "";
  }

  ngOnInit() {
    this._bookService.getAllBooks().subscribe(
      books => {this.availableBooks = books.filter(book => book.issueStatus == "AVAILABLE")},
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

  onBookIssue(user : User) {
    this.issueToUser = user;
    this.currentBook.issueStatus = "ISSUED";
    this.currentBook.issuedTo = user.name;
    this._bookService.updateBook(this.currentBook).subscribe(
      issuedBook => {
        const index = this.availableBooks.findIndex(book => book.id == issuedBook.id);
        this.availableBooks.splice(index, 1);
        console.log("Book is issued to user: " + user.name);
        this.errorMessage = "";
        this.successMessage = "'" + this.currentBook.title + "' is issued to " + issuedBook.issuedTo;
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

  onBookDelete() {
    this._bookService.deleteBook(this.currentBook).subscribe(
      deletedBook => {
        console.log("Book titled " + this.currentBook.title + " is deleted.");
        const index = this.availableBooks.findIndex(book => book.id == deletedBook.id);
        this.availableBooks.splice(index, 1);
        this.errorMessage = ""
        this.successMessage = "'" + this.currentBook.title + "' is deleted.";
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

import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Book } from "./book.model";
import { BookService } from "./book.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-new-book',
  templateUrl: './new-book.component.html',
  styleUrls: ['./new-book.component.css']
})
export class NewBookComponent implements OnInit {
  newBook : Book;
  pageTitle: string;
  submitted: boolean = false;
  errorMessage: string;
  successMessage: string;  
  @ViewChild('successAlert') successAlert : ElementRef; 

  constructor(private _bookService : BookService) { 
    this.pageTitle = "Register Book";
    this.newBook = new Book();
  }

  ngOnInit() {
  }

  onReset() {
    this.successMessage = "";
    this.submitted = false;
  }

  onSubmit() {
    this.newBook.issueStatus = "AVAILABLE";
    this._bookService.createBook(this.newBook).subscribe(
      createdBook => {
        console.log("New Book is created: " + createdBook);
        this.errorMessage = "";
        this.successMessage = "New Book Created Successfully."
        this.submitted = true;
        this.successAlert.nativeElement.style.display='block';
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
            this.errorMessage = "You are not authorized to do this operation.";
          } else {
            console.log(`Error: Http Status Code ${err.status}, Response Body: ` + JSON.stringify(err.error));
            this.errorMessage = "Coudn't publish update to the server. Please try again later.";
          }         
        }
      }
    );
  }

}

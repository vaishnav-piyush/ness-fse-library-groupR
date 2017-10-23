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
      (err : HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          this.successMessage = "";
          this.errorMessage = "Something went wrong, unable to complete operation.";
          this.submitted = true;
        } else {
          console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
          this.successMessage = "";
          this.errorMessage = "Coudn't publish update to the server. Please try again later.";
          this.submitted = true;
        }
      }
    );
  }

}

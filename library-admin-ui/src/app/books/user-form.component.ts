import { User } from "./user.model";
import { Component, Output, EventEmitter, ViewChild, ElementRef } from "@angular/core";


@Component({
    selector: 'user-form',
    templateUrl: './user-form.component.html'
})
export class UserFormComponent {
    user: User;
    @Output() issueToUser : EventEmitter<User> = new EventEmitter<User>();
    submitted : boolean = false;
    @ViewChild('closeBtn') closeButton : ElementRef;    
    
    
    ngOnInit() {
        this.user = {"name": ""};
    }
    onSubmit() {
        this.submitted = true;
        this.issueToUser.emit(this.user);      
        this.closeButton.nativeElement.click();
        this.user.name = "";  
    }

    // TODO: Remove this when we're done
    get diagnostic() {
        return JSON.stringify(this.user);
    }
}
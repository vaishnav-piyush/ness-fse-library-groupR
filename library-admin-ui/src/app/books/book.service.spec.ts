import { TestBed, inject } from '@angular/core/testing';

import { AllBooksService } from './all-books.service';

describe('AllBooksService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AllBooksService]
    });
  });

  it('should be created', inject([AllBooksService], (service: AllBooksService) => {
    expect(service).toBeTruthy();
  }));
});

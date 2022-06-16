/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { KeywordService } from './keyword.service';

describe('Service: Keyword', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KeywordService]
    });
  });

  it('should ...', inject([KeywordService], (service: KeywordService) => {
    expect(service).toBeTruthy();
  }));
});

/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DeckService } from './deck.service';

describe('Service: Deck', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DeckService]
    });
  });

  it('should ...', inject([DeckService], (service: DeckService) => {
    expect(service).toBeTruthy();
  }));
});

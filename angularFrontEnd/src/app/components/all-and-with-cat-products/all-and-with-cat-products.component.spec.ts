import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAndWithCatProductsComponent } from './all-and-with-cat-products.component';

describe('AllAndWithCatProductsComponent', () => {
  let component: AllAndWithCatProductsComponent;
  let fixture: ComponentFixture<AllAndWithCatProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllAndWithCatProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllAndWithCatProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

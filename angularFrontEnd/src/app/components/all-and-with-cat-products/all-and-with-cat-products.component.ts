import { CategoryService } from './../../services/category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-and-with-cat-products',
  templateUrl: './all-and-with-cat-products.component.html',
  styleUrls: ['./all-and-with-cat-products.component.css']
})
export class AllAndWithCatProductsComponent implements OnInit {

  categories: any;

  constructor(public categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategories().subscribe( categories => {
      this.categoryService.categoriesBS.next(categories);
      this.categories = this.categoryService.categoriesBS;
    });
  }
}

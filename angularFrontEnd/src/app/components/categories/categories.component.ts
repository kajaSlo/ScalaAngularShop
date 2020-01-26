import { CategoryService } from './../../services/category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories: any;

  constructor(public categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategories().subscribe( categories => {
      this.categoryService.categoriesBS.next(categories);
      this.categories = this.categoryService.categoriesBS;
    });
  }
}


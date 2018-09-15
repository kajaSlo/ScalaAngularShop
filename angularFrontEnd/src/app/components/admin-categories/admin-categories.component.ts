import { CategoryService } from './../../services/category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.css']
})
export class AdminCategoriesComponent implements OnInit {

  categories: any;

  constructor(public categoryService: CategoryService) { }

  ngOnInit() {
    this.categoryService.getCategories().subscribe( categories => {
 
      this.categoryService.categoriesBS.next(categories);
      this.categories = this.categoryService.categoriesBS;
    });
  }

}

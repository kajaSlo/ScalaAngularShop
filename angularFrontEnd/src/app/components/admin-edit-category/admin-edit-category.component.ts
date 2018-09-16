import { CategoryService } from './../../services/category.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-edit-category',
  templateUrl: './admin-edit-category.component.html',
  styleUrls: ['./admin-edit-category.component.css']
})
export class AdminEditCategoryComponent implements OnInit {

  category: any;
  name: string;
  catId: number;
  param: any;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.param = params['catId'];
      this.categoryService.getEditCategory(this.param).subscribe(category => {
        this.category = category;
        this.name = category.name;
        this.catId = category.catId;
      })
    })
  }

  editCategory({form, value, valid}) {
    if (valid) {    
      this.categoryService.postEditCategory(value).subscribe( res => {
        this.categoryService.getCategories().subscribe(categories => {
          this.categoryService.categoriesBS.next(categories);
          })
      })             
    } else {
        console.log('Form is not valid.');
    }
  }
}

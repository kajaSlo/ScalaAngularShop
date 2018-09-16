import { CategoryService } from './../../services/category.service';
import { Component, OnInit } from '@angular/core';

class CategoryId {
  catId: number;
}

@Component({
  selector: 'app-admin-add-category',
  templateUrl: './admin-add-category.component.html',
  styleUrls: ['./admin-add-category.component.css']
})

 
export class AdminAddCategoryComponent implements OnInit {

  objWithId: CategoryId = new CategoryId();
  public name: string;
  public amountOfCategories = [];

  constructor(public categoryService: CategoryService) { }

  ngOnInit() {

    this.categoryService.getCategories().subscribe(
      res => {
        this.amountOfCategories = res;
      }
    )

    this.objWithId.catId = this.amountOfCategories.length + 1;  
  }


  addCategory({form, value, valid}) {
    form.reset();
    if (valid) {
      console.log(this.name);

      let idAsJson = JSON.parse(JSON.stringify(this.objWithId));  
      let allInformationsAsJson = Object.assign(idAsJson,value);
  
      this.categoryService.postAddCategory(allInformationsAsJson).subscribe( res => {

        this.categoryService.getCategories().subscribe(categories => {
          this.categoryService.categoriesBS.next(categories);
          })
      })          
              
    } else {
        console.log('Form is not valid.');
    }
  }
}

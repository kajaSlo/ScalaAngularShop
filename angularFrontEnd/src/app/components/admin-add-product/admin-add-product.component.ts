import { CategoryService } from './../../services/category.service';
import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

class ProductId {
  prodId: number;
}

@Component({
  selector: 'app-admin-add-product',
  templateUrl: './admin-add-product.component.html',
  styleUrls: ['./admin-add-product.component.css']
})
export class AdminAddProductComponent implements OnInit {

  public successMsg: boolean = false;
  objWithId: ProductId = new ProductId();
  public amountOfProducts = [];
  public title: string;
  public description: string;
  public price: number;
  public catId: number;
  public categories: any;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.productService.getProducts().subscribe( res => {
      this.amountOfProducts = res;
    });
    this.objWithId.prodId = this.amountOfProducts.length + 1;
    this.categoryService.getCategories().subscribe ( res => {
      this.categories = res;
    });
  }

  addProduct({form, value, valid}) {
    form.reset();
    if (valid) {
      let idAsJson = JSON.parse(JSON.stringify(this.objWithId));  
      let allInformationsAsJson = Object.assign(idAsJson,value);
      this.productService.postAddProduct(allInformationsAsJson).subscribe(res => {
            this.productService.getProducts().subscribe(products => {
            this.productService.productsBS.next(products);
        })       
      });
  } else {
      console.log('Form is not valid.');
  }
 }
}


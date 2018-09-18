import { CategoryService } from './../../services/category.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-admin-edit-product',
  templateUrl: './admin-edit-product.component.html',
  styleUrls: ['./admin-edit-product.component.css']
})
export class AdminEditProductComponent implements OnInit {

  product: any;
  param:any;
  prodId: any;
  title: string;
  description: string;
  price: number;
  catId: number;
  categories: any;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {

    this.route.params.subscribe( params => {
      this.param = params['prodId'];
      this.productService.getEditProduct(this.param).subscribe( product => {
        this.product = product;
        this.title = product.title;
        this.prodId = product.prodId;
        this.description = product.description;
        this.price = product.price;
        this.catId = product.catId;
        console.log(product);
        
      })
    })

    this.categoryService.getCategories().subscribe( res => {

      this.categories = res;
    });
  }

  editProduct({form, value, valid}) {
  
    if (valid) {
  
     console.log(value);
      this.productService.postEditProduct(value).subscribe(res => {

            this.productService.getProducts().subscribe(products => {
            this.productService.productsBS.next(products);
        })
          
      });
  } else {
      console.log('Form is not valid.');
  }

  }

}

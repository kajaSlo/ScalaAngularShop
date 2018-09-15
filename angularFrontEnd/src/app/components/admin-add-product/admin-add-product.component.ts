import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-add-product',
  templateUrl: './admin-add-product.component.html',
  styleUrls: ['./admin-add-product.component.css']
})
export class AdminAddProductComponent implements OnInit {

  public successMsg: boolean = false;

  public prodId: number;
  public title: string;
  public description: string;
  public price: number;
  public catId: number;

  constructor(
    private productService: ProductService
  ) { }

  ngOnInit() {
  }

  addProduct({form, value, valid}) {
    console.log(this.prodId);
        console.log(this.title);
        console.log(this.description);

        console.log(this.price);
        console.log(this.catId);
    console.log("------------------");
    form.reset();
    console.log("------------------");
    if (valid) {
      console.log("form is valid");
      this.productService.postAddProduct(value).subscribe(res => {
          //   this.successMsg = true;
          //   setTimeout(function() {
          //   this.successMsg = false;
          // }.bind(this),2000);
          console.log("------------------");

              //updating productBS observable after a product is added
            this.productService.getProducts().subscribe(products => {
            this.productService.productsBS.next(products);
        })

        console.log("------------------");
        console.log("------------------");
        console.log("------------------");
        console.log("------------------");
        console.log("------------------");
        console.log("------------------");
        console.log("------------------");
        console.log("------------------");

        



          
      });
  } else {
      console.log('Form is not valid.');
  }

  }

}

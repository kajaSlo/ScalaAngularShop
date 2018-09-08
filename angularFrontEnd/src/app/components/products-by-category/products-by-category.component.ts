import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products-by-category',
  templateUrl: './products-by-category.component.html',
  styleUrls: ['./products-by-category.component.css']
})
export class ProductsByCategoryComponent implements OnInit {

  products:any;
  param: any;
  name: String;

  constructor(
    private productService: ProductService,
  private route: ActivatedRoute) { }

  ngOnInit() {
 this.route.params.subscribe(params => {
      this.param = params['id'];
      this.productService.getProductsByCategory(this.param).subscribe(products => {

        this.productService.productsBS.next(products);
        this.products = this.productService.productsBS;
        //this.products = products;
       
       // this.catId = product._id;
      // this.name = products.name;


      })

    });

   

  }

}

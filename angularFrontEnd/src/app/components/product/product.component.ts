import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: any;

  constructor(public productService: ProductService) { }

  ngOnInit() {

    this.productService.getProducts().subscribe(products => {

      this.productService.productsBS.next(products);
      this.products = this.productService.productsBS;
    });

  }

}

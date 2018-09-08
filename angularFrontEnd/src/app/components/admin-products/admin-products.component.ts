import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-products',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css']
})
export class AdminProductsComponent implements OnInit {

  products: any;

  constructor(
    private router: Router,
    private productService: ProductService
  ) { }

  ngOnInit() {

    this.productService.getProducts().subscribe(products => {

      this.productService.productsBS.next(products);
      this.products = this.productService.productsBS;
    });


  }

}

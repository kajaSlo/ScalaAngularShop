import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-products',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css']
})
export class AdminProductsComponent implements OnInit {

  products: any;

  constructor(
    private productService: ProductService
  ) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(products => {
      this.productService.productsBS.next(products);
      this.products = this.productService.productsBS;
    });
  }

  deleteProduct(prodId){
    if(confirm('Confirm deletion')){
      this.productService.deleteProduct(prodId).subscribe(res =>{
        this.productService.getProducts().subscribe(products =>{
          this.productService.productsBS.next(products);
        })
      })
    }
  }
}

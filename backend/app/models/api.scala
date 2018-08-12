package models

import play.api.libs.json.Json

case class ProductsREST(title: String, description: String, quantity: Int, price: Float, catId: Int) {}
case class CategoryREST(name: String) {}
case class PaymentREST(name: String) {}
case class ShippingREST(name: String, price: Int) {}
case class CartREST(prodId: Long) {}

object ProductsREST {
  implicit val productsFormat = Json.format[ProductsREST]
}
object CategoryREST {
  implicit val categoryFormat = Json.format[CategoryREST]
}

object PaymentREST {
  implicit val paymentFormat = Json.format[PaymentREST]
}

object ShippingREST {
  implicit val shippingFormat = Json.format[ShippingREST]
}
object CartREST {
  implicit val cartFormat = Json.format[CartREST]
}
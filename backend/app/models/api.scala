package models

import play.api.libs.json.Json

case class ProductsREST(title: String, description: String, quantity: Int, price: Float, catId: Int) {}
case class CategoryREST(catId: Int, name: String) {}
case class PaymentREST(name: String) {}
case class ShippingREST(name: String, price: Int) {}
case class CartREST(prodId: Long) {}
case class OpinionREST(prodId: Long, content: String) {}


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
object OpinionREST {
  implicit val opinionFormat = Json.format[OpinionREST]
}
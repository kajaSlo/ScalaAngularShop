package models

import java.sql.Timestamp

import play.api.libs.json.Format



case class Products(prodId: Long, title: String, description: String, quantity: Int, price: Float, catId: Int) {}
case class Categories(catId: Int, name: String) {}
case class Payments(paymentId: Int, name: String) {}
case class Shipments(shippingId: Int, name: String, price: Int) {}
case class Carts(prodId: Long)
case class Opinions(opinionId: Int, prodId: Long, content: String)



//, catId: Long, createdAt: Timestamp

//case class Categories(catId: Long, tytul: String)

//case class Purchases(purId: Long, prodId: Long, userId: Long)

//case class Users(userId: Long, username: String)
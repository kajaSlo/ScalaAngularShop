package controllers

import javax.inject.Inject
import daos.CartsDAO
import models.{Carts, CartREST}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Execution.Implicits._

class CartController @Inject() (cartsDAO: CartsDAO) extends Controller {


  /////CATEGORIES

  def addToCart = Action { implicit request =>
    var json:CartREST = request.body.asJson.get.as[CartREST]
    var cart = Carts(prodId = json.prodId)
    cartsDAO.insert(cart)
    Ok(request.body.asJson.get)
  }


  def getCartContent(prodId: Long) = Action.async { implicit request =>
    cartsDAO.getCartContent(prodId) map {
      result => Ok(Json.toJson(result))
    }
  }

  def deleteProductFromTheCart(prodId: Long) = Action.async { implicit request =>
    cartsDAO.deleteProduct(prodId) map {
      result => Ok(Json.toJson(result))
    }
  }

  def emptyCart(prodId: Long) = Action.async { implicit request =>
    cartsDAO.emptyCart(prodId) map {
      result => Ok(Json.toJson(result))
    }
  }

}
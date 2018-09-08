package controllers

import javax.inject.Inject
import daos.ProductsDAO
import daos.CategoriesDAO
import models.{Products, ProductsREST, Categories,  CategoryREST}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Execution.Implicits._
class ProductsController @Inject()(productsDAO: ProductsDAO, categoriesDAO: CategoriesDAO) extends Controller {

  def getAllProducts = Action.async { implicit  request =>
    productsDAO.all map {
      products => Ok(Json.toJson(products))
    }
  }

  def deleteProduct(typeId: Long) = Action.async { implicit request =>
    productsDAO.delete(typeId) map {
      result => Ok(Json.toJson(result))
    }
  }

  def getOneProduct(typeId: Long) = Action.async { implicit request =>
    productsDAO.getOneProd(typeId) map {
      productType => Ok(Json.toJson(productType))
    }
  }

  def productsByCategory(catId: Int) = Action.async { implicit  request =>
    productsDAO.getProductsByCategory(catId) map {
      result => Ok(Json.toJson(result))
    }
  }

//  def editProduct(typeId: Long) = Action.async { implicit request =>
//    var json:ProductsREST = request.body.asJson.get.as[ProductsREST]
//    var newProduct = Products(prodId = 0, title = json.title, description = json.description, quantity = json.quantity, price = json.price)
//    productsDAO.editProd(typeId, newProduct) map {
//      result => Ok(Json.toJson(result))
//    }
//  }


//  def editproducttype(prodId: Long) = Action.async { implicit request =>
//    var json: ProductsREST = request.body.asJson.get.as[ProductsREST]
//    var product = Products(prodId = 0, title = json.title, description = json.description, quantity = json.quantity, price = json.price)
//    productsDAO.edit(prodId, product) map {
//      result => Ok(Json.toJson(result))
//    }
//  }

  def editProduct(id: Long) = Action.async { implicit request =>
    println("-----------------------------------------------------");
    println("-----------------------------------------------------");
    println("-----------------------------------------------------");

    var json:ProductsREST = request.body.asJson.get.as[ProductsREST]

    println(json:ProductsREST);
    var product = Products(prodId = 0, title = json.title, description = json.description, quantity = json.quantity, price = json.price, catId = json.catId)
    productsDAO.edit(id, product) map {
      result => Ok(Json.toJson(result))
    }
  }

  def newproduct = Action { implicit request =>
    var json:ProductsREST = request.body.asJson.get.as[ProductsREST]
    var product = Products(prodId = 0, title = json.title, description = json.description, quantity = json.quantity, price = json.price, catId = json.catId)
    productsDAO.insert(product)
    Ok(request.body.asJson.get)
  }

}


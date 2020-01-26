package controllers

import javax.inject.Inject
import daos.CategoriesDAO
import models.{Categories,  CategoryREST}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Execution.Implicits._

class CategoriesController @Inject() (categoriesDAO: CategoriesDAO) extends Controller {

  def newCategory = Action { implicit request =>
    var json:CategoryREST = request.body.asJson.get.as[CategoryREST]
    var category = Categories(catId = 0, name = json.name)
    categoriesDAO.insert(category)
    Ok(request.body.asJson.get)
  }

  def getAllCategories = Action.async { implicit  request =>
    categoriesDAO.all map {
      categories => Ok(Json.toJson(categories))
    }
  }

  def getOneCategory(catId: Int) = Action.async { implicit request =>
    categoriesDAO.getOneCat(catId) map {
      categoryType => Ok(Json.toJson(categoryType))
    }
  }

  def deleteCategory(catId: Int) = Action.async { implicit request =>
    categoriesDAO.delete(catId) map {
      result => Ok(Json.toJson(result))
    }
  }

  def editCategory(id: Int) = Action.async { implicit request =>

    var json:CategoryREST = request.body.asJson.get.as[CategoryREST]

    var cat = Categories(catId = 0, name = json.name)
    categoriesDAO.edit(id, cat) map {
      result => Ok(Json.toJson(result))
    }
  }

}
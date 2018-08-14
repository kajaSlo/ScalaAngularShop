
package controllers

import javax.inject.Inject
import daos.OpinionsDAO
import models.{Opinions, OpinionREST}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Execution.Implicits._
class OpinionsController @Inject() (opinionsDAO: OpinionsDAO) extends Controller {


  def newOpinion = Action { implicit request =>
    var json:OpinionREST = request.body.asJson.get.as[OpinionREST]
    var opinion = Opinions(opinionId = 0, prodId = json.prodId, content = json.content)
    opinionsDAO.addOpinion(opinion)
    Ok(request.body.asJson.get)
  }

  def getAllOpinionsByproduct(prodId: Long) = Action.async { implicit request =>
    opinionsDAO.getAllOpByProduct(prodId) map {
      opinions => Ok(Json.toJson(opinions))
    }
  }

  def getOneOpinion(opinionId: Int) = Action.async { implicit request =>
    opinionsDAO.getOneOpinion(opinionId) map {
      opinion => Ok(Json.toJson(opinion))
    }
  }

  def deleteOpinion(opinionId: Int) = Action.async { implicit request =>
    opinionsDAO.delete(opinionId) map {
      result => Ok(Json.toJson(result))
    }
  }



}
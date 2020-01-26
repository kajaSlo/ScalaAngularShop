package controllers

import javax.inject.Inject
import daos.ShipmentsDAO
import models.{Shipments, ShippingREST}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Execution.Implicits._

class ShipmentsController @Inject() (shipmentsDAO: ShipmentsDAO) extends Controller {

  def newShipment = Action { implicit request =>
    var json:ShippingREST = request.body.asJson.get.as[ShippingREST]
    var shipping = Shipments(shippingId = 0, name = json.name, price = json.price)
    shipmentsDAO.insert(shipping)
    Ok(request.body.asJson.get)
  }

  def getAllShipments = Action.async { implicit  request =>
    shipmentsDAO.all map {
      shipments => Ok(Json.toJson(shipments))
    }
  }

  def getOneShipment(shippingId: Int) = Action.async { implicit request =>
    shipmentsDAO.getOneShipment(shippingId) map {
      shippingType => Ok(Json.toJson(shippingType))
    }
  }

  def deleteShipment(shippingId: Int) = Action.async { implicit request =>
    shipmentsDAO.delete(shippingId) map {
      result => Ok(Json.toJson(result))
    }
  }

  def editShipment(id: Int) = Action.async { implicit request =>

    var json:ShippingREST = request.body.asJson.get.as[ShippingREST]
    var shipping = Shipments(shippingId = 0, name = json.name, price = json.price)
    shipmentsDAO.edit(id, shipping) map {
      result => Ok(Json.toJson(result))
    }
  }

}

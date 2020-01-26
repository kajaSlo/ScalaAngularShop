package controllers

import javax.inject.Inject
import daos.PaymentDAO
import models.{PaymentREST, Payments}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Execution.Implicits._

class PaymentsControllers @Inject() (paymentDAO: PaymentDAO) extends Controller {

  def newPayment = Action { implicit request =>
    var json:PaymentREST = request.body.asJson.get.as[PaymentREST]
    var payment = Payments(paymentId = 0, name = json.name)
    paymentDAO.insert(payment)
    Ok(request.body.asJson.get)
  }

  def getAllPayments = Action.async { implicit  request =>
    paymentDAO.all map {
      payments => Ok(Json.toJson(payments))
    }
  }

  def getOnePayment(paymentId: Int) = Action.async { implicit request =>
    paymentDAO.getOnePayment(paymentId) map {
      paymentType => Ok(Json.toJson(paymentType))
    }
  }

  def deletePayment(paymentId: Int) = Action.async { implicit request =>
    paymentDAO.delete(paymentId) map {
      result => Ok(Json.toJson(result))
    }
  }

  def editPayment(id: Int) = Action.async { implicit request =>

    var json:PaymentREST = request.body.asJson.get.as[PaymentREST]

    var payment = Payments(paymentId = 0, name = json.name)
    paymentDAO.edit(id, payment) map {
      result => Ok(Json.toJson(result))
    }
  }

}

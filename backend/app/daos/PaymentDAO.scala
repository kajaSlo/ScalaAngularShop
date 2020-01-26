package daos

import javax.inject.Inject
import models.{Payments, PaymentREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class PaymentDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import driver.api._

  val Payments = TableQuery[PaymentsTable]

  def all(implicit ec: ExecutionContext): Future[List[PaymentREST]] = {
    val query = Payments
    val results = query.result
    val allResults = db.run(results)
    allResults.map(
      _.map {
        a => PaymentREST(name = a.name)
      }.toList
    )
  }

  def getOnePayment(paymentId: Int): Future[Option[PaymentREST]] = {
    val product = db.run(Payments.filter(_.paymentId === paymentId).result.headOption)
    product.map(
      _.map {
        a => PaymentREST(name = a.name)
      }
    )
  }

  def insert(payment: Payments): Future[Unit] = db.run(Payments += payment).map { _ => () }

  def delete(paymentId: Int): Future[Int] = db.run(Payments.filter(_.paymentId === paymentId).delete)

  def edit(paymentId: Int, payment: Payments): Future[Int] = {
    val toUpdate: Payments = payment.copy(paymentId = paymentId)
    db.run(Payments.filter(_.paymentId === paymentId).update(toUpdate))
  }

  class PaymentsTable(tag: Tag) extends Table[Payments](tag, "Payment") {
    def  paymentId:Rep[Int] = column[Int]("paymentId", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (paymentId, name) <> (models.Payments.tupled, models.Payments.unapply)
  }

}

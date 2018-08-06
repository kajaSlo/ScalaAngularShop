package daos


import javax.inject.Inject
import models.{Shipments, ShippingREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class ShipmentsDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import driver.api._

  val Shipments = TableQuery[ShipmentsTable]

  def all(implicit ec: ExecutionContext): Future[List[ShippingREST]] = {
    val query = Shipments
    val results = query.result
    val futureShipments = db.run(results)
    futureShipments.map(
      _.map {
        a => ShippingREST(name = a.name, price = a.price)
      }.toList
    )
  }


  def getOneShipment(shippingId: Int): Future[Option[ShippingREST]] = {
    val shipping = db.run(Shipments.filter(_.shippingId === shippingId).result.headOption)

    shipping.map(
      _.map {
        a => ShippingREST(name = a.name, price = a.price)
      }
    )
  }

  def insert(shipping: Shipments): Future[Unit] = db.run(Shipments += shipping).map { _ => () }

  def delete(shippingId: Int): Future[Int] = db.run(Shipments.filter(_.shippingId === shippingId).delete)

  def edit(shippingId: Int, shipping: Shipments): Future[Int] = {
    val toUpdate: Shipments = shipping.copy(shippingId = shippingId)

    db.run(Shipments.filter(_.shippingId === shippingId).update(toUpdate))
  }


  class ShipmentsTable(tag: Tag) extends Table[Shipments](tag, "Shipping") {
    def shippingId:Rep[Int] = column[Int]("shippingId", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def price:Rep[Int] = column[Int]("price")

    def * = (shippingId, name, price) <> (models.Shipments.tupled, models.Shipments.unapply)
  }
}

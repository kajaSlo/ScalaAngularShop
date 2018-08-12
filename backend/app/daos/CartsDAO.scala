package daos

import javax.inject.Inject
import models.{CartREST, Carts}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.Json
import play.api.mvc.Action
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class CartsDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import driver.api._

  val Cart = TableQuery[CartsTable]
  val Products = new ProductsDAO(dbConfigProvider).Products

  def insert(cart: Carts): Future[Unit] = db.run(Cart += cart).map { _ => () }

  //ZAMIENIC NA USERID zamiast PRODUCTID!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  def getCartContent(prodId: Long): Future[List[CartREST]] = {
    val futureCart = db.run(Cart.filter(_.prodId === prodId).result)

    futureCart.map(
      _.map {
        a => CartREST(prodId = a.prodId)
      }.toList
    )
  }

  def deleteProduct(prodId: Long): Future[Int] = db.run(Cart.filter(_.prodId === prodId).delete)
  def emptyCart(prodId: Long): Future[Int] = db.run(Cart.filter(_.prodId === prodId).delete)


  class CartsTable(tag: Tag) extends Table[Carts](tag, "Cart") {
    def prodId = column[Long]("prodId", O.PrimaryKey)
    def prod_fk = foreignKey("prod_fk", prodId, Products)(_.prodId, ForeignKeyAction.Restrict, ForeignKeyAction.Cascade)

    def * = (prodId) <> (models.Carts.apply _, models.Carts.unapply)
  }
}
package daos

import javax.inject.Inject

import models.{Products, ProductsREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


class ProductsDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Products = TableQuery[ProductsTable]
  val Categories = new CategoriesDAO(dbConfigProvider).Categories

  def all(implicit ec: ExecutionContext): Future[List[ProductsREST]] = {
    val query =  Products
    val results = query.result
    val futureProducts = db.run(results)
    futureProducts.map(
      _.map {
        a => ProductsREST(description = a.description, title = a.title, quantity = a.quantity, price = a.price, catId = a.catId)
      }.toList)
  }

  def insert(product: Products): Future[Unit] = db.run(Products += product).map { _ => () }

  def getOneProd(prodId: Long): Future[Option[ProductsREST]] = {
    val product = db.run(Products.filter(_.prodId === prodId).result.headOption)

    product.map(
      _.map {
        a => ProductsREST(description = a.description, title = a.title, quantity = a.quantity, price = a.price, catId = a.catId)
      }
    )
  }

  def delete(prodId: Long): Future[Int] = db.run(Products.filter(_.prodId === prodId).delete)

//  def edit(prodId: Long, product: Products): Future[Int] = {
//    val productToBeUpdated = product.copy(prodId = prodId)
//    db.run(Products.insertOrUpdate(productToBeUpdated))
//  }

  def edit(prodId: Long, product: Products): Future[Int] = {
    val toUpdate: Products = product.copy(prodId = prodId)
   // val toUpdate = product.copy(prodId = id)
    //db.run(Products.update(toUpdate))
    db.run(Products.filter(_.prodId === prodId).update(toUpdate))
  }

//  def edit(prodId: Long, product: Products): Future[Int] = {
//    val action = Products.filter(_.prodId === product.prodId).map(productBefore =>
//      (productBefore.title, productBefore.description, productBefore.quantity, productBefore.price)
//    ).update(
//      (product.title, product.description, product.quantity, product.price)
//    )
//    db.run(action)
//    //db.run(Products.insertOrUpdate(toUpdate))
//  }

  class ProductsTable(tag: Tag) extends Table[Products](tag, "Products") {
    def prodId = column[Long]("prodId",O.PrimaryKey, O.AutoInc)
    def catId = column[Int]("catId")
    def category_fk = foreignKey("category_fk", catId, Categories)(_.catId, ForeignKeyAction.Restrict, ForeignKeyAction.Cascade)

    def title = column[String]("title")
    def description = column[String]("description")
    def quantity:Rep[Int] = column[Int]("quantity")
    def price:Rep[Float] = column[Float]("price")

    def * = (prodId, title, description, quantity, price, catId) <> (models.Products.tupled, models.Products.unapply)
  }

}

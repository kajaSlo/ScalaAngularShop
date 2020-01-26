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
    val allProducts = db.run(results)
    allProducts.map(
      _.map {
        a => ProductsREST(prodId = a.prodId,description = a.description, title = a.title, price = a.price, catId = a.catId)
      }.toList)
  }

  def insert(product: Products): Future[Unit] = db.run(Products += product).map { _ => () }

  def getOneProd(prodId: Long): Future[Option[ProductsREST]] = {
    val product = db.run(Products.filter(_.prodId === prodId).result.headOption)
    product.map(
      _.map {
        a => ProductsREST(prodId = a.prodId,description = a.description, title = a.title, price = a.price, catId = a.catId)
      }
    )
  }

  def getProductsByCategory(catId: Int): Future[List[ProductsREST]] = {
    val futureProducts = db.run(Products.filter(_.catId === catId).result)
    futureProducts.map(
      _.map {
        a => ProductsREST(prodId = a.prodId,description = a.description, title = a.title, price = a.price, catId = a.catId)
      }.toList)
  }

  def delete(prodId: Long): Future[Int] = db.run(Products.filter(_.prodId === prodId).delete)

  def edit(prodId: Long, product: Products): Future[Int] = {
    val toUpdate: Products = product.copy(prodId = prodId)
    db.run(Products.filter(_.prodId === prodId).update(toUpdate))
  }

  class ProductsTable(tag: Tag) extends Table[Products](tag, "Products") {
    def prodId = column[Long]("prodId",O.PrimaryKey, O.AutoInc)
    def catId = column[Int]("catId")
    def category_fk = foreignKey("category_fk", catId, Categories)(_.catId, ForeignKeyAction.Restrict, ForeignKeyAction.Cascade)
    def title = column[String]("title")
    def description = column[String]("description")
    def price:Rep[Float] = column[Float]("price")

    def * = (prodId, title, description,price, catId) <> (models.Products.tupled, models.Products.unapply)
  }

}

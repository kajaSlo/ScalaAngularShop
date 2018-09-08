package daos


import javax.inject.Inject
import models.{Categories, CategoryREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class CategoriesDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import driver.api._

  val Categories = TableQuery[CategoriesTable]

  def all(implicit ec: ExecutionContext): Future[List[CategoryREST]] = {
    val query = Categories
    val results = query.result
    val futureCategories = db.run(results)
    futureCategories.map(
      _.map {
        a => CategoryREST(catId = a.catId, name = a.name)
      }.toList
    )
  }


  def getOneCat(catId: Int): Future[Option[CategoryREST]] = {
    val category = db.run(Categories.filter(_.catId === catId).result.headOption)

    category.map(
      _.map {
        a => CategoryREST(catId = a.catId ,name = a.name)
      }
    )
  }

  def insert(category: Categories): Future[Unit] = db.run(Categories += category).map { _ => () }

  def delete(catId: Int): Future[Int] = db.run(Categories.filter(_.catId === catId).delete)

  def edit(catId: Int, category: Categories): Future[Int] = {
    val toUpdate: Categories = category.copy(catId = catId)
    // val toUpdate = product.copy(prodId = id)
    //db.run(Products.update(toUpdate))
    db.run(Categories.filter(_.catId === catId).update(toUpdate))
  }


  class CategoriesTable(tag: Tag) extends Table[Categories](tag, "Category") {
    def  catId:Rep[Int] = column[Int]("catId", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (catId, name) <> (models.Categories.tupled, models.Categories.unapply)
  }
}

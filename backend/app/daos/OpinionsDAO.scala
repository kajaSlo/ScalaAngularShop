package daos

import javax.inject.Inject
import models.{Opinions, OpinionREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class OpinionsDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Opinions = TableQuery[OpinionsTable]
  val Products = new ProductsDAO(dbConfigProvider).Products

  def getAllOpByProduct(prodId: Long): Future[List[OpinionREST]] = {
    val opinionsByProduct = db.run(Opinions.filter(_.prodId === prodId).result)

    opinionsByProduct.map(
      _.map {
          a => OpinionREST(prodId = a.prodId, content = a.content)
      }.toList
    )
  }

  def addOpinion(opinion: Opinions): Future[Unit] = db.run(Opinions += opinion).map { _ => () }

  def getOneOpinion(opinionId: Int): Future[Option[OpinionREST]] = {
    val opinion = db.run(Opinions.filter(_.opinionId === opinionId).result.headOption)
    opinion.map(
      _.map {
        a => OpinionREST(prodId = a.prodId, content = a.content)
      }
    )
  }

  def delete(opinionId: Int): Future[Int] = db.run(Opinions.filter(_.opinionId === opinionId).delete)

  class OpinionsTable(tag: Tag) extends Table[Opinions](tag, "Opinion") {

    def opinionId = column[Int]("opinionId",O.PrimaryKey, O.AutoInc)
    def prodId = column[Long]("prodId")
    def content = column[String]("content")
    def product_fk = foreignKey("product_fk", prodId, Products)(_.prodId, ForeignKeyAction.Restrict, ForeignKeyAction.Cascade)

    def * = (opinionId, prodId, content) <> (models.Opinions.tupled, models.Opinions.unapply)
  }

}
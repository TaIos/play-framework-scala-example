package dao

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class FeedbackFormDAO @Inject() (
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import models.Tables._
  import profile.api._

  def all: Future[Seq[FeedbackFormRow]] = db.run(FeedbackForm.result)

  def insert(feedbackFormRow: FeedbackFormRow): Future[FeedbackFormRow] =
    db.run(
      FeedbackForm
        .returning(FeedbackForm.map(_.id))
        .into((form, id) => form.copy(id = id))
        += feedbackFormRow
    )

  def existsWithEmail(email: String): Future[Boolean] =
    db.run(FeedbackForm.filter(_.email === email).exists.result)

}

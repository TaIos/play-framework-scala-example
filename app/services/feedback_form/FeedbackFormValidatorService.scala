package services.feedback_form

import dao.FeedbackFormDAO
import exceptions.AlreadyExistsException
import models.dto.CreateFeedbackFormDto
import traits.Validator

import javax.inject.{Inject, Singleton}
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

@Singleton
final class FeedbackFormValidatorService @Inject() (
    feedbackFormDAO: FeedbackFormDAO
) extends Validator[CreateFeedbackFormDto] {

  @throws(classOf[AlreadyExistsException])
  override def validateOrThrow(dto: CreateFeedbackFormDto): Unit = {
    val existsFuture = feedbackFormDAO.existsWithEmail(dto.email)
    Await.ready(existsFuture, Duration.Inf)
    existsFuture.value match {
      case Some(x: Try[Boolean]) if !x.isFailure && x.get =>
        throw new AlreadyExistsException(
          s"Feedback form with email [${dto.email}] already exists"
        )
      case _ => ()
    }
  }
}

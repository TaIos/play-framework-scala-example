package services.feedback_form

import dao.FeedbackFormDAO
import exceptions.AlreadyExistsException
import factory.FeedbackFormFactory
import models.Tables.FeedbackFormRow
import models.dto.CreateFeedbackFormDto

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class FeedbackFormService @Inject() (
    feedbackFormFactory: FeedbackFormFactory,
    feedbackFormValidatorService: FeedbackFormValidatorService,
    feedbackFormDAO: FeedbackFormDAO
)(implicit executionContext: ExecutionContext) {

  def getAll: Future[Seq[FeedbackFormRow]] = feedbackFormDAO.all

  @throws(classOf[AlreadyExistsException])
  def create(dto: CreateFeedbackFormDto): Future[FeedbackFormRow] = {
    feedbackFormValidatorService.validateOrThrow(dto)
    feedbackFormDAO.insert(feedbackFormFactory.create(dto))
  }

}

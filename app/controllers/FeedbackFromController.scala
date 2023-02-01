package controllers

import forms.Forms
import play.api.libs.json.Json._
import play.api.mvc._
import services.feedback_form.FeedbackFormService
import utils.JsonSerializers._

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class FeedbackFromController @Inject() (
    val controllerComponents: ControllerComponents,
    val feedbackFormService: FeedbackFormService
)(implicit ec: ExecutionContext)
    extends BaseController
    with play.api.i18n.I18nSupport {

  def renderFormsPage(): Action[AnyContent] = Action.async { implicit request =>
    feedbackFormService.getAll.map(seq =>
      Ok(views.html.feedback_form_page(seq))
    )
  }

  def renderFormCreate(): Action[AnyContent] = Action.async {
    implicit request =>
      Forms.FeedbackForm.form
        .bindFromRequest()
        .fold(
          formWithErrors =>
            Future.successful(BadRequest(toJson(formWithErrors.errors))),
          dto => {
            feedbackFormService
              .create(dto)
              .map(_ =>
                Redirect(routes.FeedbackFromController.renderFormsPage())
              )
          }
        )
  }

  def getAll(): Action[AnyContent] = Action.async {
    feedbackFormService.getAll.map(forms => Ok(toJson(forms)))
  }

  def create(): Action[AnyContent] = Action.async { implicit request =>
    Forms.FeedbackForm.form
      .bindFromRequest()
      .fold(
        formWithErrors =>
          Future.successful(BadRequest(toJson(formWithErrors.errors))),
        dto =>
          feedbackFormService.create(dto).map(form => Created(toJson(form)))
      )
  }
}

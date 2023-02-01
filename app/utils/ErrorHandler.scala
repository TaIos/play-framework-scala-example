package utils

import exceptions.BaseException
import play.api._
import play.api.http.JsonHttpErrorHandler
import play.api.libs.json.Json._
import play.api.mvc.Results.BadRequest
import play.api.mvc.{RequestHeader, Result}

import javax.inject._
import scala.concurrent.Future

@Singleton
class ErrorHandler @Inject() (
    env: Environment,
    sourceMapper: OptionalSourceMapper
) extends JsonHttpErrorHandler(env, sourceMapper) {

  override def onServerError(
      request: RequestHeader,
      exception: Throwable
  ): Future[Result] = exception match {
    case baseException: BaseException =>
      Future.successful(BadRequest(toJson(Map("error" -> baseException.msg))))
    case _ => super.onServerError(request, exception)
  }
}

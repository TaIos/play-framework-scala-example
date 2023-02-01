package controllers

import play.api.libs.json.Json.toJson
import play.api.mvc._

import javax.inject._

/** This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() (val controllerComponents: ControllerComponents)
    extends BaseController {

  def check() = Action { implicit request: Request[AnyContent] =>
    Ok(toJson(Map("state" -> "Up and running")))
  }
}

package utils

import models.Tables.FeedbackFormRow
import play.api.data.FormError
import play.api.libs.json.{JsValue, Json, Writes}

object JsonSerializers {

  implicit val implicitFeedBackFormRowWrites: Writes[FeedbackFormRow] =
    new Writes[FeedbackFormRow] {
      def writes(row: FeedbackFormRow): JsValue = {
        Json.obj(
          "id" -> row.id,
          "firstName" -> row.firstName,
          "lastName" -> row.lastName,
          "email" -> row.email,
          "phone" -> row.phone,
          "createdAt" -> row.createdAt.toLocalDateTime.format(
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
          ),
          "text" -> row.text
        )
      }
    }

  implicit val formErrorWrites: Writes[FormError] =
    new Writes[FormError] {
      def writes(e: FormError): JsValue = {
        Json.obj(e.key -> e.message)
      }
    }

}

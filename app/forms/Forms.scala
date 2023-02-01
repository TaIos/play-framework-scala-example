package forms

import models.dto.CreateFeedbackFormDto
import play.api.data.Form
import play.api.data.Forms.{email, mapping, text}

object Forms {

  object FeedbackForm {
    val form: Form[CreateFeedbackFormDto] = Form(
      mapping(
        "firstName" -> text(minLength = 1, maxLength = 30),
        "lastName" -> text(minLength = 1, maxLength = 30),
        "email" -> email.verifying(s => s.length < 30),
        "phone" -> text.verifying(
          "Not a valid phone number or + prefix is missing",
          s => validatePhoneNumber(s)
        ),
        "text" -> text(minLength = 10, maxLength = 500)
      )(CreateFeedbackFormDto.apply)(CreateFeedbackFormDto.unapply)
    )

    private def validatePhoneNumber(s: String): Boolean = {
      val trimmed = s.filterNot(_.isWhitespace)
      trimmed.length >= 9 && trimmed.length <= 20 && trimmed.startsWith(
        "+"
      ) && trimmed.substring(1).forall(Character.isDigit)

    }
  }

}

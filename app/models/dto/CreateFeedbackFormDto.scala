package models.dto

import traits.Dto

case class CreateFeedbackFormDto(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    text: String
) extends Dto

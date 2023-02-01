package factory

import models.Tables.FeedbackFormRow
import models.dto.CreateFeedbackFormDto
import traits.Factory

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Singleton

@Singleton
class FeedbackFormFactory
    extends Factory[CreateFeedbackFormDto, FeedbackFormRow] {
  override def create(dto: CreateFeedbackFormDto): FeedbackFormRow =
    FeedbackFormRow(
      id = 0,
      firstName = dto.firstName,
      lastName = dto.lastName,
      email = dto.email,
      phone = dto.phone,
      createdAt = Timestamp.valueOf(LocalDateTime.now()),
      text = dto.text
    )

}

package traits

import javax.inject.Singleton

@Singleton
trait Validator[T <: Dto] {
  def validateOrThrow(dto: T): Unit
}

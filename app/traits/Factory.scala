package traits

import javax.inject._

@Singleton
trait Factory[In, Out] {
  def create(in: In): Out
}

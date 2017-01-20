package nlp

/**
  * Created by krbalmryde on 12/10/16.
  *
  */

sealed trait Sentiment

object Emotion {
    case object UNKNOWN extends Sentiment
    case object VERY_NEGATIVE extends Sentiment
    case object NEGATIVE extends Sentiment
    case object NEUTRAL extends Sentiment
    case object POSITIVE extends Sentiment
    case object VERY_POSITIVE extends Sentiment
}

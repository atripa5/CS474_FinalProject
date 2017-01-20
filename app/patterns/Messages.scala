package patterns

import nlp.Sentiment
import javax.swing.{DefaultListModel, JList}

import play.api.libs.json.Json

import scala.collection.JavaConversions._

/**
  * Created by krbalmryde on 12/2/16.
  */
object Messages {
    implicit val tweetFormat = Json.format[Tweet]
    implicit val sentiScoreFormat = Json.format[SentiScore]
    implicit val emoTweetFormat = Json.format[EmoTweet]

    case class Locations(temp: DefaultListModel[String])
    case object Empty
    case object ShutDown

    case class Trends(woeid:String) // The woeid could be a number OR it could be a String
    case class Query(query:String)

    case class Tweet(author:String, timeStamp:Long, body:String)
    case class SentiScore(emotion:String, score:Double)
    case class EmoTweet(sentiment:SentiScore, tweet:Tweet)
}

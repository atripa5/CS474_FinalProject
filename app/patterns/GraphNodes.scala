package patterns

import akka.NotUsed
import akka.stream.scaladsl.Flow
import nlp.SentimentAnalyzer
import patterns.Messages.{EmoTweet, SentiScore, Tweet}
import play.api.libs.json.{JsValue, Json}

/**
  * Created by krbalmryde on 12/11/16.
  *
  * There are our blueprints or Graph nodes. They define the steps we operate on in order to process a stream
  */
object GraphNodes {
    // Flow: performs sentiment analysis on tweet, converts Tweet to a EmoTweet, passes it along
    val flowSentiment: Flow[Tweet, EmoTweet, NotUsed] = Flow[Tweet].map[EmoTweet](tweet => {
        val (emotion, score) = SentimentAnalyzer(tweet.body)
        EmoTweet( SentiScore(emotion.toString, score), tweet)
    })

    val flowJson: Flow[EmoTweet, JsValue, NotUsed] = Flow[EmoTweet].map[JsValue](etweet => {
        Json.toJson(etweet)
    })

}
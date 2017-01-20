package nlp

import Emotion._

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

import scala.collection.JavaConversions._
import scala.io.Source


/**
  * Created by krbalmryde on 12/4/16.
  *
  * Take a look at this one:
  *     https://github.com/vspiewak/twitter-sentiment-analysis/blob/master/src/main/scala/com/github/vspiewak/util/SentimentAnalysisUtils.scala
  *
  * Will need to retrain the sentiment analysis engine. Check this dataset here:
  *     http://www.sananalytics.com/lab/twitter-sentiment
  *
  * See reference to this StackOverflow question:
  *     http://stackoverflow.com/questions/25729204/bias-towards-negative-sentiments-from-stanford-corenlp
  *     http://stackoverflow.com/questions/22586658/how-to-train-the-stanford-nlp-sentiment-analysis-tool?rq=1
  */
object SentimentAnalyzer {

    // I *THINK* I can create a single instance...
    val pipeline: StanfordCoreNLP = {
        val props = new Properties()
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
        new StanfordCoreNLP(props)
    }

    def apply(tweet:String):(Sentiment, Double) = {
        val annotations = pipeline.process(tweet)
        val sentences = annotations.get(classOf[CoreAnnotations.SentencesAnnotation]).toVector
        val sentiment = sentences.map( sent=> sent.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree]) )
                                 .map( tree => RNNCoreAnnotations.getPredictedClass(tree).toDouble )
        val sizes = sentences.map(_.toString.length)

        // max Sentiment score
        val maxSentiment:Double = sentiment.max

        // weighted Sentiment score
        val weightedSentiment:Double  = (sentiment, sizes).zipped.map((sent, sz) => sent * sz).sum / sizes.sum

        // average Sentiment score
        val averageSentiment:Double = {
            if (sentiment.nonEmpty) sentiment.sum / sentiment.size
            else -1
        }

        // Return the result as a tuple
        (classify(weightedSentiment),weightedSentiment)

    }

    def classify(score:Double): Sentiment = {
        score match {
            case s if s < -5.0 => UNKNOWN
            case s if s < -3.0 => VERY_NEGATIVE
            case s if s < -1.0 => NEGATIVE
            case s if s >= -1.0 && s <= 1.0 => NEUTRAL
            case s if s > 1.0 => POSITIVE
            case s if s > 3.0 => VERY_POSITIVE
            case s if s > 5.0 => UNKNOWN
        }
    }

}
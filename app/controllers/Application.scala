package controllers

// Java and Scala stuff
import java.net.URI
import javax.inject._

import scala.concurrent.ExecutionContext

// Akka stream lib
import akka.stream._
import akka.stream.scaladsl.Source
import akka.actor.{ActorRef, ActorSystem}

// Play api
import play.api.mvc._
import play.api.libs.EventSource

// My library
import actors._
import patterns.Messages._
import patterns.GraphNodes
import nlp.SentimentAnalyzer
import tweeter.TwitterStreamClient


/**
  * This is the entry point to the application.
  * @param system
  * @param mat
  * @param ec
  */
@Singleton
class Application @Inject() (implicit system: ActorSystem, mat: Materializer, ec: ExecutionContext) extends Controller {

    val twitterStream = new TwitterStreamClient(system)
    SentimentAnalyzer("init") // do this once to initialize the annotator
    openBrowser(new URI("http://localhost:9000/"))  // It works by george it works!


    def index:Action[AnyContent] = Action {
        Ok(views.html.index(""))
    }

    // This is a http/route method.
    def timeline(query:String): Action[AnyContent] = Action {
        println("our Query is "+ query)
        twitterStream.listenAndStream(query)

        // Secretly we are creating an Actor here, his name is jeff. We like jeff because he gives us the twitter stream
        val source: Source[Tweet, ActorRef] = Source.actorPublisher[Tweet](TweetActor.props)

        Ok.chunked(source via GraphNodes.flowSentiment via GraphNodes.flowJson via EventSource.flow)
    }


}




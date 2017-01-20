package tweeter
import akka.NotUsed
import patterns.Messages._
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Keep, Sink, Source}
import twitter4j.conf.{Configuration, ConfigurationBuilder}
import twitter4j._

/**
  * Created by krbalmryde on 12/2/16.
  *
  * Tweeter object used primarily for convenience in generating the various
  * Twitter4j factory objects. Saves me from having to define and declare the
  * OAuth boilerplate
  *
  * Generates a Twitter or TwitterStream object
  */

object TweeterConfig {
    /**
      * Relevant twitter authetification information.
      * Please dont steal it.
      */
    val consumerKey = "5B73c5z6nLwGtWWTIqdieTAPC"
    val consumerSecret = "LMyBlcTmSdz7iTbcug3A7R5KVQxaCK7PeO2HrkQbFUCriz6Amp"
    val accessToken = "326827611-Ds6Kas4XDgN40FjJvMfHtdIV7tlRrsVPYdzRtfzW"
    val accessTokenSecret = "El9NWfPj2OknPSZRZALMnmhqwDKpemkyfyzf9WSeUgO3Q"

    // Returns a configuration object. Should be private
    def build:Configuration = {
        new ConfigurationBuilder()
            .setOAuthConsumerKey(consumerKey)
            .setOAuthConsumerSecret(consumerSecret)
            .setOAuthAccessToken(accessToken)
            .setOAuthAccessTokenSecret(accessTokenSecret)
        .build()
    }
}


class TwitterStreamClient(system: ActorSystem) {

    val twitterStream:TwitterStream = new TwitterStreamFactory(TweeterConfig.build).getInstance

    def listenAndStream(query:String): Unit = {
        stop() // call everytime we make a new query
        twitterStream.addListener(new TweeterStatusListener(system))

        if (query.nonEmpty)
            twitterStream.filter( new FilterQuery().track(query) )
        else
            twitterStream.sample("en")
    }

    def stop(): Unit = {
        twitterStream.cleanUp()
        twitterStream.shutdown()
    }

}

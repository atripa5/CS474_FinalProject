package tweeter

import twitter4j._
import akka.actor.{ActorRef, ActorSystem}
import patterns.Messages._
import org.reactivestreams.Publisher

/**
  * Created by krbalmryde on 12/3/16.
  */
class TweeterStatusListener(system: ActorSystem) extends StatusListener {

    def onStatus(status: Status): Unit = {
        val user = status.getUser.getName
        val timeStamp = status.getCreatedAt.getTime
        val tweet = status.getText

        system.eventStream.publish(Tweet(user, timeStamp, tweet))
    }

    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = println(s"Got a status deletion notice id: $statusDeletionNotice")

    def onStallWarning(warning: StallWarning): Unit = println(s"Got stall warning: $warning")

    def onScrubGeo(userId: Long, upToStatusId: Long): Unit = println(s"Got scrub_geo event userID: $userId upToStatusId: $upToStatusId")

    def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = println(s"Got track limitation notice: $numberOfLimitedStatuses")

    def onException(ex: Exception): Unit = ex.printStackTrace()

    def geoLocationToString(geoLocation: Option[GeoLocation]): String = geoLocation match {
            case Some(x) => s"${x.getLatitude} | ${x.getLongitude}"
            case _ => "NIL"
    }
}

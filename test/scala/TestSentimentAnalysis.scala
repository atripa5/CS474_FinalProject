
import org.scalatest._
import com.finalproject.nlp.SentimentAnalyzer

/**
  * Created by krbalmryde on 12/10/16.
  *
  * See here for details about testing more fully:
  *     https://github.com/shekhargulati/52-technologies-in-2016/blob/master/03-stanford-corenlp/README.md
  */
class TestSentimentAnalysis extends FunSuite with Matchers {

    test("\"What a wonderful night it is!\" should be positive") {
        SentimentAnalyzer("What a wonderful night it is!")
        SentimentAnalyzer("What a wonderful night it is!")
        SentimentAnalyzer("PokemonGo new update. Wow!! Just loving it.")
        SentimentAnalyzer("What a waste of life of opportunity. He could have made it a lot better.")
        SentimentAnalyzer("this is the worst place I have ever been to")
        SentimentAnalyzer("What a wonderful night it is!")
        SentimentAnalyzer("I'm watching a movie")
        SentimentAnalyzer("It was a nice experience.")
        SentimentAnalyzer("It was a very nice experience.")
        SentimentAnalyzer("I am feeling very sad and frustrated.")
        SentimentAnalyzer("The oil pipeline is being rerouted! Don't ever let them tell you your voice/protest doesn't matter  #NoDAPL #DAPL")
        SentimentAnalyzer("So much respect and honor to/for you and your victory #standingrock")
        SentimentAnalyzer("Absolutely heartbreaking. David Cline, you were so effervescent")
        SentimentAnalyzer("Proof that we the people can defeat tyranny through peaceful resistance. This isn't just a victory-this is everything")
        SentimentAnalyzer("While this fight may not be over, #NoDAPL has shown the power of the People, and the power of love.")
        SentimentAnalyzer("<3 <3 <3 Meanwhile, frustrations are piling up here.")
    }
}

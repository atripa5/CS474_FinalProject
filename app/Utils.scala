import java.awt.Desktop
import java.io.File
import java.net.URI

/**
  * Created by krbalmryde on 12/1/16.
  */
package object controllers {

    // This is the United States YAHOO! Where On Earth Id
    // Used for obtaining trending tweets or hashtags
    val USWOEID = 23424977

    val instructions =
        s"""
           |${Console.YELLOW}
           |** Its Twitter Time! **
           |${Console.WHITE}
           | At the prompt '>: ' enter a space separated list of words and/or hashtags from which to query
           |
           | Not sure what to search for? Type 'trends 23424977' to see the top trending terms in the USA
           |
           |Available commands include:
           |${Console.GREEN}
           |     t | trends [string|id] ${Console.WHITE}=> Display a list of all places with available trends
           |                                               If an optional argument is provided, users are presented
           |                                               with the currently trending tweets RIGHT NOW, defaults to USA${Console.GREEN}
           |     h | help ${Console.WHITE}=> Display this help message again${Console.GREEN}
           |${Console.YELLOW}
           |** ---------------------------------------------- **
           |
        """.stripMargin


    /**
      * Convenience function which gives me the current working directory
      * @return String
      */
    def pwd:String = System.getProperty("user.dir")


    /**
      * Name:
      *     ParseFilesInDir
      *
      * Description:
      *     Recursively parses Files in the local project Resources/ directory producing
      *     an array of Strings containing file paths to each of the source files
      *     found.
      *
      * Source:
      *     This function was adapted from the accepted answer of this StackOverflow question
      *     http://stackoverflow.com/questions/2637643/how-do-i-list-all-files-in-a-subdirectory-in-scala
      *
      * @param dir: a Java File object containing the source to the directory
      * @return Array[String]
      */
    def parseFilesInDir(dir: File): Array[File] = {
        val files = dir.listFiles
        val allFiles = files ++ files.filter(_.isDirectory).flatMap(parseFilesInDir)
        allFiles.filter( f => """.*\.java$""".r.findFirstIn(f.getName).isDefined)
    }


    /**
      * Opens a Web-browser to the application showing the content
      * sources: http://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
      * @param uri the URL of the website, in this case localhost:9000
      */
    def openBrowser(uri: URI):Unit ={
        if(Desktop.isDesktopSupported) {
            try {
                Desktop.getDesktop.browse(uri)
            } catch {
                case e:Exception =>
                    e printStackTrace()
                    println("There was a problem, please open a browser at http://localhost:9000/")
            }

        }
    }
}

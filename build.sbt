import sbt.Keys._

lazy val root = (project in file("."))
        .settings(
            name := "final_project",
            version := "1.0",
            scalaVersion := "2.11.8",

            // For ScalaTest, disables the buffered Output offered by sbt and uses its own method
            logBuffered in Test := false,

            libraryDependencies ++= Seq(
                ws,
                "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp")),

                "org.json4s" %% "json4s-native" % "3.3.0",                                  // For Json parsing

//                "com.typesafe.akka" %% "akka-actor" % "2.4.8",                             // Akka actors
//                "com.typesafe.akka" %% "akka-stream" % "2.4.8",                            // Akka streams
//                "com.typesafe.akka" %% "akka-http-core" % "2.4.8",                         // Akka http stuffs
//                "com.typesafe.akka" %% "akka-http-experimental" % "2.4.8",

                // Go here in the event you need more jars
                // https://mvnrepository.com/artifact/org.twitter4j
                "org.twitter4j" % "twitter4j-core" % "4.0.5",                               // Twitter4j Core Twitter API
                "org.twitter4j" % "twitter4j-stream" % "4.0.5",                             // Twitter4j Streaming API
                "org.twitter4j" % "twitter4j-async" % "4.0.5",                              // Twitter4j Async API, needed for Streaming

                /*             Unit Test libraries             */
                "com.typesafe.akka" %% "akka-testkit" % "2.4.8",                            // Akka-testkit
                "com.typesafe.akka" %% "akka-http-testkit" % "2.4.8",                       // Akka-testkit
                "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.11",                    // Akka-testkit
                "org.scalactic" %% "scalactic" % "3.0.0",                                   // scalactic
                "org.scalatest" %% "scalatest" % "3.0.0" % "test"                           // scalatest
            )
        )
        .enablePlugins(PlayScala)

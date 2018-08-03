package au.edu.utscic.athanorserver.server

import akka.NotUsed
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.util.ByteString
import au.edu.utscic.athanorserver.StreamsContext
import au.edu.utscic.athanorserver.athanor.Athanor
import au.edu.utscic.athanorserver.corenlp.TextParser
import au.edu.utscic.athanorserver.data.RhetoricalTypes.ParsedSentence
import au.edu.utscic.athanorserver.message.Json

import scala.concurrent.Future
/**
  * Created by andrew@andrewresearch.net on 20/2/17.
  */
object TextAnalysisHandler {

  import StreamsContext._

  def analyse(msg: Json.ByteStringAnalysis): Future[Json.Results] = {
    log.info("Analysing text...")
    val pipeline = parser via athanor(msg.analysisType)
    val pipelineResults = Source.single(msg.byteStr) via pipeline runWith Sink.head[List[List[String]]]
    Json.formatResults(pipelineResults, "Text Analysis Results")
  }

  val parser: Flow[ByteString, List[ParsedSentence], NotUsed] = Flow[ByteString].mapAsync(10) { bs =>
    val inputText = bs.utf8String
    TextParser.parse(inputText)
  }

  def athanor(grammar: String): Flow[List[ParsedSentence], List[List[String]], NotUsed] = {
    Flow[List[ParsedSentence]].mapAsync(10) { sents =>
      val listOfFutures = sents.map { ps =>
        Athanor.analyseParsedSentence(ps, grammar)
      }
      Future.sequence(listOfFutures)
    }
  }
}

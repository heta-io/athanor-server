package au.edu.utscic.athanorserver.athanor

import au.edu.utscic.athanorserver.data.RhetoricalImplicits
import au.edu.utscic.athanorserver.data.RhetoricalTypes._
import com.xerox.jatanor.JAtanor
import org.json4s.JsonAST.JValue
import org.json4s.NoTypeHints
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

// Imports used to read property file

// Imports used to join paths

// Imports used for logging

/**
  * Created by andrew@andrewresearch.net on 28/6/17.
  */
object Athanor {

  val grammarPath = GrammarPath.currentPath

  //val athanor = new JAtanor
  //val aHandler = athanor.LoadProgram(grammarPath + "/apply_ana.kif","")
  //val rHandler = athanor.LoadProgram(grammarPath + "/apply.kif","")

  val ag = new AthanorGrammar(grammarPath + "/apply_ana.kif")
  val rg = new AthanorGrammar(grammarPath + "/apply.kif")

  def analyse(json:Array[String],grammar:AthanorGrammar):Future[List[String]]  = {
    //println(s">>> Starting Analysis for $id")
    val aa = Future(grammar.execute(json))
//    aa.onComplete {
//      case Success(r) => {
//        val h = s"--- Results for ID $id ---\n"
//        val b = r.mkString("\n")+"\n"
//        val f = "---------------------------\n"
//        println(h+b+f)
//      }
//      case Failure(e) => ">>> ERROR: There was a problem"
//    }
      aa
  }

  def analyseParsedSentence(parsed:ParsedSentence,grammar:String):Future[List[String]] = {
    val jsonStr:String = parsedSentenceToJsonString(parsed)
    grammar match {
      case "analytic" => analyse(List(jsonStr).toArray,ag)
//      {
//        Await.result(analyse(List(jsonStr).toArray,ag), 10 seconds)
//
//        //println(s"analytic: $aHandler")
//        //athanor.ExecuteFunctionArray(aHandler,"Apply",List(jsonStr).toArray).toList
//      }
      case "reflective" => analyse(List(jsonStr).toArray,rg)
//      {
//        Await.result(analyse(List(jsonStr).toArray,rg), 10 seconds)
//
//       // println(s"reflective: $rHandler")
//        //athanor.ExecuteFunctionArray(rHandler,"Apply",List(jsonStr).toArray).toList
//      }
      case _ => Future(List())
    }

  }



  def parseJsonSentence(sent:String):ParsedSentence = {

    import RhetoricalImplicits._

    val json:JValue = parse(sent)
    val lexNodes:LexicalNodes = json(0)
    val constTree:ConstituentTree = json(1).extract[ConstituentTree]
    val deps:Dependencies = json(2).extract[Dependencies]
    (lexNodes,constTree,deps)
  }

  def parsedSentenceToJsonString(parsedSent:ParsedSentence):String = {
    implicit val formats = Serialization.formats(NoTypeHints)
    val l = write(parsedSent._1)
    val c = write(parsedSent._2).replaceAll("""(\"(?=[0-9]))|((?<=[0-9])\")""","") //remove quotes around Ints for json
    val d = write(parsedSent._3)
    s"[$l,$c,$d]"
  }




}


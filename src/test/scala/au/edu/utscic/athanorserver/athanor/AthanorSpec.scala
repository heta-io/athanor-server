package au.edu.utscic.athanorserver.athanor

import au.edu.utscic.athanorserver.{TestData, UnitSpec}
import org.skyscreamer.jsonassert.JSONAssert

/**
  * Created by andrew@andrewresearch.net on 19/7/17.
  */
class AthanorSpec extends UnitSpec {

  val rhetoricalMoves = List("ContrastStance", "SubjectAnalysis", "contrast", "context1", "challenge1", "temporality", "StanceAnalysis", "challenge")


  behavior of "AthanorSpec"

  it should "have a grammar parser" in {
    assert(Athanor.grammarPath != "")
  }

  it should "parseJsonSentence" in {
    val ps = Athanor.parseJsonSentence(TestData.athJsonString)
    assert(ps==TestData.athParsedSentence)
  }

  it should "parsedSentenceToJsonString" in {
    import org.json4s._
    import org.json4s.jackson.JsonMethods._
    //Get strings
    val expected = TestData.athJsonString
    val actual = Athanor.parsedSentenceToJsonString(TestData.athParsedSentence)
    //Check that the strings can actually be parsed into json
    val expJson = parse(expected)
    val actJson = parse(actual)
    //Check compact version of both for each element
    val exp1 = compact(render(expJson(0)))
    val act1 = compact(render(actJson(0)))
    JSONAssert.assertEquals(exp1, act1, true)
    val exp2 = compact(render(expJson(1)))
    val act2 = compact(render(actJson(1)))
    JSONAssert.assertEquals(exp2, act2, true)
    val exp3 = compact(render(expJson(2)))
    val act3 = compact(render(actJson(2)))
    JSONAssert.assertEquals(exp3, act3, true)
    //And check full non-compact versions
    JSONAssert.assertEquals(expected,actual,true)
  }

//  it should "analyseParsedSentence" in {
//    val result = Athanor.analyseParsedSentence(TestData.athParsedSentence,"reflective")
//    assert(result.toSet==rhetoricalMoves.toSet) //Order doesn't matter
//  }

//  it should "analyseJsonSentence" in {
//    val result = Athanor.analyseJson(TestData.athJsonString)
//    assert(result.toSet==rhetoricalMoves.toSet) //Order doesn't matter
//  }





}

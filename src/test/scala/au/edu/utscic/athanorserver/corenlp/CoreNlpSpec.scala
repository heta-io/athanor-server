package au.edu.utscic.athanorserver.corenlp

import au.edu.utscic.athanorserver.UnitSpec
import au.edu.utscic.athanorserver.athanor.Athanor
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations._
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation

import scala.collection.JavaConverters._

/**
  * Created by andrew@andrewresearch.net on 19/7/17.
  */
class CoreNlpSpec extends UnitSpec {

//  import au.edu.utscic.athanorserver.TestData._
//
//  lazy val annotatedSent = TextParser.annotateSentences(textA)
//  lazy val parsedSents = TextParser.parseSentences(annotatedSent)
//  lazy val tokens1 = TextParser.getTokens(annotatedSent(0))
//  lazy val constTree1 = Some(annotatedSent(0).get(classOf[TreeAnnotation]))
//  lazy val semGraph1 = Some(annotatedSent(0).get(classOf[EnhancedPlusPlusDependenciesAnnotation]))
//
//
//  behavior of "TextParser"
//
//  it should "annotateSentences" in {
//    assert(annotatedSent.length==2)
//    val annKeys = annotatedSent.map(_.keySet().asScala).reduce(_ intersect _)
//    assert(annotationKeys.diff(annKeys)==Set[Class[_]]())
//  }
//
//  it should "getTokens" in {
//    assert(tokens1.length==10)
//    assert(tokens1.map(_.lemma())==textAlemmas1)
//  }
//
//  it should "parseSentences" in {
//    assert(parsedSents.length==2)
//    val ps1 = parsedSents(0)
//    val ps2 = parsedSents(1)
//    //Sentence 1
//    assert(ps1._1.size==11) //Correct number of nodes
//    assert(ps1._1.map(_._1).toSeq==Seq(0,1,2,3,4,5,6,7,8,9,10)) //Is sorted
//    assert(ps1._2.head=="ROOT") //Tree has root
//    val ps1s = ps1._2(1).asInstanceOf[List[Any]]
//    assert(ps1s.head=="S") //Tree has starting sentence
//    assert(ps1s.length==4) //S + 3 top level phrases
//    assert(ps1._3.size==11) //Correct number of dependencies
//    assert(ps1._3.head.name=="root") //Dependencies has root
//    //Sentence 2
//    assert(ps2._1.size==9) //Correct number of nodes
//    assert(ps2._1.map(_._1).toSeq==Seq(0,1,2,3,4,5,6,7,8)) //Is sorted
//    assert(ps2._2.head=="ROOT") //Tree has root
//    val ps2s = ps2._2(1).asInstanceOf[List[Any]]
//    assert(ps2s.head=="S") //Tree has starting sentence
//    assert(ps2s.length==4) //S + 3 top level phrases
//    assert(ps2._3.size==8) //Correct number of dependencies
//    assert(ps1._3.head.name=="root") //Dependencies has root
//  }
//
//  it should "parse" in {
//    val ps = TextParser.parse(textA)
//    assert(ps.length==2)
//    assert(ps(0)==textAparsed1)
//    assert(ps(1)==textAparsed2)
//    //Athanor sentence test
//    val demoPS = TextParser.parse(athSentence)
//    val json = Athanor.parsedSentenceToJsonString(demoPS.head)
//    println(json)
//    assert(json.toSet==athJsonString.toSet)     // Order does not matter
//  }
//
//  behavior of "SentenceParser"
//
//  it should "getNodes" in {
//    val nodes = SentenceParser.getNodes(tokens1)
//    assert(nodes.size==11)
//    assert(nodes==textAnodes1)
//  }
//
//  it should "getTree" in {
//    val tree = SentenceParser.getTree(constTree1)
//    assert(tree==textAconstTree1)
//  }
//
//  it should "getDependencies" in {
//    val deps = SentenceParser.getDependencies(semGraph1)
//    assert(deps==textAdepend1)
//  }
//
//  it should "parse" in {
//    val res = SentenceParser.parse(tokens1,constTree1,semGraph1)
//    assert(res==textAparsed1)
//  }
//
//  behavior of "ConstituentTreeParser"
//
//  it should "process" in {
//    val res = ConstituentTreeParser.process(constTree1.get)
//    assert(res==textAconstTree1)
//  }
//
//  it should "convertToJsonString" in {
//    val res = ConstituentTreeParser.convertToJsonString(textAconstTree1)
//    assert(res==textAconstTreeJson1)
//  }
//
//  it should "parse" in {
//    val res = ConstituentTreeParser.parse(constTree1)
//    assert(res==textAconstTree1)
//  }
}

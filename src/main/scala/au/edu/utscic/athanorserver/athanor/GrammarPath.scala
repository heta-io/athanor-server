package au.edu.utscic.athanorserver.athanor

import java.io.File

import com.typesafe.config.ConfigFactory

import scala.util.Try

// Imports used for logging
//import au.edu.utscic.athanorserver.StreamsContext.log

/*
* Created by jh on 25/10/2017
*/
object GrammarPath {


  // Get the local grammar path.
  // This is set to a default value of /home_dir/athanor/grammar but can be manipulated
  // by the grammar.localPath value in the property file or
  // the ATHANOR_SERVER_LOCAL_GRAMMAR_PATH environmental variable.
  // The precedence (High to Low) is:
  // 1) Environment variable 2) Property value 3) Hardcoded value


  private val environmentPath:Option[String] = for {
    ep <- Try(sys.env("GRAMMAR_PATH")).toOption
    vp <- validatePath(ep)
  } yield vp

  private val propertyPath:Option[String] = for {
    pp <- Try(ConfigFactory.load.getString("app.grammar.path")).toOption
    vp <- validatePath(pp)
  } yield vp

  private val localPath:Option[String] = validatePath(System.getProperty("user.home") + "/athanor-server/grammar")

  private val dockerPath:Option[String] = validatePath("/opt/docker/grammar")

  val currentPath:String = {
    environmentPath.getOrElse(propertyPath.getOrElse(localPath.getOrElse(dockerPath.getOrElse(""))))
  }

  def validatePath(path:String):Option[String] = {
    val file = new File(path)
    //println("FilePath: "+file.getAbsolutePath)
    if (file.exists && file.isDirectory) Some(path) else None
  }


}
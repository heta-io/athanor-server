package au.edu.utscic.athanorserver.athanor

import com.xerox.jatanor.JAtanor

class AthanorGrammar(grammarFile:String) {

  private val athanor = new JAtanor
  private val handler = athanor.LoadProgram(grammarFile, "")

  def execute(json: Array[String]): List[String] =  athanor.ExecuteFunctionArray(handler, "Apply",json).toList

}

package maze

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MazePrinterSuite extends FunSuite {
  import Maze._
//  import MazePrinter._
  
  trait TestFixture {
    val printer = new MazePrinter(10,10)
  }
  
  test("Open north") {
    new TestFixture {
      val doors = Set((Loc(1,1), Loc(1,0)))
      assert(printer.openNorth(doors, Loc(1,1)) === true)
    }
  }
  
  test("Not open north") {
    new TestFixture {
      val doors = Set((Loc(1,1), Loc(1,2)))
      assert(printer.openNorth(doors, Loc(1,1)) === false)
    }
  }
    
  test("Open west") {
    new TestFixture {
      val doors = Set((Loc(1,1), Loc(2,1)))
      assert(printer.openWest(doors, Loc(2,1)) === true)
    }
  }
      
  test("Not open west") {
    new TestFixture {
      val doors = Set((Loc(1,1), Loc(1,0)))
      assert(printer.openWest(doors, Loc(1,1)) === false)
    }
  }
  
  test("Build maze") {
    val size = 10
    val doors = new Maze(size,size).build()
    val printer = new MazePrinter(size,size)
    println(printer.printGrid(doors).mkString("\n"))
  }
}
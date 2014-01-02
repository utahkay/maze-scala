package maze

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridSuite extends FunSuite {
  import MazeTypes._
  
  test("All neighbors") {
    val nbors = new Grid(10, 10, Set(), Set()).neighbors(Point(1,1))
    assert(nbors === Set(Point(0,1), Point(2,1), Point(1,0), Point(1,2)))
  }
  
  test("Neighbors are within maze bounds") {
    val nbors = new Grid(1, 1, Set(), Set()).neighbors(Point(0,0))
    assert(nbors === Set())
  }
  
  test("Neighbors are unvisited") {
    val nbors = new Grid(10, 10, Set(), Set(Point(2,1), Point(1,2), Point(3,4))).neighbors(Point(1,1))
    assert(nbors === Set(Point(0,1), Point(1,0)))
  }  
  
  test("Open north") {
    val doors = Set(Door(Point(1,1), Point(1,0)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openNorth(Point(1,1)) === true)
  }
  
  test("Not open north") {
    val doors = Set(Door(Point(1,1), Point(1,2)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openNorth(Point(1,1)) === false)
  }
    
  test("Open west") {
    val doors = Set(Door(Point(1,1), Point(2,1)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openWest(Point(2,1)) === true)
  }
      
  test("Not open west") {
    val doors = Set(Door(Point(1,1), Point(1,0)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openWest(Point(1,1)) === false)
  }  
}
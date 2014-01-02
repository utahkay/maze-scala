package maze

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridSuite extends FunSuite {
  import MazeTypes._
  
  test("All neighbors") {
    val nbors = new Grid(10, 10, Set(), Set()).neighbors(Loc(1,1))
    assert(nbors === Set(Loc(0,1), Loc(2,1), Loc(1,0), Loc(1,2)))
  }
  
  test("Neighbors are within maze bounds") {
    val nbors = new Grid(1, 1, Set(), Set()).neighbors(Loc(0,0))
    assert(nbors === Set())
  }
  
  test("Neighbors are unvisited") {
    val nbors = new Grid(10, 10, Set(), Set(Loc(2,1), Loc(1,2), Loc(3,4))).neighbors(Loc(1,1))
    assert(nbors === Set(Loc(0,1), Loc(1,0)))
  }  
  
  test("Open north") {
    val doors = Set(Door(Loc(1,1), Loc(1,0)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openNorth(Loc(1,1)) === true)
  }
  
  test("Not open north") {
    val doors = Set(Door(Loc(1,1), Loc(1,2)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openNorth(Loc(1,1)) === false)
  }
    
  test("Open west") {
    val doors = Set(Door(Loc(1,1), Loc(2,1)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openWest(Loc(2,1)) === true)
  }
      
  test("Not open west") {
    val doors = Set(Door(Loc(1,1), Loc(1,0)))
    val grid = new Grid(10,10,doors,Set())
    assert(grid.openWest(Loc(1,1)) === false)
  }  
}
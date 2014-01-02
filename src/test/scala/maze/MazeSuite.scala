package maze

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class  MazeSuite extends FunSuite {
  import Maze._

  test("Shuffle") {
    val set = Set(1,2,3,4,5)
    val shuffles = List(shuffle(set), shuffle(set), shuffle(set))
    assert(shuffles.distinct.size > 1)
    assert(shuffles.forall(_.toSet == set))
  }
  
  test("Location + Direction = New Location") {
    assert(Location(1,2) + Direction(3,4) === Location(4, 6))
  }
  
  test("All neighbors") {
    val unvisited = new Maze(10,10).neighbors(Location(1,1))
    assert(unvisited === Set(Location(0,1), Location(2,1), Location(1,0), Location(1,2)))
  }
  
  test("Neighbors are within maze bounds") {
    val unvisited = new Maze(1,1).neighbors(Location(0,0))
    assert(unvisited === Set())
  }
  
  test("Neighbors are unvisited") {
    val maze = new Maze(10,10)
    maze.visited = Set(Location(2,1), Location(1,2), Location(3,4))
    val unvisited = maze.neighbors(Location(1,1))
    assert(unvisited === Set(Location(0,1), Location(1,0)))
  }
  
}


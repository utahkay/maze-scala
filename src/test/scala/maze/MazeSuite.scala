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
  
  test("Loc + Direction = New Loc") {
    assert(Loc(1,2) + Direction(3,4) === Loc(4, 6))
  }
  
  test("All neighbors") {
    val unvisited = new Maze(10,10).neighbors(Loc(1,1), Set())
    assert(unvisited === Set(Loc(0,1), Loc(2,1), Loc(1,0), Loc(1,2)))
  }
  
  test("Neighbors are within maze bounds") {
    val unvisited = new Maze(1,1).neighbors(Loc(0,0), Set())
    assert(unvisited === Set())
  }
  
  test("Neighbors are unvisited") {
    val maze = new Maze(10,10)
    val unvisited = maze.neighbors(Loc(1,1), Set(Loc(2,1), Loc(1,2), Loc(3,4)))
    assert(unvisited === Set(Loc(0,1), Loc(1,0)))
  }
  
}


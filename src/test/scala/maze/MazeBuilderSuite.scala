package maze

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MazeBuilderSuite extends FunSuite {
  import MazeBuilder._

  test("Shuffle") {
    val set = Set(1,2,3,4,5)
    val shuffles = List(shuffle(set), shuffle(set), shuffle(set))
    assert(shuffles.distinct.size > 1)
    assert(shuffles.forall(_.toSet == set))
  }
  
  test("Loc + Direction = New Loc") {
    assert(Loc(1,2) + Direction(3,4) === Loc(4, 6))
  }

  test("Build maze") {
    val size = 10
    val grid = MazeBuilder.build(size, size)
    println(grid.printGrid.mkString("\n"))
  }
}


package maze

import scala.util.Random

object MazeTypes {
  case class Direction(val dx: Int, val dy: Int)

  case class Point(val x: Int, val y: Int) {
    def +(that: Direction): Point = Point(x + that.dx, y + that.dy)
  }
  
  case class Door(val from: Point, to: Point)

  val North = Direction(0,-1)
  val South = Direction(0,1)
  val West = Direction(-1,0)
  val East = Direction(1,0)
  val directions = Set(North, South, West, East)
}

object MazeBuilder {
  import MazeTypes._

  def build(width: Int, height: Int): Grid = {
    val exit = Point(width-1, height-1)
    buildImpl(exit, new Grid(width, height, Set(), Set()))
  }  
  
  private def buildImpl(current: Point, grid: Grid): Grid = {
    var newgrid = grid.markVisited(current)
    val nbors = shuffle(grid.neighbors(current))
    nbors.foreach { n =>
      if (!newgrid.isVisited(n)) {
        newgrid = buildImpl(n, newgrid.markVisited(current).addDoor(Door(current, n)))
      }
    }
    newgrid
  }
  
  def shuffle[T](set: Set[T]): List[T] = Random.shuffle(set.toList)
}




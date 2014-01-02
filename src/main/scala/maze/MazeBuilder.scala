package maze

import scala.util.Random

object MazeTypes {
  case class Direction(val dx: Int, val dy: Int)

  case class Loc(val x: Int, val y: Int) {
    def +(that: Direction): Loc = Loc(x + that.dx, y + that.dy)
  }
  
  case class Door(val from: Loc, to: Loc)

  val North = Direction(0,-1)
  val South = Direction(0,1)
  val West = Direction(-1,0)
  val East = Direction(1,0)
  val directions = Set(North, South, West, East)
}

object MazeBuilder {
  import MazeTypes._

  def shuffle[T](set: Set[T]): List[T] = Random.shuffle(set.toList)
  
  def buildImpl(current: Loc, grid: Grid): Grid = {
    var newgrid = grid.markVisited(current)
    val nbors = shuffle(grid.neighbors(current))
    nbors.foreach { n =>
      if (!newgrid.isVisited(n)) {
        newgrid = buildImpl(n, newgrid.markVisited(current).addDoor(Door(current, n)))
      }
    }
    newgrid
  }
    
  def build(width: Int, height: Int): Grid = {
    val result = buildImpl(Loc(width-1, height-1), new Grid(width, height, Set(), Set()))
    // add door for entry 
    result.addDoor(Door(Loc(0,0), Loc(-1,0)))
    // TODO: add door for exit *sigh*
  }  
}




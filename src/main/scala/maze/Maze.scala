package maze

import scala.util.Random

object Maze {
  case class Direction(val dx: Int, val dy: Int)

  case class Loc(val x: Int, val y: Int) {
    def +(that: Direction): Loc = Loc(x + that.dx, y + that.dy)
  }
  
  val North = Direction(0,-1)
  val South = Direction(0,1)
  val West = Direction(-1,0)
  val East = Direction(1,0)
  val directions = Set(North, South, West, East)

  type Door = (Loc,Loc)

  def shuffle[T](set: Set[T]): List[T] = Random.shuffle(set.toList)
}

class Maze(val width: Int, val height: Int) {
  import Maze._
  
  var visited = Set.empty[Loc]
  
  def neighbors(current: Loc): Set[Loc] = 
    directions.map(current + _).filter(inBounds) -- visited
  
  def inBounds(loc: Loc): Boolean = 
    loc.x >= 0 && loc.x < width && loc.y >= 0 && loc.y < height

  def buildImpl(current: Loc = Loc(0,0), doors: Set[Door] = Set()): Set[Door] = {
    visited = visited + current  
    val nbors = shuffle(neighbors(current))
    var newdoors = doors
    nbors.foreach { n =>
      if (!visited.contains(n)) {
        newdoors = newdoors ++ buildImpl(n, doors + ((current, n)))
      }
    }
    newdoors
  }
    
  def build(): Set[Door] = {
    val doors = buildImpl(Loc(width-1, height-1), Set())
    // add door for entry 
    doors + ((Loc(0,0),Loc(-1,0)))
    // TODO: add door for exit *sigh*
  }
}


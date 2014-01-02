package maze

import scala.util.Random

object Maze {
  case class Direction(val dx: Int, val dy: Int)

  case class Location(val x: Int, val y: Int) {
    def +(that: Direction): Location = Location(x + that.dx, y + that.dy)
  }
  
  val North = Direction(0,-1)
  val South = Direction(0,1)
  val West = Direction(-1,0)
  val East = Direction(1,0)
  val directions = Set(North, South, West, East)

  type Door = (Location,Location)

  def shuffle[T](set: Set[T]): List[T] = Random.shuffle(set.toList)
}

class Maze(val height: Int, val width: Int) {
  import Maze._
  
  var visited = Set.empty[Location]
  var doors = Set.empty[Door]
  
  def neighbors(current: Location): Set[Location] = 
    directions.map(current + _).filter(inBounds) -- visited
  
  def inBounds(loc: Location): Boolean = 
    loc.x >= 0 && loc.x < width && loc.y >= 0 && loc.y < height

  def build(current: Location = Location(0,0)): Set[Door] = {
    visited = visited + current  
    val nbors = shuffle(neighbors(current))
    nbors.foreach { n =>
      if (!visited.contains(n))
        doors = doors + ((current, n))
        build(n)
    }
    doors
  }
}


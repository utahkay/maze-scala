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
  
  def neighbors(current: Loc, visited: Set[Loc]): Set[Loc] = 
    directions.map(current + _).filter(inBounds) -- visited
  
  def inBounds(loc: Loc): Boolean = 
    loc.x >= 0 && loc.x < width && loc.y >= 0 && loc.y < height

  def buildImpl(current: Loc, doors: Set[Door], visited: Set[Loc]): (Set[Door], Set[Loc]) = {
    var newvisited = visited + current  
    var newdoors = doors
    val nbors = shuffle(neighbors(current, newvisited))
    nbors.foreach { n =>
      if (!newvisited.contains(n)) {
        val result = buildImpl(n, doors + ((current, n)), newvisited)
        newdoors = newdoors ++ result._1
        newvisited = newvisited ++ result._2
      }
    }
    (newdoors, newvisited)
  }
    
  def build(): Set[Door] = {
    val result = buildImpl(Loc(width-1, height-1), Set(), Set())
    // add door for entry 
    result._1 + ((Loc(0,0),Loc(-1,0)))
    // TODO: add door for exit *sigh*
  }
}


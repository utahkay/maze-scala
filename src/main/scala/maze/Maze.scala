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
    
  def printCell(x: Int, y: Int): List[String] = {
    if (y == height) List("+--")
    else List(
        if (openNorth(Location(x,y))) "+  " else "+--", 
        if (openWest(Location(x,y))) "   " else "|  ")
  }
  
  def printRow(y: Int): List[String] = {
    val row = (0 until width).toList.map(printCell(_, y))
    val newRow = row :+ List("+", "|")
    List.transpose(newRow).map(_.mkString)
  }
  
  def printGrid(): List[String] = {
    (0 to height).toList.flatMap(printRow(_))
  }
  
  def openNorth(loc: Location): Boolean = 
    doors.contains(loc, loc + North) || doors.contains(loc + North, loc)
  
  def openWest(loc: Location): Boolean = 
    doors.contains(loc, loc + West) || doors.contains(loc + West, loc)

    
}
package maze

import MazeTypes._

class Grid(val width: Int, val height: Int, val doors: Set[Door], val visited: Set[Point]) {

  def addDoor(door: Door): Grid = 
    new Grid(width, height, doors + door, visited)
  
  def markVisited(loc: Point): Grid = 
    new Grid(width, height, doors, visited + loc)
  
  def isVisited(loc: Point): Boolean = 
    visited.contains(loc)
    
  def neighbors(current: Point): Set[Point] = 
    directions.map(current + _).filter(inBounds(_)) -- visited
    
  def printGrid(): List[String] = {
    (0 to height).toList.flatMap(y => printRow(y))
  }
  
  private def inBounds(loc: Point): Boolean = 
    loc.x >= 0 && loc.x < width && loc.y >= 0 && loc.y < height

  private val entrance = Point(0,0)
  private val exit = Point(width-1, height-1)
  
  private def printRow(y: Int): List[String] = {
    val row = (0 until width).toList.map(x => printCell(Point(x, y)))
    val rightSide = if (y == exit.y) " " else "|"
    val newRow = row :+ List("+", rightSide)
    List.transpose(newRow).map(_.mkString)
  }
  
  private def printCell(loc: Point): List[String] = {
    if (loc.y == height) 
      List("+--")
    else List(
      if (openNorth(loc)) "+  " else "+--", 
      if (openWest(loc) || loc == entrance) "   " else "|  "
    )
  }
  
  def openNorth(loc: Point): Boolean = openInDirection(loc, North)
  
  def openWest(loc: Point): Boolean = openInDirection(loc, West)
    
  private def openInDirection(loc: Point, dir: Direction): Boolean = 
    doors.contains(Door(loc, loc + dir)) || doors.contains(Door(loc + dir, loc))
}
package maze

import MazeTypes._

class Grid(val width: Int, val height: Int, val doors: Set[Door], val visited: Set[Loc]) {

  def addDoor(door: Door): Grid = 
    new Grid(width, height, doors + door, visited)
  
  def markVisited(loc: Loc): Grid = 
    new Grid(width, height, doors, visited + loc)
  
  def isVisited(loc: Loc): Boolean = 
    visited.contains(loc)
    
  def neighbors(current: Loc): Set[Loc] = 
    directions.map(current + _).filter(inBounds(_)) -- visited
    
  def printGrid(): List[String] = {
    (0 to height).toList.flatMap(y => printRow(y))
  }
  
  private def inBounds(loc: Loc): Boolean = 
    loc.x >= 0 && loc.x < width && loc.y >= 0 && loc.y < height

  private def printRow(y: Int): List[String] = {
    val row = (0 until width).toList.map(x => printCell(Loc(x, y)))
    val rightSide = if (y == height-1) " " else "|"
    val newRow = row :+ List("+", rightSide)
    List.transpose(newRow).map(_.mkString)
  }
  
  private val entrance = Loc(0,0)

  private def printCell(loc: Loc): List[String] = {
    if (loc.y == height) 
      List("+--")
    else List(
      if (openNorth(loc)) "+  " else "+--", 
      if (openWest(loc) || loc == entrance) "   " else "|  "
    )
  }
  
  def openNorth(loc: Loc): Boolean = openInDirection(loc, North)
  
  def openWest(loc: Loc): Boolean = openInDirection(loc, West)
    
  private def openInDirection(loc: Loc, dir: Direction): Boolean = 
    doors.contains(Door(loc, loc + dir)) || doors.contains(Door(loc + dir, loc))
}
package maze

import MazeBuilder._

class Grid(val width: Int, val height: Int, val doors: Set[Door], val visited: Set[Loc]) {
  def markVisited(loc: Loc): Grid = 
    new Grid(width, height, doors, visited + loc)
  
  def addDoor(door: Door): Grid = 
    new Grid(width, height, doors + door, visited)
  
  def inBounds(loc: Loc): Boolean = 
    loc.x >= 0 && loc.x < width && loc.y >= 0 && loc.y < height

  def neighbors(current: Loc): Set[Loc] = 
    directions.map(current + _).filter(inBounds(_)) -- visited
    
  def isVisited(loc: Loc): Boolean = 
    visited.contains(loc)
    
  def printCell(loc: Loc): List[String] = {
    if (loc.y == height) 
      List("+--")
    else List(
      if (openNorth(loc)) "+  " else "+--", 
      if (openWest(loc))  "   " else "|  "
    )
  }
  
  def printRow(y: Int): List[String] = {
    val row = (0 until width).toList.map(x => printCell(Loc(x, y)))
    val newRow = row :+ List("+", "|")
    List.transpose(newRow).map(_.mkString)
  }
  
  def printGrid(): List[String] = {
    (0 to height).toList.flatMap(y => printRow(y))
  }
  
  def openNorth(loc: Loc): Boolean = 
    doors.contains(loc, loc + North) || doors.contains(loc + North, loc)
  
  def openWest(loc: Loc): Boolean = 
    doors.contains(loc, loc + West) || doors.contains(loc + West, loc)    
}
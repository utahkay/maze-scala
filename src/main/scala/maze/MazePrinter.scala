package maze

class MazePrinter(val height: Int, val width: Int) {
  import Maze._
    
  def printCell(doors: Set[Door], loc: Location): List[String] = {
    if (loc.y == height) 
      List("+--")
    else List(
      if (openNorth(doors, loc)) "+  " else "+--", 
      if (openWest(doors, loc))  "   " else "|  "
    )
  }
  
  def printRow(doors: Set[Door], y: Int): List[String] = {
    val row = (0 until width).toList.map(x => printCell(doors, Location(x, y)))
    val newRow = row :+ List("+", "|")
    List.transpose(newRow).map(_.mkString)
  }
  
  def printGrid(doors: Set[Door]): List[String] = {
    (0 to height).toList.flatMap(printRow(doors, _))
  }
  
  def openNorth(doors: Set[Door], loc: Location): Boolean = 
    doors.contains(loc, loc + North) || doors.contains(loc + North, loc)
  
  def openWest(doors: Set[Door], loc: Location): Boolean = 
    doors.contains(loc, loc + West) || doors.contains(loc + West, loc)
}
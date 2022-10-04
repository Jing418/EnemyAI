package tests

import game.enemyai.{AIGameState, AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest._

class LectureTask5 extends FunSuite {


  test("LT5") {


    val value4 = new PlayerLocation(2,2,"num1")
    val P4: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](value4, null)

    val value3 = new PlayerLocation(1,3,"num2")
    val P3 = new LinkedListNode[PlayerLocation](value3, P4)

    val value2 = new PlayerLocation(4,5,"num3") //player
    val P2 = new LinkedListNode[PlayerLocation](value2, P3)

    val value1 = new PlayerLocation(6,7,"num4")
    val P1 = new LinkedListNode[PlayerLocation](value1, P2)

    val my_aiplayer: AIPlayer = new AIPlayer("num3")
    my_aiplayer.locatePlayer("num3",P1)

    val start = new GridLocation(2,2)
    val end = new GridLocation(4,2)


    val wall1 = new GridLocation(3,0)
    val wall2 = new GridLocation(3,1)
    val wall3 = new GridLocation(3,2)
    val wall4 = new GridLocation(3,3)

    val gameState = new AIGameState()
    gameState.levelHeight = 5
    gameState.levelWidth = 5
    gameState.wallLocations = List(wall1,wall2,wall3,wall4)
    val output1 = my_aiplayer.distanceAvoidWalls(gameState: AIGameState, start, end)
    val output2 = my_aiplayer.distanceAvoidWalls(gameState: AIGameState,new GridLocation(1,1), new GridLocation(1,4))
    assert(output2 == 3)
    assert(output1 == 6)

  }

  /*
  LT5
  1. Setup the gameState
    a. levelHeight =7
  b. levelWidth = 7
  c. Walls = List[GridLocation]
  2. Start and end
    a. Start GridLocation(3.3)
  b. End: GridLocation(4.4)

  ai player.distanceAvoidWalls(gameState, start, end) //Returns the distance*/


}

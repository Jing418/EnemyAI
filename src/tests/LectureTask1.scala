package tests

import game.enemyai.PlayerLocation
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.enemyai.AIPlayer

import org.scalatest._

class LectureTask1 extends FunSuite {


  test("LT1") {

    // TODO
    val value4 = new PlayerLocation(2,2,"num1")//distance = The square root of 13//SR of 2
    val P4: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](value4, null)

    val value3 = new PlayerLocation(1,3,"num2")//distance = The square root of 13//do not calculate
    val P3 = new LinkedListNode[PlayerLocation](value3, P4)//my_player1 3

    val value2 = new PlayerLocation(4,5,"num3")// do not calculate//SR of 13
    val P2 = new LinkedListNode[PlayerLocation](value2, P3)//my_player2

    val value1 = new PlayerLocation(6,7,"num4")//distance = The square root of 8 //SR of 25+16
    val P1 = new LinkedListNode[PlayerLocation](value1, P2)

    val my_aiplayer: AIPlayer = new AIPlayer("num2")
    println(P1.next.value.playerId)
    my_aiplayer.locatePlayer("num2",P1)
    assert(my_aiplayer.locatePlayer("num2",P1).playerId == P3.value.playerId)
    assert(my_aiplayer.locatePlayer("num2",P1).x == P3.value.x)


    val aiplayer1 = new AIPlayer("num1")
    aiplayer1.locatePlayer("num1",P1)
    val aiplayer2 = new AIPlayer("num3")
    aiplayer2.locatePlayer("num3",P1)
    val aiplayer4 = new AIPlayer("num4")
    aiplayer4.locatePlayer("num4",P1)
    println(aiplayer2.id)
    println(aiplayer2.closestPlayer(P1).playerId)
    assert(aiplayer2.closestPlayer(P1).playerId == P1.value.playerId)
    assert(my_aiplayer.closestPlayer(P1).playerId == P4.value.playerId)

  }


  /*
  LT1：
  create new linked list node:
  Value: new playerLocation(2,2,"p1")
  Next : Null
  2 options:
  1. Change p1's next reference to be a new player location (appending)
  2. The new player location, next reference is p1 (prepending)

  P4: Create new linked list node (playerLocation)
      Value: new playerlocation(2,2,"num1")
      Next: null
  P3: Create new linked list node (playerLocation)
      Value: new playerLocation(1, 3,"num2")
      Next: P4
  P2: Create new linked list node (playerLocation)
      Value: new playerLocation(4,5,"num3")
      Next: P3
  P1: Create new linked list node (playerLocation)
      Value: new playerlLocation(6,7,"num4")
      Next: P2

  ^build the linked list
  Create AIPlayer("aiplayer").

  playerlD="num2"
  my_aiplayer.locatePlayer(playerID, P1)  //assert this to be P3 //don't create new players

  Create AIPlayer( "num3")
  aiPlayer.closestPlayer(P1)
  //looking for player with id "num3" = this.id
  //locate the player with this id, that's our player
  //go through every node in the linked list
  //which one is closest?
  //returns P3

  LT2

  G4: Create new linked list node (gridLocations)
      Value: new gridLocations(0,0)
      Next: null
  G3: Create new linked list node (gridLocations)
      Value: new gridLocations(2,2)
      Next: G4
  G2: Create new linked list node (playerLocation)
      Value: new gridLocations(1,3)
      Next: G3
  G1: Create new linked list node (playerLocation)
      Value: new gridLocations(4,5)
      Next: G2

  Create AIPlayer( "num3")

  ai_player.pathToDirection(P2,G4)
      //length of gridLocations is 1
      //move player to (0.0) //use centerAsVector
      //physicsVector(0.5 - 4.2, 0.5 - 5.7) = (-3.7, -5.2)

  ai_player.pathToDirection(P2,G1)
      //length of gridLocations is 4
      //move to center of second grid location (G2) use centerAs Vector
      //physicsVector(1.5 - 4. 3.5 - 5) = (-2.5, -1.5)

  ai_player.pathToDirection(P1,G4)
      //length of gridLocations is 1
      ///move player to (0.0) //use centerAsVector
      //physicsVector(0.5 - 4, 0.5 - 5) = (-3.5, -4.5)

  LT3
  Start: GridLocation(3,3)
  End: GridLocation(4,4)
  Create an AlPlayer
  ai_player.computePath(start, end) //returns a path
    Option1: (3,3)->(3,4)->(4.4)
    Option2: (3,3)->(4,3)->(4,4)

  LT4
  //There is no test

  LT5

  1. Setup the gameState
      a. levelHeight =7
      b. levelWidth = 7
      c. Walls = List[GridLocation]
  2. Start and end
      a. Start GridLocation(3.3)
      b. End: GridLocation(4.4)

  ai player.distanceAvoidWalls(gameState, start, end) //Returns the distance


  */

}

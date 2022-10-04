package tests

import game.enemyai.{AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest._

class LectureTask2 extends FunSuite {

  def compareDouble(a:Double,b:Double):Boolean={
    val epsilon = 0.00001
    Math.abs(a-b)<0.00001
  }
  test("LT2") {

    val value4 = new PlayerLocation(2,2,"num1")
    val P4: LinkedListNode[PlayerLocation] = new LinkedListNode[PlayerLocation](value4, null)

    val value3 = new PlayerLocation(1,3,"num2")
    val P3 = new LinkedListNode[PlayerLocation](value3, P4)

    val value2 = new PlayerLocation(4.2,5.7,"num3") //player
    val P2 = new LinkedListNode[PlayerLocation](value2, P3)

    val value1 = new PlayerLocation(6,7,"num4")
    val P1 = new LinkedListNode[PlayerLocation](value1, P2)

    val my_aiplayer: AIPlayer = new AIPlayer("num3")
    my_aiplayer.locatePlayer("num3",P1)

    val gridValue4 = new GridLocation(0,0)
    val G4 = new LinkedListNode[GridLocation](gridValue4,null)

    val gridValue3 = new GridLocation(2,2)
    val G3 = new LinkedListNode[GridLocation](gridValue3,G4)

    val gridValue2 = new GridLocation(1,3)
    val G2 = new LinkedListNode[GridLocation](gridValue2,G3)

    val gridValue1 = new GridLocation(4,5)
    val G1 = new LinkedListNode[GridLocation](gridValue1,G2)


    assert(compareDouble(my_aiplayer.pathToDirection(P2,G4).x,-3.7))
    assert(compareDouble(my_aiplayer.pathToDirection(P2,G4).y,-5.2))

    assert(compareDouble(my_aiplayer.pathToDirection(P2,G1).x,-2.7))
    assert(compareDouble(my_aiplayer.pathToDirection(P2,G1).y,-2.2))

  }

  /*LT2

  P4: Create new linked list node (playerLocation)
      Value: new playerLocation(2,2,"num1")
      Next: null
  P3: Create new linked list node (playerLocation)
      Value: new playerLocation(1, 3,"num2")
      Next: P4
  P2: Create new linked list node (playerLocation)// player
      Value: new playerLocation(4.2, 5.7,"num3")
      Next: P3
  P1: Create new linked list node (playerLocation)
      Value: new playerLocation(6,7,"num4")
      Next: P2

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

  Create AIPlayer( "num3")//P2

  ai_player.pathToDirection(P2,G4)
      //length of gridLocations is 1
      //move player to (0.0) //use centerAsVector
      //physicsVector(0.5 - 4.2, 0.5 - 5.7) = (-3.7, -5.2)

  ai_player.pathToDirection(P2,G1)
      //length of gridLocations is 4
      //move to center of second grid location (G2) use centerAs Vector
      //physicsVector(1.5 - 4, 3.5 - 5) = (-2.5, -1.5)

  ai_player.pathToDirection(P1,G4)
      //length of gridLocations is 1
      ///move player to (0.0) //use centerAsVector
      //physicsVector(0.5 - 4, 0.5 - 5) = (-3.5, -4.5)*/
}




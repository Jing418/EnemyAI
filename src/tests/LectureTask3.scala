package tests


import game.enemyai.{AIPlayer, PlayerLocation}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import org.scalatest._

class LectureTask3 extends FunSuite {

  test("LT3"){

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

    val start = new GridLocation(3,3)
    val end = new GridLocation(4,4)
    val midPoint1 = new GridLocation(3,4)
    val midPoint2 = new GridLocation(4,3)

    val option1 = new LinkedListNode[GridLocation](end,null)
    val option11 = new LinkedListNode[GridLocation](midPoint1,option1)
    val option111 = new LinkedListNode[GridLocation](start,option11)

    val option2 = new LinkedListNode[GridLocation](end,null)
    val option22 = new LinkedListNode[GridLocation](midPoint2,option2)
    val option222 = new LinkedListNode[GridLocation](start,option22)

    var bool1 = false
    var bool2 = false
    println("actual:"+my_aiplayer.computePath(start, end).toString)
    println("option1:"+option111.toString)
    println("option2:"+option222.toString)

    if(my_aiplayer.computePath(start, end).toString == option111.toString){
      bool1 = true
    }
    if(my_aiplayer.computePath(start, end).toString == option222.toString){
      bool2 = true
    }
    assert(bool1 || bool2)





    /*
    if (compareList(my_aiplayer.computePath(start, end),option111) == true ){
      bool1 = true
    }

    val option2 = new LinkedListNode[GridLocation](end,null)
    val option22 = new LinkedListNode[GridLocation](midPoint2,option2)
    val option222 = new LinkedListNode[GridLocation](start,option22)
    var bool2 = false

    if (compareList(my_aiplayer.computePath(start, end),option222) == true){
      bool2 = true
    }

    assert(bool1 || bool2)

    val option3 = new LinkedListNode[GridLocation](wrongEnd,null)
    val option33 = new LinkedListNode[GridLocation](midPoint1,option3)
    val option333 = new LinkedListNode[GridLocation](start,option33)
    var bool3 = false

    if (compareList(my_aiplayer.computePath(start, end),option333) == true){
      bool3 = true
    }
    //assert(bool3 == false)
*/

  }

  def compareList(a:LinkedListNode[GridLocation],b:LinkedListNode[GridLocation]): Boolean={
    var output = false

    if(a.size() != b.size()){
      false
    }else{
      var time = 1

      for (l1 <- a){
        for (l2 <- b){
          }
        }
        if((a(time).value.x == a(time).value.x) && (a(time).value.y == a(time).value.y)){
          output = true
        time = time + 1
      }
      output
    }

  }
}

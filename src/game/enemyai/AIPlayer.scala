package game.enemyai

import game.enemyai.decisiontree.{ActionNode, DecisionNode, DecisionTreeValue, Queue}
import game.lo4_data_structures.graphs.Graph
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.physics.PhysicsVector
import game.{AIAction, Fire, MovePlayer}

class AIPlayer(val id: String){


  // TODO: LT1

  def locatePlayer(playerId: String, playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    var playerLocation = playerLocations
    val i = playerLocation.value
    if (i.playerId == playerId){
      val output = new PlayerLocation(i.x,i.y,i.playerId)
      //println("aaa")
      output
    }else if(playerLocation.next == null){
      //println("bbb")
      null
    }else{
      playerLocation = playerLocation.next
      //println("ccc")
      locatePlayer(playerId,playerLocation)
    }
  }

  def closestPlayer(playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    val player = this.id
    var distance: Double = Double.MaxValue
    var pl = playerLocations
    var output = new PlayerLocation(0,0,"")
    //println(player)
    val playerLocation111 = locatePlayer(player,playerLocations)
    //println(playerLocation111.y)

    while (pl != null) {
      if(pl.value.playerId != player){
        val distance2 = math.sqrt(math.pow(pl.value.x - playerLocation111.x,2) + math.pow(pl.value.y - playerLocation111.y,2))
        if(distance2<distance){
          output = pl.value
          distance = distance2
        }
      }
      pl = pl.next
    }
    output

  }


  // TODO: LT2

 /*def size(input:LinkedListNode[GridLocation]): Int = {
    if(input.next == null){
      1
    }else{
      input.next.size() + 1
    }
  } */

  def pathToDirection(playerLocations: LinkedListNode[PlayerLocation], path: LinkedListNode[GridLocation]): PhysicsVector = {
    val player = this.id
    val playerLocation111 = locatePlayer(player,playerLocations)
    //println(playerLocation111.x)

    if (path.size() == 1){

      val vectorX: Double = path.value.x + 0.5
      val vectorY: Double = path.value.y + 0.5
      new PhysicsVector(vectorX - playerLocation111.x, vectorY - playerLocation111.y)

    }else{

      val vectorX: Double = path.next.value.x + 0.5
      val vectorY: Double = path.next.value.y + 0.5
      new PhysicsVector(vectorX - playerLocation111.x, vectorY - playerLocation111.y)

    }


  }


  // TODO: LT3


  def computePath(start: GridLocation, end: GridLocation): LinkedListNode[GridLocation] = {
    var output = new LinkedListNode[GridLocation](new GridLocation(end.x, end.y), null)
    if(start.y < end.y){
      for( i <- end.y-1 to start.y by -1){
        var addGrid = new GridLocation(end.x,i)
        output = new LinkedListNode[GridLocation](addGrid, output)
      }
    }else if(start.y > end.y){
      for( i <- end.y+1 to start.y by 1){
        var addGrid = new GridLocation(end.x,i)
        output = new LinkedListNode[GridLocation](addGrid, output)
      }
    }else{
      output
    }
    if(start.x < end.x){
      for( i <- end.x-1 to start.x by -1){
        var addGrid = new GridLocation(i,start.y)
        output = new LinkedListNode[GridLocation](addGrid, output)
      }
    }else if(start.x > end.x){
      for( i <- end.x+1 to start.x by 1){
        var addGrid = new GridLocation(i,start.y)
        output = new LinkedListNode[GridLocation](addGrid, output)
      }
    }else{
      output
    }
  output
  }

  // TODO: LT4
  def makeDecision(gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction ={
    if(decisionTree.value.check(gameState) > 0){
      makeDecision(gameState,decisionTree.right)
    }else if(decisionTree.value.check(gameState) < 0){
      makeDecision(gameState,decisionTree.left)
    }else{
      decisionTree.value.action(gameState)
    }
    //MovePlayer(this.id, Math.random() - 0.5, Math.random() - 0.5)
  }


  // TODO: LT5



  def bfs[A](graph: Graph[A], startID: Int, endId: Int): Int = {
    var explored: Set[Int] = Set(startID)
    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startID)

    var distance : Int = 0
    var level : Int = 0

    while (!toExplore.empty()) {
      if(level == 0){
        distance = distance + 1
        level = toExplore.front.size()
      }

      val nodeToExplore = toExplore.dequeue()
      level = level - 1
      println("nodeToExplore："+ nodeToExplore)

      for (node <- graph.adjacencyList(nodeToExplore)) {
        if (!explored.contains(node)) {
          println("exploring: " + graph.nodes(node))
          toExplore.enqueue(node)
          explored = explored + node
          if (node == endId){
            return distance
          }
        }
      }
    }
    distance
  }

  def distanceAvoidWalls(gameState: AIGameState,start: GridLocation, end: GridLocation): Int ={
    var distance: Int = 0
    val validGraph : Graph[GridLocation] = gameState.levelAsGraph()
    var startID : Int = 0
    var endID : Int = 0

    for (node <- validGraph.nodes){
      if (node._2 == start){
        startID = node._1
      }
      if (node._2 == end){
        endID = node._1
      }
    }
    println("startID: " + startID)
    println("endID: " +endID)
    distance = bfs(validGraph,startID,endID)
    distance
  }










  // TODO: LT6


  // TODO: AO1

  // TODO: AO2


  def decisionTree(referencePlayer: AIPlayer): BinaryTreeNode[DecisionTreeValue] = {
    val huntClosestPlayer = new BinaryTreeNode[DecisionTreeValue](new ActionNode((gameState: AIGameState) => {
      val myLocation: PlayerLocation = referencePlayer.locatePlayer(referencePlayer.id, gameState.playerLocations)
      val closestPlayer: PlayerLocation = referencePlayer.closestPlayer(gameState.playerLocations)
      val path = referencePlayer.computePath(myLocation.asGridLocation(), closestPlayer.asGridLocation())
      val direction: PhysicsVector = referencePlayer.pathToDirection(gameState.playerLocations, path)
      MovePlayer(referencePlayer.id, direction.x, direction.y)
    }), null, null)

    val fire = new BinaryTreeNode[DecisionTreeValue](new ActionNode((gameState: AIGameState) => {
      Fire(referencePlayer.id)
    }), null, null)

    val fireProbability = 0.1
    val decider = new BinaryTreeNode[DecisionTreeValue](
      new DecisionNode((gameState: AIGameState) => if (Math.random() < fireProbability) -1 else 1), fire, huntClosestPlayer
    )

    decider
  }


}


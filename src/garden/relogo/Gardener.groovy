package garden.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;

import garden.ReLogoTurtle
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;;

import Random

class Gardener extends ReLogoTurtle {
	def Map Qtable
	def reward = 0
	def alpha = 0.5
	def gamma = 0.9
	def epsilon = 0.005
	def lastState = ""
	def neighborsList = []
	def newState = []
	Random rand = new Random()
	
	def step(){
		neighborsList = getSurroundings()
		facexy(getXcor(), minPycor)
		def bestDir = 0
		def bestVal = -100
		def curVal = 0
		def heading =0
		//def otherVals = [0, 1, 2, 3, 4, 5, 6, 7]
		for (int a=0; a<8; a++){
			neighborsList.add(a)
			if (Qtable.containsKey(neighborsList.toString())){
				curVal = Qtable[neighborsList.toString()]
			}
			else{
				curVal = 0
			}
			neighborsList.remove(9)
			if (curVal>bestVal){
				bestVal = curVal
				bestDir = a
			}
		}
		def explore = rand.nextFloat()
		//System.out.println("explore (0-1) "+ explore)
		if (explore>=epsilon){
			heading = bestDir
			neighborsList.add(bestDir)
			right(45*bestDir)
			//System.out.println("rotating " + 45*bestDir + " " + bestDir)
		}
		else{
			def num = rand.next(3)
			//System.out.println("num (0-7?) " + num)
			heading =  num
			neighborsList.add(num)
			right(45*num)
			//System.out.println("rotating " + 45*num)
		}
		//neighborsList.add(heading)
		if(heading==neighborsList[8])
		{
			reward += 0.02
		}
		lastState = neighborsList.toString()
		//System.out.println("heading " + getHeading())
		
		
		
		//Q["apple"] = 0.3
		/*System.out.println(Qtable["apple"])
		//System.out.println(thisVariable)
		def winner = maxOneOf(neighbors()){
			count(rabbitsOn(it))
		}
		face (winner)*/
		forward(0.55)
		if (count(rabbitsHere()) > 0) {
			//label = ""
			reward += 0.5
			def pest = oneOf(rabbitsHere())
			capture(pest)
		}
		if (count(plantsHere()) > 0) {
			label = "Oops"
			reward -= 0.1
			def blunder = oneOf(plantsHere())
			step_on(blunder)
		}
		else{
			label = ""
		}
		newState = getSurroundings()
	}
	
	def update_Q(total_reward){
		def prev_val = 0
		if (Qtable.containsKey(lastState))
		{
			prev_val = Qtable[lastState]
		}
		// whatever is your latest state and direction
		// ohgodno is the maximum thing for the next thing 
		def max_next_val = max_next_step()
		Qtable[lastState] = ((float) (1-alpha) * prev_val + alpha * (total_reward+reward+gamma*(max_next_val))).trunc(8)
	//	System.out.println("updated val " + Qtable[lastState] + " "+ lastState)
		//System.out.println("Dollaz " + (total_reward+reward))
	}
	
	def max_next_step()
	{
		def bestVal = -100
		def curVal = 0
		for (int a=0; a<8; a++){
			newState.add(a)
			if (Qtable.containsKey(newState.toString())){
				curVal = Qtable[newState.toString()]
			}
			else{
				curVal = 0
			}
			newState.remove(9)
			if (curVal>bestVal){
				bestVal = curVal
			}
		}
		return bestVal
	}
	
	def getSurroundings(){
		def neighborsList = []
		for (int dir = 0; dir<360; dir+=45){
			def p = patchRightAndAhead(dir, 0.55)
			def neighboringTurtles = other(turtlesOn(p))
			if (neighboringTurtles.size()>0) {
				neighborsList.add(neighboringTurtles.get(0).getTurtleType())
//Multiple turtles on one patch: see what happens?
				if(neighboringTurtles.size()>1){
					//System.out.println("Oh no!")
				}
			}
			else{
				neighborsList.add("0")
			}
		}
		//System.out.println(neighborsList)
		
		def minDist=1000
		def dir=0
		def allRabbits=rabbits()
		for (int a=0; a<allRabbits.size(); a++){
			if( distance(allRabbits.get(a)) < minDist) {
				minDist =  distance(allRabbits.get(a))
				dir = getDir(towards(allRabbits.get(a)))
				//System.out.println("rabbit "+ minDist + " "+towards(allRabbits.getAt(a))+ " "+dir)
			}
		}
		neighborsList.add(dir)
	//	System.out.println("shouldb " + neighborsList)
		return neighborsList
		
	}
	
	def getDir(double direction)
	{
		def dir = 0
		if (direction > 337.5 || direction <=22.5){
			return dir
		}
		else{
			def a = 22.5
			for(int b=1; b<8; b++)
			{
				if(direction >= a+(45*b)){
					//print(direction)
					dir = b
				}
			}	
		}
		return dir
	}
	
	def clear_reward()
	{
		reward = 0
	}
	
	def setQ(Map table)
	{
		Qtable = table
	}
	
	def capture(Rabbit rabbit){
		rabbit.caught = true
	}
	
	def step_on(Plant plant){
		plant.eaten = true
	}
}

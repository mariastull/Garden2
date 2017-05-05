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
	def epsilon_greedy = true
	def reward = 0
	def alpha = 0.8
	def gamma = 0.9
	def epsilon = 0.5
	def final_epsilon = 0.05
	def lastState = ""
	def neighborsList = []
	def newState = []
	def rabbitNear = 0
	def tau = 0.1
	def e = 2.71828
	
	
	//def rabbitReward = 50 
	//def plantReward = -10
	//def towardRabbitReward = 5
	Random rand = new Random()
	
	def step(){
		if (days>=5){
			epsilon = final_epsilon
		}
		neighborsList = getSurroundings()
		facexy(getXcor(), getYcor()+1)
		def heading =0
	if (alg == "Epsilon Greedy"){
		def bestDir = 0
		def bestVal = -100
		def curVal = 0
		
		//def otherVals = [0, 1, 2, 3, 4, 5, 6, 7]
		for (int a=0; a<8; a++){
			neighborsList.add(a)
			if (Qtable.containsKey(neighborsList.toString())){
				curVal = Qtable[neighborsList.toString()]
			}
			else{
				curVal = 0
			}
			//System.out.println(curVal + " "+ a)
			neighborsList.remove(11)
			if (curVal>bestVal && Math.abs(curVal-bestVal) > 0.0005){
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
		}}
//SOFTMAX
		else {
	
	// Expected reward for each action
	def vals = [:]
	def etotal = 0
	def qval = 0
	for (int a=0; a<8; a++)
	{
		neighborsList.add(a)
		if (Qtable.containsKey(neighborsList.toString())){
			qval = Qtable[neighborsList.toString()]	
		}
		else{
			qval = 0
		}
		//System.out.println(qval +" " + a)
		def toadd =  Math.pow(e, (qval/tau))
		etotal += toadd
		vals[a] = toadd
		neighborsList.remove(11)
	}
	def explore = rand.nextFloat()
	
	def probs = [:]
	
	for (int a=0; a<8; a++){
		probs[a] = vals[a]/etotal
	}
	
	def choose = rand.nextFloat()
	def probs_found = 0
	def cur =0
	for (int a=1; a<8; a++){
		probs_found += probs[a]
		if (probs_found < choose){
			heading++
		}
	}
		neighborsList.add(heading)
		right(45*heading)
		}
		//label = "hello"

		//neighborsList.add(heading)
		if(heading==neighborsList[8])
		{
			reward += toward_rabbit_reward
			//label = "bonus"
			//System.out.println("bonus " + heading)
		}
		//else{
			//label = neighborsList.toString() 
		//}
		lastState = neighborsList.toString()
		//System.out.println("heading " + getHeading())
		
		
		
		/*
		def winner = maxOneOf(neighbors()){
			count(rabbitsOn(it))
		}
		face (winner)*/
		forward(0.55)
		label = ""
		if (count(rabbitsHere()) > 0) {
			label = "Gotchya"
			reward += rabbit_reward
			def pest = oneOf(rabbitsHere())
			capture(pest)
		}
		if (count(plantsHere()) > 0) {
			label = "Oops"
			reward += plant_reward
			def blunder = oneOf(plantsHere())
			step_on(blunder)
		}
		//facexy(0,0)
		facexy(getXcor(), getYcor()+1)
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
			newState.remove(11)
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
		//System.out.println(minDist)
		if (minDist <= 2){
			rabbitNear = 1
		//	System.out.println("rabbit near")
		}
		else{
			if (minDist<=8){
				rabbitNear = 2
				//System.out.println("rabbit kinda near")
			}
			else{
				rabbitNear = 0
			}
		}
	//	System.out.println("shouldb " + neighborsList)
		neighborsList.add(rabbitNear)
		def rabbits_on_board = 0
		if (count(rabbits()) > 0){
			rabbits_on_board =1
		}
		neighborsList.add(rabbits_on_board)
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
		rabbitsCaught+=1
		rabbit.caught = true
	}
	
	def step_on(Plant plant){
		plantsSquished+=1
		plant.eaten = true
	}
}

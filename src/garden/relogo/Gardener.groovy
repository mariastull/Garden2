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
	
	//Can change these parameters
	def alpha = 0.8
	def gamma = 0.9
	def epsilon = 0.5
	def final_epsilon = 0.05
	def tau = 0.1
	
	//Variables used in program
	def Map Qtable
	def reward = 0
	def lastState = ""
	def neighborsList = []
	def newState = []
	def rabbitNear = 0
	def e = 2.71828
	Random rand = new Random()
	
	def step(){
		//Gardeners use an "exploratory" epsilon for first 5 runs, then settle into a more profitable one
		if (days>=5){
			epsilon = final_epsilon
		}
		//Faces north (to standardize mapping of surroundings and which direction is which)
		facexy(getXcor(), getYcor()+1)
		//Gets surroundings
		neighborsList = getSurroundings()
		
		def heading =0
		
		//Chooses action using epsilon greedy
		if (alg == "Epsilon Greedy"){
			def bestDir = 0
			def bestVal = -100
			def curVal = 0
			
			//Finds highest-valued action for the current state
			for (int a=0; a<8; a++){
				neighborsList.add(a)
				if (Qtable.containsKey(neighborsList.toString())){
					curVal = Qtable[neighborsList.toString()]
				}
				else{
					curVal = 0
				}
				neighborsList.remove(11)
				if (curVal>bestVal && Math.abs(curVal-bestVal) > 0.0005){
					bestVal = curVal
					bestDir = a
				}
			}
			//Explores the most profitable action (1-epsilon) % of the time and a random action epsilon % of the time
			def explore = rand.nextFloat()			
			if (explore>=epsilon){
				heading = bestDir
				neighborsList.add(bestDir)
				right(45*bestDir)
			}
			else{
				def num = rand.next(3)
				heading =  num
				neighborsList.add(num)
				right(45*num)
			}
		}
//SOFTMAX -- uses softmax to decide on an action
		else {
	
		def vals = [:]
		def etotal = 0
		def qval = 0
		//Finds value of each possible action from q-table, and calculates e^(qval/tau) for each
		for (int a=0; a<8; a++)
		{
			neighborsList.add(a)
			if (Qtable.containsKey(neighborsList.toString())){
				qval = Qtable[neighborsList.toString()]	
			}
			else{
				qval = 0
			}
			def toadd =  Math.pow(e, (qval/tau))
			etotal += toadd
			vals[a] = toadd
			neighborsList.remove(11)
		}
		
		//Finds probability of choosing each action
		def probs = [:]		
		for (int a=0; a<8; a++){
			probs[a] = vals[a]/etotal
		}
		
		//Choose each action with probability calculated
		def choose = rand.nextFloat()
		def probs_found = 0
		def cur =0
		for (int a=0; a<8; a++){
			probs_found += probs[a]
			if (probs_found < choose){
				heading++
			}
		}
		neighborsList.add(heading)
		right(45*heading)
	}
		
		//Gardener is heading toward rabbit, receives reward
		if(heading==neighborsList[8])
		{
			reward += toward_rabbit_reward
		}
		//Save what the previous state was (for q-table calculations)
		lastState = neighborsList.toString()		
		
		//Non-learning command; could add in as a comparison point
		/*
		def winner = maxOneOf(neighbors()){
			count(rabbitsOn(it))
		}
		face (winner)*/
		
		forward(0.55)
		label = ""
		
		//Catches a rabbit, receives a reward
		if (count(rabbitsHere()) > 0) {
			label = "Gotchya"
			reward += rabbit_reward
			def pest = oneOf(rabbitsHere())
			capture(pest)
		}
		//Steps on a plant, receives a reward
		if (count(plantsHere()) > 0) {
			label = "Oops"
			reward += plant_reward
			def blunder = oneOf(plantsHere())
			step_on(blunder)
		}
		//Faces north (to standardize mapping of surroundings and which direction is which)
		facexy(getXcor(), getYcor()+1)
		//Calculates current state
		newState = getSurroundings()
	}
	
	//Update q table
	def update_Q(total_reward){
		def prev_val = 0
		if (Qtable.containsKey(lastState))
		{
			prev_val = Qtable[lastState]
		} 
		def max_next_val = max_next_step()
		Qtable[lastState] = ((float) (1-alpha) * prev_val + alpha * (total_reward+reward+gamma*(max_next_val))).trunc(8)
	}
	
	//Finds maximum possible value for next step
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
	
	/*Returns a list of the objects surrounding the gardener, clockwise from north
	as well as the direction of the nearest rabbit, whether there are rabbits on the
	board, and a value indicating whether the nearest rabbit is close, kind of close,
	or further away*/
	def getSurroundings(){
		def neighborsList = []
		for (int dir = 0; dir<360; dir+=45){
			def p = patchRightAndAhead(dir, 0.55)
			def neighboringTurtles = other(turtlesOn(p))
			//Note: this simplifies surroundings by only choosing one turtle on an adjacent
			//patch, if there are multiple
			if (neighboringTurtles.size()>0) {
				neighborsList.add(neighboringTurtles.get(0).getTurtleType())
			}
			else{
				//Adds 0 if there are no actors on the adjacent patch
				neighborsList.add("0")
			}
		}

		//Finds distance and direction of nearest rabbit		
		def minDist=1000
		def dir=0
		def allRabbits=rabbits()
		for (int a=0; a<allRabbits.size(); a++){
			if( distance(allRabbits.get(a)) < minDist) {
				minDist =  distance(allRabbits.get(a))
				dir = getDir(towards(allRabbits.get(a)))
			}
		}
		neighborsList.add(dir)
		//1 indicates the rabbit is very close
		if (minDist <= 2){
			rabbitNear = 1
		}
		else{
			//2 indicates the rabbit is somewhat nearby
			if (minDist<=8){
				rabbitNear = 2
			}
			//0 indicates the rabbit is not nerby
			else{
				rabbitNear = 0
			}
		}
		neighborsList.add(rabbitNear)
		//0 if there are no rabbits, 1 if there are rabbits
		def rabbits_on_board = 0
		if (count(rabbits()) > 0){
			rabbits_on_board =1
		}
		neighborsList.add(rabbits_on_board)
		return neighborsList
		
	}
	
	//Translates between a 360 degree direction to a simplified north, northeast, east etc.
	//set of headings
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

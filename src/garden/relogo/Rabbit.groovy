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

class Rabbit extends ReLogoTurtle {

	def caught = false
	
	//Rabbits move toward a plant if they are near one, eat a plant if they are 
	//adjacent to it, and die if someone catches them
	def step(){
		if(caught){
			label = ":("
			die()
		}
		def winner = maxOneOf(neighbors()){
			count(plantsOn(it))
		}
		try{
		face (winner)
		forward(((float) rabbit_move)/ 100)}
		catch (IllegalArgumentException){}
		if (count(plantsHere()) > 0) {
			label = "Nom"
			def snack = oneOf(plantsHere())
			eat(snack)
		}
	}
	
	def eat(Plant plant){
		plant.eaten = true
	}
}

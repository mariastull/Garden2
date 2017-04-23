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

class Gardener extends ReLogoTurtle {
	def step(){
		def winner = maxOneOf(neighbors()){
			count(rabbitsOn(it))
		}
		face (winner)
		forward(0.75)
		if (count(rabbitsHere()) > 0) {
			//label = ""
			def pest = oneOf(rabbitsHere())
			capture(pest)
		}
		//else {
			//label = ""
		//}
	}
	
	def capture(Rabbit rabbit){
		rabbit.caught = true
	}
}

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

class Plant extends ReLogoTurtle {
	
	def eaten = false
	//Plants die if they have been eaten (or stepped on)
	def step(){
		if(eaten){
			die()
		}
	}
	
}

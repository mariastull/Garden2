package garden.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import garden.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	/**
	 * Add observer methods here. For example:

		@Setup
		def setup(){
			clearAll()
			createTurtles(10){
				forward(random(10))
			}
		}
		
	 *
	 * or
	 * 	
	
		@Go
		def go(){
			ask(turtles()){
				left(random(90))
				right(random(90))
				forward(random(10))
			}
		}

	 */

	@Setup
	def setup(){
		clearAll()
		setDefaultShape(Plant, "plant")
		createPlants(numPlants){
			setxy(randomXcor(), randomYcor())
			setColor(green())
		}
		setDefaultShape(Rabbit, "rabbit")
		createRabbits(numRabbits){
			setxy(randomXcor(), randomYcor())
			setColor(white())
			size = 2
		}
		setDefaultShape(Gardener, "person")
		createGardeners(numGardeners){
			setxy(randomXcor(), randomYcor())
			setColor(blue())
			size = 2
		}
		ask(patches())
		{setPcolor(22)
		}
	}
	
	@Go
	def go(){
		ask(rabbits()){
			step()
		}
		ask(plants()){
			step()
		}
		ask(gardeners()){
			step()
		}
	}
	
	def remainingPlants(){
		count(plants())
	}
	
}
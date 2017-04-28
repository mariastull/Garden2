package garden.relogo
//package uchicago.src.sim.util.Random

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import garden.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	//Random.createUniform();
	
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
		for (int a= -10; a< 11; a+=5)
		{
			for (int b=minPycor; b<maxPycor; b++)
			{
				if (b==0 || b==-8 || b==8){
					b+=1
				}
				
				createPlants(1){
					setxy(a,b)
					setColor(green())
				}
			}
		}
		//createPlants(numPlants){
			//setxy(randomXcor(), randomYcor())
			//setColor(green())
		//}
		setDefaultShape(Rabbit, "rabbit")
		createRabbits(1){
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
		//int plantsLeft = count(plants())
	}
	
	@Go
	def go(){
		int x = (int) Math.floor(Math.random() * 501)
		if (x<numRabbits){
			createRabbits(1){
				setxy(randomXcor(), randomYcor())
				setColor(white())
				size = 2
			}
		}
		ask(rabbits()){
			step()
		}
		ask(plants()){
			step()
		}
		ask(gardeners()){
			step()
		}
		//plantsLeft=count(plants())
	}
	
	
	
	def plantsLeft(){
		count(plants())
		//System.out.println(count(plants()))
		//return plantsLeft
		//System.out.print(remainingPlants)
	}
	
}
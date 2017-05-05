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
	def fout = new PrintWriter("output.txt")
	def Random randomizer = new Random()
	//
	//writer.println("The second line");
	//writer.close();
	
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
		def Qtable = [:]
		setDefaultShape(Gardener, "person")
		createGardeners(numGardeners){
			setxy(randomXcor(), randomYcor())
			setColor(blue())
			size = 1
			setQ(Qtable)
		}
		ask(patches())
		{setPcolor(22)
		}
		//int plantsLeft = count(plants())
	}
	
	@Go
	def go(){
		if(days==0 && time==0){
			fout.println("alg: " + alg + "\nRabbit Reward: " + rabbit_reward + "\nPlant Reward: " + plant_reward + "\nToward Rabbit: " + toward_rabbit_reward + "\nRabbit Movement: " + rabbit_move + "\nGardeners: " + numGardeners)
		}
		if (time==1500){
			fout.println(days + "," + plantsLeft() + "," + rabbitsCaught + "," + plantsSquished)
			rabbitsCaught = 0
			plantsSquished = 0
			days +=1
			if (days==calendar){
				fout.close()
				pause()
			}
			else{
				ask(rabbits()){
					die()
				}
				ask(plants()){
					die()
				}
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
				createRabbits(1){
					setxy(randomXcor(), randomYcor())
					setColor(white())
					size = 2
				}
				
			}
			time = 0
		}
		else
		{
			int x = randomizer.next(11)
			if (x<numRabbits){
				createRabbits(1){
					setxy(randomXcor(), randomYcor())
					setColor(white())
					size = 2
				}
			}
			try{
			ask(rabbits()){
				step()
			}}
			catch(IllegalArgumentException){}
			ask(plants()){
				step()
			}
			ask(gardeners()){
				step()
			}
			def total_reward = (prev_plants - count(plants())) * 0
			ask(gardeners()){
				update_Q(total_reward)
				clear_reward()
			}
			prev_plants = count(plants())
			time += 1
		}
	}
	
	def days(){
		return days+1
	}
	
	def plantsLeft(){
		count(plants())
		//System.out.println(count(plants()))
		//return plantsLeft
		//System.out.print(remainingPlants)
	}
	
}
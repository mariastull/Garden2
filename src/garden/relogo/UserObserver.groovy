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

	def fout = new PrintWriter("output.txt")
	def Random randomizer = new Random()
	
	

	@Setup
	def setup(){
		clearAll()
		//Create plants in rows
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
		//Create one rabbit, to start with
		setDefaultShape(Rabbit, "rabbit")
		createRabbits(1){
			setxy(randomXcor(), randomYcor())
			setColor(white())
			size = 2
		}
		//Define q-table; create gardeners and pass q-table to them
		def Qtable = [:]
		setDefaultShape(Gardener, "person")
		createGardeners(numGardeners){
			setxy(randomXcor(), randomYcor())
			setColor(blue())
			size = 1
			setQ(Qtable)
		}
		//Set the background to brown
		ask(patches())
		{setPcolor(22)
		}
	}
	
	@Go
	def go(){
		//At first tick of first day, output settings for the current run
		if(days==0 && time==0){
			fout.println("alg: " + alg + "\nRabbit Reward: " + rabbit_reward + "\nPlant Reward: " + plant_reward + "\nToward Rabbit: " + toward_rabbit_reward + "\nRabbit Movement: " + rabbit_move + "\nGardeners: " + numGardeners)
		}
		//At the end of a day, reset
		if (time==1500){
			//Output data about current run and reset variables
			fout.println(days + "," + plantsLeft() + "," + rabbitsCaught + "," + plantsSquished)
			rabbitsCaught = 0
			plantsSquished = 0
			days +=1
			if (days==calendar){
				fout.close()
				pause()
			}
			//Reset all plants; remove all rabbits and add one new one
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
			//Creates a rabbit occasionally (determined by frequency slider)
			int x = randomizer.next(11)
			if (x<numRabbits){
				createRabbits(1){
					setxy(randomXcor(), randomYcor())
					setColor(white())
					size = 2
				}
			}
			//Move all agents
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
			//Currently not doing anything; could punish gardeners for having rabbits on the board
			def total_reward = (prev_plants - count(plants())) * 0
			//Update q-table
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
	}
	
}
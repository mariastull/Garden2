package garden;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.ast.Diffusible;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;
import repast.simphony.space.SpatialException;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoPatch extends BasePatch{

	/**
	 * Sprouts (makes) a number of new gardeners and then executes a set of commands on the
	 * created gardeners.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created gardeners
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> sproutGardeners(int number, Closure closure) {
		AgentSet<garden.relogo.Gardener> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Gardener");
		for (Turtle t : createResult){
			if (t instanceof garden.relogo.Gardener){
				result.add((garden.relogo.Gardener)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new gardeners and then executes a set of commands on the
	 * created gardeners.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created gardeners
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> sproutGardeners(int number) {
		return sproutGardeners(number,null);
	}

	/**
	 * Returns an agentset of gardeners from the patch of the caller.
	 * 
	 * @return agentset of gardeners from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> gardenersHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<garden.relogo.Gardener> result = new AgentSet<garden.relogo.Gardener>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"gardener")){
			if (t instanceof garden.relogo.Gardener)
			result.add((garden.relogo.Gardener)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of gardeners on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of gardeners at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> gardenersAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<garden.relogo.Gardener> result = new AgentSet<garden.relogo.Gardener>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"gardener")){
			if (t instanceof garden.relogo.Gardener)
			result.add((garden.relogo.Gardener)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<garden.relogo.Gardener>();
		}
	}

	/**
	 * Returns an agentset of gardeners on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of gardeners on patch p
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> gardenersOn(Patch p){
		AgentSet<garden.relogo.Gardener> result = new AgentSet<garden.relogo.Gardener>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"gardener")){
			if (t instanceof garden.relogo.Gardener)
			result.add((garden.relogo.Gardener)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of gardeners on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of gardeners on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> gardenersOn(Turtle t){
		AgentSet<garden.relogo.Gardener> result = new AgentSet<garden.relogo.Gardener>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"gardener")){
			if (tt instanceof garden.relogo.Gardener)
			result.add((garden.relogo.Gardener)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of gardeners on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of gardeners on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> gardenersOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<garden.relogo.Gardener>();
		}

		Set<garden.relogo.Gardener> total = new HashSet<garden.relogo.Gardener>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(gardenersOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(gardenersOn(p));
				}
			}
		}
		return new AgentSet<garden.relogo.Gardener>(total);
	}

	/**
	 * Queries if object is a gardener.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a gardener
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public boolean isGardenerQ(Object o){
		return (o instanceof garden.relogo.Gardener);
	}

	/**
	 * Returns an agentset containing all gardeners.
	 * 
	 * @return agentset of all gardeners
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public AgentSet<garden.relogo.Gardener> gardeners(){
		AgentSet<garden.relogo.Gardener> a = new AgentSet<garden.relogo.Gardener>();
		for (Object e : this.getMyObserver().getContext().getObjects(garden.relogo.Gardener.class)) {
			if (e instanceof garden.relogo.Gardener){
				a.add((garden.relogo.Gardener)e);
			}
		}
		return a;
	}

	/**
	 * Returns the gardener with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Gardener")
	public garden.relogo.Gardener gardener(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof garden.relogo.Gardener)
			return (garden.relogo.Gardener) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new plants and then executes a set of commands on the
	 * created plants.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created plants
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> sproutPlants(int number, Closure closure) {
		AgentSet<garden.relogo.Plant> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Plant");
		for (Turtle t : createResult){
			if (t instanceof garden.relogo.Plant){
				result.add((garden.relogo.Plant)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new plants and then executes a set of commands on the
	 * created plants.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created plants
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> sproutPlants(int number) {
		return sproutPlants(number,null);
	}

	/**
	 * Returns an agentset of plants from the patch of the caller.
	 * 
	 * @return agentset of plants from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> plantsHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<garden.relogo.Plant> result = new AgentSet<garden.relogo.Plant>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"plant")){
			if (t instanceof garden.relogo.Plant)
			result.add((garden.relogo.Plant)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of plants on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of plants at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> plantsAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<garden.relogo.Plant> result = new AgentSet<garden.relogo.Plant>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"plant")){
			if (t instanceof garden.relogo.Plant)
			result.add((garden.relogo.Plant)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<garden.relogo.Plant>();
		}
	}

	/**
	 * Returns an agentset of plants on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of plants on patch p
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> plantsOn(Patch p){
		AgentSet<garden.relogo.Plant> result = new AgentSet<garden.relogo.Plant>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"plant")){
			if (t instanceof garden.relogo.Plant)
			result.add((garden.relogo.Plant)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of plants on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of plants on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> plantsOn(Turtle t){
		AgentSet<garden.relogo.Plant> result = new AgentSet<garden.relogo.Plant>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"plant")){
			if (tt instanceof garden.relogo.Plant)
			result.add((garden.relogo.Plant)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of plants on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of plants on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> plantsOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<garden.relogo.Plant>();
		}

		Set<garden.relogo.Plant> total = new HashSet<garden.relogo.Plant>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(plantsOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(plantsOn(p));
				}
			}
		}
		return new AgentSet<garden.relogo.Plant>(total);
	}

	/**
	 * Queries if object is a plant.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a plant
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public boolean isPlantQ(Object o){
		return (o instanceof garden.relogo.Plant);
	}

	/**
	 * Returns an agentset containing all plants.
	 * 
	 * @return agentset of all plants
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public AgentSet<garden.relogo.Plant> plants(){
		AgentSet<garden.relogo.Plant> a = new AgentSet<garden.relogo.Plant>();
		for (Object e : this.getMyObserver().getContext().getObjects(garden.relogo.Plant.class)) {
			if (e instanceof garden.relogo.Plant){
				a.add((garden.relogo.Plant)e);
			}
		}
		return a;
	}

	/**
	 * Returns the plant with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Plant")
	public garden.relogo.Plant plant(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof garden.relogo.Plant)
			return (garden.relogo.Plant) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new rabbits and then executes a set of commands on the
	 * created rabbits.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created rabbits
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> sproutRabbits(int number, Closure closure) {
		AgentSet<garden.relogo.Rabbit> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Rabbit");
		for (Turtle t : createResult){
			if (t instanceof garden.relogo.Rabbit){
				result.add((garden.relogo.Rabbit)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new rabbits and then executes a set of commands on the
	 * created rabbits.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created rabbits
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> sproutRabbits(int number) {
		return sproutRabbits(number,null);
	}

	/**
	 * Returns an agentset of rabbits from the patch of the caller.
	 * 
	 * @return agentset of rabbits from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> rabbitsHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<garden.relogo.Rabbit> result = new AgentSet<garden.relogo.Rabbit>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"rabbit")){
			if (t instanceof garden.relogo.Rabbit)
			result.add((garden.relogo.Rabbit)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of rabbits on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of rabbits at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> rabbitsAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<garden.relogo.Rabbit> result = new AgentSet<garden.relogo.Rabbit>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"rabbit")){
			if (t instanceof garden.relogo.Rabbit)
			result.add((garden.relogo.Rabbit)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<garden.relogo.Rabbit>();
		}
	}

	/**
	 * Returns an agentset of rabbits on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of rabbits on patch p
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> rabbitsOn(Patch p){
		AgentSet<garden.relogo.Rabbit> result = new AgentSet<garden.relogo.Rabbit>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"rabbit")){
			if (t instanceof garden.relogo.Rabbit)
			result.add((garden.relogo.Rabbit)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of rabbits on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of rabbits on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> rabbitsOn(Turtle t){
		AgentSet<garden.relogo.Rabbit> result = new AgentSet<garden.relogo.Rabbit>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"rabbit")){
			if (tt instanceof garden.relogo.Rabbit)
			result.add((garden.relogo.Rabbit)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of rabbits on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of rabbits on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> rabbitsOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<garden.relogo.Rabbit>();
		}

		Set<garden.relogo.Rabbit> total = new HashSet<garden.relogo.Rabbit>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(rabbitsOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(rabbitsOn(p));
				}
			}
		}
		return new AgentSet<garden.relogo.Rabbit>(total);
	}

	/**
	 * Queries if object is a rabbit.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a rabbit
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public boolean isRabbitQ(Object o){
		return (o instanceof garden.relogo.Rabbit);
	}

	/**
	 * Returns an agentset containing all rabbits.
	 * 
	 * @return agentset of all rabbits
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public AgentSet<garden.relogo.Rabbit> rabbits(){
		AgentSet<garden.relogo.Rabbit> a = new AgentSet<garden.relogo.Rabbit>();
		for (Object e : this.getMyObserver().getContext().getObjects(garden.relogo.Rabbit.class)) {
			if (e instanceof garden.relogo.Rabbit){
				a.add((garden.relogo.Rabbit)e);
			}
		}
		return a;
	}

	/**
	 * Returns the rabbit with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.Rabbit")
	public garden.relogo.Rabbit rabbit(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof garden.relogo.Rabbit)
			return (garden.relogo.Rabbit) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> sproutUserTurtles(int number, Closure closure) {
		AgentSet<garden.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof garden.relogo.UserTurtle){
				result.add((garden.relogo.UserTurtle)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> sproutUserTurtles(int number) {
		return sproutUserTurtles(number,null);
	}

	/**
	 * Returns an agentset of userTurtles from the patch of the caller.
	 * 
	 * @return agentset of userTurtles from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> userTurtlesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<garden.relogo.UserTurtle> result = new AgentSet<garden.relogo.UserTurtle>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof garden.relogo.UserTurtle)
			result.add((garden.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of userTurtles on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of userTurtles at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> userTurtlesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<garden.relogo.UserTurtle> result = new AgentSet<garden.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof garden.relogo.UserTurtle)
			result.add((garden.relogo.UserTurtle)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<garden.relogo.UserTurtle>();
		}
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<garden.relogo.UserTurtle> result = new AgentSet<garden.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"userTurtle")){
			if (t instanceof garden.relogo.UserTurtle)
			result.add((garden.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<garden.relogo.UserTurtle> result = new AgentSet<garden.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"userTurtle")){
			if (tt instanceof garden.relogo.UserTurtle)
			result.add((garden.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<garden.relogo.UserTurtle>();
		}

		Set<garden.relogo.UserTurtle> total = new HashSet<garden.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<garden.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof garden.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public AgentSet<garden.relogo.UserTurtle> userTurtles(){
		AgentSet<garden.relogo.UserTurtle> a = new AgentSet<garden.relogo.UserTurtle>();
		for (Object e : this.getMyObserver().getContext().getObjects(garden.relogo.UserTurtle.class)) {
			if (e instanceof garden.relogo.UserTurtle){
				a.add((garden.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserTurtle")
	public garden.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof garden.relogo.UserTurtle)
			return (garden.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof garden.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserLink")
	public AgentSet<garden.relogo.UserLink> userLinks(){
		AgentSet<garden.relogo.UserLink> a = new AgentSet<garden.relogo.UserLink>();
		for (Object e : this.getMyObserver().getContext().getObjects(garden.relogo.UserLink.class)) {
			if (e instanceof garden.relogo.UserLink){
				a.add((garden.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserLink")
	public garden.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (garden.relogo.UserLink)(this.getMyObserver().getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("garden.relogo.UserLink")
	public garden.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Returns the value of the global variable prev_plants.
	 *
	 * @return the value of the global variable prev_plants
	 */
	@ReLogoBuilderGeneratedFor("global: prev_plants")
	public Object getPrev_plants(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("prev_plants");
	}

	/**
	 * Sets the value of the global variable prev_plants.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: prev_plants")
	public void setPrev_plants(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("prev_plants",value);
	}

	/**
	 * Returns the value of the global variable time.
	 *
	 * @return the value of the global variable time
	 */
	@ReLogoBuilderGeneratedFor("global: time")
	public Object getTime(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("time");
	}

	/**
	 * Sets the value of the global variable time.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: time")
	public void setTime(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("time",value);
	}

	/**
	 * Returns the value of the global variable days.
	 *
	 * @return the value of the global variable days
	 */
	@ReLogoBuilderGeneratedFor("global: days")
	public Object getDays(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("days");
	}

	/**
	 * Sets the value of the global variable days.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: days")
	public void setDays(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("days",value);
	}

	/**
	 * Returns the value of the global variable rabbitsCaught.
	 *
	 * @return the value of the global variable rabbitsCaught
	 */
	@ReLogoBuilderGeneratedFor("global: rabbitsCaught")
	public Object getRabbitsCaught(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("rabbitsCaught");
	}

	/**
	 * Sets the value of the global variable rabbitsCaught.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: rabbitsCaught")
	public void setRabbitsCaught(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("rabbitsCaught",value);
	}

	/**
	 * Returns the value of the global variable plantsSquished.
	 *
	 * @return the value of the global variable plantsSquished
	 */
	@ReLogoBuilderGeneratedFor("global: plantsSquished")
	public Object getPlantsSquished(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("plantsSquished");
	}

	/**
	 * Sets the value of the global variable plantsSquished.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: plantsSquished")
	public void setPlantsSquished(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("plantsSquished",value);
	}

	/**
	 * Returns the value of the global variable alg.
	 *
	 * @return the value of the global variable alg
	 */
	@ReLogoBuilderGeneratedFor("global: alg")
	public Object getAlg(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("alg");
	}

	/**
	 * Sets the value of the global variable alg.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: alg")
	public void setAlg(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("alg",value);
	}

	/**
	 * Returns the value of the global variable rabbit_reward.
	 *
	 * @return the value of the global variable rabbit_reward
	 */
	@ReLogoBuilderGeneratedFor("global: rabbit_reward")
	public Object getRabbit_reward(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("rabbit_reward");
	}

	/**
	 * Sets the value of the global variable rabbit_reward.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: rabbit_reward")
	public void setRabbit_reward(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("rabbit_reward",value);
	}

	/**
	 * Returns the value of the global variable plant_reward.
	 *
	 * @return the value of the global variable plant_reward
	 */
	@ReLogoBuilderGeneratedFor("global: plant_reward")
	public Object getPlant_reward(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("plant_reward");
	}

	/**
	 * Sets the value of the global variable plant_reward.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: plant_reward")
	public void setPlant_reward(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("plant_reward",value);
	}

	/**
	 * Returns the value of the global variable toward_rabbit_reward.
	 *
	 * @return the value of the global variable toward_rabbit_reward
	 */
	@ReLogoBuilderGeneratedFor("global: toward_rabbit_reward")
	public Object getToward_rabbit_reward(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("toward_rabbit_reward");
	}

	/**
	 * Sets the value of the global variable toward_rabbit_reward.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: toward_rabbit_reward")
	public void setToward_rabbit_reward(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("toward_rabbit_reward",value);
	}

	/**
	 * Returns the value of the global variable rabbit_move.
	 *
	 * @return the value of the global variable rabbit_move
	 */
	@ReLogoBuilderGeneratedFor("global: rabbit_move")
	public Object getRabbit_move(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("rabbit_move");
	}

	/**
	 * Sets the value of the global variable rabbit_move.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: rabbit_move")
	public void setRabbit_move(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("rabbit_move",value);
	}

	/**
	 * Returns the value of the global variable numGardeners.
	 *
	 * @return the value of the global variable numGardeners
	 */
	@ReLogoBuilderGeneratedFor("global: numGardeners")
	public Object getNumGardeners(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("numGardeners");
	}

	/**
	 * Sets the value of the global variable numGardeners.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: numGardeners")
	public void setNumGardeners(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("numGardeners",value);
	}

	/**
	 * Returns the value of the global variable calendar.
	 *
	 * @return the value of the global variable calendar
	 */
	@ReLogoBuilderGeneratedFor("global: calendar")
	public Object getCalendar(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("calendar");
	}

	/**
	 * Sets the value of the global variable calendar.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: calendar")
	public void setCalendar(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("calendar",value);
	}

	/**
	 * Returns the value of the global variable numRabbits.
	 *
	 * @return the value of the global variable numRabbits
	 */
	@ReLogoBuilderGeneratedFor("global: numRabbits")
	public Object getNumRabbits(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("numRabbits");
	}

	/**
	 * Sets the value of the global variable numRabbits.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: numRabbits")
	public void setNumRabbits(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("numRabbits",value);
	}


}
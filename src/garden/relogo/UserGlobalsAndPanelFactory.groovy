package garden.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	public void addGlobalsAndPanelComponents(){
		
		/**
		 * Place custom panels and globals below, for example:
		 * 
	        addGlobal("globalVariable1")	// Globally accessible variable ( variable name)
	        // Slider with label ( variable name, slider label, minimum value, increment, maximum value, initial value )
	        addSliderWL("sliderVariable", "Slider Variable", 0, 1, 10, 5)
	        // Slider without label ( variable name, minimum value, increment, maximum value, initial value )
	        addSlider("sliderVariable2", 0.2, 0.1, 0.8, 0.5)
	        // Chooser with label  ( variable name, chooser label, list of choices , zero-based index of initial value )
	        addChooserWL("chooserVariable", "Chooser Variable", ["yes","no","maybe"], 2)
	        // Chooser without label  ( variable name, list of choices , zero-based index of initial value )
	        addChooser("chooserVariable2", [1, 66, "seven"], 0)
	        // State change button (method name in observer)
	        addStateChangeButton("change")
	        // State change button with label (method name in observer, label)
	        addStateChangeButtonWL("changeSomething","Change Something")
	        
		 */
		//Global variables, and sliders
		addGlobal("prev_plants", 145)	//number of plants in previous timestep
		addGlobal("time", 0)	//tick for the current day
		addGlobal("days", 0)	//number of days total
		addGlobal("rabbitsCaught", 0)	//number of rabbits caught by gardeners
		addGlobal("plantsSquished", 0)	//number of plants squished by gardeners
		addMonitorWL("plantsLeft", "Remaining Plants", 1)	//number of plants remaining in environment
		addMonitorWL("days", "Day", 20)	//which day is it in the current run

		addChooser("alg", ["Epsilon Greedy", "Softmax"], 0)
		addSliderWL("rabbit_reward", "Reward: Catching Rabbit", -50, 1, 50, 10)
		addSliderWL("plant_reward", "Reward: Step on Plant", -50, 1, 50, -5)
		addSliderWL("toward_rabbit_reward", "Reward: Go Toward Rabbit", -50, 1, 50, 1)
		addSliderWL("rabbit_move", "Rabbit Movement", 0, 1, 50, 20)
		addSliderWL("numGardeners", "Number of Gardeners", 1, 1, 10, 5)
		addSliderWL("calendar", "Number of Days", 0, 25, 1000, 200)
		addSliderWL("numRabbits", "Rabbit frequency", 1, 1, 50, 5)
		

	}
}
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
		//def static Q = [:]
		//Q.put("apple": 3)
		
		//addGlobal("Q", [:])
		//Q["apple"] = 0.3
		addGlobal("prev_plants", 145)
		addGlobal("time", 0)
		addGlobal("days", 0)
		//addGlobal("calendar", 300)
		addGlobal("rabbitsCaught", 0)
		addGlobal("plantsSquished", 0)
		//def prev_plants = 145
		//addSliderWL("numPlants", "Number of Plants", 1, 1, 100, 50)
		addMonitorWL("plantsLeft", "Remaining Plants", 1)
		addMonitorWL("days", "Day", 20)

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
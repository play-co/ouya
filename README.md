# Game Closure DevKit Plugin: OUYA

The OUYA plugin provides support for the OUYA controller.

## Usage

Install the plugin with `basil install ouya`.

Include it in the `manifest.json` file under the "addons" section for your game:

~~~
"addons": [
	"ouya"
],
~~~

To use the OUYA controller in your game, install the plugin at the top of Application.js, like so:

~~~
import plugins.ouya.install;
~~~

By default `ouya.onControllerInput` will log out input events. You can override it like this:

~~~
ouya.onControllerInput = function(evt) {
	
	// Do something with evt. It has these properties:
	//  - controller (integer - source of event)
	//  - code (integer - button pressed, one of: BUTTON or AXIS constants)
	//  - action (integer - type of input, one of: ACTION_ANALOG, ACTION_DOWN, or ACTION_UP)
	//  - analogValue (float - value of analog motion)
	
	logger.log("input received from controller", evt.controller);
	if (evt.action) { // digital event -- ACTION_DOWN or ACTION_UP
		logger.log("digital input - keyCode", evt.code, "with action",
			evt.action == ouya.ACTION_DOWN ? "DOWN" : "UP");
	} else { // analog event -- ACTION_ANALOG
		logger.log("analog input - keyCode", evt.code, "with value", evt.analogValue);
	}
	
};
~~~

## Testing

To test for successful integration, build your game:

~~~
basil debug native-android --clean --open
~~~

If the OUYA plugin is hooked up properly, you'll be able to use the OUYA controller.
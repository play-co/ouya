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

To use the OUYA controller in your game, import the JavaScript component:

~~~
import plugins.ouya.ouya as ouya;
~~~

By default `ouya.onDigitalInput` and `ouya.onAnalogInput` will log out input events. You can override them like this:

~~~
ouya.onDigitalInput = function(evt) {
	
	// Do something with evt. It has these properties:
	//  - player (integer - source of event)
	//  - code (integer - button pressed, one of: ouya.BUTTON constants)
	//  - action (integer - type of input, one of: ouya.ACTION.DOWN, or ouya.ACTION.UP)
	
	logger.log("received digital input from player", evt.player, "with keyCode",
		evt.code, "and action", evt.action == ouya.ACTION.DOWN ? "DOWN": "UP");
};

ouya.onAnalogInput = function(evt) {
	
	// Do something with evt. It has these properties:
	//  - player (integer - source of event)
	//  - lsx, lsy (left stick position)
	//  - rsx, rsy (right stick position)
	//  - l2, r2 (left and right trigger values)
	
	logger.log("received analog input from player", evt.player, ": lsx", evt.lsx, ", lsy",
		evt.lsy, ", rsx", evt.rsx, ", rsy", evt.rsy, ", l2", evt.l2, ", r2", evt.r2);
	
};
~~~

## Testing

To test for successful integration, build your game:

~~~
basil debug native-android --clean --open
~~~

If the OUYA plugin is hooked up properly, you'll be able to use the OUYA controller.

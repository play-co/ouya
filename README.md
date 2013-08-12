# Game Closure DevKit Plugin: OUYA

The OUYA plugin provides support for the OUYA controller.

## OUYA Hardware Setup

To install and test games on the OUYA you will need to add a new Vendor ID to your .android/adb_usb.ini file as described in the OUYA documentation and also the [DevKit Android troubleshooting guide](http://docs.gameclosure.com/native/android-troubleshooting.html).

The Vendor ID for the OUYA is 0x2836

Then you can connect a micro-USB data cable to the OUYA just like an Android cellphone and run `basil debug native-android --clean --install` to build and install the game as normal.

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

To receive input events from the OUYA controller, override the `ouya.onDigitalInput`
and `ouya.onAnalogInput` event handlers:

~~~
ouya.onDigitalInput = function(evt) {
	logger.log("received digital input from player", evt.player, "with keyCode",
		evt.code, "and action", evt.action == ouya.ACTION.DOWN ? "DOWN": "UP");
};

ouya.onAnalogInput = function(evt) {
	logger.log("received analog input from player", evt.player, ": lsx", evt.lsx, ", lsy",
		evt.lsy, ", rsx", evt.rsx, ", rsy", evt.rsy, ", l2", evt.l2, ", r2", evt.r2);
};
~~~

For an example application that uses the OUYA controller for input, check out the
Platformer example project under `devkit/projects/platformer` and also on
[the Platformer github page](http://github.com/gameclosure/platformer)

When you hold down an "O", "U", "Y", or "A" button on the OUYA controller, it will
report an ouya.ACTION.DOWN event repeatedly until the key is released, so you may
want to write code like this to edge-trigger on the key presses:

~~~
ouya.onDigitalInput = function(evt) {
	// If "O" button is pressed or released,
	if (evt.code == ouya.BUTTON.O && this.lastAction != evt.action) {
		this.lastAction = evt.action;

		// If button was just pressed,
		if (evt.action == ouya.ACTION.DOWN) {
			this.onJump();
		} else {
			this.onJumpDone();
		}
	}
}.bind(this);
~~~

The OUYA analog sticks produce x,y values in the range of -1..1, with 0 being
the center neutral position.  The following code demonstrates how to respond to
an analog stick swipe:

~~~
ouya.onAnalogInput = function(evt) {
	// Get left analog stick x,y
	var x = evt.lsx, y = evt.lsy;

	// If the stick is far from center,
	if (x * x + y * y > 0.5 * 0.5) {
		this.onDive();
	}
}.bind(this);
~~~

# ouya object

## Events

### ouya.onDigitalInput (evt)

+ `callback {function}` ---Set to your callback function.
			The first argument will be the event information object.

The event object schema is

+ `player` {integer} : Which remote controller is generating the event.

+ `code` {integer} : Enumeration with values:

	+ ouya.BUTTON.O
	+ ouya.BUTTON.U
	+ ouya.BUTTON.Y
	+ ouya.BUTTON.A
	+ ouya.BUTTON.MENU
	+ ouya.BUTTON.DPAD.UP
	+ ouya.BUTTON.DPAD.DOWN
	+ ouya.BUTTON.DPAD.RIGHT
	+ ouya.BUTTON.DPAD.LEFT
	+ ouya.BUTTON.L[1]
	+ ouya.BUTTON.L[2]
	+ ouya.BUTTON.L[3]
	+ ouya.BUTTON.R[1]
	+ ouya.BUTTON.R[2]
	+ ouya.BUTTON.R[3]

+ `action` {integer} : Enumeration with values:

	+ ouya.ACTION.DOWN = Button is pressed down (sent repeatedly)
	+ ouya.ACTION.UP = Button is released

See the [main OUYA controller docs](https://devs.ouya.tv/developers/docs/controllers) for more information.

### ouya.onAnalogInput (evt)

+ `callback {function}` ---Set to your callback function.
			The first argument will be the event information object.

The event object schema is

+ `player` {integer} : Which remote controller is generating the event.

+ `lsx` {float} : Left stick x position [-1..0..1]
+ `lsy` {float} : Left stick y position [-1..0..1]

+ `rsx` {float} : Right stick x position [-1..0..1]
+ `rsy` {float} : Right stick y position [-1..0..1]

+ `l2` {float} : Left top button 2 position
+ `r2` {float} : Right top button 2 position

See the [main OUYA controller docs](https://devs.ouya.tv/developers/docs/controllers) for more information.


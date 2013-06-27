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

## Testing

To test for successful integration, build your game:

~~~
basil debug native-android --clean --open
~~~

If the OUYA plugin is hooked up properly, you'll be able to use the OUYA controller.
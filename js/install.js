GLOBAL.ouya = {
	onControllerInput: function(evt) {
		logger.log("got OUYA event", JSON.stringify(evt));
	},
	ACTION_ANALOG: 0,
	ACTION_DOWN: 1,
	ACTION_UP: 2
};

NATIVE.events.registerHandler('ouya', function(evt) {
	ouya.onControllerInput(evt);
});
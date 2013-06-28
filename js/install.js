GLOBAL.ouya = {
	onControllerInput = function(evt) {
		logger.log("got OUYA event", JSON.stringify(evt));
	}
};

NATIVE.events.registerHandler('ouya', function(evt) {
	ouya.onControllerInput(evt);
});
GLOBAL.ouya = {
	onControllerInput = function(evt) {
		logger.log("got OUYA event", JSON.stringify(evt));
	}
};

NATIVE.events.registerHandler('ouya', bind(ouya, 'onControllerInput'));
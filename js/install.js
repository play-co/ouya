GLOBAL.ouya = {
	onControllerInput: function(evt) {
		logger.log("got OUYA event", JSON.stringify(evt));
	},
	ACTION_ANALOG: 0,
	ACTION_DOWN: 1,
	ACTION_UP: 2,
	BUTTON_O: 96,
	BUTTON_U: 99,
	BUTTON_Y: 100,
	BUTTON_A: 97,
	BUTTON_MENU: 82,
	BUTTON_DPAD_UP: 19,
	BUTTON_DPAD_DOWN: 20,
	BUTTON_DPAD_RIGHT: 22,
	BUTTON_DPAD_LEFT: 21,
	BUTTON_L1: 104,
	BUTTON_L2: 102,
	BUTTON_L3: 106,
	BUTTON_R1: 105,
	BUTTON_R2: 103,
	BUTTON_R3: 107,
	AXIS_LEFT_X: 0,
	AXIS_LEFT_Y: 1,
	AXIS_LEFT_TRIGGER: 2,
	AXIS_RIGHT_X: 3,
	AXIS_RIGHT_Y: 4,
	AXIS_RIGHT_TRIGGER: 5
};

NATIVE.events.registerHandler('ouya', function(evt) {
	ouya.onControllerInput(evt);
});
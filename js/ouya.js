var Ouya = Class(function () {
	this.onDigitalInput = function(evt) {
		logger.log("got OUYA digital event", JSON.stringify(evt));
	};

	this.onAnalogInput = function(evt) {
		logger.log("got OUYA analog event", JSON.stringify(evt));
	},

	this.ACTION = {
		DOWN: 1,
		UP: 2
	};

	this.BUTTON = {
		O: 96,
		U: 99,
		Y: 100,
		A: 97,
		MENU: 82,
		DPAD: {
			UP: 19,
			DOWN: 20,
			RIGHT: 22,
			LEFT: 21
		},
		L1: 104,
		L2: 102,
		L3: 106,
		R1: 105,
		R2: 103,
		R3: 107
	};

	this.AXIS = {
		LEFT: {
			X: 0,
			Y: 1,
			TRIGGER: 2
		},
		RIGHT: {
			X: 3,
			Y: 4,
			TRIGGER: 5
		}
	};

	this.init = function() {
		NATIVE.events.registerHandler('ouyakey', function(evt) {
			this.onDigitalInput(evt);
		});

		NATIVE.events.registerHandler('ouyamotion', function(evt) {
			this.onAnalogInput(evt);
		});
	}
});

exports = new Ouya();


var Ouya = Class(function () {
	this.onDigitalInput = function(evt) {
		logger.log("got OUYA digital event", JSON.stringify(evt));
	};

	this.onAnalogInput = function(evt) {
		logger.log("got OUYA analog event", JSON.stringify(evt));
	};

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
		L: [
			0,
			104,
			102,
			106
		],
		R: [
			0,
			105,
			103,
			107
		]
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
		logger.log("{ouya} Installing key listeners");

		NATIVE.events.registerHandler('ouyakey', function(evt) {
			logger.log("CAT: OUYAKEY", evt);
			exports.onDigitalInput(evt);
		});

		NATIVE.events.registerHandler('ouyamotion', function(evt) {
			logger.log("CAT: OUYAMOTION", evt);
			exports.onAnalogInput(evt);
		});
	}
});

exports = new Ouya();



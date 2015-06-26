package cjug.firmata4j.demo
import java.io.IOException;

import org.firmata4j.IODevice
import org.firmata4j.Pin
import org.firmata4j.firmata.FirmataDevice

import static org.firmata4j.Pin.Mode.INPUT
import static org.firmata4j.Pin.Mode.OUTPUT

class HourGlass {
	private IODevice device = null
	
	public HourGlass(String deviceName) {
		this(new HourGlass(new FirmataDevice(deviceName)))
	}
	
	public HourGlass(IODevice device) {
		this.device = device
		device.start() // initiate communication to the device
		device.ensureInitializationIsDone() // wait for initialization is done
	}
	
	public stop() {
		device.stop()
	}

	public void run() {
		def led = 2
		def switchPin = 12
		def previousTime = 0L; // store the last time an LED was updated
		def switchState = 0; // the current switch state
		def prevSwitchState = 0; // the previous switch state
		final interval = 1000L; // interval at which to light the next LED
		
		while (true) {
			def currentTime = new Date().getTime()

			if (currentTime - previousTime > interval) {
				previousTime = currentTime;

				// Turn the LED on
				pinOn(led)

				// go to the next led
				led++;

				if (led == 7) {
					// timer expired, alert or whatever
					break
				}
			}

			// read the switch value
			switchState = pinRead(switchPin)

			// if the switch has changed
			if (switchState != prevSwitchState) {
				// turn all the LEDs low
				(2..8).each { pinOff(it) }

				// reset the LED variable to the first one
				led = 2;

				//reset the timer
				previousTime = currentTime;
			}
			// set the previous switch state to the current state
			prevSwitchState = switchState;
		}
	}
	
	private pinOn = { led -> pinWrite(led, 1) }
	private pinOff = { led -> pinWrite(led, 0) }
	
	private pinWrite(led, val) {
		def pin = device.getPin(led)
		pin.setMode(OUTPUT)
		pin.setValue(val)
	}

	private pinRead(led) {
		def pin = device.getPin(led)
		pin.setMode(INPUT)
		pin.getValue();
	}

	def static main(String[] args) throws IOException, InterruptedException {
		def hourGlass
		try {
			def deviceName = args? args[0]: "COM7" 
			hourGlass = new HourGlass(new FirmataDevice(deviceName))
			hourGlass.run()
		}
		catch (e) {
			e.printStackTrace()
		}
		finally {
			hourGlass.stop()
		}
	}
}

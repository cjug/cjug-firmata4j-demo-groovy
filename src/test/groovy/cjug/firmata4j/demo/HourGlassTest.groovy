package cjug.firmata4j.demo;

import org.firmata4j.IODevice
import org.firmata4j.Pin
import org.firmata4j.firmata.FirmataDevice

import static org.firmata4j.Pin.Mode.INPUT
import static org.firmata4j.Pin.Mode.OUTPUT

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class HourGlassTest {
	static led
	
	static fakePin = [
		'setMode' : {},
		'setValue' : { println "set pin value: $it" },
		'getValue' : {1L}
		] as Pin
	
	static fakeDevice = [
		'start': {-> println "starting" },
		'stop' : {-> println "stopping" },
		'ensureInitializationIsDone' : {->},
		'getPin' : { led = it; fakePin }
		] as IODevice
	
	@Test
	public void testMain() {
		def hourGlass = new HourGlass(fakeDevice)
		led = 0
		hourGlass.run()
		assertEquals(6, led)
	}
}

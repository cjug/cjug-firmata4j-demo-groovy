import org.firmata4j.IODevice
import org.firmata4j.firmata.FirmataDevice

import static org.firmata4j.Pin.Mode.OUTPUT

/**
 * Created by justin on 6/24/15.
 */
class CjugFirmata4jDemo {

    static IODevice device = new FirmataDevice("/dev/tty.usbmodem1451");

    def static main(String[] args) throws IOException, InterruptedException {
        try {
            setup();
            loop();
        }
        finally {
            device.stop();
        }
    }

    def static setup() {
        device.start() // initiate communication to the device
        device.ensureInitializationIsDone() // wait for initialization is done
        println "Device is ready"
    }

    def static loop() {
        while (true) {
            def pin = device.getPin 13;
            pin.setMode OUTPUT

            pin.setValue 1
            Thread.sleep 500
            pin.setValue 0
            Thread.sleep 500
        }
    }
}
##Firmata4j Demo

A simple demo of firmata4j written in Groovy for the CJUG Maker Track meeting @ SPR on 6/25.

This was tested on an Arduino Uno, communicating over USB/Serial.  This is a re-implementation of the classic 'Blink' application, which uses the all-purpose LED on pin 13.  Please note you will likely have to update the Serial Port name depending on your platform, etc.

###Running
Before running this app, please upload 'Standard Firmata' to your Arduino.  The source for Standard Firmata can be found in the Arduino IDE in the Examples menu item, and can be uploaded from there.  For a more detailed guide to installing Firmata and preparing to use your Arduino with Firmata4j, please refer to the Firmata4j documentation here: https://github.com/kurbatov/firmata4j

The app is built using Gradle.  Simply execute 'gradle build' to pull in necessary dependencies and 'gradle idea' or 'gradle eclipse' to generate a workspace.  The app can be executed from the Groovy console or from the IDE as a CLI Groovy application.

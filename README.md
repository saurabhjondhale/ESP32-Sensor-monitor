# ESP32-Sensor-monitor
ESP32 sensor monitor in Android studio


Connect to MQTT broker

Subscribe to a topic (e.g., esp32/temperature)

Display real-time data from ESP32 (sent via MQTT)

Allow sending commands (like LED ON, LED OFF) to ESP32

Android App (Java or Kotlin):
UI: Minimal (TextView for sensor data, Buttons to send commands)

MQTT: Use Eclipse Paho MQTT client

ESP32:
Publishes sensor data (temp, humidity, etc.) via MQTT

Subscribes to control commands from app (e.g., toggle LED)

Android Studio Setup (Java version)
Add Paho MQTT to build.gradle
implementation 'org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5'
implementation 'org.eclipse.paho:org.eclipse.paho.android.service:1.1.1'


 AndroidManifest.xml
 <service android:name="org.eclipse.paho.android.service.MqttService" />
<uses-permission android:name="android.permission.INTERNET"/>


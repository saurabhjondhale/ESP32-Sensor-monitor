#include <WiFi.h>
#include <PubSubClient.h>

WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  WiFi.begin("SSID", "PASS");
  client.setServer("broker.hivemq.com", 1883);
  client.setCallback(callback);
  while (!client.connected()) {
    client.connect("ESP32Client");
  }
  client.subscribe("esp32/led");
}

void callback(char* topic, byte* payload, unsigned int length) {
  String command = "";
  for (int i = 0; i < length; i++) {
    command += (char)payload[i];
  }
  if (command == "on") digitalWrite(LED_BUILTIN, HIGH);
  else digitalWrite(LED_BUILTIN, LOW);
}

void loop() {
  client.loop();
  float temp = random(20, 30); // dummy temp
  String msg = String(temp);
  client.publish("esp32/temperature", msg.c_str());
  delay(3000);
}

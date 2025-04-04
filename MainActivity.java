public class MainActivity extends AppCompatActivity {

    MqttAndroidClient client;
    Button ledOnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Your layout

        // Button reference
        ledOnButton = findViewById(R.id.ledOnButton);  // Make sure ID matches your layout

        // Setup MQTT client
        client = new MqttAndroidClient(getApplicationContext(),
            "tcp://broker.hivemq.com:1883", MqttClient.generateClientId());

        try {
            client.connect(null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Subscribe if needed
                    client.subscribe("esp32/temperature", 0);

                    // Enable button after connection
                    ledOnButton.setEnabled(true);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e("MQTT", "Failed to connect");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        // Send command when button is clicked
        ledOnButton.setOnClickListener(v -> {
            try {
                client.publish("esp32/led", new MqttMessage("on".getBytes()));
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }
}

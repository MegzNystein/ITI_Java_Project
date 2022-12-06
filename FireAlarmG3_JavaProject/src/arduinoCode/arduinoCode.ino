#include "DHT.h"

#define DHTPIN 2     // Digital pin connected to the DHT sensor

#define DHTTYPE DHT11   // DHT 11
/************************* define ******************************/
int LED = 7;       //set the led pin
int BUZZER = 8;     //set the buzzer pin

int vout = 0;       //variable to get the temperature from the sensor
char data;           //buffer to get the data from the software

int flag = 0;

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);     //set the bautrate of the serial communication
  pinMode(LED,OUTPUT);    //set the led pin as a digital output pin 
  pinMode(BUZZER,OUTPUT); //set the buzzer pin as a digital output pin 
  Serial.setTimeout(50);  //set the timeout of the communication
    Serial.println(F("DHTxx test!"));
  dht.begin();
  delay(50);
}

void loop() {
    delay(200);

  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();
  // Read temperature as Fahrenheit (isFahrenheit = true)
  float f = dht.readTemperature(true);

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }

  // Compute heat index in Fahrenheit (the default)
  float hif = dht.computeHeatIndex(f, h);
  // Compute heat index in Celsius (isFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);


  
  if(flag == 1){ //if there is a fire, the led will blink.
      digitalWrite(LED,HIGH);
      delay(500);
      digitalWrite(LED,LOW);
      delay(500); 
  } 
  if(flag == 0){
    digitalWrite(LED,LOW);
    digitalWrite(BUZZER,LOW);
  }
  if(Serial.available()>0){         //polling to get a message in the serial buffer from the software.
      data = Serial.read();

      if(data == '0' || data == 0){ //signal from the software to start the connection.
          flag = 0;
          digitalWrite(LED,LOW);
          digitalWrite(BUZZER,LOW);
      }
      if(data == '1' || data == 1){ //signal from the software when there is a fire.
        flag = 1;
        digitalWrite(LED,HIGH);
        delay(50);
        digitalWrite(LED,LOW);
        delay(50);
        digitalWrite(BUZZER,HIGH);
        delay(50);
      }
      
      if(data == '2'||data == 2 ){ //signal from the software to stop the alert.
        flag = 0;
        digitalWrite(LED,LOW);
        digitalWrite(BUZZER,LOW);
      }
  }
  h= 
  Serial.print(int(t)-2);
  Serial.print("-");
  Serial.print(int(h));
  Serial.println();
  delay(2000);
}
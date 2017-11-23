#include "DHT.h"

int currentTime;
const byte THERMISTORPIN = A0;
const byte DHT22PIN = A1;
const byte MOSFETPIN = 3;

const byte DHTTYPE = DHT22;

const float TEMPERATUREORDERED = 5;

const float HYSTERESIS = 0.5;
char HYSTERESISACHIEVED = false;
DHT dht(DHT22PIN, DHTTYPE);

float Thermistor(float RawADC) {
 float temperature;
 temperature =log(10000.0/(1024.0/RawADC-1)); 
 temperature = 1 / (0.001129148 + (0.000234125 + (0.0000000876741 * temperature * temperature ))* temperature );
 temperature = temperature - 273.15;
 return temperature;
}

float dewPoint(float temperature, float humidity) {

  float temperatureDew = (17.27 * temperature) / (237.7 + temperature) + log(humidity * 0.01);
  return (237.7 * temperatureDew) / (17.27 - temperatureDew);
  
}

void peltierModuleActivation(float temperature) {

  if(HYSTERESISACHIEVED == false && temperature >= TEMPERATUREORDERED - HYSTERESIS){
    digitalWrite(MOSFETPIN, HIGH);
    //Serial.println("High");
  }
  else if(HYSTERESISACHIEVED && temperature <= TEMPERATUREORDERED + HYSTERESIS){
    digitalWrite(MOSFETPIN, LOW);
    //Serial.println("Low");
  }
  else{
    digitalWrite(MOSFETPIN, LOW);
    //Serial.println("Low2");
  }

  if(temperature <= TEMPERATUREORDERED - HYSTERESIS){
    HYSTERESISACHIEVED = true;
  }
  if(temperature >= TEMPERATUREORDERED + HYSTERESIS){
    HYSTERESISACHIEVED = false;
  }
  if(HYSTERESISACHIEVED){
  //Serial.println("yes");
  }else{
    //Serial.println("no");
  }
  
}

void setup() {

  Serial.begin(9600);
  
  pinMode(MOSFETPIN, OUTPUT);
  pinMode(DHT22PIN, OUTPUT);
  pinMode(THERMISTORPIN, INPUT);

  
  dht.begin();

}

void loop() {

  delay(1000);
  currentTime = millis()/1000;

  
  
  float humidity = dht.readHumidity();
  float temperatureDht = dht.readTemperature();
  float temperatureThermistor = Thermistor(analogRead(THERMISTORPIN));
  float temperatureDew = dewPoint(temperatureThermistor, humidity);

  if (isnan(humidity) || isnan(temperatureDht))
 {
   Serial.println("Failed to read from DHT sensor");
   return;
 }
 if (isnan(temperatureThermistor))
 {
   Serial.println("Failed to read from Thermistor sensor");
   return;
 }

  peltierModuleActivation(temperatureThermistor);

  digitalWrite(MOSFETPIN, HIGH);

  Serial.print(temperatureThermistor);
  Serial.print(";");
  Serial.print(humidity);
  Serial.print(";");
  Serial.print(temperatureDew);
  Serial.print(";");
  Serial.print(currentTime);
  Serial.println(";"); 
  
}

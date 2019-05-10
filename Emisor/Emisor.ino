#define PIN_TRIGGER 8
#define PIN_ECHO 7
#define ESPERA_ENTRE_LECTURAS 1000 // tiempo entre lecturas consecutivas en milisegundos
#define TIMEOUT_PULSO 25000 // la espera máxima de es 30 ms o 30000 µs
#define MEDIA_VELOCIDAD_SONIDO 0.017175 // Mitad de la velocidad del sonido a 20 °C expresada en cm/µs
#define CO2 A1

// Incluimos librería
#include <DHT.h>
 
// Definimos el pin digital donde se conecta el sensor
#define DHTPIN 2
// Dependiendo del tipo de sensor
#define DHTTYPE DHT11
 
// Inicializamos el sensor DHT11
DHT dht(DHTPIN, DHTTYPE);


 
float distancia;
unsigned long tiempo;
unsigned long cronometro;
unsigned long reloj=0;
unsigned short deley=350;

void setup()
{
  Serial.begin(9600);
  dht.begin();
  pinMode(PIN_ECHO,INPUT);
  pinMode(PIN_TRIGGER,OUTPUT);
   pinMode(CO2,INPUT);
  digitalWrite(PIN_TRIGGER,LOW); // Para «limpiar» el pulso del pin trigger del módulo
  delayMicroseconds(2);
}
 
void loop()
{
  cronometro=millis()-reloj;
  if(cronometro>ESPERA_ENTRE_LECTURAS)
  {
    digitalWrite(PIN_TRIGGER,HIGH); // Un pulso a nivel alto…
    delayMicroseconds(10); // …durante 10 µs y
    digitalWrite(PIN_TRIGGER,LOW); // …volver al nivel bajo
    tiempo=pulseIn(PIN_ECHO,HIGH,TIMEOUT_PULSO); // Medir el tiempo que tarda en llegar un pulso
    distancia=MEDIA_VELOCIDAD_SONIDO*tiempo;
    reloj=millis();
  }
  
 double Co2=analogRead(CO2)/20.13;  //leo la entrada analogica y convierto en un porcentaje
 float tem=dht.readTemperature();   //leo temperatura
 float Hum=dht.readHumidity();      //leo humedad
 
  // doy una etiquetao identifiacion a  cada valor y los envio
 Serial.println("d"+(String)distancia);
 delay(deley);
 Serial.println("t"+(String)tem);
 delay(deley);
 Serial.println("c"+(String)Co2);
 delay(deley);
 Serial.println("h"+(String)Hum);
 delay(deley);
}

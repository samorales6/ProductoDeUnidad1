//incluyo las librerias para el lcd
#include <LiquidCrystal_I2C.h>
#include <Wire.h> 
//defino los pines necesarios
#define foco 9
#define niquelina 6
#define ventilador 7
//creo arreglos para presentarlos en el lcd
String etiqueta[4]={"DISTANCIA: ","TEMPERATURA: ","CO2: ","HUMEDAD: "};
String unidad[4]={" cm"," C"," %"," %"};
String valor[5]={"","","","",""};
int i=0,j=0;
float tiempo;
//defino el lcd a usar
LiquidCrystal_I2C lcd(0x27,16,2);
void setup() {
  pinMode(foco,OUTPUT);        //defino el pin como salida
  pinMode(niquelina,OUTPUT);   //defino el pin como salida
  pinMode(ventilador,OUTPUT);  //defino el pin como salida
  lcd.init();          //inicializo el lcd
  lcd.backlight();     //coloco contraste en el lcd
  Serial.begin(9600);  //inicio comunicaion serial a 96000 baudios
  delay(500);          //retraso de 500ms
  lcd.print("listo");  //notifico
  delay(500);          //otro retraso
  tiempo=millis();     //leo tiempo de arranque
}

void loop() {
  if(Serial.available()>0){     ///si hay datos de llegada
     String data = Serial.readStringUntil('-');    //separo los datos cada vez que llegue un -
      valor[i]=data;                               // asigno el dato al valor de turno
      //voy ingrementando 0 1 2 3 4 y regreso a 0 
      if(i!=4){   
        i++;
      }else{
        i=0;
      }
  }
  if(millis()-tiempo>2000){       // si el timepo transcurrido es mayor a 2 
   mostrar(j);                    // muestro los datos de turno
   tiempo=millis();               //mido tiempo
    //voy variando entre 0 1 2 3 0 1 2 3 ....
    if(j!=3){
        j++;
      }else{
        j=0;
      }
  }
 
 if(!valor[1].toFloat()>12){    //si la temperatua es mayor a 12 se prendera la niquelina
  digitalWrite(niquelina,HIGH);
 }else{
  digitalWrite(niquelina,LOW);
 }
 
 if(!valor[2].toFloat()>75){   // si  el co2 es mayor al 75% prende el ventilador
  digitalWrite(ventilador,LOW);
  
 }else{
  digitalWrite(ventilador,HIGH);
 }

if(!valor[3].toFloat()>12){     // si la humedad es mayor a 12% prende el foco
  digitalWrite(foco,HIGH);
 }else{
  digitalWrite(foco,LOW);
 }
  
}
void mostrar(int a){  ///metodo para mostrar en el lcd
  lcd.clear();          ///limpio el lcd
  lcd.setCursor(0,0);    ///ubico el cursor en el primer espacio de la primera fila 
  lcd.print(etiqueta[a]);  ///muestro la etiqueta 
  lcd.setCursor(0,1);       ///me ubico en la fila inferior 
  lcd.print(valor[a]+unidad[a]);  /////escribo el valor 
}

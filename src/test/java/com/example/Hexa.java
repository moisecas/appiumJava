package com.example;

import java.net.MalformedURLException; // importar la librería de MalformedURLException porque es la encargada de manejar los errores de URL
import java.net.URL; // importar la librería de URL porque es la encargada de manejar las URL
import java.util.concurrent.TimeUnit; // importar la librería de TimeUnit porque es la encargada de manejar los tiempos

import org.junit.Test; // importar la librería de Test porque es la encargada de manejar los test
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities; // importar la librería de DesiredCapabilities porque es la encargada de manejar las capacidades deseadas
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mongodb.client.model.geojson.Point;

import ch.qos.logback.core.util.Duration;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement; // importar la librería de MobileElement porque es la encargada de manejar los elementos móviles
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Hexa {
    @Test // crear el método TestMovil
    public void main() throws MalformedURLException { // crear el método main
        AndroidDriver<MobileElement> driverAndroid;  // crear el driver de Android para manejar los elementos móviles
        System.out.println("creación del driver");  // imprimir en consola el mensaje "creación del driver" por  consola

        DesiredCapabilities capabilities = new DesiredCapabilities(); //DesiredCapabilities es la clase que maneja las capacidades deseadas
        
        capabilities.setCapability("platformName", "Android"); //capabilities es el objeto que maneja las capacidades deseadas
        capabilities.setCapability("platformVersion", "11"); //setCapability para asignar la capacidad deseada
        capabilities.setCapability("appActivity", "com.crezcamos.users.MainActivity"); //llamamos la appActivity de la app que queremos probar
        capabilities.setCapability("appPackage", "com.crezcamos.users");  //llamamos la appPackage de la app que queremos probar

        driverAndroid = new AndroidDriver<MobileElement>(new URL( "http://localhost:4723/wd/hub"), capabilities); //creamos el driver de Android para manejar los elementos móviles usando la URL y las capacidades deseadas con la ayuda de appium
        driverAndroid.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //timeouts para manejar los tiempos de espera de los elementos móviles
        // driverAndroid.findElementByName("Acepto términos y condiciones").click(); //findElementByName para encontrar el elemento móvil por su nombre
        
        //espera 5 segundos 
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //click sobre el elemento bounds [30,1342][138,1412] 
        MobileElement checkButton = driverAndroid.findElementByXPath("//android.view.View[@index='5']"); //(5)View[30,1342][138,1412] en el inspector de elementos UI automator viewer que esta en la carpeta tools de la sdk de android
        checkButton.click(); //mobileElement es el objeto que maneja los elementos móviles
        //chekButton es el nombre del elemento móvil que se va a usar
        //driverAndroid es el driver de Android creado anteriormente para manejar los elementos móviles
        //findElementByXPath para encontrar el elemento móvil por su xpath 

        MobileElement desembolso = driverAndroid.findElementByXPath("//android.view.View[@index='7']"); //(7)View{Iniciar desembolso}[50,1432][670,1513] en el inspector de elementos UI automator viewer que esta en la carpeta tools de la sdk de android
        desembolso.click();

        //espera 5 segundos 
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //continua con el flujo de la app
        MobileElement cedula = driverAndroid.findElementByXPath("(//android.view.View[@content-desc=\"Cédula de Ciudadanía\"])[1]"); //(0)View{Cédula de Ciudadanía}[0,1160][720,1316] en el inspector de elementos UI automator viewer que esta en la carpeta tools de la sdk de android
        cedula.click(); 
        //ingresar cédula 
        MobileElement textField = driverAndroid.findElement(By.className("android.widget.EditText"));
        textField.click();
        textField.sendKeys("1095810491"); //sendKeys para escribir en el elemento móvil
        
         // espera 5 segundos
         try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //primer nombre

         MobileElement primerNombre = driverAndroid.findElement(By.className("android.widget.EditText"));
            primerNombre.click();
            primerNombre.sendKeys("Luz");

            //espera 5 segundos
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        //apellido
        MobileElement primerApellido = driverAndroid.findElement(By.className("android.widget.EditText"));
        primerApellido.click();
        primerApellido.sendKeys("Garcia"); 
        
        //espera 5 segundos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //celular y confirmar celular 
        MobileElement campoNumeroCelular = driverAndroid.findElement(By.className("android.widget.EditText"));
        // //MobileElement campoNumeroCelular = (MobileElement) driverAndroid.findElementByXPath("//android.widget.EditText[@text='Número de celular']");
        campoNumeroCelular.click();
        campoNumeroCelular.sendKeys("3153566309"); //sendKeys para escribir en el elemento móvil
        
        // //espera 9 segundos
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // Escribir el correo electrónico
MobileElement correo = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Correo electrónico')]"));
correo.click();
correo.sendKeys("moises.castro@hexasolutions.co");

// Escribir la confirmación del correo electrónico
MobileElement confirmarCorreo = driverAndroid.findElements(By.className("android.widget.EditText")).get(1);
confirmarCorreo.click();
confirmarCorreo.sendKeys("moises.castro@hexasolutions.co");


//fecha de nacimiento manual 
try {
    Thread.sleep(9000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

//ciudad de residencia 
// Escribir "BUCARAMANGA" en el campo de texto
MobileElement municipioField = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Municipio')]"));
municipioField.click();
municipioField.sendKeys("BUCARAMANGA");

//espera 15 segundos
try {
    Thread.sleep(15000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
//seleccionar ocupación empleado 
//manual
//seleccionar empresa 

//hace cuanto trabajas en la empresa
MobileElement antiguedadLaboral = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Antiguedad laboral (meses)')]"));
antiguedadLaboral.click();
antiguedadLaboral.sendKeys("40");

//ingresos al mes 
MobileElement ingresos = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Valor de tus ingresos al mes')]"));
ingresos.click();
ingresos.sendKeys("10000000");

//gastos al mes 
MobileElement tusGastos = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Tus gastos')]"));
tusGastos.click();
tusGastos.sendKeys("1000000");  


//suma de activos 
MobileElement valorBienes = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Valor total de todos tus bienes')]"));
valorBienes.click();
valorBienes.sendKeys("20000000");

//espera 5 segundos
try {
    Thread.sleep(5000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

//escribir barrio 
MobileElement barrioInput = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Barrio o vereda')]"));
barrioInput.click(); 
barrioInput.sendKeys("antonia santos");

//espera 60 segundos 
try {
    Thread.sleep(60000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

//diligenciar dirección
// MobileElement direccionInput = driverAndroid.findElement(By.xpath("//android.widget.EditText[@text='Autopista']"));
// direccionInput.click();
// direccionInput.sendKeys("direccion");
// MobileElement elemento = driverAndroid.findElement(By.xpath("//android.widget.EditText[@bounds='[371,1430][423,1513]']"));
// elemento.click(); 
// elemento.sendKeys("a");

//manual 

//datos de tu vivienda, seleccionar apartamento 
// Encontrar el elemento ScrollView
// MobileElement scrollView = driverAndroid.findElement(By.xpath("//android.widget.ScrollView[contains(@resource-id, 'scrollViewVivienda')]"));

// // Hacer scroll hasta el elemento con texto "Apartamento"
// MobileElement apartamento = (MobileElement) new WebDriverWait(driverAndroid, 30)
//     .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@text='Apartamento']")));
// org.openqa.selenium.Point location = apartamento.getLocation();
// int x = location.getX();
// int y = location.getY();
// TouchAction touchAction = new TouchAction(driverAndroid);
// touchAction.press(PointOption.point(x, y)).release().perform();

// // Dar click sobre el elemento "Apartamento"
// apartamento.click();


//datos adicionales vivienda

//fecha de expedición manual 

//tipo de casa manual

//tipo de vivienda manual

//estrato manual

//cuantos meses llevo ahí 
MobileElement antiguedad = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Antiguedad laboral')]"));
antiguedad.click(); 
antiguedad.sendKeys("40"); 

//materiales de la vivienda manual 

//escribir bucaramanga en expedición 
MobileElement lugarExpedicion = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Lugar de expedición')]"));
lugarExpedicion.click(); 
lugarExpedicion.sendKeys("Bucaramanga");

//lugar de nacimiento
MobileElement lugarNacimiento = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Lugar de nacimiento')]"));
lugarExpedicion.click(); 
lugarNacimiento.sendKeys("bucaramanga");

//selección de genero 
MobileElement btnFemenino = driverAndroid.findElement(By.xpath("//android.view.View[contains(@text, 'Femenino')]"));
btnFemenino.click();

//estado civil manual 

//seleccionar nivel de estudios manual

//destino del dinero manual 

//espera de 15 segundos
try {
    Thread.sleep(15000);
} catch (InterruptedException e) {
    e.printStackTrace();

}

//cuanta plata necesitas

MobileElement valorElement = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Valor')]"));
valorElement.click(); 
valorElement.sendKeys("500000");

//frecuencia del  pago mensual 
MobileElement mensual = driverAndroid.findElementByXPath("//android.view.View[@text='Mensual']");
mensual.click();

//espera 15 segundos
try {
    Thread.sleep(15000);
} catch (InterruptedException e) {
    e.printStackTrace();

}

//numero de cuotas manual
//seguros manual 
//recibir el diner manual 
//vivienda manual 
//información sobre vivienda
MobileElement pisoVivienda = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, '¿Cuántos piso tiene la vivienda?')]"));
pisoVivienda.click(); 
pisoVivienda.sendKeys("1");
MobileElement numSotanos = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, '¿Cuántos sotanos tiene?')]"));
numSotanos.click(); 
numSotanos.sendKeys("0");

//nombre apellidos referencia 
MobileElement nombres = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Nombres')]"));
nombres.click();
nombres.sendKeys("david");

MobileElement apellidos = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Apellidos')]"));
apellidos.click();
apellidos.sendKeys("castro");
//manual parentesco y numero de cel

//referencia dos 
MobileElement nombresDos = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Nombres')]"));
nombresDos.click();
nombresDos.sendKeys("david");
MobileElement apellidosDos = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Apellidos')]"));
apellidosDos.click();
apellidosDos.sendKeys("castro");

//referencia tres 
MobileElement nombresTres = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Nombres')]"));
nombresTres.click();
nombresTres.sendKeys("david");
MobileElement apellidosTres = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Apellidos')]"));
apellidosTres.click();
apellidosTres.sendKeys("castro");


//referencia cuatro 
MobileElement nombresCuatro = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Nombres')]"));
nombresCuatro.click();
nombresCuatro.sendKeys("david");
MobileElement apellidosCuatro = driverAndroid.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Apellidos')]"));
apellidosCuatro.click();
apellidosCuatro.sendKeys("castro");



















           


    }
}

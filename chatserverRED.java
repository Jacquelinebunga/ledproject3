import java.net.*;
import java.io.IOException;
import java.io.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class  chatserverRed
 {
 public static void main(String[] args) throws IOException,InterruptedException
   {
    ServerSocket serverSocket = null;
    try {
         serverSocket = new ServerSocket(10007);
        }
    catch (IOException e)
        {
         System.err.println("Could not listen port: 10007.");
         System.exit(1);
        }
    Socket clientSocket = null; 
    
    System.out.println ("Connecting..");

    try {
         clientSocket = serverSocket.accept();
        }
    catch (IOException e)
        {
         System.err.println("Failed.");
         System.exit(1);
        }
    System.out.println ("Connection successful"); // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "THE LED", PinState.HIGH);
        
        pin.setShutdownOptions(true, PinState.LOW);
      
    System.out.println ("Waiting");

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
    BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
    String inputLine;

    while ((inputLine = in.readLine()) != null)
        {
         if( inputLine != null) 
	{
         System.out.println ("Message From Client: " + inputLine);
         out.println(inputLine); 
	}

         if (inputLine.equals("Exit.")) break;
        }
    out.close();
    in.close();
    clientSocket.close();
    pin.low();

    System.out.println("THE LED TURN OFF");
    serverSocket.close();
   }
   }


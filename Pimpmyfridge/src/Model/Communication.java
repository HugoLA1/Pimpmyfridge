package Model;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.*;
import java.util.Observable;

public class Communication extends Observable implements SerialPortEventListener{

	InputStream input;
    OutputStream output;

    public Communication(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String inputLine;
                if (reader.ready()) {
                    inputLine = reader.readLine();

                    String[] values = inputLine.split(";");

                    System.out.println(values[0] + " \t " + values[1] + " \t " + values[2] + " \t " + values[3] + "\t");

                    this.setChanged();
                    this.notifyObservers(values);
                }

            } catch (Exception e) {
//                System.err.println(e.toString());
            }
        }
    }

    public void writeMessage(String message) {
        try {
            this.output.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}

package Model;


import gnu.io.*;
import java.io.*;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class Connection {

	private Enumeration ports = null;
	private SerialPort serialPort = null;

	public OutputStream getOutput() {
		return output;
	}

	private InputStream input = null;
	private OutputStream output = null;

	final static int TIMEOUT = 2000;
	private static final int DATA_RATE = 9600;


	public Connection() {
		this.searchForPorts();
	}

	public CommPortIdentifier searchForPorts() {
		ports = CommPortIdentifier.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier curPort = (CommPortIdentifier) ports.nextElement();
			if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				return curPort;
			}
		}
		return null;
	}

	public InputStream getInput() {
		return input;
	}

	public void openPort(CommPortIdentifier port) {

		try {
			serialPort = (SerialPort) port.open(this.getClass().getName(), TIMEOUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			this.input = this.serialPort.getInputStream();
			this.output = this.serialPort.getOutputStream();
			System.out.println("Port openned");
			System.out.println("The port is:"+ serialPort);
			this.serialPort.notifyOnDataAvailable(true);
		} catch (PortInUseException | UnsupportedCommOperationException | IOException e) {
			e.printStackTrace();
		}

	}

	public void addSerialListener(SerialPortEventListener listener) {
		try {
			this.serialPort.addEventListener(listener);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
	}

}

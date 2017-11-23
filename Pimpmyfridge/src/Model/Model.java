package Model;

import Model.Communication;
import Model.Connection;

import java.io.BufferedReader;
import java.io.OutputStream;

public class Model{

	public Connection connection;
    public Communication communication;

    public Model() {
        this.connection = new Connection();
    }

    public void runModel() {

        this.connection.openPort(connection.searchForPorts());
        this.communication = new Communication(this.connection.getInput(), this.connection.getOutput());
        this.connection.addSerialListener(this.communication);
    }
}
	
	
	
	

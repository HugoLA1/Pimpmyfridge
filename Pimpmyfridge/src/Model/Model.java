package Model;

import Model.Communication;
import Model.Connection;

public class Model{

	public Connection connection;
	public Communication communication;

	public Model(){
		this.connection = new Connection();
		this.connection.openPort(connection.searchForPorts());
		this.communication = new Communication(this.connection.getInput(), this.connection.getOutput());
		this.connection.addSerialListener(this.communication);
	}

	public void runModel() {


	}
}





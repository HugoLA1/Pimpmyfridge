package Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	private int temp;
	private int time;
	private int humidity;	
	private int ptrose;
	private ArrayList<Observer> tabObserver;

public Model(){
	temp=10;
	time=20;
	humidity=40;
	ptrose=5;
	tabObserver=new ArrayList<Observer>();
}
public void addObserver(Observer o){
	tabObserver.add(o);
}

public void deleteObserver(Observer o){
    tabObserver.remove(o);              
}

public void notifyObserver(){
	setChanged();
	notifyObservers();
}

public void setMesures(int temp, int time, int humidity, int ptrose){
	this.temp=temp;
	this.time=time;
	this.humidity=humidity;
	this.ptrose=ptrose;
	notifyObserver();
}

public int getTemp() {
	return temp;
}

public int getTime() {
	return time;
}

public int getHumidity() {
	return humidity;
}

public int getPtrose() {
	return ptrose;
}

}
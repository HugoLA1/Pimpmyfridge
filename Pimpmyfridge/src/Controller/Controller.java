package Controller;

import Model.Model;
import View.FrameView;

public class Controller {
	
	Model model;
	FrameView frameView;
	
	public Controller() {
		
		this.model = new Model();
		this.frameView = new FrameView();
		this.model.addObserver(this.frameView);
		
	}
	
	public void Run() {
		while(true) {
			
		}
			
	}
}

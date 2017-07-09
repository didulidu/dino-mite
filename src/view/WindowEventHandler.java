package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import model.FileHandler;

class WindowEventHandler extends WindowAdapter {
	  public void windowClosing(WindowEvent evt) {
		  try {
			FileHandler.upisUFajl();
		} catch (IOException e) {
			// TODO Auto-generated catch block//
			e.printStackTrace();
		} 
	  }
}

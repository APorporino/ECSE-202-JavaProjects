import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import acm.gui.TableLayout;


/*This class is used to facilitate the creation of 
 * all the slider boxes needed for the program.
 *It contains two constructors for sliders that require double values and
 * others that require integers.
 * It also has a 2 getters one for getting the integer value of the slider and the other
 * for double value.
 * Lastly it contains a setter the set the new value of the slider once the user changes it
 */
public class sliderBox {
	Integer imin;
	Integer imax;
	JPanel myPanel;
	JLabel minLabel;
	JLabel maxLabel;
	JLabel sReadout;
	JLabel nameLabel;
	JSlider mySlider;
	
	//used to easily set up a sliderBox with int values
	public sliderBox(String name, Integer min, Integer dValue, Integer max) { 
		myPanel = new JPanel();
		nameLabel = new JLabel(name);
		minLabel = new JLabel(min.toString()); 
		maxLabel = new JLabel(max.toString()); 
		mySlider = new JSlider(10*min,10*max,10*dValue); 
		sReadout = new JLabel(dValue.toString()); 
		sReadout.setForeground(Color.blue); 
		myPanel.setLayout(new TableLayout(1,5));
		myPanel.add(nameLabel,"width=100"); 
		myPanel.add(minLabel,"width=25"); 
		myPanel.add(mySlider,"width=100"); 
		myPanel.add(maxLabel,"width=100"); 
		myPanel.add(sReadout,"width=80"); 
		imin=min;
		imax=max;
	}
	
	//used to easily set up a sliderBox with double values
	public sliderBox(String name, double min, double dValue, double max) { 
		myPanel = new JPanel();
		nameLabel = new JLabel(name);
		minLabel = new JLabel(min + ""); 
		maxLabel = new JLabel(max + ""); 
		mySlider = new JSlider( (int) (10*min), (int) (10*max), (int) (10*dValue)); 
		sReadout = new JLabel((double) dValue + ""); 
		sReadout.setForeground(Color.blue); 
		myPanel.setLayout(new TableLayout(1,5));
		myPanel.add(nameLabel,"width=100"); 
		myPanel.add(minLabel,"width=25"); 
		myPanel.add(mySlider,"width=100"); 
		myPanel.add(maxLabel,"width=100"); 
		myPanel.add(sReadout,"width=80"); 
		imin= (int) min;
		imax= (int) max;
	}
	
	public int getISlider_int() { //returns an integer of the value the slider is currently at
		return (mySlider.getValue()/10);
	}
	public double getISlider() { //returns a double of the value the slider is currently at
		return ((double) mySlider.getValue()/10);
	}
	public void setISlider(double val) { //sets the value of the slider to the one its currently at
		mySlider.setValue((int) (10*val));
		sReadout.setText((double) val + "");
		
	}
}

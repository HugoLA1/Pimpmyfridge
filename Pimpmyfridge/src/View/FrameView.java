package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import javax.swing.UIManager;
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.data.xy.XYSeriesCollection;

import Model.Model;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.util.Observable;
import java.util.Observer;



public class FrameView implements Observer {
	int temp;
	int time;
	int hum;
	int ptrose;
	public JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameView window = new FrameView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	  Create the application.
	 */
	@Override
	public void update(Observable o, Object obj) {
			if(o instanceof Model)
			{
				Model m = (Model) o;
				temp= m.getTemp();
				hum=m.getHumidity();
				time=m.getTime();
				ptrose=m.getPtrose();
			}
		}
	
	public FrameView() {
		
		/*
		  Create the frame.
		 */
			
			frame = new JFrame();
			frame.setResizable(false);
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameView.class.getResource("/View/Icon.png")));
			frame.setBounds(100, 100, 992, 433);
			frame.getContentPane().setLayout(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		/*
		 Initialize the labels in the frame.
		*/
			
			 JFreeChart xylineChart = ChartFactory.createXYLineChart(
			         "",
			         "Temps",
			         "Valeurs",
			         createDataset(),
			         PlotOrientation.VERTICAL,
			         true,true,false);
			      ChartPanel chartPanel = new ChartPanel( xylineChart );
			      final XYPlot plot = xylineChart.getXYPlot( );
			      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
			      //Temp
			      renderer.setSeriesPaint( 0 , Color.RED );
			      renderer.setSeriesStroke( 0 , new BasicStroke( 3.0f ) );
			      //Humidity
			      renderer.setSeriesPaint( 1 , Color.BLUE );
			      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
			      plot.setRenderer( renderer );
			      
			      JLabel lblNewLabel = new JLabel("");
			      lblNewLabel.setIcon(new ImageIcon(FrameView.class.getResource("/View/True.png")));
			      lblNewLabel.setBounds(112, 116, 171, 171);
			      frame.getContentPane().add(lblNewLabel);
			      
			      if (temp < ptrose){
			      JLabel lblNewLabel_1 = new JLabel("");
			      lblNewLabel_1.setIcon(new ImageIcon(FrameView.class.getResource("/View/False.png")));
			      lblNewLabel_1.setBounds(112, 100, 207, 198);
			      frame.getContentPane().add(lblNewLabel_1);
			      lblNewLabel.setVisible( false );
			      }
			      
			      JButton btnNewButton = new JButton("");
			      btnNewButton.setMultiClickThreshhold(2L);
			      btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
			      btnNewButton.addActionListener(new ActionListener() {
			      	public void actionPerformed(ActionEvent arg0) {
			      		frame.setBounds(100, 100, 992, 833);
			      	}
			      	
			      });
			      
			      btnNewButton.setIcon(new ImageIcon(FrameView.class.getResource("/View/+.PNG")));
			      btnNewButton.setBounds(908, 365, 32, 31);
			      frame.getContentPane().add(btnNewButton);
			      
			      JLabel lblTauxDhumidit = new JLabel("Taux d'humidit\u00E9");
			      lblTauxDhumidit.setForeground(Color.WHITE);
			      lblTauxDhumidit.setFont(new Font("Tahoma", Font.PLAIN, 30));
			      lblTauxDhumidit.setBounds(679, 100, 235, 37);
			      frame.getContentPane().add(lblTauxDhumidit);
			      
			      JLabel lblTemprature = new JLabel("Temp\u00E9rature ");
			      lblTemprature.setFont(new Font("Tahoma", Font.PLAIN, 30));
			      lblTemprature.setForeground(Color.WHITE);
			      lblTemprature.setBounds(413, 100, 187, 37);
			      frame.getContentPane().add(lblTemprature);		      

			      
			      //Actual Temp
			      JLabel jTemp = new JLabel(temp +"°");
			      jTemp.setFont(new Font("Tw Cen MT", Font.PLAIN, 90));
			      jTemp.setForeground(Color.WHITE);
			      jTemp.setBounds(443, 144, 136, 143);
			      frame.getContentPane().add(jTemp);
			      
			      JLabel jHumidity = new JLabel(hum +"%");
			      jHumidity.setFont(new Font("Tw Cen MT", Font.PLAIN, 90));
			      jHumidity.setForeground(Color.WHITE);
			      jHumidity.setBounds(708, 144, 175, 143);
			      frame.getContentPane().add(jHumidity);
			      frame.getContentPane().add( chartPanel );
			      chartPanel.setBorder(UIManager.getBorder("TextField.border"));
				  chartPanel.setVisible( true );
				  chartPanel.setSize(891,337);
				  chartPanel.setLocation(42, 406);
				  chartPanel.setLayout(null);
			      /*
					 Create Title 
					*/
			      JLabel Title = new JLabel("Interface ExiaFridge\r\n");
			      Title.setHorizontalAlignment(SwingConstants.CENTER);
			      Title.setFont(new Font("Tahoma", Font.PLAIN, 34));
			      Title.setBounds(0, 11, 976, 37);
			      frame.getContentPane().add(Title);
			      
				  /*
					 Create Background (GUI)
					*/
				  JLabel GUI = new JLabel("");
				  GUI.setIcon(new ImageIcon(FrameView.class.getResource("/View/GUI.jpg")));
				  GUI.setBounds(0, 0, 993, 833);
				  frame.getContentPane().add(GUI);
				  
				 
			}
	
	 private XYDataset createDataset( )
	   {
		 
		  // X first Value and Y second Value
	      final XYSeries tempe = new XYSeries( "Température" );          
	      tempe.add( time , temp );          
	      final XYSeries humidity = new XYSeries( "Humidité" );          
	      humidity.add( time , hum );           
	      final XYSeriesCollection dataset = new XYSeriesCollection( );          
	      dataset.addSeries( tempe );    
	      dataset.addSeries( humidity ); 
	      return dataset;
	   }
	
}

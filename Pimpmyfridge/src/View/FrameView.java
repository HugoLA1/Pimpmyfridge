package View;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import javax.swing.BoxLayout;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class FrameView {
	public JFrame frame;
	private JTextField textField;
	int temp=10;
	int time=10;

	/**
	 * Create the application.
	 */
	public FrameView() {
		initialize();
	}
	
	 private XYDataset createDataset( )
	   {
		 /*
		  * X first Value and Y second Value
		  */
	      final XYSeries tempe = new XYSeries( "Température" );          
	      tempe.add( temp , time );          
	      tempe.add( temp+80 , time+60 );          
	      tempe.add( temp+10 , time+30 );    
	      final XYSeries humidity = new XYSeries( "Humidité" );          
	      humidity.add( temp+35 , time );          
	      humidity.add( temp+75 , time+10 );          
	      humidity.add( temp+80 , time+70);    
	      final XYSeriesCollection dataset = new XYSeriesCollection( );          
	      dataset.addSeries( tempe );    
	      dataset.addSeries( humidity ); 
	      return dataset;
	   }
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
	/**
	 * Create the frame.
	 */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameView.class.getResource("/View/Icon.png")));
		frame.setBounds(100, 100, 992, 833);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	/**
	* Initialize the labels in the frame.
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
		      renderer.setSeriesPaint( 0 , Color.RED );
		      renderer.setSeriesStroke( 0 , new BasicStroke( 3.0f ) );
		      renderer.setSeriesPaint( 1 , Color.BLUE );
		      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
		      plot.setRenderer( renderer );
		      
		      JLabel lblNewLabel = new JLabel(temp +"°");
		      lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 90));
		      lblNewLabel.setForeground(Color.WHITE);
		      lblNewLabel.setBounds(442, 116, 136, 143);
		      frame.getContentPane().add(lblNewLabel);
		      
		      textField = new JTextField();
		      textField.setBounds(456, 271, 86, 20);
		      frame.getContentPane().add(textField);
		      textField.setColumns(10);
		      frame.getContentPane().add( chartPanel );
		      chartPanel.setBorder(UIManager.getBorder("TextField.border"));
			  chartPanel.setVisible( true );
			  chartPanel.setSize(891,337);
			  chartPanel.setLocation(42, 406);
			  chartPanel.setLayout(null);
		      /**
				* Create Title 
				*/
		      JLabel Title = new JLabel("Interface ExiaFridge\r\n");
		      Title.setHorizontalAlignment(SwingConstants.CENTER);
		      Title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		      Title.setBounds(0, 11, 976, 37);
		      frame.getContentPane().add(Title);
		      
			  /**
				* Create Background (GUI)
				*/
			  JLabel GUI = new JLabel("");
			  GUI.setIcon(new ImageIcon(FrameView.class.getResource("/View/GUI.jpg")));
			  GUI.setBounds(0, 0, 993, 833);
			  frame.getContentPane().add(GUI);
		}
}

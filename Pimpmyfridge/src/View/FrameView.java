package View;

import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection;

import Model.Model;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FrameView implements Observer {


	public JFrame frame;

	JLabel lblGUI;
	JLabel lblTitle;
	JLabel lblHumidity;
	JLabel lblTemperature;
	JLabel lblTextTemperature;
	JLabel lblTextHumidity;
	JLabel lblWarningDewYes;
	JLabel lblWarningDewNo;

	JButton btnNewButton;

	XYSeries graphicTemperature;
	XYSeries graphicHumidity;
	XYSeriesCollection datasetCollection;

	@Override
	public void update(Observable o, Object obj) {
		if(o instanceof Model)
		{
			Model model = (Model) o;

			graphicTemperature.add(model.getTime(), model.getTemp());
			graphicHumidity.add(model.getTime(), model.getHumidity());

			lblHumidity.setText(model.getHumidity() +"%");
			lblTemperature.setText(model.getTemp() + "°");

			if (model.getTemp() < model.getPtrose()){
				lblWarningDewNo.setIcon(new ImageIcon(FrameView.class.getResource("/View/False.png")));
				lblWarningDewNo.setBounds(112, 100, 207, 198);
				frame.getContentPane().add(lblWarningDewNo);
				lblWarningDewYes.setVisible( false );
			}

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
		 Initialize labels in the frame.
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
		//Temperature
		renderer.setSeriesPaint( 0 , Color.RED );
		renderer.setSeriesStroke( 0 , new BasicStroke( 3.0f ) );
		//Humidity
		renderer.setSeriesPaint( 1 , Color.BLUE );
		renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
		plot.setRenderer( renderer );

		this.lblWarningDewYes = new JLabel("");
		lblWarningDewYes.setIcon(new ImageIcon(FrameView.class.getResource("/View/True.png")));
		lblWarningDewYes.setBounds(112, 116, 171, 171);
		frame.getContentPane().add(lblWarningDewYes);

		this.lblWarningDewNo = new JLabel("");

		this.btnNewButton = new JButton("");
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

		this.lblTextHumidity = new JLabel("Taux d'humidit\u00E9");
		lblTextHumidity.setForeground(Color.WHITE);
		lblTextHumidity.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTextHumidity.setBounds(679, 100, 235, 37);
		frame.getContentPane().add(lblTextHumidity);

		this.lblTextTemperature = new JLabel("Temp\u00E9rature ");
		lblTextTemperature.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTextTemperature.setForeground(Color.WHITE);
		lblTextTemperature.setBounds(413, 100, 187, 37);
		frame.getContentPane().add(lblTextTemperature);		      

		this.lblTemperature = new JLabel("0" +"°");
		lblTemperature.setFont(new Font("Tw Cen MT", Font.PLAIN, 90));
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBounds(443, 144, 136, 143);
		frame.getContentPane().add(lblTemperature);

		this.lblHumidity = new JLabel("0" +"%");
		lblHumidity.setFont(new Font("Tw Cen MT", Font.PLAIN, 90));
		lblHumidity.setForeground(Color.WHITE);
		lblHumidity.setBounds(708, 144, 175, 143);
		frame.getContentPane().add(lblHumidity);
		frame.getContentPane().add( chartPanel );
		chartPanel.setBorder(UIManager.getBorder("TextField.border"));
		chartPanel.setVisible( true );
		chartPanel.setSize(891,337);
		chartPanel.setLocation(42, 406);
		chartPanel.setLayout(null);
		/*
					 Create Title 
		 */
		this.lblTitle = new JLabel("Interface ExiaFridge\r\n");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblTitle.setBounds(0, 11, 976, 37);
		frame.getContentPane().add(lblTitle);

		/*
					 Create Background (GUI)
		 */
		this.lblGUI = new JLabel("");
		lblGUI.setIcon(new ImageIcon(FrameView.class.getResource("/View/GUI.jpg")));
		lblGUI.setBounds(0, 0, 993, 833);
		frame.getContentPane().add(lblGUI);

		frame.setVisible(true);
	}

	private XYDataset createDataset( )
	{

		// X first Value and Y second Value
		this.graphicTemperature = new XYSeries( "Température" );          
		graphicTemperature.add( 0 , 0 );          
		this.graphicHumidity = new XYSeries( "Humidité" );          
		graphicHumidity.add( 0 , 0 );           
		this.datasetCollection = new XYSeriesCollection( );          
		datasetCollection.addSeries( graphicTemperature );    
		datasetCollection.addSeries( graphicHumidity ); 
		return datasetCollection;
	}

}

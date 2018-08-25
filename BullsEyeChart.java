package BullsEye;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.Series;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesChangeListener;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.text.DecimalFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BullsEyeChart extends javax.swing.JFrame implements ChartMouseListener, MouseListener, MouseMotionListener {

	public JFreeChart chart;
	public JFreeChart jfreechart = null;
	
	private ChartPanel chartPanel;
	private XYSeries added = new XYSeries("Added");

	public XYSeriesCollection XYcollection = new XYSeriesCollection();
	public XYSeries series = new XYSeries("Series 1"); //to store the mean
	public XYSeries series1 = new XYSeries("Series 2"); //to store the datapoints
	public XYSeries series2 = new XYSeries("Series 3"); //to store the ignored dataitems in different color and shape

	public double xi, xc;
	public double yi, yc;
	public String[] grade = new String[] {};
	public static DecimalFormat df2 = new DecimalFormat(".##");	
	public String nameofshoot = "";
	public int cnt;
	public int gridsz;
	public double dx;
	public double dy;
	public double Meanx;
	public double Meany;
	public int AddorLoad;
	public int mX, mY;
	ArrayList list1 = new ArrayList();
	ArrayList list2 = new ArrayList();
	public double chartX = 0;
	public double chartY = 0;
	public int moveitemIndex;
	public int Tankno;
	public String Targetdesc;
	public String gunnerName;
	public Integer AID;
	public Integer RID;
	public XYPlot plot;
	public int ignorerds;
	public XYItemRenderer renderer;
	public Rectangle2D e;
	public XYShapeAnnotation xs;
	
	
	ArrayList <Round> roundobj = new ArrayList <Round>();
	 
	/* For mouselistener and mouse motion listener */

	boolean canMove = true;
	boolean isLocked = false;
	
	double finalMovePointX = 0;
	double finalMovePointY = 0;
	ChartRenderingInfo info = null;;
	double initialMovePointX = 0;
	double initialMovePointY = 0;	
	ChartPanel localChartPanel = null;
	
	XYItemEntity xyItemEntity = null;
	ChartMouseEvent cme;
	ChartEntity entity; 
	/* Till Here */

	public BullsEyeChart(final Model m, Round rs) {

		super();
		initComponents();
		
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int iCoordX = (objDimension.width - this.getWidth()) / 2;
	    int iCoordY = (objDimension.height - this.getHeight()) / 2;
	    this.setLocation(iCoordX, iCoordY); 
	        
	        
		String chartTitle = "";
		gridsz = m.getgridsize();
		nameofshoot = m.getShootname().toString();
		AddorLoad = m.getAddorLoad();		

		jlnameofshoot.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		jlnameofshoot.setText("<html><u><b>" + nameofshoot + "</b></u></html>");

		/** Retrieve data from model class **/

		this.jlvammotype.setText(m.getAmmunition());
		this.jlvrange.setText(m.getRange());
		Tankno = m.gettankno();
		gunnerName = m.getgunnername();
		AID = m.getaid();
		RID = m.getrid();			
				
		if (gridsz == 0){
			Targetdesc = "3X3";
		} else if( gridsz == 1){
			Targetdesc = "7X7";
		}else{
			Targetdesc = "10X10";
		}
			
		
		series1.addChangeListener(new SeriesChangeListener() {			
					
			int s = 0;
			double rxc = 0.0;
			double ryc = 0.0;
			
			@Override
			public void seriesChanged(SeriesChangeEvent arg0) {			
				
				ArrayList list3 = new ArrayList();
				ArrayList list4 = new ArrayList();
				
				list3.clear();
				list4.clear();
										
				for (s=0; s < roundobj.size();s++){	
					
					if (roundobj.get(s).gettype().equals("A") || roundobj.get(s).gettype().equals("F")){
						list3.add(roundobj.get(s).getxcoord());						
						list4.add(roundobj.get(s).getycoord());
						series1.add(roundobj.get(s).getxcoord(), roundobj.get(s).getycoord(), false);
					} else if (roundobj.get(s).gettype().equals("I")){
						series2.add(roundobj.get(s).getxcoord(), roundobj.get(s).getycoord(),false);
					}	
				
				}
				
				//populating the ignore round and actual round labels.
				int ir = 0;				
				
				for (s=0; s < roundobj.size();s++){	
					if (roundobj.get(s).gettype().equals("I")) {
						ir = ir + 1;					
					}
					
				}
								
				//4th July
				
				jlvnoofrounds.setText(String.valueOf(roundobj.size()));
				jlvignoredrounds.setText(String.valueOf(ir));
				
				// Calculate Mean and SD

                dx = 0;
                dy = 0;
                double mdx = 00;
                double  mdy = 00;
                Meanx = 0;
                Meany = 0;
                double mdxcm = 00;
                double mdycm = 00;
                double mdxmils = 00;
                double mdymils = 00;
                double SDxcm = 00;
                double SDycm = 00;
                double SDxmils = 00;
                double SDymils = 00;
                
                series.clear();
                
                for (int j = 0; j < list3.size(); j++) {
                    Meanx = Meanx + (double) list3.get(j);
                    
                }

                for (int k = 0; k < list4.size(); k++) {
                    Meany = Meany + (double) list4.get(k);
                           
                }
                
                Meanx = Meanx / list3.size() ;
                Meany = Meany / list4.size();
                
                
                //Calculating Mean Deviation
                
                for (int p = 0; p < list3.size(); p++) {
                       mdx = mdx + ((Math.abs(((Double) list3.get(p) - Meanx))));
                       
                } 
               
                mdx = mdx /list3.size();
                
                mdxcm = mdx * 30.48;  // conversion of feet into centimeter
                mdxmils = (mdxcm*10)/Double.parseDouble(m.getRange()); // conversion into miliradians 
                
                for (int p = 0; p < list4.size(); p++) {
                       mdy = mdy + ((Math.abs(((Double) list4.get(p) - Meany))));
                }
                
                mdy = mdy /list4.size();
                mdycm = mdy * 30.48;  // conversion of feet into centimeter
                mdymils = (mdycm*10)/Double.parseDouble(m.getRange()); // conversion into miliradians 
                
                
                if(Double.isNaN(mdxmils)) 
                {
                       jlvmeanxaxis.setText("00");
                }
                else
                {
                       jlvmeanxaxis.setText("" + df2.format(mdxmils) + "");     
                }
                
                if(Double.isNaN(mdymils))
                {
                       jlvyaxis.setText("00");
                }
                else
                {
                       jlvyaxis.setText("" + df2.format(mdymils) + "");
                }
                
                
                if(Double.isNaN(mdxcm))
                {
                    jlv_meandev_xaxis_cms.setText("00");
                }
                else
                {
                    jlv_meandev_xaxis_cms.setText("" + df2.format(mdxcm) + "");     
                }
                
                if(Double.isNaN(mdycm))
                {
                    jvl_meandev_yaxis_cms.setText("00");
                }
                else
                {
                    jvl_meandev_yaxis_cms.setText("" + df2.format(mdycm) + "");
                }               
                                
                series.add(Meanx, Meany);
                       
                //Calculating Standard Deviation

                for (int p = 0; p < list3.size(); p++) {
                       dx = dx + ((Math.pow(((Double) list3.get(p) - Meanx), 2)));
                }
                
                double SDx = Math.sqrt(dx / (list3.size()));
                SDxcm = SDx * 30.48;  // conversion of feet into centimeter
                SDxmils = (SDxcm*10)/Double.parseDouble(m.getRange()); // conversion into miliradians 

                for (int n = 0; n < list4.size(); n++) {
                       dy = dy + ((Math.pow(((Double) list4.get(n) - Meany), 2)));

                }
                
                double SDy = Math.sqrt(dy / (list4.size()));
                SDycm = SDy * 30.48;  // conversion of feet into centimeter
                SDymils = (SDycm*10)/Double.parseDouble(m.getRange()); // conversion into miliradians 
                
                
                if(Double.isNaN(SDxmils))
                {
                       jlvstdxaxis.setText("00");
                }
                else
                {
                       jlvstdxaxis.setText("" + df2.format(SDxmils) + "");      
                }
                
                if(Double.isNaN(SDymils))
                {
                       jlvstdyaxis.setText("00");
                }
                else
                {
                       jlvstdyaxis.setText("" + df2.format(SDymils) + "");
                }
                
                if(Double.isNaN(SDxcm))
                {
                    jlv_stddev_xaxis_cms.setText("00");
                }
                else
                {
                    jlv_stddev_xaxis_cms.setText("" + df2.format(SDxcm) + "");      
                }
                
                if(Double.isNaN(SDycm))
                {
                    jlv_stddev_yaxis_cms.setText("00");
                }
                else
                {
                    jlv_stddev_yaxis_cms.setText("" + df2.format(SDycm) + "");
                }
                          
          }             
   
   });

	
		jfreechart = ChartFactory.createScatterPlot(chartTitle, "", "", createDataset());
		this.chart = jfreechart;
		
		plot = (XYPlot) this.chart.getPlot();
		plot.setDomainPannable(true);
		plot.setRangePannable(true);
		plot.setBackgroundPaint(Color.WHITE);
		
		plot.setDomainGridlinesVisible(true); 
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.black);
		plot.setDomainGridlinePaint(Color.black);	
		
		
		
		renderer = plot.getRenderer();
		renderer.setBaseSeriesVisibleInLegend(false);
		
		//5th July
		renderer.setSeriesShape(0, ShapeUtilities.createDiamond(3)); //mean
		renderer.setSeriesPaint(0, Color.blue); 
		
		renderer.setSeriesShape(1, ShapeUtilities.createDownTriangle(3)); //data points
		renderer.setSeriesPaint(1, Color.green);
		
		renderer.setSeriesShape(2, ShapeUtilities.createRegularCross(3, 3));
		renderer.setSeriesPaint(2, Color.red); //Ignore
		
		
		NumberAxis range = (NumberAxis) plot.getRangeAxis();		
		range.setTickUnit(new NumberTickUnit(1));
		range.setInverted(true);
		
		Font font2 = new Font("Dialog", Font.BOLD, 18);		
		range.setTickLabelFont(font2);
		

		if (gridsz == 0) {
			
			grade = new String[8];
			grade[0] = "";
			grade[1] = "A";
			grade[2] = "B";
			grade[3] = "C";
			grade[4] = "D";
			grade[5] = "E";
			grade[6] = "F";
			SymbolAxis rangeAxis = new SymbolAxis("", grade);
			rangeAxis.setTickUnit(new NumberTickUnit(1));
			rangeAxis.setRange(0, 6);
			range.setRange(0, 6);
			
			rangeAxis.setAxisLineVisible(false);
			
			plot.setDomainAxis(rangeAxis);
			rangeAxis.setTickLabelFont(font2);
			rangeAxis.setTickLabelsVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(3,2.5,3,3.5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(2.5,3,3.5,3);
						
			plot.addAnnotation(lineAnno);
			plot.addAnnotation(lineAnno1);
			
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(2,2,2,4);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(4,2,4,4);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(2,2,4,2);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(2,4,4,4);
			
			plot.addAnnotation(lineAnno2);
			plot.addAnnotation(lineAnno3);
			plot.addAnnotation(lineAnno4);
			plot.addAnnotation(lineAnno5);
			
			XYTextAnnotation xyTA = new XYTextAnnotation("A", 0.5, 0.5);
			XYTextAnnotation xyTB = new XYTextAnnotation("B", 1.5, 0.5);
			XYTextAnnotation xyTC = new XYTextAnnotation("C", 2.5, 0.5);
			XYTextAnnotation xyTD = new XYTextAnnotation("D", 3.5, 0.5);
			XYTextAnnotation xyTE = new XYTextAnnotation("E", 4.5, 0.5);
			XYTextAnnotation xyTF = new XYTextAnnotation("F", 5.5, 0.5);
					
			xyTA.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTB.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTC.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTD.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTE.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTF.setFont((new Font("SansSerif", Font.BOLD, 18)));
			
					
			plot.addAnnotation(xyTA);
			plot.addAnnotation(xyTB);
			plot.addAnnotation(xyTC);
			plot.addAnnotation(xyTD);
			plot.addAnnotation(xyTE);
			plot.addAnnotation(xyTF);
			
			
		} else if (gridsz == 1) {
			
			grade = new String[9];
			grade[0] = "";
			grade[1] = "A";
			grade[2] = "B";
			grade[3] = "C";
			grade[4] = "D";
			grade[5] = "E";
			grade[6] = "F";
			grade[7] = "G";
			SymbolAxis rangeAxis = new SymbolAxis("", grade);
			rangeAxis.setTickUnit(new NumberTickUnit(1));
			rangeAxis.setRange(0, 7);
			range.setRange(0, 7);
			plot.setDomainAxis(rangeAxis);
			rangeAxis.setTickLabelFont(font2);
			rangeAxis.setTickLabelsVisible(false);
			
			rangeAxis.setAxisLineVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(3.5,2.5,3.5,4.5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(2.5,3.5,4.5,3.5);
			
			plot.addAnnotation(lineAnno);
			plot.addAnnotation(lineAnno1);
						
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(2,2,2,5);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(5,2,5,5);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(2,2,5,2);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(2,5,5,5);
			
			plot.addAnnotation(lineAnno2);
			plot.addAnnotation(lineAnno3);
			plot.addAnnotation(lineAnno4);
			plot.addAnnotation(lineAnno5);
			
			XYTextAnnotation xyTA = new XYTextAnnotation("A", 0.5, 0.5);
			XYTextAnnotation xyTB = new XYTextAnnotation("B", 1.5, 0.5);
			XYTextAnnotation xyTC = new XYTextAnnotation("C", 2.5, 0.5);
			XYTextAnnotation xyTD = new XYTextAnnotation("D", 3.5, 0.5);
			XYTextAnnotation xyTE = new XYTextAnnotation("E", 4.5, 0.5);
			XYTextAnnotation xyTF = new XYTextAnnotation("F", 5.5, 0.5);
			XYTextAnnotation xyTG = new XYTextAnnotation("G", 6.5, 0.5);
			
					
			xyTA.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTB.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTC.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTD.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTE.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTF.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTG.setFont((new Font("SansSerif", Font.BOLD, 18)));
			
					
			plot.addAnnotation(xyTA);
			plot.addAnnotation(xyTB);
			plot.addAnnotation(xyTC);
			plot.addAnnotation(xyTD);
			plot.addAnnotation(xyTE);
			plot.addAnnotation(xyTF);
			plot.addAnnotation(xyTG);
						
		} else {
			
			grade = new String[12];
			grade[0] = "";
			grade[1] = "A";
			grade[2] = "B";
			grade[3] = "C";
			grade[4] = "D";
			grade[5] = "E";
			grade[6] = "F";
			grade[7] = "G";
			grade[8] = "H";
			grade[9] = "I";
			grade[10] = "J";
			grade[11] = "K";
			
			SymbolAxis rangeAxis = new SymbolAxis("", grade);
			
			rangeAxis.setTickUnit(new NumberTickUnit(1));
			rangeAxis.setRange(0, 10);
			range.setRange(0, 10);
			
			plot.setDomainAxis(rangeAxis);
			rangeAxis.setTickLabelFont(font2);
			rangeAxis.setTickLabelsVisible(false);
			
			rangeAxis.setAxisLineVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(4,5,6,5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(5,4,5,6);
			
			plot.addAnnotation(lineAnno);
			plot.addAnnotation(lineAnno1);
			
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(3,3,3,7);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(7,3,7,7);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(3,3,7,3);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(3,7,7,7);
			
			plot.addAnnotation(lineAnno2);
			plot.addAnnotation(lineAnno3);
			plot.addAnnotation(lineAnno4);
			plot.addAnnotation(lineAnno5);
			
			XYTextAnnotation xyTA = new XYTextAnnotation("A", 0.5, 0.5);
			XYTextAnnotation xyTB = new XYTextAnnotation("B", 1.5, 0.5);
			XYTextAnnotation xyTC = new XYTextAnnotation("C", 2.5, 0.5);
			XYTextAnnotation xyTD = new XYTextAnnotation("D", 3.5, 0.5);
			XYTextAnnotation xyTE = new XYTextAnnotation("E", 4.5, 0.5);
			XYTextAnnotation xyTF = new XYTextAnnotation("F", 5.5, 0.5);
			XYTextAnnotation xyTG = new XYTextAnnotation("G", 6.5, 0.5);
			XYTextAnnotation xyTH = new XYTextAnnotation("H", 7.5, 0.5);
			XYTextAnnotation xyTI = new XYTextAnnotation("J", 8.5, 0.5);
			XYTextAnnotation xyTJ = new XYTextAnnotation("K", 9.5, 0.5);
					
			xyTA.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTB.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTC.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTD.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTE.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTF.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTG.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTH.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTI.setFont((new Font("SansSerif", Font.BOLD, 18)));
			xyTJ.setFont((new Font("SansSerif", Font.BOLD, 18)));
					
			plot.addAnnotation(xyTA);
			plot.addAnnotation(xyTB);
			plot.addAnnotation(xyTC);
			plot.addAnnotation(xyTD);
			plot.addAnnotation(xyTE);
			plot.addAnnotation(xyTF);
			plot.addAnnotation(xyTG);
			plot.addAnnotation(xyTH);
			plot.addAnnotation(xyTI);
			plot.addAnnotation(xyTJ);
						        
		}
				
		plot.setRangeAxis(range);
		
		plot.setDomainAxisLocation(0, AxisLocation.TOP_OR_RIGHT);
		plot.setRangeAxisLocation(0,AxisLocation.BOTTOM_OR_LEFT);
		range.setTickLabelsVisible(false); 

		range.setVisible(false);		
		
		XYTextAnnotation xyT1 = new XYTextAnnotation("1", 0.5, 1.5);
		XYTextAnnotation xyT2 = new XYTextAnnotation("2", 0.5, 2.5);
		XYTextAnnotation xyT3 = new XYTextAnnotation("3", 0.5, 3.5);
		XYTextAnnotation xyT4 = new XYTextAnnotation("4", 0.5, 4.5);
		XYTextAnnotation xyT5 = new XYTextAnnotation("5", 0.5, 5.5);
		XYTextAnnotation xyT6 = new XYTextAnnotation("6", 0.5, 6.5);
		XYTextAnnotation xyT7 = new XYTextAnnotation("7", 0.5, 7.5);
		XYTextAnnotation xyT8 = new XYTextAnnotation("8", 0.5, 8.5);
		XYTextAnnotation xyT9 = new XYTextAnnotation("9", 0.5, 9.5);
				
		
		xyT1.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT2.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT3.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT4.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT5.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT6.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT7.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT8.setFont((new Font("SansSerif", Font.BOLD, 18)));
		xyT9.setFont((new Font("SansSerif", Font.BOLD, 18)));
				
		
		plot.addAnnotation(xyT1);
		plot.addAnnotation(xyT2);
		plot.addAnnotation(xyT3);
		plot.addAnnotation(xyT4);
		plot.addAnnotation(xyT5);
		plot.addAnnotation(xyT6);
		plot.addAnnotation(xyT7);
		plot.addAnnotation(xyT8);
		plot.addAnnotation(xyT9);	
		
		chartPanel = new ChartPanel(this.chart);
		chartPanel.setMouseWheelEnabled(true);	
		chartPanel.setMouseZoomable(false);
		chartPanel.addChartMouseListener(this);
		chartPanel.addMouseListener(this);
		chartPanel.addMouseMotionListener(this);
		this.info = chartPanel.getChartRenderingInfo();
		
		jpMapframe.removeAll();		
		jpMapframe.setLayout(new BorderLayout());
		jpMapframe.add(chartPanel).setVisible(true);		 
		jpMapframe.add(chartPanel, BorderLayout.CENTER);
		getContentPane().setBackground(Color.WHITE);
		jpsidepanel.setBackground(Color.WHITE);
		
				
		// check if this form is loaded from Add new shoot or Load shoot, if its
		// load new shoot then get the details of the points from dtls table

		if (AddorLoad == 1) {
			Connection condtls = null;
			Statement stmtdtls = null;
			ResultSet rsdtls = null;
			list1.clear();
			list2.clear();
			
			
			

			try {

				Class.forName("org.h2.Driver");
				condtls = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
				stmtdtls = condtls.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				String strqry = "select * from Rounds where Shoot_ID = " + m.getshootid();
								
				rsdtls = stmtdtls.executeQuery(strqry);
				
				rsdtls.last();
				int rowcnt = rsdtls.getRow();				
				rsdtls.beforeFirst();
								
				
				int i = 0;
				ignorerds = 0;
				
				roundobj.clear();				
				
				while (rsdtls.next()) {					
					
					Round rnd = new Round();
					
					//Load the details from te round table in the round object array
					rnd.setrno(rsdtls.getInt("Round_No")); 
					rnd.setatmtemp(rsdtls.getInt("AtmTemp"));
					rnd.setchtemp(rsdtls.getInt("ChTemp"));
					//rnd.setDateTime(); //manjusha
					rnd.setHWind(rsdtls.getInt("HWind"));
					rnd.setXWind(rsdtls.getInt("XWind"));
					rnd.settype(rsdtls.getString("Type"));					
					rnd.setxcoord(rsdtls.getDouble("XCoord"));
					rnd.setycoord(rsdtls.getDouble("YCoord"));
					rnd.setAddUpdate("U");
					roundobj.add(rnd);

					i = i + 1;		

				}
				
				int actualrds = 0;
				
				series1.fireSeriesChanged();   //Added newly
				
				m.setnoOfRounds(rowcnt);
				
				rsdtls.close();
				stmtdtls.close();
				condtls.close();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmtdtls != null)
						stmtdtls.close();
				} catch (SQLException se2) {
				}
				try {
					if (condtls != null)
						condtls.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}		

		} else {
			jlvnoofrounds.setText("1");
			jlvignoredrounds.setText("O");
			
			m.noOfRounds = 1;			
			roundobj.add(rs);
			
			series.clear();	
			series1.add(rs.getxcoord(),rs.getycoord());			
			rs.setseriesind(series1.indexOf(rs.getxcoord()));
		}
		
		//4th July
		
		//this.jlvdatetime.setText(rs.getDateTime().toString());
		this.jlvatmtemp.setText(String.valueOf(roundobj.get(0).getatmtemp()));		
		this.jlvchtemp.setText(String.valueOf(roundobj.get(0).getchTemp()));		
		this.jlvhwind.setText(String.valueOf(roundobj.get(0).getHWind()));		
		this.jlvxtemp.setText(String.valueOf(roundobj.get(0).getXWind()));
		
		final EntityCollection entities = this.info.getEntityCollection();
	
		final JPopupMenu popup1 = new JPopupMenu();
		
		
		JMenuItem menuItem = new JMenuItem("Add New Round", new ImageIcon("images/newproject.png"));
		menuItem.setMnemonic(KeyEvent.VK_P);

		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int mouseX = mX;
				int mouseY = mY;

				Point2D p = chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
				XYPlot plot = (XYPlot) chart.getPlot();
				ChartRenderingInfo info = chartPanel.getChartRenderingInfo();
				Rectangle2D dataArea = info.getPlotInfo().getDataArea();
				ValueAxis domainAxis = plot.getDomainAxis();
				RectangleEdge domainAxisEdge = plot.getDomainAxisEdge();
				ValueAxis rangeAxis = plot.getRangeAxis();
				RectangleEdge rangeAxisEdge = plot.getRangeAxisEdge();

				chartX = domainAxis.java2DToValue(p.getX(), dataArea, domainAxisEdge);
				chartY = rangeAxis.java2DToValue(p.getY(), dataArea, rangeAxisEdge);
				
				Round R = new Round();				
				BullsEyeAddRounds bea = new BullsEyeAddRounds(m,R);
				
				bea.setAlwaysOnTop(true);				
				bea.setModal(true);
				bea.setVisible(true);
				
				if (m.getOkCancel() == 1) {			
					R.setxcoord(chartX);
					R.setycoord(chartY);
					
					roundobj.add(R); //Add the round to arraylist
					series1.fireSeriesChanged();
					jlvnoofrounds.setText("" + m.noOfRounds + "");
				}				
			}
		});

		popup1.add(menuItem);
		
		
		//Ignore round
		JMenuItem menuItemignore = new JMenuItem("Ignore this Round", new ImageIcon("images/newfile.png"));
		menuItemignore.setMnemonic(KeyEvent.VK_F);
				
			menuItemignore.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) { 
		            	
		            	XYItemEntity e1 = (XYItemEntity)entities.getEntity(mX, mY);				
		        		int item = e1.getItem();
		        								
						if (e1.getSeriesIndex() == 1){
						
							for (int y =0; y<roundobj.size();y++){					
								if (roundobj.get(y).getxcoord() == (Double)series1.getX(item) && (roundobj.get(y).getycoord() == (Double)series1.getY(item))){
								
									roundobj.get(y).settype("I");
									roundobj.get(y).setAddUpdate("U");
								
									ignorerds = ignorerds + 1;
									jlvignoredrounds.setText("" + ignorerds + "");
																
									break;
								}					
							}
							series1.clear();
						}					
						
		            }
				});
				
				popup1.add(menuItemignore);
		//Ignore round
				
		//Include this round
		
		JMenuItem menuIteminclude = new JMenuItem("Include this Round", new ImageIcon("images/newfile.png"));				 
		menuIteminclude.setMnemonic(KeyEvent.VK_F);                            
		menuIteminclude.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {        	       	
        	XYItemEntity e1 = (XYItemEntity)entities.getEntity(mX, mY);                         
            int item = e1.getItem();
            
            if (e1.getSeriesIndex() ==2){
            	for (int y =0; y<roundobj.size();y++){                             
            			if (roundobj.get(y).getxcoord() == (Double)series2.getX(item) && (roundobj.get(y).getycoord() == (Double)series2.getY(item))){            	
            				roundobj.get(y).settype("A");
            				roundobj.get(y).setAddUpdate("U");                
            				series2.remove(item); //removing from the ignored series                
            				series1.fireSeriesChanged(); //This will add the point back to main series 
            				break;
            			}                                
            	}
            }
         }         
         });
		
         popup1.add(menuIteminclude);  
		
		//Include this round
		//EntityCollection entities = this.info.getEntityCollection();
				
		menuItem = new JMenuItem("Delete this Round", new ImageIcon("images/newfile.png"));
		menuItem.setMnemonic(KeyEvent.VK_F);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Double xcd,ycd;
				
				XYItemEntity e1 = (XYItemEntity)entities.getEntity(mX, mY);
				int item = e1.getItem();
				
				if (e1.getSeriesIndex() == 1){
					xcd = (Double)series1.getX(item);
					ycd = (Double)series1.getY(item);
				}else {
					xcd = (Double)series2.getX(item);
					ycd = (Double)series2.getY(item);
				}					
				
				for (int y =0; y<roundobj.size();y++){					
					if (roundobj.get(y).getxcoord() == xcd && (roundobj.get(y).getycoord() == ycd)){
						roundobj.get(y).settype("D");
						roundobj.get(y).setAddUpdate("U");									
						break;
					}					
				}
				
				if (e1.getSeriesIndex() == 2){
					series2.remove(item);
				}
				
				series1.clear();
									
				//decreasing the count.
				int r = Integer.valueOf(jlvnoofrounds.getText());								
				jlvnoofrounds.setText("" + (r - 1) + "");
				m.setnoOfRounds(r-1);
				
			}
		});
		popup1.add(menuItem);

		chartPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				mX = e.getX();
				mY = e.getY();				
				cme = new ChartMouseEvent(jfreechart, e, entities.getEntity(mX, mY));
				entity = cme.getEntity();
				
				showPopup(e);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

			private void showPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup1.show(e.getComponent(), e.getX(), e.getY());

				}
			}

		});

		menuItem = new JMenuItem("Cancel", new ImageIcon("images/newfile.png"));
		menuItem.setMnemonic(KeyEvent.VK_F);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		popup1.add(menuItem);

	}

	public XYDataset createDataset() {		
		//series2.add(xi, yi);
		XYcollection.addSeries(series);
		XYcollection.addSeries(series1);
		XYcollection.addSeries(series2);
		return XYcollection;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jpMapframe = new javax.swing.JPanel();
		jpsidepanel = new javax.swing.JPanel();
		jlvhwind = new javax.swing.JLabel();
		jlvchtemp = new javax.swing.JLabel();
		jlvignoredrounds = new javax.swing.JLabel();
		jlroundno = new javax.swing.JLabel();
		jlvxtemp = new javax.swing.JLabel();
		jlvdatetime = new javax.swing.JLabel();
		jlmpi = new javax.swing.JLabel();
		jlvmpi = new javax.swing.JLabel();
		jlmeandeviation = new javax.swing.JLabel();
		jlvmeandeviation = new javax.swing.JLabel();
		jlstddeviation = new javax.swing.JLabel();
		jlcordinates = new javax.swing.JLabel();
		jlvcordinates = new javax.swing.JLabel();
		jlammotype = new javax.swing.JLabel();
		jldatetime = new javax.swing.JLabel();
		jlvammotype = new javax.swing.JLabel();
		jlatmtemp = new javax.swing.JLabel();
		jlrange = new javax.swing.JLabel();
		jlhtemp = new javax.swing.JLabel();
		jlvrange = new javax.swing.JLabel();
		jlchtemp = new javax.swing.JLabel();
		jlnoofrounds = new javax.swing.JLabel();
		jlxtemp = new javax.swing.JLabel();
		jlignoredrounds = new javax.swing.JLabel();
		jlvatmtemp = new javax.swing.JLabel();
		jlvnoofrounds = new javax.swing.JLabel();
		jlyaxis = new javax.swing.JLabel();
		jlxaxis = new javax.swing.JLabel();
		jlstddevyaxis = new javax.swing.JLabel();
		jlvmeanxaxis = new javax.swing.JLabel();
		jlvyaxis = new javax.swing.JLabel();
		jlvstdxaxis = new javax.swing.JLabel();
		jlvstdyaxis = new javax.swing.JLabel();
		jl_meandev_xaxis_cms = new javax.swing.JLabel();
        jlv_meandev_xaxis_cms = new javax.swing.JLabel();
        jl_meandev_yaxis_cms = new javax.swing.JLabel();
        jvl_meandev_yaxis_cms = new javax.swing.JLabel();
        jl_stddev_xaxis_cms = new javax.swing.JLabel();
        jlv_stddev_xaxis_cms = new javax.swing.JLabel();
        jl_stddev_yaxis_cms = new javax.swing.JLabel();
        jlv_stddev_yaxis_cms = new javax.swing.JLabel();
		jblock = new javax.swing.JButton();
		jbcancel = new javax.swing.JButton();
		jbhelp = new javax.swing.JButton();
		jlnameofshoot = new javax.swing.JLabel();
		jlwashout = new javax.swing.JLabel();
		
		this.setTitle("Bulls Eye");
	    java.awt.Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("BULLS.png"));
	    ImageIcon icon = new ImageIcon(image);
	    this.setIconImage(icon.getImage());
	    	
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new java.awt.Color(0, 51, 153));

		jpMapframe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jpMapframe.setOpaque(false);
		jpMapframe.setPreferredSize(new java.awt.Dimension(500, 500));

		javax.swing.GroupLayout jpMapframeLayout = new javax.swing.GroupLayout(jpMapframe);
		jpMapframe.setLayout(jpMapframeLayout);
		jpMapframeLayout.setHorizontalGroup(
				jpMapframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 498, Short.MAX_VALUE)
		);
		jpMapframeLayout.setVerticalGroup(
			jpMapframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 0, Short.MAX_VALUE)
		);
		
		jpsidepanel.removeAll();
		
		
		jpsidepanel.setBackground(Color.WHITE);

		jpsidepanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		jlvhwind.setText("");

		jlvchtemp.setText("");

		jlvignoredrounds.setText("00");

		jlroundno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
		jlroundno.setText("<html><u>Round No.1</u></html>");

		jlvxtemp.setText("");

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		jlvdatetime.setText("" + df.format(date) + "");

		jlmpi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
		jlmpi.setText("MPI:");

		jlvmpi.setText("00");

		jlmeandeviation.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
		jlmeandeviation.setText("<html><u>Mean Deviation:</u></html>");

		jlvmeandeviation.setText("X Axis (mils)");

		jlstddeviation.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
		jlstddeviation.setText("<html><u>Standard Deviation:</u></html>");

		jlcordinates.setText("Coordinates");

		jlvcordinates.setText("00");

		jlammotype.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jlammotype.setText("Ammunition Type:");

		jldatetime.setText("Date/Time");

		jlvammotype.setText("");

		jlatmtemp.setText("Atm temp");

		jlrange.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jlrange.setText("Range (M)");

		jlhtemp.setText("H Wind:");
		jlhtemp.setToolTipText("");

		jlvrange.setText("");

		jlchtemp.setText("Ch Temp:");

		jlnoofrounds.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jlnoofrounds.setText("No of Rounds");

		jlxtemp.setText("X Temp:");

		jlignoredrounds.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jlignoredrounds.setText("Ignored Rounds");

		jlvatmtemp.setText("");

		jlyaxis.setText("Y Axis (mils) :");

        jlxaxis.setText("X Axis (mils) :");

        jlstddevyaxis.setText("Y Axis (mils) :");

        jlvmeanxaxis.setText("");

        jlvyaxis.setText("");

        jlvstdxaxis.setText("");

        jlvstdyaxis.setText("");

        jl_meandev_xaxis_cms.setText("X Axis (cms) :");

        jlv_meandev_xaxis_cms.setText("");

        jl_meandev_yaxis_cms.setText("Y Axis (cms) :");

        jvl_meandev_yaxis_cms.setText("");

        jl_stddev_xaxis_cms.setText("X Axis (cms) :");

        jlv_stddev_xaxis_cms.setText("");

        jl_stddev_yaxis_cms.setText("Y Axis (cms) :");

        jlv_stddev_yaxis_cms.setText("");

        
javax.swing.GroupLayout jpsidepanelLayout = new javax.swing.GroupLayout(jpsidepanel);
        jpsidepanel.setLayout(jpsidepanelLayout);
        jpsidepanelLayout.setHorizontalGroup(
            jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpsidepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jlrange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlammotype, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addComponent(jlignoredrounds)
                            .addComponent(jlnoofrounds, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlvnoofrounds, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlvignoredrounds, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                    .addComponent(jlvrange, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jlvammotype, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpsidepanelLayout.createSequentialGroup()
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpsidepanelLayout.createSequentialGroup()
                                .addComponent(jlhtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlvmpi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jlvatmtemp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                        .addComponent(jlvhwind, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jlatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpsidepanelLayout.createSequentialGroup()
                                .addComponent(jlxtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128))
                            .addGroup(jpsidepanelLayout.createSequentialGroup()
                                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                                        .addComponent(jlchtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlvchtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlvxtemp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlroundno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpsidepanelLayout.createSequentialGroup()
                                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlstddeviation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(jlmeandeviation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlvmeandeviation)
                                            .addComponent(jl_meandev_xaxis_cms))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlvmeanxaxis, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                            .addComponent(jlv_meandev_xaxis_cms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlxaxis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_stddev_xaxis_cms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlv_stddev_xaxis_cms, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                            .addComponent(jlvstdxaxis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(27, 27, 27)
                                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlyaxis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jl_meandev_yaxis_cms, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                    .addComponent(jlstddevyaxis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jl_stddev_yaxis_cms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlvyaxis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                    .addComponent(jvl_meandev_yaxis_cms, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlvstdyaxis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlv_stddev_yaxis_cms, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpsidepanelLayout.createSequentialGroup()
                                .addComponent(jldatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlvdatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlmpi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpsidepanelLayout.createSequentialGroup()
                                .addComponent(jlcordinates, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlvcordinates, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpsidepanelLayout.setVerticalGroup(
            jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpsidepanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlvrange, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlammotype, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlvammotype, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlrange)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlnoofrounds)
                    .addComponent(jlvnoofrounds))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlignoredrounds)
                    .addComponent(jlvignoredrounds))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlroundno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlvcordinates, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jlcordinates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jldatetime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpsidepanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlvdatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlchtemp)
                    .addComponent(jlvatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlvchtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlhtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlvhwind, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlxtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlvxtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlmpi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlvmpi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlmeandeviation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpsidepanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jlvmeandeviation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpsidepanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlyaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlvyaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlvmeanxaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_meandev_xaxis_cms)
                    .addComponent(jlv_meandev_xaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_meandev_yaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jvl_meandev_yaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jlstddeviation, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlxaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlvstdxaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlstddevyaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlvstdyaxis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_stddev_yaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpsidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jl_stddev_xaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlv_stddev_xaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlv_stddev_yaxis_cms, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
		jblock.setText("Lock");		
		jblock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jblockActionPerformed(evt);
			}

		});
		

		jbcancel.setText("Close");
		jbcancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbcancelActionPerformed(evt);
			}

		});

		jbhelp.setText("Help");

		jlnameofshoot.setText("");
		jlwashout.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
		jlwashout.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("Washout.png")));  // NOI18N
        jlwashout.setText("WASHOUT");
        jlwashout.setToolTipText("");
        jlwashout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jblock, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(307, 307, 307))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpMapframe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlwashout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpsidepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlnameofshoot, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        //.addGap(331, 331, 331))))
                        .addGap(150, 150, 150))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jlnameofshoot, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpsidepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlwashout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jpMapframe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jbcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jblock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        
        
     	pack();
	}// </editor-fold>

	/**
	 * @param args
	 *            the command line arguments
	 */

	/* removed main from here **/

	// Variables declaration - do not modify
	private javax.swing.JButton jbcancel;
	private javax.swing.JButton jbhelp;
	private javax.swing.JButton jblock;
	private javax.swing.JLabel jl_meandev_xaxis_cms;
    private javax.swing.JLabel jl_meandev_yaxis_cms;
    private javax.swing.JLabel jl_stddev_xaxis_cms;
    private javax.swing.JLabel jl_stddev_yaxis_cms;
	private javax.swing.JLabel jlammotype;
	private javax.swing.JLabel jlatmtemp;
	private javax.swing.JLabel jlchtemp;
	private javax.swing.JLabel jlcordinates;
	private javax.swing.JLabel jldatetime;
	private javax.swing.JLabel jlhtemp;
	private javax.swing.JLabel jlignoredrounds;
	private javax.swing.JLabel jlmeandeviation;
	private javax.swing.JLabel jlmpi;
	private javax.swing.JLabel jlnameofshoot;
	private javax.swing.JLabel jlnoofrounds;
	private javax.swing.JLabel jlrange;
	private javax.swing.JLabel jlroundno;
	private javax.swing.JLabel jlstddeviation;
	private javax.swing.JLabel jlstddevyaxis;
	private javax.swing.JLabel jlv_meandev_xaxis_cms;
    private javax.swing.JLabel jlv_stddev_xaxis_cms;
    private javax.swing.JLabel jlv_stddev_yaxis_cms;
	private javax.swing.JLabel jlvammotype;
	private javax.swing.JLabel jlvatmtemp;
	private javax.swing.JLabel jlvchtemp;
	private javax.swing.JLabel jlvcordinates;
	private javax.swing.JLabel jlvdatetime;
	private javax.swing.JLabel jlvhwind;
	private javax.swing.JLabel jlvignoredrounds;
	private javax.swing.JLabel jlvmeandeviation;
	private javax.swing.JLabel jlvmeanxaxis;
	private javax.swing.JLabel jlvmpi;
	private javax.swing.JLabel jlvnoofrounds;
	private javax.swing.JLabel jlvrange;
	private javax.swing.JLabel jlvstdxaxis;
	private javax.swing.JLabel jlvstdyaxis;
	private javax.swing.JLabel jlvxtemp;
	private javax.swing.JLabel jlvyaxis;
	private javax.swing.JLabel jlwashout;
	private javax.swing.JLabel jlxaxis;
	private javax.swing.JLabel jlxtemp;
	private javax.swing.JLabel jlyaxis;
	private javax.swing.JPanel jpMapframe;
	private javax.swing.JPanel jpsidepanel;
	private javax.swing.JLabel jvl_meandev_yaxis_cms;
	// End of variables declaration

	private void jbcancelActionPerformed(java.awt.event.ActionEvent evt) {

		Connection con = null;
		Statement stmt = null; 
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Integer shootid = 0;
		
		try { 

			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
			stmt = con.createStatement();
			stmt1 = con.createStatement(); 
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			
			if (AddorLoad == 0) {				
				String sqlstr = "insert into shoot(ShootName,Range,Target,Tank_No,GunnerName,MeanDevX,MeanDevY,StdDevX,StdDevY,Lock,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate,A_ID,R_ID,TargetDesc) "
						+ "values ('" + nameofshoot + "' , " + jlvrange.getText() + ", " + gridsz + ", " + Tankno +", '" + gunnerName + "', "
						+ jlvmeanxaxis.getText() + "," + jlvyaxis.getText() + "," + jlvstdxaxis.getText() + ","
						+ jlvstdyaxis.getText() + ", '0' , 'admin', '" + LocalDateTime.now() + "', 'admin' ,'" + LocalDateTime.now() + "' ," + AID + " ," + RID + ", '"+ Targetdesc +"')";
												
				stmt.execute(sqlstr);
				
				//Find the shoot_id for the shoot_name
				String sqlstr1 = "Select Shoot_ID from shoot where ShootName = '" + nameofshoot + "'" ;
				ResultSet rsdtls1  = stmt1.executeQuery(sqlstr1);
				
	  			while (rsdtls1.next()) {  				
	  				shootid = rsdtls1.getInt("Shoot_ID");

	  			}	
	  			
	  			
	  				  			
	  			//Insert into Rounds table  			  			
				for (int sqlcntr = 0; sqlcntr <=roundobj.size()-1; sqlcntr++){
					String sql2 = "insert into Rounds(Round_No, XCoord, YCoord, AtmTemp, ChTemp, XWind, HWind,Type, CreatedBy, CreateDate, ModifiedBy, ModifiedDate, Shoot_ID) values ("
								+ roundobj.get(sqlcntr).Round_No +", " + roundobj.get(sqlcntr).XCoord + ", " + roundobj.get(sqlcntr).YCoord + ", "
								+ roundobj.get(sqlcntr).AtmTemp + ", " + roundobj.get(sqlcntr).ChTemp + ", " + roundobj.get(sqlcntr).XWind + ", " 
								+ roundobj.get(sqlcntr).HWind + ", " + "'" + roundobj.get(sqlcntr).Type + "', 'admin' ,'" +  LocalDateTime.now() + "' , 'admin', '"							
								+  LocalDateTime.now() + "' ," + shootid + ")";
					 stmt.execute(sql2);
								
				}

				
				
			} else if (AddorLoad == 1) { 
				
				String sqlstr1 = "Select Shoot_ID from shoot where ShootName = '" + nameofshoot + "'" ;
				ResultSet rsdtls1  = stmt1.executeQuery(sqlstr1);
				
	  			while (rsdtls1.next()) {  				
	  				shootid = rsdtls1.getInt("Shoot_ID");

	  			}
	  			
	  			rsdtls1.close();
	  			
	  			//Insert the new mean and stddev in shoot table
	  			String sqlstr2 = "update shoot set MeanDevX = " + jlvmeanxaxis.getText() + " , MeanDevY = " + jlvyaxis.getText() + " , StdDevX = " + jlvstdxaxis.getText() + " , StdDevY = " + jlvstdyaxis.getText() + " where Shoot_ID = " + shootid;  					
	  			stmt.execute(sqlstr2);
	  			
	  			
				String upd = "Select * from Rounds where Shoot_ID = " + shootid;
				ResultSet rsupdtls = stmt2.executeQuery(upd);
				
				
				for (int sqlcntr = 0; sqlcntr <=roundobj.size()-1; sqlcntr++){
					if (roundobj.get(sqlcntr).getAddUpdate() == "U"){
						while (rsupdtls.next()) { 
							if (rsupdtls.getInt("Round_No") == roundobj.get(sqlcntr).getrno()){
								if ((rsupdtls.getDouble("XCoord") != roundobj.get(sqlcntr).getxcoord()) || (rsupdtls.getDouble("YCoord") != roundobj.get(sqlcntr).getycoord()) || (rsupdtls.getString("Type") != roundobj.get(sqlcntr).gettype())){
									String sql3 = "update Rounds set XCoord = " + roundobj.get(sqlcntr).getxcoord() + ", YCoord = " + roundobj.get(sqlcntr).getycoord() + ", Type = '" + roundobj.get(sqlcntr).gettype() + "' , ModifiedDate = '"+ LocalDateTime.now() +"' where Round_No =  " + roundobj.get(sqlcntr).getrno();							
									stmt3.execute(sql3);
									break;								
								} else {
									break;
								}
									
							}
						}
						
					} else if (roundobj.get(sqlcntr).getAddUpdate() == "A"){
						String sql2 = "insert into Rounds(Round_No, XCoord, YCoord, AtmTemp, ChTemp, XWind, HWind,Type, CreatedBy, CreateDate, ModifiedBy, ModifiedDate, Shoot_ID) values ("
								+ roundobj.get(sqlcntr).Round_No +", " + roundobj.get(sqlcntr).XCoord + ", " + roundobj.get(sqlcntr).YCoord + ", "
								+ roundobj.get(sqlcntr).AtmTemp + ", " + roundobj.get(sqlcntr).ChTemp + ", " + roundobj.get(sqlcntr).XWind + ", " 
								+ roundobj.get(sqlcntr).HWind + ", " + "'" + roundobj.get(sqlcntr).Type + "', 'admin' ,'" +  LocalDateTime.now() + "' , 'admin', '"							
								+  LocalDateTime.now() + "' ," + shootid + ")";							
								stmt3.execute(sql2);
					}
				}
				
			}
			
						
			//stmt1.close();
			//stmt.close();
			//stmt2.close();
			//con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		this.dispose();

	}
	
	
	private void jblockActionPerformed(java.awt.event.ActionEvent evt) {
		if (jblock.getText().equalsIgnoreCase("Lock")){
			jblock.setText("Unlock");
			isLocked = true;
		}
		else{
			jblock.setText("Lock");
			isLocked = false;
		}
		
	}

	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
		
		
		ChartEntity entity = event.getEntity(); 
				
		xyItemEntity = (XYItemEntity) entity;
		int itemIndex = xyItemEntity.getItem();
		
		for (int y =0; y<roundobj.size();y++){					
			if (roundobj.get(y).getxcoord() == (Double)series1.getX(itemIndex) && (roundobj.get(y).getycoord() == (Double)series1.getY(itemIndex))){
			
			//Set the labels in the right side panel with the details of the round
				String s = "Round No " + roundobj.get(y).getrno();
				
				jlroundno.setText("<html><u>" + s + "</u></html>");
				//this.jlvdatetime.setText(roundobj.get(y).getDateTime().toString()); This is not set in the round obj due to conversion error.						
				this.jlvatmtemp	.setText(String.valueOf(roundobj.get(y).getatmtemp()));
				this.jlvchtemp.setText(String.valueOf(roundobj.get(y).getchTemp()));
				this.jlvhwind.setText(String.valueOf(roundobj.get(y).getHWind()));		
				this.jlvxtemp.setText(String.valueOf(roundobj.get(y).getXWind()));
				
				
			break;
			}					
		}				
				
		
		
	}
 
	public void chartMouseMoved(ChartMouseEvent event) {	
	}

	@Override
	public void mouseDragged(MouseEvent e) {		
		movePoint(e);

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {


	}

	@Override
	public void mouseExited(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX(); // initialized point whenenver mouse is pressed
		int y = e.getY();	
				
		EntityCollection entities = this.info.getEntityCollection();			
		ChartMouseEvent cme = new ChartMouseEvent(jfreechart, e, entities.getEntity(x, y));
		ChartEntity entity = cme.getEntity();
		
		
		if ((entity != null) && (entity instanceof XYItemEntity)) {
			int seriesIndex = ((XYItemEntity) entity).getSeriesIndex();			
			
			if (seriesIndex == 0){				
				canMove = false;			
			}else{
				canMove = true;
			}
			
			xyItemEntity = (XYItemEntity) entity;
			moveitemIndex = xyItemEntity.getItem();
						
						
			//4th july kaavya
			if (seriesIndex == 1){
				initialMovePointX = (Double) series1.getX(moveitemIndex);
				initialMovePointY = (Double)series1.getY(moveitemIndex);
			} else {
				initialMovePointX = (Double) series2.getX(moveitemIndex);
				initialMovePointY = (Double)series2.getY(moveitemIndex);
			}
				
		} else if (!(entity instanceof XYItemEntity)) {
			xyItemEntity = null;
			return;
		}
		if (xyItemEntity == null) {
			return; // return if not pressed on any series point
		}
				
		chartPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		canMove = false;
		initialMovePointX = 0;
		initialMovePointY = 0;
		chartPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
		
	}
	
	public void movePoint(MouseEvent me) {		
		
		try{
		
				if (canMove == true && isLocked == false) { 
					
					Point pt = me.getPoint();
					XYPlot xy = jfreechart.getXYPlot();
					Rectangle2D dataArea = chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();
					Point2D p = chartPanel.translateScreenToJava2D(pt);
											
					
					finalMovePointX = xy.getDomainAxis().java2DToValue(p.getX(), dataArea, xy.getDomainAxisEdge());	        
					finalMovePointY = xy.getRangeAxis().java2DToValue(p.getY(),dataArea, xy.getRangeAxisEdge());
					
					/*
					switch (gridsz){
					case 0:
						if (finalMovePointX  >3 || finalMovePointX < 0 ||finalMovePointY >3 || finalMovePointY < 0){
							return;
						}
					case 1:
						if (finalMovePointX > 7 || finalMovePointX < 0 ||finalMovePointY > 7 || finalMovePointY < 0){
							return;
						}
					case 2:	
						if (finalMovePointX > 10 || finalMovePointX < 0 ||finalMovePointY > 10 || finalMovePointY < 0){
							return;
						}
					}
					*/
					
					for (int y =0; y<roundobj.size();y++){						
						if ((roundobj.get(y).getxcoord() == initialMovePointX) && (roundobj.get(y).getycoord() == initialMovePointY)){							
							roundobj.get(y).setxcoord(finalMovePointX);
							roundobj.get(y).setycoord(finalMovePointY);
							series1.clear();
							series2.clear(); //kaavya
							series1.fireSeriesChanged();
							break;							
						}
					}
					
						        	        
					jfreechart.fireChartChanged();
					chartPanel.updateUI();						
										
					initialMovePointX = finalMovePointX; 
					initialMovePointY = finalMovePointY;
					
					finalMovePointX = 0;
					finalMovePointY = 0;
					
				}
			} catch(Exception e){
				System.out.print(e);				
			}		
		
	}
}

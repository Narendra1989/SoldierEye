/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BullsEye;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;




/**
 *
 * @author Manjusha.Ganjewar
 */
public class CompareShoots extends javax.swing.JFrame {
	
	public JFreeChart jfleft = null;
	public JFreeChart jfright = null;
	public JFreeChart jfcenter = null;
	
	private ChartPanel chartPanelleft;
	private ChartPanel chartPanelright;
	private ChartPanel chartPanelcenter;
	
	public XYSeriesCollection XYcollection = new XYSeriesCollection();
	
	public XYSeries series1 = new XYSeries("Series 1"); //to store the left panel mean
	public XYSeries series2 = new XYSeries("Series 2"); //to store the left panel datapoints including ignored points
	
	public XYPlot plot;
	public XYItemRenderer renderer;
	public String[] grade = new String[] {};
	public NumberAxis range;
	public Font font2; 
	
	public XYSeriesCollection XYcollectionright = new XYSeriesCollection();
	
	
	public XYSeries seriesright1 = new XYSeries("Series 3"); //to store the left panel mean
	public XYSeries seriesright2 = new XYSeries("Series 4"); //to store the left panel datapoints including ignored points
	
	public XYPlot plotright;
	public XYItemRenderer rendererright;
	public String[] graderight = new String[] {};
	public NumberAxis rangeright;
	public Font font2right;
	
	//for superimposed JFC
	public XYSeriesCollection XYcollectioncenter = new XYSeriesCollection();
	
	public XYPlot plotcenter;
	public XYItemRenderer renderercenter;
	public String[] gradecenter = new String[] {};
	public NumberAxis rangecenter;
	public Font font2center;
	
	
    /**
     * Creates new form ComapringShoots
     */
    public CompareShoots() {
        initComponents();
        
        
        //Adding jfreechart to the left panel
        jfleft = ChartFactory.createScatterPlot("", "", "", createDataset());
		plot = (XYPlot) this.jfleft.getPlot();
		plot.setDomainPannable(true);
		plot.setRangePannable(true);
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinesVisible(true); 
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.black);
		plot.setDomainGridlinePaint(Color.black);	
		renderer = plot.getRenderer();
		renderer.setBaseSeriesVisibleInLegend(false);
		
		
		renderer.setSeriesShape(0, ShapeUtilities.createDiamond(5)); //mean
		renderer.setSeriesPaint(0, Color.red);
		
		
		renderer.setSeriesShape(1, ShapeUtilities.createDownTriangle(3)); //data points
		renderer.setSeriesPaint(1, Color.red);
		
		
		//For Right Panel Jfreechart
		jfright = ChartFactory.createScatterPlot("", "", "", createDatasetright());
		plotright = (XYPlot) this.jfright.getPlot();
		plotright.setDomainPannable(true);
		plotright.setRangePannable(true);
		plotright.setBackgroundPaint(Color.WHITE);
		plotright.setDomainGridlinesVisible(true); 
		plotright.setRangeGridlinesVisible(true);
		plotright.setRangeGridlinePaint(Color.black);
		plotright.setDomainGridlinePaint(Color.black);	
		rendererright = plotright.getRenderer();
		rendererright.setBaseSeriesVisibleInLegend(false);
				
		rendererright.setSeriesShape(0, ShapeUtilities.createDiamond(5)); //mean
		rendererright.setSeriesPaint(0, Color.blue);
		
		rendererright.setSeriesShape(1, ShapeUtilities.createDownTriangle(3)); //data points
		rendererright.setSeriesPaint(1, Color.blue);
		
		
		//For Center Panel Jfreechart
		jfcenter = ChartFactory.createScatterPlot("", "", "", createDatasetcenter());
		plotcenter = (XYPlot) this.jfcenter.getPlot();
		
		plotcenter.setDomainPannable(true);
		plotcenter.setRangePannable(true);
		plotcenter.setBackgroundPaint(Color.WHITE);
		plotcenter.setDomainGridlinesVisible(true); 
		plotcenter.setRangeGridlinesVisible(true);
		plotcenter.setRangeGridlinePaint(Color.black);
		plotcenter.setDomainGridlinePaint(Color.black);
		
		renderercenter = plotcenter.getRenderer();
		renderercenter.setBaseSeriesVisibleInLegend(false);
		
		renderercenter.setSeriesShape(0, ShapeUtilities.createDiamond(5)); //mean
		renderercenter.setSeriesPaint(0, Color.red); 
		renderercenter.setSeriesShape(1, ShapeUtilities.createDownTriangle(3)); //data points
		renderercenter.setSeriesPaint(1, Color.red);
		
		renderercenter.setSeriesShape(2, ShapeUtilities.createDiamond(5)); //mean
		renderercenter.setSeriesPaint(2, Color.blue);
		renderercenter.setSeriesShape(3, ShapeUtilities.createDownTriangle(3)); //data points		
		renderercenter.setSeriesPaint(3, Color.blue);  
				
    }
    
    
    public XYDataset createDataset() {		
		
		XYcollection.addSeries(series1); // For datapoints		
		XYcollection.addSeries(series2); //For Mean
		return XYcollection;
	}

    
    public XYDataset createDatasetright() {		
		
		XYcollectionright.addSeries(seriesright1); // For datapoints
		XYcollectionright.addSeries(seriesright2); //For Mean
		return XYcollectionright;
	}
    
    
    public XYDataset createDatasetcenter() {		
		
    	XYcollectioncenter.addSeries(series1); // For datapoints
		XYcollectioncenter.addSeries(series2); //For Mean
		
    	XYcollectioncenter.addSeries(seriesright1); // For datapoints
		XYcollectioncenter.addSeries(seriesright2); //For Mean
		
		return XYcollectioncenter;
	}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LefttopPanel = new javax.swing.JPanel();
        jLabelLeft = new javax.swing.JLabel();
        jComboBoxLeft = new javax.swing.JComboBox<>();
        jLabelRangeLeft = new javax.swing.JLabel();
        jvlTarget = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MiddleTopPanel = new javax.swing.JPanel();
        jLabelMiddle = new javax.swing.JLabel();
        jLabelRangeMiddle = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jComboBoxMiddle = new javax.swing.JComboBox<>();
        jLabelTargetMiddle = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        checkbox1 = new java.awt.Checkbox();
        RightTopPanel = new javax.swing.JPanel();
        jLabelRight = new javax.swing.JLabel();
        jComboBoxRight = new javax.swing.JComboBox<>();
        jLabelRangeRight = new javax.swing.JLabel();
        jLabelTargetRight = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        CompareLabel = new javax.swing.JLabel();
        
        jPanel2.setVisible(false); //hiding the center panel 
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LefttopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jLabelLeft.setText("Select Shoot");        
        jLabelLeft.setText("<HTML><U>Select Shoot</U></HTML>");
        jLabelLeft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jComboBoxLeft.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBoxLeft.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBoxLeftActionPerformed(evt);
                }
            });

            jLabelRangeLeft.setText("Range:");

            jLabel4.setText("Target");

            javax.swing.GroupLayout LefttopPanelLayout = new javax.swing.GroupLayout(LefttopPanel);
            LefttopPanel.setLayout(LefttopPanelLayout);
            LefttopPanelLayout.setHorizontalGroup(
                LefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LefttopPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(LefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBoxLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(LefttopPanelLayout.createSequentialGroup()
                            .addComponent(jLabelRangeLeft)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jvlTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            LefttopPanelLayout.setVerticalGroup(
                LefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LefttopPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(LefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addGroup(LefttopPanelLayout.createSequentialGroup()
                            .addComponent(jLabelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBoxLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(LefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelRangeLeft)
                                .addComponent(jvlTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            MiddleTopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            jLabelMiddle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            jLabelMiddle.setText("Select Common Template");
            jLabelMiddle.setToolTipText("");

            jLabelRangeMiddle.setText("  Range:");

            jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1800, 250, 9950, 50));

            jComboBoxMiddle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "3MX3M", "7FTX7FT", "10FTX10FT" }));
            jComboBoxMiddle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	jComboBoxMiddleActionPerformed(evt);
                }
            });

            

            jLabelTargetMiddle.setText("  Target:");

            jLabel9.setText("Ignore MPI Offset");

            javax.swing.GroupLayout MiddleTopPanelLayout = new javax.swing.GroupLayout(MiddleTopPanel);
            MiddleTopPanel.setLayout(MiddleTopPanelLayout);
            MiddleTopPanelLayout.setHorizontalGroup(
                MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MiddleTopPanelLayout.createSequentialGroup()
                    .addGroup(MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MiddleTopPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabelMiddle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(MiddleTopPanelLayout.createSequentialGroup()
                            .addGroup(MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(MiddleTopPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelTargetMiddle)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBoxMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(MiddleTopPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelRangeMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(MiddleTopPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 44, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            MiddleTopPanelLayout.setVerticalGroup(
                MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MiddleTopPanelLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jLabelMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelRangeMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(26, 26, 26)
                    .addGroup(MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTargetMiddle)
                        .addComponent(jComboBoxMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(MiddleTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            RightTopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            jLabelRight.setText("Select Shoot");

            //jComboBoxRight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shoot with a consistent gun 1800M", "Shoot with an inconsistent gun 1800M", "ARJUN APFSDS Shoot 2100M on 3M Target", "ARJUN AP Shoot 1000M 2005/09/04" }));
            jComboBoxRight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
            
            jComboBoxRight.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBoxRightActionPerformed(evt);
                }
            });

            jLabelRangeRight.setText("Range:");

            jLabelTargetRight.setText("Target:");

            javax.swing.GroupLayout RightTopPanelLayout = new javax.swing.GroupLayout(RightTopPanel);
            RightTopPanel.setLayout(RightTopPanelLayout);
            RightTopPanelLayout.setHorizontalGroup(
                RightTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RightTopPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(RightTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RightTopPanelLayout.createSequentialGroup()
                            .addComponent(jComboBoxRight, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(RightTopPanelLayout.createSequentialGroup()
                            .addComponent(jLabelRangeRight)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTargetRight)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(44, Short.MAX_VALUE))
                        .addGroup(RightTopPanelLayout.createSequentialGroup()
                            .addComponent(jLabelRight, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))))
            );
            RightTopPanelLayout.setVerticalGroup(
                RightTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RightTopPanelLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabelRight, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(RightTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(RightTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRangeRight)
                            .addComponent(jLabelTargetRight)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(55, Short.MAX_VALUE))
            );

            jPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 232, Short.MAX_VALUE)
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 353, Short.MAX_VALUE)
            );

            jPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            
            //for setting size
            jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel1.setOpaque(false);
            jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
            
            

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jPanel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            
           //for setting size
            jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel4.setOpaque(false);
            jPanel4.setPreferredSize(new java.awt.Dimension(500, 500));
                        

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 342, Short.MAX_VALUE)
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jButton1.setText("Reset");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton4.setText("Superimpose");
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
                }
            });
            
            jButton4.setEnabled(false);
            jButton6.setText("Close");
            jButton6.setActionCommand("");
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });

            jButton7.setText("Help");
            jButton7.setToolTipText("");
            jButton7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
                }
            });

            CompareLabel.setFont(new java.awt.Font("Gabriola", 0, 28)); // NOI18N
            //CompareLabel.setForeground(new java.awt.Color(0, 51, 51));
            CompareLabel.setForeground(Color.blue);
            CompareLabel.setText("          Compare Shoots");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(CompareLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(LefttopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                //.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(MiddleTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RightTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(CompareLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(MiddleTopPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(LefttopPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RightTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton6)
                        .addComponent(jButton7)
                        .addComponent(jButton1))
                    .addGap(47, 47, 47))
            );
            
            try {
                
                Class.forName("org.h2.Driver");
                 Connection con = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB","Rolta", "");
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("select * from shoot");                
                 while (rs.next()) {
                	 jComboBoxLeft.addItem(rs.getString("ShootName"));            	 
                 	 jComboBoxRight.addItem(rs.getString("ShootName"));      
                 }
       
        
         } catch (Exception e) {  
         		JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
         		System.exit(0);  
         } 

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRightActionPerformed
    	int shootidr = 0;
    	int tgtr = 0; 
    	Statement st;
    	ResultSet rs1;
    	
    	try {

    		Class.forName("org.h2.Driver");
    		Connection con = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
    		st = con.createStatement();
    		rs1 = st.executeQuery("select Shoot_ID, Range, Target, TargetDesc from shoot where ShootName = '" + jComboBoxRight.getSelectedItem() + "'");                
    	    while (rs1.next()) {
    	    	shootidr = rs1.getInt("Shoot_ID");
    	    	tgtr = rs1.getInt("Target");    
    	    	jLabel2.setText(String.valueOf(rs1.getInt("Range")));
    	    	jLabel3.setText(rs1.getString(("TargetDesc")));
    	    	
    	     }  
    	    rs1.close(); 

    	    rangeright = (NumberAxis) plotright.getRangeAxis();		
    	    rangeright.setTickUnit(new NumberTickUnit(1));
    	    rangeright.setInverted(true);
		
    	    font2right = new Font("Dialog", Font.BOLD, 18);		
    	    rangeright.setTickLabelFont(font2right);
			    			
    	    plotRightPanelJFC(tgtr);		
		
		
	 } catch (ClassNotFoundException e) {
		 	e.printStackTrace();
	 } catch (SQLException e) {
	       e.printStackTrace();  
	 }
 }
    

    
    public void plotRightPanelJFC(int tgr){
    	
    	seriesright1.clear();
    	seriesright2.clear();
    	plotright.clearAnnotations();
    	
    	int shootidr = 0;
    	Statement st;
    	ResultSet rs1;
    	
    	try {

    		Class.forName("org.h2.Driver");
    		Connection con = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
    		st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    		
    		rs1 = st.executeQuery("select Shoot_ID, Range, Target, TargetDesc from shoot where ShootName = '" + jComboBoxRight.getSelectedItem() + "'");                
    	    while (rs1.next()) {
    	    	shootidr = rs1.getInt("Shoot_ID");    	    	    
    	    	
    	     }  
    	    rs1.close();  
    	    
    	    
    	if (tgr == 0) {
			
			grade = new String[8];
			grade[0] = "";
			grade[1] = "A";
			grade[2] = "B";
			grade[3] = "C";
			grade[4] = "D";
			grade[5] = "E";
			grade[6] = "F";
			
			SymbolAxis rangeAxisright = new SymbolAxis("", grade);
			rangeAxisright.setTickUnit(new NumberTickUnit(1));
			rangeAxisright.setRange(0, 6);
			rangeright.setRange(0, 6);
			
			rangeAxisright.setAxisLineVisible(false);
			
			plotright.setDomainAxis(rangeAxisright);
			rangeAxisright.setTickLabelFont(font2right);
			rangeAxisright.setTickLabelsVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(3,2.5,3,3.5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(2.5,3,3.5,3);
						
			plotright.addAnnotation(lineAnno);
			plotright.addAnnotation(lineAnno1);
			
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(2,2,2,4);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(4,2,4,4);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(2,2,4,2);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(2,4,4,4);
			
			plotright.addAnnotation(lineAnno2);
			plotright.addAnnotation(lineAnno3);
			plotright.addAnnotation(lineAnno4);
			plotright.addAnnotation(lineAnno5);
			
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
			
					
			plotright.addAnnotation(xyTA);
			plotright.addAnnotation(xyTB);
			plotright.addAnnotation(xyTC);
			plotright.addAnnotation(xyTD);
			plotright.addAnnotation(xyTE);
			plotright.addAnnotation(xyTF);
			
			
		} else if (tgr == 1) {
			
			grade = new String[9];
			grade[0] = "";
			grade[1] = "A";
			grade[2] = "B";
			grade[3] = "C";
			grade[4] = "D";
			grade[5] = "E";
			grade[6] = "F";
			grade[7] = "G";
			
			SymbolAxis rangeAxisright = new SymbolAxis("", grade);
			rangeAxisright.setTickUnit(new NumberTickUnit(1));
			rangeAxisright.setRange(0, 7);
			rangeright.setRange(0, 7);
			plotright.setDomainAxis(rangeAxisright);
			rangeAxisright.setTickLabelFont(font2right);
			rangeAxisright.setTickLabelsVisible(false);
			
			rangeAxisright.setAxisLineVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(3.5,2.5,3.5,4.5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(2.5,3.5,4.5,3.5);
			
			plotright.addAnnotation(lineAnno);
			plotright.addAnnotation(lineAnno1);
						
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(2,2,2,5);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(5,2,5,5);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(2,2,5,2);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(2,5,5,5);
			
			plotright.addAnnotation(lineAnno2);
			plotright.addAnnotation(lineAnno3);
			plotright.addAnnotation(lineAnno4);
			plotright.addAnnotation(lineAnno5);
			
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
			
					
			plotright.addAnnotation(xyTA);
			plotright.addAnnotation(xyTB);
			plotright.addAnnotation(xyTC);
			plotright.addAnnotation(xyTD);
			plotright.addAnnotation(xyTE);
			plotright.addAnnotation(xyTF);
			plotright.addAnnotation(xyTG);
						
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
			
			SymbolAxis rangeAxisright = new SymbolAxis("", grade);
			
			rangeAxisright.setTickUnit(new NumberTickUnit(1));
			rangeAxisright.setRange(0, 10);
			rangeright.setRange(0, 10);
			
			plotright.setDomainAxis(rangeAxisright);
			rangeAxisright.setTickLabelFont(font2right);
			rangeAxisright.setTickLabelsVisible(false);			
			rangeAxisright.setAxisLineVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(4,5,6,5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(5,4,5,6);
			
			plotright.addAnnotation(lineAnno);
			plotright.addAnnotation(lineAnno1);
			
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(3,3,3,7);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(7,3,7,7);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(3,3,7,3);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(3,7,7,7);
			
			plotright.addAnnotation(lineAnno2);
			plotright.addAnnotation(lineAnno3);
			plotright.addAnnotation(lineAnno4);
			plotright.addAnnotation(lineAnno5);
			
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
					
			plotright.addAnnotation(xyTA);
			plotright.addAnnotation(xyTB);
			plotright.addAnnotation(xyTC);
			plotright.addAnnotation(xyTD);
			plotright.addAnnotation(xyTE);
			plotright.addAnnotation(xyTF);
			plotright.addAnnotation(xyTG);
			plotright.addAnnotation(xyTH);
			plotright.addAnnotation(xyTI);
			plotright.addAnnotation(xyTJ);
						        
		}
				
		plotright.setRangeAxis(rangeright);
		plotright.setDomainAxisLocation(0, AxisLocation.TOP_OR_RIGHT);
		plotright.setRangeAxisLocation(0,AxisLocation.BOTTOM_OR_LEFT);
		rangeright.setTickLabelsVisible(false); 

		rangeright.setVisible(false);		
		
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
				
		
		plotright.addAnnotation(xyT1);
		plotright.addAnnotation(xyT2);
		plotright.addAnnotation(xyT3);
		plotright.addAnnotation(xyT4);
		plotright.addAnnotation(xyT5);
		plotright.addAnnotation(xyT6);
		plotright.addAnnotation(xyT7); 
		plotright.addAnnotation(xyT8);
		plotright.addAnnotation(xyT9);	
		
		chartPanelright = new ChartPanel(this.jfright);
		chartPanelright.setMouseWheelEnabled(true);	
		chartPanelright.setMouseZoomable(false);
		chartPanelright.setSize(500, 500);
		
		jPanel4.removeAll();
		jPanel4.setLayout(new BorderLayout());
		jPanel4.add(chartPanelright).setVisible(true);		 
		jPanel4.add(chartPanelright, BorderLayout.CENTER);		
		jPanel4.setBackground(Color.WHITE);		
		
		 //for setting size
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(500, 500));
        
      //Getting the details of rounds from Rounds Table
		 ResultSet rs = st.executeQuery("select * from Rounds where Shoot_ID  = '" + shootidr + "' and Type in ('A', 'F')");                
	     
		 Double xmean= 0.0,ymean=0.0;
		 int rowcnt = 0;
		 rs.last();
		 rowcnt = rs.getRow();				
		 rs.beforeFirst();
		 
		 while (rs.next()) {                 
			 seriesright2.add(rs.getDouble("XCoord"), rs.getDouble("YCoord"));
			 xmean = xmean + rs.getDouble("XCoord");
			 ymean = ymean + rs.getDouble("YCoord");
	     } 
		 xmean = xmean / rowcnt;
		 ymean = ymean / rowcnt;
		 
		 seriesright1.add(xmean,ymean);
		 
   	} catch (ClassNotFoundException e) {
		 	e.printStackTrace();
	 } catch (SQLException e) {
	       e.printStackTrace();  
	 }
    	
    }
    
  
    private void jComboBoxMiddleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLeftActionPerformed
    	jButton4.setEnabled(true);
    	
    	if (jComboBoxMiddle.getSelectedIndex() == 1) { 
    		plotLeftPanelJFC(0);
    		plotRightPanelJFC(0);
    	} else if (jComboBoxMiddle.getSelectedIndex() == 2) {
    		plotLeftPanelJFC(1);
    		plotRightPanelJFC(1);
    	} else {
    		plotLeftPanelJFC(2);
    		plotRightPanelJFC(2);
    	}
    	
    }

        
    private void jComboBoxLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLeftActionPerformed
    
    	int shootid = 0;
    	int tgt = 0; 
    	//series1.clear();
    	//series2.clear();
    	//plot.clearAnnotations();
    	
    	try {

    		Class.forName("org.h2.Driver");
    		Connection con = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
    		Statement st = con.createStatement();
    		ResultSet rs1 = st.executeQuery("select * from shoot where ShootName = '" + jComboBoxLeft.getSelectedItem() + "'");                
    	    while (rs1.next()) {
    	    	shootid = rs1.getInt("Shoot_ID");
    	    	tgt = rs1.getInt("Target");    
    	    	jLabel1.setText(String.valueOf(rs1.getInt("Range")));
    	    	jvlTarget.setText(rs1.getString(("TargetDesc")));
    	    	//series1.add(rs1.getDouble("MeanDevX"), rs1.getDouble("MeanDevX"));
    	    	//series2.add(rs1.getDouble("StdDevX"), rs1.getDouble("StdDevY"));
    	    	
    	     }  
    	    rs1.close();  
    	    
    	    range = (NumberAxis) plot.getRangeAxis();		
    		range.setTickUnit(new NumberTickUnit(1));
    		range.setInverted(true);
    		
    		font2 = new Font("Dialog", Font.BOLD, 18);		
    		range.setTickLabelFont(font2);
    		
    		plotLeftPanelJFC(tgt);
						 
    	    
    	 } catch (ClassNotFoundException e) {
 		 	e.printStackTrace();
 	 } catch (SQLException e) {
 	       e.printStackTrace();  
 	 }
    	
    }
    
    public void plotLeftPanelJFC(int tgl){
    	
    	series1.clear();
    	series2.clear();
    	plot.clearAnnotations();
    	int shootid = 0;
    	
    	try {

    		Class.forName("org.h2.Driver");
    		Connection con = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
    		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);    		
    		ResultSet rs1 = st.executeQuery("select * from shoot where ShootName = '" + jComboBoxLeft.getSelectedItem() + "'");                
    	    while (rs1.next()) {
    	    	shootid = rs1.getInt("Shoot_ID"); 	    	    
    	    	
    	     }  
    	    rs1.close();  
    	    	
		if (tgl == 0) {
			
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
			
			
		} else if (tgl == 1) {
			
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
		
		
		chartPanelleft = new ChartPanel(this.jfleft);
		chartPanelleft.setMouseWheelEnabled(true);	
		chartPanelleft.setMouseZoomable(false);
		chartPanelleft.setSize(500, 500);
				
		jPanel1.removeAll();
		jPanel1.setLayout(new BorderLayout());
		jPanel1.add(chartPanelleft).setVisible(true);		 
		jPanel1.add(chartPanelleft, BorderLayout.CENTER);
		
		jPanel1.setBackground(Color.WHITE);
    	
		//for setting size
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        
        //Getting the details of rounds from Rounds Table
        
        //Class.forName("org.h2.Driver");
    	//Connection con1 = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
    	//Statement st1 = con.createStatement();    		  
    	ResultSet rs = st.executeQuery("select * from Rounds where Shoot_ID  = '" + shootid + "' and Type in ('A', 'F')");

    	Double xmean= 0.0,ymean=0.0;
		int rowcnt = 0;
		rs.last();
		rowcnt = rs.getRow();				
		rs.beforeFirst();
		 
    	while (rs.next()) {                 
    		series2.add(rs.getDouble("XCoord"), rs.getDouble("YCoord"));
    		 xmean = xmean + rs.getDouble("XCoord");
			 ymean = ymean + rs.getDouble("YCoord");
    	}
    	 
    	xmean = xmean / rowcnt;
		 ymean = ymean / rowcnt;
		 
		 series1.add(xmean,ymean);
    	
        } catch (ClassNotFoundException e) {
 		 	e.printStackTrace();
 	 } catch (SQLException e) {
 	       e.printStackTrace();  
 	 }
		 
   }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	jPanel1.setVisible(true);
        jPanel4.setVisible(true);
        jPanel2.setVisible(false);
        jComboBoxMiddle.setSelectedIndex(0);
    	jComboBoxLeftActionPerformed(evt);
    	jComboBoxRightActionPerformed(evt);    	    	
    }

    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    	this.dispose();
    }

    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    	try {    	
    		
    		Runtime.getRuntime().exec("hh.exe ./res/Analysis_of_Shoots.chm");
    	
    	} catch (Exception ex) {
    	ex.printStackTrace();
    	}	
    	
    }


    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jPanel1.setVisible(false);
        jPanel4.setVisible(false);
        jPanel2.setVisible(true);
        
        plotcenter.clearAnnotations();
        
        rangecenter = (NumberAxis) plotcenter.getRangeAxis();		
        rangecenter.setTickUnit(new NumberTickUnit(1));
        rangecenter.setInverted(true);
	
	    font2center = new Font("Dialog", Font.BOLD, 18);		
	    rangecenter.setTickLabelFont(font2center);
	
	    
        //Create a new jfreechart with all points from both shoots.
        if (jComboBoxMiddle.getSelectedIndex() == 1) {
			
			gradecenter = new String[8];
			gradecenter[0] = "";
			gradecenter[1] = "A";
			gradecenter[2] = "B";
			gradecenter[3] = "C";
			gradecenter[4] = "D";
			gradecenter[5] = "E";
			gradecenter[6] = "F";
			
			SymbolAxis rangeAxiscenter = new SymbolAxis("", gradecenter);
			rangeAxiscenter.setTickUnit(new NumberTickUnit(1));
			rangeAxiscenter.setRange(0, 6);
			rangecenter.setRange(0, 6);
			
			rangeAxiscenter.setAxisLineVisible(false);
			
			plotcenter.setDomainAxis(rangeAxiscenter);
			rangeAxiscenter.setTickLabelFont(font2center);
			rangeAxiscenter.setTickLabelsVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(3,2.5,3,3.5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(2.5,3,3.5,3);
						
			plotcenter.addAnnotation(lineAnno);
			plotcenter.addAnnotation(lineAnno1);
			
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(2,2,2,4);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(4,2,4,4);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(2,2,4,2);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(2,4,4,4);
			
			plotcenter.addAnnotation(lineAnno2);
			plotcenter.addAnnotation(lineAnno3);
			plotcenter.addAnnotation(lineAnno4);
			plotcenter.addAnnotation(lineAnno5);
			
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
			
					
			plotcenter.addAnnotation(xyTA);
			plotcenter.addAnnotation(xyTB);
			plotcenter.addAnnotation(xyTC);
			plotcenter.addAnnotation(xyTD);
			plotcenter.addAnnotation(xyTE);
			plotcenter.addAnnotation(xyTF);
			
			
		} else if (jComboBoxMiddle.getSelectedIndex() == 2) {
			
			gradecenter = new String[9];
			gradecenter[0] = "";
			gradecenter[1] = "A";
			gradecenter[2] = "B";
			gradecenter[3] = "C";
			gradecenter[4] = "D";
			gradecenter[5] = "E";
			gradecenter[6] = "F";
			gradecenter[7] = "G";
			
			SymbolAxis rangeAxiscenter = new SymbolAxis("", gradecenter);
			rangeAxiscenter.setTickUnit(new NumberTickUnit(1));
			rangeAxiscenter.setRange(0, 7);
			rangecenter.setRange(0, 7);			
			plotcenter.setDomainAxis(rangeAxiscenter);
			rangeAxiscenter.setTickLabelFont(font2center);
			rangeAxiscenter.setTickLabelsVisible(false);
			
			rangeAxiscenter.setAxisLineVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(3.5,2.5,3.5,4.5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(2.5,3.5,4.5,3.5);
			
			plotcenter.addAnnotation(lineAnno);
			plotcenter.addAnnotation(lineAnno1);
						
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(2,2,2,5);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(5,2,5,5);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(2,2,5,2);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(2,5,5,5);
			
			plotcenter.addAnnotation(lineAnno2);
			plotcenter.addAnnotation(lineAnno3);
			plotcenter.addAnnotation(lineAnno4);
			plotcenter.addAnnotation(lineAnno5);
			
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
			
					
			plotcenter.addAnnotation(xyTA);
			plotcenter.addAnnotation(xyTB);
			plotcenter.addAnnotation(xyTC);
			plotcenter.addAnnotation(xyTD);
			plotcenter.addAnnotation(xyTE);
			plotcenter.addAnnotation(xyTF);
			plotcenter.addAnnotation(xyTG);
						
		} else {
			
			gradecenter = new String[12];
			gradecenter[0] = "";
			gradecenter[1] = "A";
			gradecenter[2] = "B";
			gradecenter[3] = "C";
			gradecenter[4] = "D";
			gradecenter[5] = "E";
			gradecenter[6] = "F";
			gradecenter[7] = "G";
			gradecenter[8] = "H";
			gradecenter[9] = "I";
			gradecenter[10] = "J";
			gradecenter[11] = "K";
			
			SymbolAxis rangeAxiscenter = new SymbolAxis("", gradecenter);
			
			rangeAxiscenter.setTickUnit(new NumberTickUnit(1));
			rangeAxiscenter.setRange(0, 10);
			rangecenter.setRange(0, 10);
			
			plotcenter.setDomainAxis(rangeAxiscenter);
			rangeAxiscenter.setTickLabelFont(font2center);
			rangeAxiscenter.setTickLabelsVisible(false);			
			rangeAxiscenter.setAxisLineVisible(false);
			
			XYLineAnnotation lineAnno = new XYLineAnnotation(4,5,6,5);
			XYLineAnnotation lineAnno1 = new XYLineAnnotation(5,4,5,6);
			
			plotcenter.addAnnotation(lineAnno);
			plotcenter.addAnnotation(lineAnno1);
			
			XYLineAnnotation lineAnno2 = new XYLineAnnotation(3,3,3,7);
			XYLineAnnotation lineAnno3 = new XYLineAnnotation(7,3,7,7);
			XYLineAnnotation lineAnno4 = new XYLineAnnotation(3,3,7,3);
			XYLineAnnotation lineAnno5 = new XYLineAnnotation(3,7,7,7);
			
			plotcenter.addAnnotation(lineAnno2);
			plotcenter.addAnnotation(lineAnno3);
			plotcenter.addAnnotation(lineAnno4);
			plotcenter.addAnnotation(lineAnno5);
			
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
					
			plotcenter.addAnnotation(xyTA);
			plotcenter.addAnnotation(xyTB);
			plotcenter.addAnnotation(xyTC);
			plotcenter.addAnnotation(xyTD);
			plotcenter.addAnnotation(xyTE);
			plotcenter.addAnnotation(xyTF);
			plotcenter.addAnnotation(xyTG);
			plotcenter.addAnnotation(xyTH);
			plotcenter.addAnnotation(xyTI);
			plotcenter.addAnnotation(xyTJ);
						        
		}
				
        plotcenter.setRangeAxis(rangecenter);
        plotcenter.setDomainAxisLocation(0, AxisLocation.TOP_OR_RIGHT);
        plotcenter.setRangeAxisLocation(0,AxisLocation.BOTTOM_OR_LEFT);
		rangecenter.setTickLabelsVisible(false); 

		rangecenter.setVisible(false);		
		
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
				
		
		plotcenter.addAnnotation(xyT1);
		plotcenter.addAnnotation(xyT2);
		plotcenter.addAnnotation(xyT3);
		plotcenter.addAnnotation(xyT4);
		plotcenter.addAnnotation(xyT5);
		plotcenter.addAnnotation(xyT6);
		plotcenter.addAnnotation(xyT7); 
		plotcenter.addAnnotation(xyT8);
		plotcenter.addAnnotation(xyT9);	
		
		chartPanelcenter = new ChartPanel(this.jfcenter);
		chartPanelcenter.setMouseWheelEnabled(true);	
		chartPanelcenter.setMouseZoomable(false);
		chartPanelcenter.setSize(500, 500);
		
		jPanel2.removeAll();
		jPanel2.setLayout(new BorderLayout());
		jPanel2.add(chartPanelcenter).setVisible(true);		 
		jPanel2.add(chartPanelcenter, BorderLayout.CENTER);		
		jPanel2.setBackground(Color.WHITE);		
		
		 //for setting size
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 500));      
	    	
    }

    
    /**
     * 
     * 
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CompareShoots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompareShoots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompareShoots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompareShoots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompareShoots().setVisible(true);
            }
        });
    }
	*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CompareLabel;
    private javax.swing.JPanel LefttopPanel;
    private javax.swing.JPanel MiddleTopPanel;
    private javax.swing.JPanel RightTopPanel;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBoxLeft;
    private javax.swing.JComboBox<String> jComboBoxMiddle;
    private javax.swing.JComboBox<String> jComboBoxRight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLeft;
    private javax.swing.JLabel jLabelMiddle;
    private javax.swing.JLabel jLabelRangeLeft;
    private javax.swing.JLabel jLabelRangeMiddle;
    private javax.swing.JLabel jLabelRangeRight;
    private javax.swing.JLabel jLabelRight;
    private javax.swing.JLabel jLabelTargetMiddle;
    private javax.swing.JLabel jLabelTargetRight;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JLabel jvlTarget;
    // End of variables declaration//GEN-END:variables
}

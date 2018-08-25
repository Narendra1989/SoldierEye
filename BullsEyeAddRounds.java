package BullsEye;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class BullsEyeAddRounds extends JDialog  {
	   /**
	     * Creates new form BullsEyeAddRounds  
	     */
	   
	   public int NoofRnds;
	   public Model m1;
	   public Round R1;
	   
	   public BullsEyeAddRounds  (Model m, Round R) {
	        initComponents();	        
	        
	        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
	        int iCoordX = (objDimension.width - this.getWidth()) / 2;
	        int iCoordY = (objDimension.height - this.getHeight()) / 2;
	        this.setLocation(iCoordX, iCoordY); 

	        
	        m1 = m;
	        R1 = R;
	        
	        jtshootname.setText(m1.getShootname());
	        NoofRnds = m1.getnoOfRounds();	        
	        jtroundno.setValue(NoofRnds + 1);
	        m1.setOkCancel(0); // Set it to zero intially
	        
	    }
	   
	   private void jbcancelActionPerformed(java.awt.event.ActionEvent evt) {		    
		   	
		   	m1.setOkCancel(0);		   			   	
	    	this.dispose();	      
	    	this.getParent().setEnabled(true);	    	
	    } 
	    
	    
	    private void jbokActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {  
	    	try{
	    		
	    	
		    	m1.setOkCancel(1);	    	
		    	m1.setnoOfRounds(NoofRnds+1);	    	
		    	R1.settype("A");
		    	R1.setatmtemp((Integer)jtatmtemp.getValue());
		    	R1.setchtemp((Integer)jtchtemp.getValue());
		    	R1.setXWind((Integer)jtxwind.getValue());
		    	R1.setHWind((Integer)jthwind.getValue());		    	
		    	R1.setDateTime((Date)jtdateandtime.getValue());	
		    	R1.setAddUpdate("A");
		    	R1.setrno(NoofRnds + 1);
	    	} catch(Exception e) {
	    		 e.printStackTrace();
	    		
	    	}
	    	 
		    	this.dispose();	 
		    	this.getParent().setEnabled(true);
	    } 
	    
	   

	    /**
	     * This method is called from within the constructor to initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is always
	     * regenerated by the Form Editor.
	     */
	    @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	    private void initComponents() {

	        jladdrounds = new javax.swing.JLabel();
	        jpaddrounds = new javax.swing.JPanel(); 
	        jlshootname = new javax.swing.JLabel();
	        jlroundno = new javax.swing.JLabel();
	        jldateandtime = new javax.swing.JLabel();
	        jtshootname = new javax.swing.JTextField();
	        jtroundno = new javax.swing.JSpinner();
	        jtdateandtime = new javax.swing.JSpinner();
	        jlrange = new javax.swing.JLabel();
	        jtrange = new javax.swing.JSpinner();
	        jpmetconditions = new javax.swing.JPanel();
	        jlmetconditions = new javax.swing.JLabel();
	        jlxwind = new javax.swing.JLabel();
	        jlatmtemp = new javax.swing.JLabel();
	        jtxwind = new javax.swing.JSpinner();
	        jtatmtemp = new javax.swing.JSpinner();
	        jthwind = new javax.swing.JSpinner();
	        jtchtemp = new javax.swing.JSpinner();
	        jlhwind = new javax.swing.JLabel();
	        jlchtemp = new javax.swing.JLabel();
	        jbok = new javax.swing.JButton();
	        jbcancel = new javax.swing.JButton();
	        jbhelp = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        
	        this.setTitle("Bulls Eye");
	    	java.awt.Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("BULLS.png"));
	    	ImageIcon icon = new ImageIcon(image);
	    	this.setIconImage(icon.getImage());
	    	
	    	
	        jladdrounds.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
	        jladdrounds.setText("Add Rounds");

	        jpaddrounds.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

	        jlshootname.setText("Shoot Name :");	        

	        jlroundno.setText("Round No :");

	        jldateandtime.setText("Date and Time");

	        jtshootname.setNextFocusableComponent(jtdateandtime);
	        
	        jtshootname.setEnabled(false);
	        jtroundno.setEnabled(false);
	        	        
	        jtdateandtime.setModel(new SpinnerDateModel());
	        	        
	        JSpinner.DateEditor de = new JSpinner.DateEditor(jtdateandtime, "yyyy-MM-dd HH:mm:ss");
	        jtdateandtime.setEditor(de);
	        
	        
	        	        
	        jlrange.setText("Range :");

	        jtrange.setModel(new javax.swing.SpinnerListModel(new String[] {"1000"}));
	        jtrange.setEnabled(false);

	        javax.swing.GroupLayout jpaddroundsLayout = new javax.swing.GroupLayout(jpaddrounds);
	        jpaddrounds.setLayout(jpaddroundsLayout);
	        jpaddroundsLayout.setHorizontalGroup(
	            jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jpaddroundsLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addComponent(jlroundno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                   .addComponent(jldateandtime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
	                    .addComponent(jlshootname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jtshootname, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jpaddroundsLayout.createSequentialGroup()
	                        .addComponent(jtroundno, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(93, 93, 93)
	                        .addComponent(jlrange, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(jtrange))
	                    .addComponent(jtdateandtime, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(40, Short.MAX_VALUE))
	        );
	        jpaddroundsLayout.setVerticalGroup(
	            jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jpaddroundsLayout.createSequentialGroup()
	                .addGap(11, 11, 11)
	                .addGroup(jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jlshootname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jtshootname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jlroundno, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jtroundno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jlrange)
	                    .addComponent(jtrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jpaddroundsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jldateandtime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jtdateandtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(17, Short.MAX_VALUE))
	        );

	        jpmetconditions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

	        jlmetconditions.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
	        jlmetconditions.setText("MET CONDITIONS");

	        jlxwind.setText("X Wind:");

	        jlatmtemp.setText("Atm Temp:");

	        jtatmtemp.setModel(new javax.swing.SpinnerNumberModel(35, null, null, 1));

	        jtchtemp.setModel(new javax.swing.SpinnerNumberModel(35, null, null, 1));

	        jlhwind.setText("H Wind:");

	        jlchtemp.setText("CH Temp:");

	        javax.swing.GroupLayout jpmetconditionsLayout = new javax.swing.GroupLayout(jpmetconditions);
	        jpmetconditions.setLayout(jpmetconditionsLayout);
	        jpmetconditionsLayout.setHorizontalGroup(
	            jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jpmetconditionsLayout.createSequentialGroup()
	                .addGap(171, 171, 171)
	                .addComponent(jlmetconditions, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            .addGroup(jpmetconditionsLayout.createSequentialGroup()
	                .addGap(19, 19, 19)
	                .addGroup(jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jlatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jlxwind, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jtxwind)
	                    .addComponent(jtatmtemp, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jlchtemp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jlhwind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jtchtemp, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
	                    .addComponent(jthwind))
	                .addGap(44, 44, 44))
	        );
	        jpmetconditionsLayout.setVerticalGroup(
	            jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jpmetconditionsLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jlmetconditions, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jlxwind, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jtxwind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jthwind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jlhwind))
	                .addGap(18, 18, 18)
	                .addGroup(jpmetconditionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jlatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jtatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jtchtemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jlchtemp))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jbok.setText("OK");
	        jbok.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                try {
						jbokActionPerformed(evt);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}                                
	            }
	        });
	        

	        jbcancel.setText("Cancel");
	        jbcancel.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jbcancelActionPerformed(evt);                                
	            }
	        });

	        jbhelp.setText("Help");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jpaddrounds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(jpmetconditions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	                .addContainerGap())
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(0, 0, Short.MAX_VALUE)
	                .addComponent(jladdrounds, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(154, 154, 154))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(158, 158, 158)
	                .addComponent(jbok)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jbcancel)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jbhelp)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jladdrounds, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jpaddrounds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jpmetconditions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jbcancel)
	                    .addComponent(jbhelp)
	                    .addComponent(jbok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );

	        pack();
	    }// </editor-fold>                        

	    /**
	     * @param args the command line arguments
	     */
	    	    
	    // Variables declaration - do not modify                     
	    private javax.swing.JButton jbcancel;
	    private javax.swing.JButton jbhelp;
	    private javax.swing.JButton jbok;
	    private javax.swing.JLabel jladdrounds;
	    private javax.swing.JLabel jlatmtemp;
	    private javax.swing.JLabel jlchtemp;
	    private javax.swing.JLabel jldateandtime;
	    private javax.swing.JLabel jlhwind;
	    private javax.swing.JLabel jlmetconditions;
	    private javax.swing.JLabel jlrange;
	    private javax.swing.JLabel jlroundno;
	    private javax.swing.JLabel jlshootname;
	    private javax.swing.JLabel jlxwind;
	    private javax.swing.JPanel jpaddrounds;
	    private javax.swing.JPanel jpmetconditions;
	    private javax.swing.JSpinner jtatmtemp;
	    private javax.swing.JSpinner jtchtemp;
	    private javax.swing.JSpinner jtdateandtime;
	    private javax.swing.JSpinner jthwind;
	    private javax.swing.JSpinner jtrange;
	    private javax.swing.JSpinner jtroundno;
	    private javax.swing.JTextField jtshootname;
	    private javax.swing.JSpinner jtxwind;
	    // End of variables declaration                   

}

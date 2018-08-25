package BullsEye;

import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


//import BullsEyeChart;

public class BullsEyeData extends javax.swing.JFrame {
	
	public Model md = new Model();	
	public Round r =  new Round();
	
	
    public BullsEyeData() {
        initComponents();
        
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - this.getWidth()) / 2;
        int iCoordY = (objDimension.height - this.getHeight()) / 2;
        this.setLocation(iCoordX, iCoordY); 

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jprecordnewshoot = new javax.swing.JPanel();
        jlnameofshoot = new javax.swing.JLabel();
        jtnameofshoot = new javax.swing.JTextField();
        jlrange = new javax.swing.JLabel();
        jtrange = new javax.swing.JSpinner();
        jltarget = new javax.swing.JLabel();
        jlammunition = new javax.swing.JLabel();
        jltankbano = new javax.swing.JLabel();
        jttankbano = new javax.swing.JTextField();
        jcbtarget = new javax.swing.JComboBox<>();
        jcbammunition = new javax.swing.JComboBox<>();
        jlgunnerrank = new javax.swing.JLabel();
        jcbgunnerrank = new javax.swing.JComboBox<>();
        jlgunnername = new javax.swing.JLabel();
        jtgunnername = new javax.swing.JTextField();
        jlrecordnewshoot = new javax.swing.JLabel();
        jpfirstrounddata = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlfirstrounddata = new javax.swing.JLabel();
        jldateandtime = new javax.swing.JLabel();
        jlxwind = new javax.swing.JLabel();
        jlhwind = new javax.swing.JLabel();
        jtxwind = new javax.swing.JSpinner();
        jthwind = new javax.swing.JSpinner();
        jlatmtemp = new javax.swing.JLabel();
        jlchtemp = new javax.swing.JLabel();
        jtatmtemp = new javax.swing.JSpinner();
        jtchtemp = new javax.swing.JSpinner();
        jtdateandtime = new javax.swing.JSpinner();
        jbok = new javax.swing.JButton();
        jbcancel = new javax.swing.JButton();
        jbhelp = new javax.swing.JButton();
        
        
       this.setTitle("Bulls Eye"); 
    	java.awt.Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("BULLS.png"));
    	ImageIcon icon = new ImageIcon(image);
    	this.setIconImage(icon.getImage());
    	
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jprecordnewshoot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jlnameofshoot.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlnameofshoot.setLabelFor(jlnameofshoot);
        jlnameofshoot.setText("<html><u>Name of the Shoot:</u></html>");

        jlrange.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlrange.setText("<html><u>Range:</u></html>");
        jlrange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtrange.setModel(new javax.swing.SpinnerNumberModel(1000, null, null, 50));

        jltarget.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jltarget.setText("<html><u>Target:</u></html>");

        jlammunition.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlammunition.setText("<html><u>Ammunition:</u></html>");

        jltankbano.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jltankbano.setText("<html><u>Tank BA no:</u></html>");

        jcbtarget.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3MX3M\t", "7FTX7FT", "10FTX10FT" }));
        jcbtarget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbtargetActionPerformed(evt);
            }
        });

        //jcbammunition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "APFSDS", "HESH", "HEAT", "CO-AXIAL", "MISSILE" }));

        jlgunnerrank.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlgunnerrank.setText("<html><u>Gunner Rank:</u></html>");

        //jcbgunnerrank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lt Col", "Maj", "Capt", "Ris Maj", "Ris", "Nb Ris", "Dfr", "LD", "ALD", "Swr" }));

        jlgunnername.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlgunnername.setText("<html><u>Gunner Name:</u></html>");

        javax.swing.GroupLayout jprecordnewshootLayout = new javax.swing.GroupLayout(jprecordnewshoot);
        jprecordnewshoot.setLayout(jprecordnewshootLayout);
        jprecordnewshootLayout.setHorizontalGroup(
            jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jprecordnewshootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jprecordnewshootLayout.createSequentialGroup()
                        .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlnameofshoot)
                            .addGroup(jprecordnewshootLayout.createSequentialGroup()
                                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlrange, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtrange, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jltarget, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbtarget, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jprecordnewshootLayout.createSequentialGroup()
                                        .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlammunition)
                                            .addComponent(jcbammunition, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(48, 48, 48)
                                        .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jltankbano)
                                            .addComponent(jttankbano)))
                                    .addGroup(jprecordnewshootLayout.createSequentialGroup()
                                        .addComponent(jlgunnername, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtgunnername, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jprecordnewshootLayout.createSequentialGroup()
                        .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jprecordnewshootLayout.createSequentialGroup()
                                .addComponent(jlgunnerrank, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbgunnerrank, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtnameofshoot, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jprecordnewshootLayout.setVerticalGroup(
            jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jprecordnewshootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlnameofshoot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtnameofshoot, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlrange, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jltarget, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlammunition)
                    .addComponent(jltankbano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcbammunition, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jttankbano)
                    .addComponent(jcbtarget)
                    .addComponent(jtrange))
                .addGap(18, 18, 18)
                .addGroup(jprecordnewshootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtgunnername, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jlgunnername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlgunnerrank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbgunnerrank))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlrecordnewshoot.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jlrecordnewshoot.setText("Record New Shoot");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jlfirstrounddata.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jlfirstrounddata.setText("FIRST ROUND DATA");

        jldateandtime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jldateandtime.setText("Date & Time:");

        jlxwind.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlxwind.setText("X Wind :");

        jlhwind.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlhwind.setText("H Wind :");

        jlatmtemp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlatmtemp.setText("Atm temp:");

        jlchtemp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jlchtemp.setText("Ch Temp :");

        jtatmtemp.setModel(new javax.swing.SpinnerNumberModel(35, null, null, 1));

        jtchtemp.setModel(new javax.swing.SpinnerNumberModel(35, null, null, 1));

        jtdateandtime.setModel(new javax.swing.SpinnerDateModel());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jldateandtime, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlxwind, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtdateandtime, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxwind)
                            .addComponent(jtatmtemp, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlhwind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlchtemp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jthwind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtchtemp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlfirstrounddata)
                .addGap(135, 135, 135))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlfirstrounddata)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jldateandtime)
                    .addComponent(jtdateandtime, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlxwind)
                    .addComponent(jlhwind, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jthwind, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxwind, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlchtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtatmtemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtchtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jbok.setText("OK");
        jbok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbokActionPerformed(evt);            	
            }           
                       
        });

        jbcancel.setText("CANCEL");
        jbcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcancelActionPerformed(evt);                                
            }
        });

        jbhelp.setText("HELP");
        jbhelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jbhelpActionPerformed(evt);                                
            }
        });

        javax.swing.GroupLayout jpfirstrounddataLayout = new javax.swing.GroupLayout(jpfirstrounddata);
        jpfirstrounddata.setLayout(jpfirstrounddataLayout);
        jpfirstrounddataLayout.setHorizontalGroup(
            jpfirstrounddataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpfirstrounddataLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpfirstrounddataLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jbok, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jbcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jpfirstrounddataLayout.setVerticalGroup(
            jpfirstrounddataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpfirstrounddataLayout.createSequentialGroup()
               .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpfirstrounddataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbok, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbcancel)
                    .addComponent(jbhelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlrecordnewshoot, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpfirstrounddata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jprecordnewshoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlrecordnewshoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jprecordnewshoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpfirstrounddata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        
        //Fill the static data from the table for Ammunition and Rank combo box.
        Connection condtls = null;
		Statement stmtdtls = null;
		Statement stmtdtls1 = null;
		
        try {

			Class.forName("org.h2.Driver");
			condtls = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
			stmtdtls = condtls.createStatement();
			stmtdtls1 = condtls.createStatement();
			
			ResultSet rsdtls = stmtdtls.executeQuery("select * from Ammunition");
			ResultSet rsdtls1 = stmtdtls1.executeQuery("select * from Rank");
			
			while (rsdtls.next()) {
				jcbammunition.addItem(rsdtls.getString("A_Type"));

			}			
			
			while (rsdtls1.next()) {
				jcbgunnerrank.addItem(rsdtls1.getString("R_Name"));				

			}			
			
			stmtdtls.close();
			stmtdtls1.close();
			condtls.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmtdtls != null)
					stmtdtls.close();
			}  
			catch (SQLException se2) {
			}
			try {
				if (condtls != null)
					condtls.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}		

        pack();
    }// </editor-fold>                        

    private void jbokActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    	//Performing validations
    	
    	if (jtnameofshoot.getText().equals("")){    		
    		JOptionPane.showMessageDialog(null, "Please enter the Shoot Name");
    		System.exit(0);
    	}    			   	
    	
    	/** Load the data in model and round class**/
    	r.setrno(1);    	
    	r.setatmtemp((Integer) this.jtatmtemp.getValue());
    	r.setchtemp((Integer)this.jtchtemp.getValue());
    	r.setHWind((Integer) this.jthwind.getValue());
    	r.setXWind((Integer)this.jtxwind.getValue());
    	r.settype("F");
    	r.setDateTime((Date)this.jtdateandtime.getValue());    	
    	
    	md.setAtmtemp((Integer) this.jtatmtemp.getValue());
    	md.setChtemp((Integer)this.jtchtemp.getValue());
    	md.setHWind((Integer) this.jthwind.getValue());
    	md.setXWind((Integer)this.jtxwind.getValue());
    	
		
    	md.setAmmunition(this.jcbammunition.getSelectedItem().toString());
    	md.setRange(this.jtrange.getValue().toString());    	
    	md.setShootname(this.jtnameofshoot.getText().toString());
    	md.setAddorLoad(0); //Chart form is loaded from Add new shoot form
    	
    	
    	if (jttankbano.getText().trim().isEmpty()){
    		md.settankno(0);
    	}else {
    		md.settankno(Integer.parseInt(jttankbano.getText()));
    	}
    	
    	if (jcbtarget.getSelectedIndex() == 0){
    		md.setgridsize(0);
    		r.setxcoord(3);
        	r.setycoord(3);
    		
    	}else if (jcbtarget.getSelectedIndex() ==1){
    		md.setgridsize(1);
    		r.setxcoord(3.5);
        	r.setycoord(3.5);
    	}else {
    		md.setgridsize(2);
    		r.setxcoord(5.0);
        	r.setycoord(5.0);
    	}
    	    	
    	md.setgunnername(jtgunnername.getText());
    	
    	//Get the AID and RID from database.
    	
    	Connection condtls = null;
  		Statement stmtdtls = null;
  		Statement stmtdtls1 = null;
  		
          try {

  			Class.forName("org.h2.Driver");
  			condtls = DriverManager.getConnection("jdbc:h2:~/BullsEyeDB", "Rolta", "");
  			stmtdtls = condtls.createStatement();
  			stmtdtls1 = condtls.createStatement();
  			
  			ResultSet rsdtls = stmtdtls.executeQuery("select A_ID from Ammunition where A_Type = '"+ jcbammunition.getSelectedItem().toString() + "'" );
  			ResultSet rsdtls1 = stmtdtls1.executeQuery("select R_ID from Rank where R_Name = '"+ jcbgunnerrank.getSelectedItem().toString() + "'" );
  			
  			while (rsdtls.next()) {  				
  				md.setaid(rsdtls.getInt("A_ID"));

  			}			
  			
  			while (rsdtls1.next()) {  				
  				md.setrid(rsdtls1.getInt("R_ID"));

  			}			
  			  			
  			stmtdtls.close();
  			stmtdtls1.close();
  			condtls.close();

  		} catch (ClassNotFoundException e) {
  			e.printStackTrace();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				if (stmtdtls != null)
  					stmtdtls.close();
  			}  
  			catch (SQLException se2) {
  			}
  			try {
  				if (condtls != null)
  					condtls.close();
  			} catch (SQLException se) {
  				se.printStackTrace();
  			}
  		}		

    	
    	//Get
    	this.dispose();
    	BullsEyeChart graph1 = new BullsEyeChart(md,r);    	
    	graph1.show(true);
    }                                    

    private void jcbtargetActionPerformed(java.awt.event.ActionEvent evt) {                                       

    	if (jcbtarget.getSelectedIndex() == 0){
    		md.setgridsize(0);
    		
    	}else if (jcbtarget.getSelectedIndex() ==1){
    		md.setgridsize(1);    		
    	}else {
    		md.setgridsize(2);
    	}
    }                                         

    private void jbcancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	this.dispose();
      
    } 
    
    
    private void jbhelpActionPerformed(java.awt.event.ActionEvent evt) {                                        

    	try {    	
    		
    		Runtime.getRuntime().exec("hh.exe ./res/Recording_Shoots.chm");
    	
    	} catch (Exception ex) {
    	ex.printStackTrace();
    	}
      
    } 

   
    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbcancel;
    private javax.swing.JButton jbhelp;
    private javax.swing.JButton jbok;
    private javax.swing.JComboBox<String> jcbammunition;
    private javax.swing.JComboBox<String> jcbgunnerrank;
    private javax.swing.JComboBox<String> jcbtarget;
    private javax.swing.JLabel jlammunition;
    private javax.swing.JLabel jlatmtemp;
    private javax.swing.JLabel jlchtemp;
    private javax.swing.JLabel jldateandtime;
    private javax.swing.JLabel jlfirstrounddata;
    private javax.swing.JLabel jlgunnername;
    private javax.swing.JLabel jlgunnerrank;
    private javax.swing.JLabel jlhwind;
    private javax.swing.JLabel jlnameofshoot;
    private javax.swing.JLabel jlrange;
    private javax.swing.JLabel jlrecordnewshoot;
    private javax.swing.JLabel jltankbano;
    private javax.swing.JLabel jltarget;
    private javax.swing.JLabel jlxwind;
    private javax.swing.JPanel jpfirstrounddata;
    private javax.swing.JPanel jprecordnewshoot;
    private javax.swing.JSpinner jtatmtemp;
    private javax.swing.JSpinner jtchtemp;
    private javax.swing.JSpinner jtdateandtime;
    private javax.swing.JTextField jtgunnername;
    private javax.swing.JSpinner jthwind;
    private javax.swing.JTextField jtnameofshoot;
    private javax.swing.JSpinner jtrange;
    private javax.swing.JTextField jttankbano;
    private javax.swing.JSpinner jtxwind;
    // End of variables declaration                   
}

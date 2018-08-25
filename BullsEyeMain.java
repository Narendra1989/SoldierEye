package BullsEye;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.*;

public class BullsEyeMain extends javax.swing.JFrame {

	/**
     * Creates new form BullseyeMainPage
     */
    public BullsEyeMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">     
    
    private void initComponents() {
    	this.setTitle("Bulls Eye");
    	java.awt.Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("BULLS.png"));
    	ImageIcon icon = new ImageIcon(image);
    	this.setIconImage(icon.getImage()); 
    	
    	//setSize(getMaximumSize().width,getMaximumSize().height);
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
        Label_BackgroundImage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_File = new javax.swing.JMenu();
        jMenuItem_NewShoot = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_LoadShoot = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_Exit = new javax.swing.JMenuItem();
        jMenu_Edit = new javax.swing.JMenu();
        jMenuItem_ChangeParameter = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_DeleteShoot = new javax.swing.JMenuItem();
        jMenu_View = new javax.swing.JMenu();
        jMenuItem_TransposeShoot = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_CompareShoot = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_MergeShoots = new javax.swing.JMenuItem();
        jMenu_Tool = new javax.swing.JMenu();
        jMenuItem_MovetoArchive = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_RetrivefromArchive = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenu_Tool_Security = new javax.swing.JMenu();
        jMenuItem_LockShoot = new javax.swing.JMenuItem();
        jMenuItem_UnlockShoots = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_ChangePass = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_Options = new javax.swing.JMenuItem();
        jMenu_Help = new javax.swing.JMenu();
        jMenuItem_BullseyeHelp = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_AboutBullsEye = new javax.swing.JMenuItem();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Label_BackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("MainPage.jpg")));
        jMenu_File.setText("File");

        jMenuItem_NewShoot.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("File new.png"))); // NOI18N
        jMenuItem_NewShoot.setText("New Shoot");
        jMenuItem_NewShoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NewShootActionPerformed(evt);
            }
        });
        jMenu_File.add(jMenuItem_NewShoot);
        jMenu_File.add(jSeparator1);

        jMenuItem_LoadShoot.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("File-Add.png"))); // NOI18N
        jMenuItem_LoadShoot.setText("Load Shoot");
        jMenuItem_LoadShoot.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_LoadShootActionPerformed(evt);
            }
        });
        
        jMenu_File.add(jMenuItem_LoadShoot);
        jMenu_File.add(jSeparator2);

        jMenuItem_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_Exit.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("exit.jpg"))); // NOI18N
        jMenuItem_Exit.setText("Exit");
        
        jMenuItem_Exit.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jMenuItem_ExitActionPerformed(evt);
            }
        });
        
        
        jMenu_File.add(jMenuItem_Exit);

        jMenuBar1.add(jMenu_File);

        jMenu_Edit.setText("Edit");

        jMenuItem_ChangeParameter.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("change parameter.png"))); // NOI18N
        jMenuItem_ChangeParameter.setText("Change Parameter");
        jMenu_Edit.add(jMenuItem_ChangeParameter);
        jMenu_Edit.add(jSeparator3);

        jMenuItem_DeleteShoot.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("delete.png"))); // NOI18N
        jMenuItem_DeleteShoot.setText("Delete Shoot");
        jMenu_Edit.add(jMenuItem_DeleteShoot);

        jMenuBar1.add(jMenu_Edit);

        jMenu_View.setText("View");

        jMenuItem_TransposeShoot.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_TransposeShoot.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("iconTranspose.png"))); // NOI18N
        jMenuItem_TransposeShoot.setText("Transpose Shoots");
        jMenu_View.add(jMenuItem_TransposeShoot);
        jMenu_View.add(jSeparator4);

        jMenuItem_CompareShoot.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("compare.png"))); // NOI18N
        jMenuItem_CompareShoot.setText("Compare Shoots");
        jMenuItem_CompareShoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jMenuItem_CompareShootActionPerformed(evt);
            }
        });
        
        jMenu_View.add(jMenuItem_CompareShoot);
        jMenu_View.add(jSeparator5);

        jMenuItem_MergeShoots.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("merge.png"))); // NOI18N
        jMenuItem_MergeShoots.setText("Merge Shoots");
        jMenu_View.add(jMenuItem_MergeShoots);

        jMenuBar1.add(jMenu_View);

        jMenu_Tool.setText("Tool");

        jMenuItem_MovetoArchive.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("archive.png"))); // NOI18N
        jMenuItem_MovetoArchive.setText("Move Shoot to Archive");
        jMenu_Tool.add(jMenuItem_MovetoArchive);
        jMenu_Tool.add(jSeparator6);

        jMenuItem_RetrivefromArchive.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("retrieve.png"))); // NOI18N
        jMenuItem_RetrivefromArchive.setText("Retrieve from Archive");
        jMenuItem_RetrivefromArchive.setToolTipText("");
        jMenu_Tool.add(jMenuItem_RetrivefromArchive);
        jMenu_Tool.add(jSeparator7);

        jMenu_Tool_Security.setText("Security");

        jMenuItem_LockShoot.setText("Lock Shoots");
        jMenu_Tool_Security.add(jMenuItem_LockShoot);

        jMenuItem_UnlockShoots.setText("Unlock Shoots");
        jMenu_Tool_Security.add(jMenuItem_UnlockShoots);
        jMenu_Tool_Security.add(jSeparator10);

        jMenuItem_ChangePass.setText("Change Password");
        jMenu_Tool_Security.add(jMenuItem_ChangePass);

        jMenu_Tool.add(jMenu_Tool_Security);
        jMenu_Tool.add(jSeparator8);

        jMenuItem_Options.setText("Options...");
        jMenu_Tool.add(jMenuItem_Options);

        jMenuBar1.add(jMenu_Tool);

        jMenu_Help.setText("Help");

        jMenuItem_BullseyeHelp.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("help.png"))); // NOI18N
        jMenuItem_BullseyeHelp.setText("Bulls Eye Help");
        jMenuItem_BullseyeHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jMenu_HelpActionPerformed(evt);
            }
        });
        
        
        jMenuItem_BullseyeHelp.setToolTipText("");
        jMenu_Help.add(jMenuItem_BullseyeHelp);
        jMenu_Help.add(jSeparator9);

        jMenuItem_AboutBullsEye.setText("About Bulls Eye");
        jMenu_Help.add(jMenuItem_AboutBullsEye);

        jMenuBar1.add(jMenu_Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_BackgroundImage, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_BackgroundImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 775, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jMenuItem_CompareShootActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        new CompareShoots().setVisible(true);
    }                                       
    
    private void jMenuItem_NewShootActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        new BullsEyeData().setVisible(true);
    }                                                  
    
    private void jMenuItem_LoadShootActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        new BullsEyeLoadSavedShoot().setVisible(true);
    }
    
    private void jMenu_HelpActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	 
    	
    	try {    	
    		//Runtime.getRuntime().exec("hh.exe G:/Bullseye_help/Bullseye_help.chm");
    		
    		//ClassLoader classLoader = this.getClass().getClassLoader();
    		//File configFile=new File(classLoader.getResource("Bullseye_help.chm").getFile());
    		
    		Runtime.getRuntime().exec("hh.exe ./res/Bullseye_Java.chm");
    	
    	} catch (Exception ex) {
    	ex.printStackTrace();
    	}
    	
    }
    
    
    private void jMenuItem_ExitActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        this.dispose();
    }                                       
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BullsEyeMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BullsEyeMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BullsEyeMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BullsEyeMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {            	
                new BullsEyeMain().setVisible(true);            	
            }
        });
    }

    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel Label_BackgroundImage;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_AboutBullsEye;
    private javax.swing.JMenuItem jMenuItem_BullseyeHelp;
    private javax.swing.JMenuItem jMenuItem_ChangeParameter;
    private javax.swing.JMenuItem jMenuItem_ChangePass;
    private javax.swing.JMenuItem jMenuItem_CompareShoot;
    private javax.swing.JMenuItem jMenuItem_DeleteShoot;
    private javax.swing.JMenuItem jMenuItem_Exit;
    private javax.swing.JMenuItem jMenuItem_LoadShoot;
    private javax.swing.JMenuItem jMenuItem_LockShoot;
    private javax.swing.JMenuItem jMenuItem_MergeShoots;
    private javax.swing.JMenuItem jMenuItem_MovetoArchive;
    private javax.swing.JMenuItem jMenuItem_NewShoot;
    private javax.swing.JMenuItem jMenuItem_Options;
    private javax.swing.JMenuItem jMenuItem_RetrivefromArchive;
    private javax.swing.JMenuItem jMenuItem_TransposeShoot;
    private javax.swing.JMenuItem jMenuItem_UnlockShoots;
    private javax.swing.JMenu jMenu_Edit;
    private javax.swing.JMenu jMenu_File;
    private javax.swing.JMenu jMenu_Help;
    private javax.swing.JMenu jMenu_Tool;
    private javax.swing.JMenu jMenu_Tool_Security;
    private javax.swing.JMenu jMenu_View;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;

	
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BackupFolder.java
 *
 * Created on Jan 19, 2010, 11:58:41 AM
 */
package com.forms;

import com.dao.ConnectionManager;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.dao.UserDAO;
import com.helper.FileHelper;
import com.helper.GetSetProperties;
import com.helper.SimpleCrypto;
import com.helper.UserSession;
import com.model.UserModel;
import java.awt.Desktop;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import swinghelper.OptionHelper;
import swinghelper.StringHelper;

/**
 *
 * @author Administrator
 */
public class UploadFiles extends javax.swing.JInternalFrame {
DefaultComboBoxModel    combo1;
    DefaultTableModel tm;
    DefaultListModel  dm;
    private int WARNING_MESSAGE;
    // public static ArrayList arr = null,  arr1 = null;
    MainForm jd;
    List list=null;
    /** Creates new form BackupFolder */
    public UploadFiles() throws Exception {
        System.out.println("BackupFolder");
        initComponents();
//              swinghelper.SwingUtilities.setScreenCenter(this);
        UserDAO u = new UserDAO();

        dm= (DefaultListModel) jList1.getModel();

        u.populateFiles(jTableDisplay1);
        dm.removeAllElements();
        UploadFiles.activeUserList = u.getUsers();
        for (Iterator it = activeUserList.iterator(); it.hasNext();) {
            UserModel object = (UserModel) it.next();
            System.out.println("Adding Element "+object.getFName() + " " + object.getLName());
            dm.addElement(object.getFName() + " " + object.getLName());
        }

        jList1.setModel(dm);
        jList1.updateUI();
        tm = (DefaultTableModel) jTableDisplay1.getModel();
        jTableDisplay1.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                try {
                    int selRow = jTableDisplay1.getSelectedRow();
                    System.out.println("Selected Row " + selRow);
                    String filename = tm.getValueAt(selRow, 2).toString();
                    System.out.println("filename " + filename);

                    File file = new File(GetSetProperties.getProperty("UPLOAD_FILE_DIR")+filename);
                    File parent = new File(file.getParent());

                    if (Desktop.isDesktopSupported() && file.exists()) {
                        try {
                            Desktop.getDesktop().open(parent);
                        } catch (IOException ex) {
                            OptionHelper.showError("Error while opening the file " + file.getAbsolutePath(), "Error");
                        }
                    } else {
                        OptionHelper.showMessage("Desktop Class is not supported by your JDK.");
                        return;
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });


        String query="SELECT  *,date_format( ar.udate, '%d-%b-%y %a') as fudate FROM agentrequest ar,userinfo ui where ar.`status` =-1 and ar.agentid=ui.Userid and ar.ditributorid="+UserSession.loggedInUser.getUserId();
        list=ConnectionManager.getMapList(query);
        System.out.println(" Size "+list.size());
        if(list.size()==0){
            jLabel5.setVisible(false);
            jComboBox1.setVisible(false);
        }else{
            jLabel5.setVisible(true);
            jComboBox1.setVisible(true);

            combo1=(DefaultComboBoxModel) jComboBox1.getModel();

            combo1.removeAllElements();
            combo1.addElement("-----Select-------");
            for (int i = 0; i < list.size(); i++) {
                HashMap param = (HashMap)list.get(i);
                String fname=StringHelper.nullObjectToStringEmpty(param.get("FName"));
                String lname=StringHelper.nullObjectToStringEmpty(param.get("LName"));
                String IPAddress=StringHelper.nullObjectToStringEmpty(param.get("IPAddress"));
                String description=StringHelper.nullObjectToStringEmpty(param.get("description"));
                String formatdate=StringHelper.nullObjectToStringEmpty(param.get("fudate"));
                combo1.addElement(description+"=>"+ fname+" "+lname+"=>"+formatdate);
            }


        }




    }

    public String getUserId(String username) {
        String userId = "-1";
        ArrayList arr = (ArrayList) UploadFiles.activeUserList;
        for (int i = 0; i < arr.size(); i++) {
            UserModel object = (UserModel) arr.get(i);
            String uname = object.getFName() + " " + object.getLName().trim();

            if (uname.equalsIgnoreCase(username.trim())) {
                userId = object.getUserId();
                break;
            }
        }
        return userId;
    }
    public static List activeUserList = null;

    private boolean checkForRepeatedPath() {
        int rCount = jTableDisplay.getRowCount();
        if (rCount >= 1) {
            String[] path1 = new String[rCount];
            for (int i = 0; i < rCount; i++) {
                path1[i] = jTableDisplay.getModel().getValueAt(i, 1).toString();
            }
            for (int i = 0; i < rCount; i++) {
                if (txtDirName.getText().equals(path1[i])) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            GetSetProperties.getProperty("FLDR_ALREDY_EXIST"),
                            "Error Message", WARNING_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jDialog4 = new javax.swing.JDialog();
        jDialog5 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtDirName = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDisplay = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDisplay1 = new JTable() {

            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 1) {
                    tip = getValueAt(rowIndex, colIndex).toString();
                }
                return tip;
            }
        }
        ;
        jLabel6 = new javax.swing.JLabel();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jDialog2Layout = new org.jdesktop.layout.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jDialog3Layout = new org.jdesktop.layout.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jDialog4Layout = new org.jdesktop.layout.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jDialog5Layout = new org.jdesktop.layout.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(UploadFiles.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setToolTipText(resourceMap.getString("title")); // NOI18N
        setName("Peer2peer Trust Framework"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("File To Be Shared"));
        jPanel1.setFont(new java.awt.Font("Verdana", 0, 11));

        btnBrowse.setText("Browse");
        btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBrowseMouseClicked(evt);
            }
        });
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(txtDirName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 309, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnBrowse)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtDirName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnBrowse)
                    .add(btnAdd))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("List of Folders to be Shared"));
        jPanel2.setFont(new java.awt.Font("Verdana", 0, 11));

        jTableDisplay.setFont(new java.awt.Font("Verdana", 0, 10));
        jTableDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr. No", "Folder Path", "Size", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDisplay.setDragEnabled(true);
        jScrollPane1.setViewportView(jTableDisplay);
        jTableDisplay.getColumnModel().getColumn(0).setPreferredWidth(30);

        btnSave.setText("Share File");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRemoveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRemoveMouseEntered(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sharing Details"));

        jLabel1.setText("Encryption Key");

        jLabel2.setText("Share With");

        jLabel3.setText("File Description");

        dm=new javax.swing.DefaultListModel();
        jList1.setModel(  dm
        );
        jScrollPane4.setViewportView(jList1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Agent Requests ");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .add(jLabel1))
                        .add(43, 43, 43)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                            .add(jPasswordField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .add(43, 43, 43))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                            .add(jComboBox1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(btnSave)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                        .add(btnRemove)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnRemove)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnSave))
        );

        jLabel4.setText("Shared Files By You");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableDisplay1.setFont(new java.awt.Font("Verdana", 0, 10));
        jTableDisplay1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr. No", "Name", "Folder Path", "Size", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDisplay1.setDragEnabled(true);
        jScrollPane3.setViewportView(jTableDisplay1);

        jScrollPane5.setViewportView(jScrollPane3);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/download-upload-icons.jpg"))); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .add(502, 502, 502))
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .add(1, 1, 1)
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 204, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("List of Files to share");
        jPanel2.getAccessibleContext().setAccessibleDescription("List of Files to share");

        getAccessibleContext().setAccessibleName(resourceMap.getString("title")); // NOI18N
        getAccessibleContext().setAccessibleDescription(resourceMap.getString("title")); // NOI18N
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static int index = 0;

    // File srcPath = new File("E:/vidio songs/NEW SONGS");
    private void btnRemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveMouseEntered
        // TODO add your handling code here:
}//GEN-LAST:event_btnRemoveMouseEntered

    private void btnRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveMouseClicked
        // TODO add your handling code here:
        int rCount = jTableDisplay.getRowCount();
        if (rCount > 0) {
            for (int i = rCount - 1; i >= 0; i--) {
                DefaultTableModel model = new DefaultTableModel();
                model = (DefaultTableModel) jTableDisplay.getModel();
                if (jTableDisplay.getModel().getValueAt(i, 3) != null && jTableDisplay.getModel().getValueAt(i, 3).toString().equalsIgnoreCase("true")) {
                    model.removeRow(i);
                    --index;
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    GetSetProperties.getProperty("SELECT_FLDR_MESSAGE"),
                    "Error Message", WARNING_MESSAGE);
        }

        rCount = jTableDisplay.getRowCount();
        if (rCount > 0) {
            for (int i = 0; i < rCount; i++) {
                jTableDisplay.getModel().setValueAt(i + 1, i, 0);
            }
        }

    }//GEN-LAST:event_btnRemoveMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
}//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        new SwingWorker<Object, Object>() {

            @Override
            protected Object doInBackground() throws Exception {



                if (jTableDisplay.getRowCount() == 0) {
                    OptionHelper.showMessage("Please select a file to share.");
                    return null;

                }
                if (jTextField1.getText().length() == 0) {
                    OptionHelper.showMessage("Please enter File Description");
                    return null;

                }


                if (jPasswordField1.getText().length() == 0) {
                    OptionHelper.showMessage("Please enter encryption key for your file");
                    return null;

                }
                if ( jList1.getSelectedValues()==null|| jList1.getSelectedValues().length==0) {
                    OptionHelper.showMessage("Please select person names.");
                    return null;

                }


                if (jTableDisplay.getRowCount() > 0) {
                    try {
//                         if(jComboBox1!=null&&combo1!=null&&jComboBox1.getSelectedIndex()>0){
//                             OptionHelper.showMessage("Please select an agent request to process");
//
//                           }

                        MainForm.jProgressBar1.setIndeterminate(true);
                        MainForm.statusLabel.setText("Processing....");
                        UserDAO u = new UserDAO();
                        int rowCount = jTableDisplay.getRowCount();
                        String[] paths = new String[rowCount];
                        String[] size = new String[rowCount];
                        for (int i = 0; i < rowCount; i++) {
                            paths[i] = (String) jTableDisplay.getModel().getValueAt(i, 1);

                            size[i] = (String) jTableDisplay.getModel().getValueAt(i, 2);
                            System.out.println("paths[i]:" + paths[i]);
                            File file = new File(paths[i]);
                            String name = file.getName();
                            name = name.substring(0, name.lastIndexOf("."));
                            String extn = name.substring(name.lastIndexOf(".") + 1);
                            String dstPathnew= "/"+System.currentTimeMillis() + "/" + name + ".dmp";
                            String dstPath = GetSetProperties.getProperty("UPLOAD_FILE_DIR") +dstPathnew;
                            
                            String description = jTextField1.getText();
File dm=new File(GetSetProperties.getProperty("UPLOAD_FILE_DIR") +"/"+System.currentTimeMillis());
dm.mkdir();
                            SimpleCrypto.encryptFile(jPasswordField1.getText(), file.getAbsolutePath(), dstPath);
                            File dst = new File(dstPath);


                            Object[] sel=jList1.getSelectedValues();
boolean success =false;
                            for (int j = 0; sel!=null&&j < sel.length; j++) {
                            Object k = sel[j];
                            String receiverId = getUserId(k.toString());
                            success = u.addRequest(UserSession.loggedInUser.getUserId(), dstPathnew, dst.length() + "", receiverId, jPasswordField1.getText(), file.getName(), description);
                            if(jComboBox1!=null&&combo1!=null&&jComboBox1.getSelectedIndex()>0){
                            HashMap param=(HashMap) list.get(jComboBox1.getSelectedIndex()-1);
                            String idAgentRequest=StringHelper.nullObjectToStringEmpty(param.get("idAgentRequest"));
                            String query="Update agentrequest set `status`=1 where idAgentRequest="+idAgentRequest;
                            ConnectionManager.executeUpdate(query, null);



                           }

                            }



                            if (success) {
                                javax.swing.JOptionPane.showMessageDialog(null,
                                        "File has been saved",
                                        "Error Message", JOptionPane.INFORMATION_MESSAGE);


                                u.populateFiles(jTableDisplay1);
                                MainForm.jProgressBar1.setIndeterminate(false);
                                MainForm.statusLabel.setText("Done....");
                                

                                return null;
                            }


//                    MainForm.setStatus("Encrypted file "+file.getAbsolutePath());


//                    jd.setStatus("File Encrypted Successfully! "+file.getAbsolutePath());

                        }


//               if( u.addInfoToBackupInfo(paths, size, rowCount)){
//                   javax.swing.JOptionPane.showMessageDialog(this, GetSetProperties.getProperty("FILES_SAVED"));
//               }
//                BackupRequest backupRequest = new BackupRequest();
//                backupRequest.setVisible(true);
                    //     this.setCursor(Cursor.getDefaultCursor());
                    } catch (Exception ex) {
                        Logger.getLogger(UploadFiles.class.getName()).log(Level.SEVERE, null, ex);
                        MainForm.jProgressBar1.setIndeterminate(false);
                        MainForm.statusLabel.setText("Done....");
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null,
                            GetSetProperties.getProperty("SELECT_FLDR_TO_SAVE"),
                            "Error Message", WARNING_MESSAGE);
                    MainForm.jProgressBar1.setIndeterminate(false);
                
                }

                return null;
            }

            @Override
            protected void done() {
                    MainForm.statusLabel.setText("Done....");
            }
        }.execute();


}//GEN-LAST:event_btnSaveActionPerformed



    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        try {
            String dirName = txtDirName.getText();

            boolean available = checkForRepeatedPath();
            if (available) {

                try {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    double size = FileHelper.getDirectorySize(txtDirName.getText());
                    double newsize = size / 1024;

                    String finalSize;
                    if (newsize <= 1024) {
                        long y = (long) (newsize * 100);
                        double newsize1 = (double) y / 100;
                        finalSize = Double.toString(newsize1) + " KB";
                    } else if (newsize > 1024 && newsize <= 1024 * 1024) {
                        newsize = newsize / (1024);
                        long y = (long) (newsize * 100);
                        double newsize1 = (double) y / 100;
                        finalSize = Double.toString(newsize1) + " MB";
                    } else {
                        newsize = newsize / (1024 * 1024);
                        long y = (long) (newsize * 100);
                        double newsize1 = (double) y / 100;
                        finalSize = Double.toString(newsize1) + " GB";
                    }

                    DefaultTableModel model = new DefaultTableModel();
                    model = (DefaultTableModel) jTableDisplay.getModel();
                    index++;
                    model.addRow(new Object[]{index, dirName, finalSize});
                    this.setCursor(Cursor.getDefaultCursor());

                } catch (Exception es) {
                  es.printStackTrace();
                }
            }

        } catch (Exception npe) {
                npe.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    GetSetProperties.getProperty("SELECT_FLDR_MESSAGE") + "\n" + GetSetProperties.getProperty("BROWSE_TO_SELECT"),
                    "Error Message", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (JFileChooser.APPROVE_OPTION == chooser.showDialog(this, "Select")) {
            File dir = chooser.getSelectedFile();
            txtDirName.setText(dir.getAbsolutePath());

        }
}//GEN-LAST:event_btnBrowseActionPerformed

    private void btnBrowseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBrowseMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {

                    new UploadFiles().setVisible(true);
                } catch (Exception ex) {
                   ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    public javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableDisplay;
    private javax.swing.JTable jTableDisplay1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtDirName;
    // End of variables declaration//GEN-END:variables
    public JComboBox jcombo;
}

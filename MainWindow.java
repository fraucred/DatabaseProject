package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.Generated;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import entity.Field;
import entity.Record;
import entity.Table;
import logic.LogicMain;

public class MainWindow implements ActionListener, TreeSelectionListener {

	private JFrame mainFrame,generalPopup;
	private JMenuBar menuBar;
	private JMenu menu1, menu2, menu3, menu4, menu5, menu6;
	private JMenuItem reloadItem,exitItem,createItem,openItem,newTabItem,modTabItem,openTabItem,delTabItem;
	private JMenuItem addFieldItem,modFieldItem,delFieldItem,addRecItem,modRecItem,delRecItem,queryRecItem;
	private JMenuItem aboutItem;
	private JPanel mainPanel;
	private JScrollPane panelleft,panelright,panelright2;
	private JToolBar toolbar;
	private JButton newFile, openFile;
	private JSplitPane centerPanel;
	private JLabel statusLabel;
	private String databaseName;
	private JTree tree;
	private DefaultMutableTreeNode root, table, moving=null;
	private JTable showTable,recordTable,showTable2;
	private boolean is_mandatory = false,is_primarykey = false,is_foreignkey = false;
//	private Table movingTable;

	private DefaultTableModel tableModel = new DefaultTableModel(0,0);
	private DefaultTableModel dtm = new DefaultTableModel(0,0),dtm2;
	private JMenuItem test = new JMenuItem("test = swap between FieldView and RecordView");
	public MainWindow() {

		mainFrame = new JFrame("DMBS");
		generalPopup = new JFrame();
		mainPanel = new JPanel(new BorderLayout());
		centerPanel = new JSplitPane();
		mainFrame.setContentPane(mainPanel);
		statusLabel = new JLabel("Running the program.");
		//aboutPopup = new JFrame("About this");
		menuBar = new JMenuBar();
		//textArea = new JTextArea(10,10);
		//aboutTextArea = new JTextArea("This is all about this program.");
		//label = new JLabel(" ");
		//		scrollPane = new JScrollPane(textArea);
		//		scrollPane.setPreferredSize(new Dimension(200,200));
		menu1 = new JMenu("System(S)");
		menu2 = new JMenu("Database(D)");
		menu3 = new JMenu("Table(T)");
		menu5 = new JMenu("Record(R)");
		menu6 = new JMenu("Help(H)");
		menu4 = new JMenu("Field(F)");
		//		key_n = new JMenuItem("New",KeyEvent.VK_N);
		//		key_n.addActionListener(this);
		//		key_o = new JMenuItem("Open",KeyEvent.VK_O);
		//		key_o.addActionListener(this);
		//		key_s = new JMenuItem("Save",KeyEvent.VK_S);
		//		key_s.addActionListener(this);
		//		key_d = new JMenuItem("Save as",KeyEvent.VK_D);
		//		key_d.addActionListener(this);
		//		key_c = new JMenuItem("Close",KeyEvent.VK_C);
		//		key_c.addActionListener(this);
		//		key_e = new JMenuItem("Exit",KeyEvent.VK_E);
		//		key_e.addActionListener(this);
		//		key_a = new JMenuItem("About",KeyEvent.VK_A);
		//		key_a.addActionListener(this);
		//		fileChooser = new JFileChooser();
		//		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//		file = new File("");

		toolbar = new JToolBar();
		newFile = new JButton(new ImageIcon("Images/new.png"));
		openFile = new JButton(new ImageIcon("Images/open.png"));
		reloadItem = new JMenuItem("Refresh = adapt FieldView when choosing one table");
		exitItem = new JMenuItem("Exit(X)");
		createItem = new JMenuItem("Create Database(C)");
		openItem = new JMenuItem("Open Database(O)");
		newTabItem = new JMenuItem("New Table(N)");
		modTabItem = new JMenuItem("Modify Table(M)");
		openTabItem = new JMenuItem("View Record(O)");
		delTabItem = new JMenuItem("Delete Table(D)");
		addFieldItem = new JMenuItem("Add Field(A)");
		modFieldItem = new JMenuItem("Modify Field(M)");
		delFieldItem = new JMenuItem("Delete Field(D)");
		addRecItem = new JMenuItem("Add Record(A)");
		modRecItem = new JMenuItem("Modify Record(M)");
		delRecItem = new JMenuItem("Delete Record(D)");
		queryRecItem = new JMenuItem("Query Record(Q)");
		aboutItem = new JMenuItem("About Database Management System (DMBS)(H)");

		root = new DefaultMutableTreeNode("Root of Database"); // Top element of the tree list
		tree = new JTree(root);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		panelleft = new JScrollPane(tree);

		String[] columnNames = {"Column",
				"Data type",
				"Not Null",
				"Primary Key",
		"Default Value"};
		showTable = new JTable();
		showTable.setFillsViewportHeight(true);
		tableModel.setColumnIdentifiers(columnNames);
		showTable.setModel(tableModel);
		panelright = new JScrollPane(showTable);
		showTable2 = new JTable();
		dtm.setColumnIdentifiers(columnNames);
		showTable2.setFillsViewportHeight(true);
		showTable2.setModel(dtm);
		panelright2 = new JScrollPane(showTable2);
		dtm2 = new DefaultTableModel(0,0);
		showTable2.setModel(dtm2);
		showTable2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(showTable2.getSelectedRow());
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void launch() {

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		mainFrame.setJMenuBar(menuBar);
		generalPopup.setSize(370, 225);


		menu1.setMnemonic('S');
		menu2.setMnemonic('D');
		menu3.setMnemonic('T');
		menu4.setMnemonic('F');
		menu5.setMnemonic('R');
		menu6.setMnemonic('H');
		// new
		menu1.add(reloadItem);
		reloadItem.addActionListener(this);
		//new

		menu1.add(test);
		test.addActionListener(this);

		menu1.add(exitItem);
		exitItem.addActionListener(this);
		exitItem.setMnemonic('X');
		menu2.add(createItem);
		createItem.addActionListener(this);
		createItem.setMnemonic('C');
		menu2.add(openItem);
		openItem.addActionListener(this);
		openItem.setMnemonic('O');
		menu3.add(newTabItem);
		newTabItem.addActionListener(this);
		newTabItem.setMnemonic('N');
		menu3.add(modTabItem);
		modTabItem.addActionListener(this);
		modTabItem.setMnemonic('M');
		menu3.add(openTabItem);
		openTabItem.addActionListener(this);
		openTabItem.setEnabled(true);
		openTabItem.setMnemonic('O');
		menu3.add(delTabItem);
		delTabItem.addActionListener(this);
		delTabItem.setEnabled(false);
		delTabItem.setMnemonic('D');
		delTabItem.hide();
		menu4.add(addFieldItem);
		addFieldItem.addActionListener(this);
		addFieldItem.setMnemonic('A');
		menu4.add(modFieldItem);
		modFieldItem.addActionListener(this);
		modFieldItem.setEnabled(false);
		modFieldItem.setMnemonic('M');
		modFieldItem.hide();
		menu4.add(delFieldItem);
		delFieldItem.addActionListener(this);
		delFieldItem.setEnabled(false);
		delFieldItem.setMnemonic('D');
		delFieldItem.hide();
		menu5.add(addRecItem);
		addRecItem.addActionListener(this);
		addRecItem.setMnemonic('A');
		menu5.add(modRecItem);
		modRecItem.addActionListener(this);
		modRecItem.setEnabled(true);
		modRecItem.setMnemonic('M');
		menu5.add(delRecItem);
		delRecItem.addActionListener(this);
		delRecItem.setEnabled(true);
		delRecItem.setMnemonic('D');
		menu5.add(queryRecItem);
		queryRecItem.addActionListener(this);
		queryRecItem.setEnabled(true);
		queryRecItem.setMnemonic('Q');
//		queryRecItem.hide();
		menu6.add(aboutItem);
		aboutItem.addActionListener(this);
		aboutItem.setMnemonic('H');

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		menuBar.add(menu5);
		menuBar.add(menu6);

		toolbar.add(newFile);
		newFile.setToolTipText("New database");
		newFile.addActionListener(this);
		toolbar.add(openFile);
		openFile.setToolTipText("Open database");
		openFile.addActionListener(this);
		toolbar.setFloatable(false);

		centerPanel.setDividerLocation(200);
		centerPanel.setLeftComponent(panelleft);
		centerPanel.setRightComponent(panelright);


		mainPanel.add(toolbar,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(statusLabel,BorderLayout.SOUTH);

	}

	public static void main(String args[]) {

		MainWindow project = new MainWindow();
		project.launch();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == reloadItem) {
			updateTable();
		}
		if (arg0.getSource() == test) {
			if (centerPanel.getRightComponent() == panelright)
				centerPanel.setRightComponent(panelright2);
			else if (centerPanel.getRightComponent() == panelright2)
				centerPanel.setRightComponent(panelright);
		}
		if (arg0.getSource() == exitItem) {
			System.exit(0);
		}
		if (arg0.getSource() == createItem || arg0.getSource() == newFile) {
			createDatabase();
		}
		if (arg0.getSource() == openItem || arg0.getSource() == openFile) {
			openDatabase();
		}
		if (arg0.getSource() == newTabItem) {
			newTable();
		}
		if (arg0.getSource() == modTabItem) {
			modTable();
		}
		if (arg0.getSource() == openTabItem) {
			try {
				openTable();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (arg0.getSource() == delTabItem) {
			deleteTable();
		}
		if (arg0.getSource() == addFieldItem) {
			addField();
		}
		if (arg0.getSource() == modFieldItem) {
			modField();
		}
		if (arg0.getSource() == delFieldItem) {
			delField();
		}
		if (arg0.getSource() == addRecItem) {
			addRecord();
		}
		if (arg0.getSource() == modRecItem) {
			try {
				modRecord();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (arg0.getSource() == delRecItem) {
			try {
				delRecord();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (arg0.getSource() == queryRecItem) {
			queryRecord();
		}
		if (arg0.getSource() == aboutItem) {
			about();
		}

	}

	private void about() {

		statusLabel.setText("Informations about this DBMS");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("About this database");
		generalPopup.setVisible(true);
	}

	private void queryRecord() 
	{

		statusLabel.setText("Querying record");
		JPanel panel = new JPanel(new BorderLayout());
		JCheckBox all = new JCheckBox("All data");
		JPanel buttonPanel = getButtonPanel(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (all.isSelected()) {
					try {
						openTable();
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				generalPopup.setVisible(false);
			}
		});
		
		JPanel searchPanel = new JPanel();
		
		
		JLabel tableName = new JLabel("Table name");
		JLabel fieldName = new JLabel("Field name");
		LogicMain m_pDocument = LogicMain.getDocument();
		java.util.List<Table> tableList = m_pDocument.getTbArray();
		java.util.List<Field> fieldList;
		JComboBox comboBox = getComboBox("queryRecord");
		JComboBox comboBox2 = getComboBox("queryRecord2");
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 m_pDocument.setEditTable((String) comboBox.getSelectedItem());
				 movingTable = m_pDocument.getTbEntity();
				 
			}
		});
		
		if (m_pDocument.getTbEntity()!=null) {
			fieldList = m_pDocument.getTbEntity().getFieldArray();
		}
 		searchPanel.add(all);
 		searchPanel.add(tableName);
 		searchPanel.add(comboBox);
 		searchPanel.add(fieldName);
 		searchPanel.add(comboBox2);
		
		panel.add(searchPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Query a record");
		generalPopup.setVisible(true);

	}

	private void delRecord() throws SecurityException, IOException {

		//		for (int i = 0; i < dtm2.getRowCount(); i++) {
		//			for (int j = 0; j < dtm2.getColumnCount(); j++) {
		//				System.out.println(dtm2.getValueAt(i, j));
		//			}
		//		}
		int selectedRow = showTable2.getSelectedRow();
		dtm2.removeRow(selectedRow);

		LogicMain m_pDocument = LogicMain.getDocument();
		m_pDocument.setEditTable((String)moving.getUserObject());
		m_pDocument.loadRecord();
		Record record = new Record();
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			for (int j = 0; j < dtm2.getColumnCount(); j++) {
				// we put into hashmap the values
				String fieldName = dtm2.getColumnName(j);
				Object fieldValue = dtm2.getValueAt(i, j);
				record.put(fieldName, fieldValue);
				m_pDocument.replaceRecord(record);
			}
		}
		/*LogicMain m_pDocument = LogicMain.getDocument();
		java.util.List<Record> recordList = m_pDocument.getRecArray();

		statusLabel.setText("Deleting record");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Delete a record");
		generalPopup.setVisible(true);*/
	}

	private void modRecord() throws SecurityException, IOException {				// save the modified record and write it

		statusLabel.setText("Modifying record");
		Record record = new Record();

		LogicMain m_pDocument = LogicMain.getDocument();
		m_pDocument.setEditTable((String)moving.getUserObject());
		m_pDocument.loadRecord();

		for (int i = 0; i < dtm2.getRowCount(); i++) {
			for (int j = 0; j < dtm2.getColumnCount(); j++) {
				// we put into hashmap the values
				String fieldName = dtm2.getColumnName(j);
				Object fieldValue = dtm2.getValueAt(i, j);
				record.put(fieldName, fieldValue);
				//				m_pDocument.replaceRecord(record);
				m_pDocument.insertRecord(record);
			}
		}
		new JOptionPane("ANDREW MADE IT!!");
	}

	private void addRecord() {

		statusLabel.setText("Adding a new record");
		JPanel panel = new JPanel(new BorderLayout());
		String[] column = new String[] {"Field Name", "Field Type", "Value"};
		final Vector<String> newcolumn = new Vector<String>();
		recordTable = new JTable();
		while(dtm.getRowCount()!=0){
			dtm.removeRow(0);
		}
		dtm.setColumnIdentifiers(column);
		recordTable.setModel(dtm);

		LogicMain m_pDocument = LogicMain.getDocument();
		m_pDocument.setEditTable((String)moving.getUserObject());
		java.util.List<Field> fieldlist = m_pDocument.getTbEntity().getFieldArray();
		for(int j = 0; j<fieldlist.size();j++){
			Field field = fieldlist.get(j);
			dtm.addRow(new Object[] {field.getFieldName(), field.getFieldType()});
			newcolumn.add(field.getFieldName());
		}
		//		}
		/*
		Action action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        System.out.println("Row   : " + tcl.getRow());
		        System.out.println("Column: " + tcl.getColumn());
		        System.out.println("Old   : " + tcl.getOldValue());
		        System.out.println("New   : " + tcl.getNewValue());
		        dtm2 = new DefaultTableModel(0,0);
		        dtm2.setColumnIdentifiers(newcolumn);
		        showTable.setModel(dtm2);
		        if(dtm2.getValueAt(, arg1))
		        dtm2.addRow(new Object[] {tcl.getNewValue()});
		    }
		};

		TableCellListener tcl = new TableCellListener(recordTable, action);
		 */
		JPanel buttonpanel = getButtonPanel(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LogicMain m_pDocument = LogicMain.getDocument();
				m_pDocument.setEditTable((String)moving.getUserObject());
				Record record = new Record();

				dtm2.setColumnIdentifiers(newcolumn);

				centerPanel.setRightComponent(panelright2);
				for (int i = 0; i < dtm.getRowCount(); i++) {
					for (int j = 0; j < dtm.getColumnCount(); j++) {
						// we put into hashmap the values
						String fieldName = (String)dtm.getValueAt(i, 0);
						Object fieldValue = dtm.getValueAt(i, 2);
						record.put(fieldName, fieldValue);
					}
				}
				generalPopup.setVisible(false);
				m_pDocument.insertRecord(record);
				try {
					m_pDocument.loadRecord();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				java.util.List<Record> recList= m_pDocument.getRecArray();
				Record recordTest;


				dtm2.setColumnCount(dtm.getRowCount());
				dtm2.setRowCount((recList.size()/dtm.getRowCount()));
				int d = 0;
				for (int l = 0; l < (recList.size()/dtm.getRowCount()); l++ ) {
					for (int k = 0; k < dtm.getRowCount(); k++) {

						dtm2.setValueAt(recList.get(d).get(dtm2.getColumnName(k)), l, k);
						d++;
					}
				}
			}

		});
		JScrollPane fieldpanel = new JScrollPane(recordTable);
		panel.add(fieldpanel,BorderLayout.CENTER);
		panel.add(buttonpanel, BorderLayout.SOUTH);
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Add a new record");
		generalPopup.setVisible(true);
	}

	private void delField() {

		statusLabel.setText("Deleting field");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Delete a field");
		generalPopup.setVisible(true);
	}

	private void modField() {

		statusLabel.setText("Modifying field");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Modify a field");
		generalPopup.setVisible(true);
	}

	private void addField() {

		statusLabel.setText("Adding a new field");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.LINE_AXIS));
		//		fieldPanel.setSize(500, 100);
		JPanel checkPanel = new JPanel();
		final JComboBox dataTypeBox = getComboBox("addField");
		JLabel nameLabel = new JLabel("Name");
		JLabel dataTypeLabel = new JLabel("Data Type");
		JLabel valeurParDefo = new JLabel("Default Value");
		final JTextField nameField = new JTextField(10);
		final JTextField defaultField = new JTextField(10);
		final JCheckBox primary = new JCheckBox("Primary Key");
		final JCheckBox mandatory = new JCheckBox("Not NULL");
		final JCheckBox foreign_key = new JCheckBox("Foreign Key");
		JPanel buttonPanel = getButtonPanel(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (nameField.getText() != null)
				{
					generalPopup.setVisible(false);
					Field field = new Field();
					if(is_mandatory) {
						if (nameField.getText().isEmpty()) {
							field.setFieldName(defaultField.getText());
						} else {
							field.setFieldName(nameField.getText());
						}
					} else {
						field.setFieldName(nameField.getText());
					}
					field.setDefaultValue(defaultField.getText());
					field.setFieldType((String)dataTypeBox.getSelectedItem());
					int integrity = -1;
					if(primary.isSelected())
						integrity = 1;
					else if (mandatory.isSelected())
						integrity = 0;
					field.setFieldIntegrities(integrity);
					LogicMain m_pDocument = LogicMain.getDocument();
					m_pDocument.setEditTable((String)moving.getUserObject());
					field = m_pDocument.addField(field);
					String strError = m_pDocument.getError();
					if (strError != null)
					{
						new JOptionPane(strError);
						m_pDocument.setError("");
					}
					else
					{
						if (m_pDocument.getDatabaseName() != null)
						{
							mainFrame.setTitle(m_pDocument.getDatabaseName());
							createNodes(moving,"field",nameField.getText());
						}
					}
				}
				updateTree();
			}
		});
		fieldPanel.add(nameLabel);
		fieldPanel.add(nameField);
		fieldPanel.add(dataTypeLabel);
		fieldPanel.add(dataTypeBox);
		fieldPanel.add(valeurParDefo);
		fieldPanel.add(defaultField);

		primary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mandatory.setSelected(true);
				is_mandatory = true;
				is_primarykey = true;
				if (foreign_key.isSelected() && primary.isSelected()) {
					foreign_key.setSelected(false);
					primary.setSelected(false);
					is_mandatory = false;
					is_primarykey = false;
				}
			}
		});
		mandatory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				is_mandatory = true;
				if (foreign_key.isSelected() && primary.isSelected()) {
					foreign_key.setSelected(false);
					primary.setSelected(false);
					is_mandatory = false;
					is_primarykey = false;
				}
			}
		});
		foreign_key.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				is_foreignkey = true;
				if (foreign_key.isSelected() && primary.isSelected()) {
					foreign_key.setSelected(false);
					primary.setSelected(false);
					is_mandatory = false;
					is_primarykey = false;
				}
			}
		});

		checkPanel.add(primary);
		checkPanel.add(mandatory);
		checkPanel.add(foreign_key);

		panel.add(fieldPanel,BorderLayout.NORTH);
		panel.add(checkPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);

		setDesignPanel(fieldPanel, "Please complete the form below");
		setDesignPanel(checkPanel, "");

		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Add a new field");
		generalPopup.setVisible(true);

	}

	private void deleteTable() {

		statusLabel.setText("Deleting table");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Delete a table");
		generalPopup.setVisible(true);
	}

	private void openTable() throws SecurityException, IOException {			// refresh the record view on mainwindow

		statusLabel.setText("View record");
		// read the records and display the record View
		centerPanel.setRightComponent(panelright2);
		LogicMain m_pDocument = LogicMain.getDocument();
		m_pDocument.setEditTable(movingTable.getTableName());
		try {
			m_pDocument.loadRecord();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		java.util.List<Record> recList= m_pDocument.getRecArray();
		java.util.List<Field> fieldList= m_pDocument.getTbEntity().getFieldArray();
//		Record recordTest;


		dtm2.setColumnCount(fieldList.size());
		dtm2.setRowCount((recList.size()/fieldList.size()));
		int d = 0;
		for (int l = 0; l < (recList.size()/fieldList.size()); l++ ) {
			for (int k = 0; k < fieldList.size(); k++) {

				dtm2.setValueAt(recList.get(d).get(dtm2.getColumnName(k)), l, k);
				d++;
			}
		}
	}

	private void modTable() {	// function not yet implemented

		statusLabel.setText("Modifying table");
		updateTable();
	}
	public void setDesignPanel(JPanel panel,String title) {
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(title),
				BorderFactory.createEmptyBorder(10,10,10,10)));
	}
	// New starting now...
	private void newTable() {

		statusLabel.setText("Creating a new table");
		JPanel panel = new JPanel(new BorderLayout());
		final JTextField tableName = new JTextField(10);
		JPanel buttonPanel = getButtonPanel(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (tableName.getText() != null)
				{
					generalPopup.setVisible(false);
					LogicMain m_pDocument = LogicMain.getDocument();
					Table pTable = m_pDocument.createTable(tableName.getText());
					String strError = m_pDocument.getError();
					if (strError != null)
					{
						new JOptionPane(strError);
						m_pDocument.setError("");
					}
					else
					{
						if (m_pDocument.getDatabaseName() != null)
						{
							mainFrame.setTitle(m_pDocument.getDatabaseName());
							table = createNodes(root,"table",tableName.getText());
						}
					}
				}
				updateTree();
			}
		});
		JPanel createPanel = new JPanel();


		createPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Please enter the name of the table to be created"),
				BorderFactory.createEmptyBorder(10,10,10,10)));

		createPanel.add(tableName);
		panel.add(createPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);

		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Create a new table");
		generalPopup.setVisible(true);
	}
	public JPanel getButtonPanel(ActionListener okayButtonListener) {
		JPanel buttonPanel = new JPanel();
		JButton okayButton = new JButton("Accept"), cancelButton = new JButton("Cancel");

		buttonPanel.add(okayButton);
		buttonPanel.add(cancelButton);

		buttonPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(10,10,10,10)));
		okayButton.addActionListener(okayButtonListener);
		cancelButton.addActionListener(cancelListener);

		return buttonPanel;
	}
	//open database done!
	private void openDatabase() {

		statusLabel.setText("Opening database");
		JPanel panel = new JPanel(new BorderLayout());
		final JComboBox dbNameBox = getComboBox("openDatabase");
		JPanel buttonPanel = getButtonPanel(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(dbNameBox.getSelectedItem() != null)
				{
					generalPopup.setVisible(false);
					databaseName = (String) dbNameBox.getSelectedItem();
					LogicMain m_pDocument = LogicMain.getDocument();
					m_pDocument.setDatabaseName(databaseName);
					m_pDocument.getDatabase();
					String strError = m_pDocument.getError();
					if (strError != null)
					{
						new JOptionPane(strError);
						m_pDocument.setError("");
					}
					else
					{
						if (m_pDocument.getDatabaseName() != null)
						{
							mainFrame.setTitle(m_pDocument.getDatabaseName());
							root.setUserObject(m_pDocument.getDatabaseName());
							root.removeAllChildren();

							tree.setRootVisible(true);
							m_pDocument.loadTables();
							java.util.List<Table> tables = m_pDocument.getTbArray();
							for(int i=0; i<tables.size(); i++)
							{
								table = createNodes(root, "table", tables.get(i).getTableName());
								if(tables.get(i).getFieldArray()!= null)
								{
									for(int j=0; j<tables.get(i).getFieldArray().size(); j++)
									{
										createNodes(table, "field", tables.get(i).getFieldArray().get(j).getFieldName());
									}
								}

							}

						}
					}
				}
				updateTree();
			}
		});
		JPanel openPanel = new JPanel();


		// to acccess the selected object

		openPanel.add(dbNameBox);

		openPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Please choose the database you want to open :"),
				BorderFactory.createEmptyBorder(10,10,10,10)));

		panel.add(openPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);

		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Open a database");
		generalPopup.setVisible(true);
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox getComboBox(String typeOfComboBox) 
	{
		LogicMain m_pDocument = LogicMain.getDocument();
		JComboBox comboBox = new JComboBox<>();
		if ( typeOfComboBox == "openDatabase") {
			java.util.List<String> dbList = m_pDocument.getDBList();
			String strError = m_pDocument.getError();
			if(strError == null && dbList != null)
			{
				for(int i=0; i<dbList.size(); i++)
					comboBox.addItem(dbList.get(i));
			}
			else
			{
				//				AfxMessagstrError); //popup here JOptionPanel
				m_pDocument.setError("");
			}

		} else if ( typeOfComboBox == "modTable") 
		{
			comboBox.addItem(new Integer(2));
			comboBox.addItem(new Integer(3));
			comboBox.addItem(new Float(2.524));

		} else if ( typeOfComboBox == "openTable") 
		{
			comboBox.addItem(new Integer(2));
			comboBox.addItem(new Integer(3));
			comboBox.addItem(new Float(2.524));
		} else if ( typeOfComboBox == "addField" ) {
			comboBox.addItem("VARCHAR");
			comboBox.addItem("BOOLEAN");
			comboBox.addItem("INTEGER");
			comboBox.addItem("DOUBLE");
			comboBox.addItem("DATETIME");

		} else if ( typeOfComboBox == "queryRecord") {
			java.util.List<Table> tableArray = m_pDocument.getTbArray();
			String strError = m_pDocument.getError();
			if(strError == null && tableArray != null)
			{
				for(int i=0; i<tableArray.size(); i++)
					comboBox.addItem(tableArray.get(i).getTableName());
			}
			else
			{
				//				AfxMessagstrError); //popup here JOptionPanel
				m_pDocument.setError("");
			}
		} else if ( typeOfComboBox == "queryRecord2") {
			java.util.List<Field> fieldArray = null;
			if (m_pDocument.getTbEntity()!=null)
			fieldArray = m_pDocument.getTbEntity().getFieldArray();
			String strError = m_pDocument.getError();
			if(strError == null && fieldArray != null)
			{
				for(int i=0; i<fieldArray.size(); i++)
					comboBox.addItem(fieldArray.get(i).getFieldName());
			}
			else
			{
				//				AfxMessagstrError); //popup here JOptionPanel
				m_pDocument.setError("");
			}
		}
		return comboBox;
	}	

	// create database done!
	private void createDatabase() {

		statusLabel.setText("Creating database");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		JPanel createPanel = new JPanel();
		JButton okayButton = new JButton("Accept"), cancelButton = new JButton("Cancel");
		final JTextField dbnameText = new JTextField(10);

		createPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Please input the name of the database to be created"),
				BorderFactory.createEmptyBorder(10,10,10,10)));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(10,10,10,10)));

		createPanel.add(dbnameText);
		buttonPanel.add(okayButton);

		okayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				databaseName = dbnameText.getText();

				if (databaseName != null)
				{
					generalPopup.setVisible(false);
					LogicMain m_pDocument = LogicMain.getDocument();
					m_pDocument.setDatabaseName(databaseName);
					m_pDocument.createDatabase();
					String strError = m_pDocument.getError();
					if (strError != null)
					{
						//						AfxMessagstrError); //popup here JOptionPanel
						m_pDocument.setError("");
					}
					else
					{
						if (m_pDocument.getDatabaseName() != null)
						{
							mainFrame.setTitle(m_pDocument.getDatabaseName());
							root.removeAllChildren();
							tree.setRootVisible(true);
							root.setUserObject(m_pDocument.getDatabaseName());
							root = createNodes(root,"","");
						}
					}
				}
				updateTree();
			}
		});

		buttonPanel.add(cancelButton);

		cancelButton.addActionListener(cancelListener);

		panel.add(createPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);

		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Create a new database");
		generalPopup.setVisible(true);


	}
	ActionListener cancelListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			generalPopup.setVisible(false);
		}

	};
	private boolean unclearTables;
	private DefaultMutableTreeNode tableNode;
	private Table movingTable = null;
	private boolean unclearFields;

	// to list the tree of the databases / tables ...
	private DefaultMutableTreeNode createNodes(DefaultMutableTreeNode parent, String nodeType, String nameOfNode) {
		if(nodeType.equals("table"))
		{
			return getTreeNode(nameOfNode, parent);
		}
		else if(nodeType=="field")
		{
			return getTreeNode(nameOfNode, parent);
		}
		return parent;

	}

	private DefaultMutableTreeNode getTreeNode(Object anything, DefaultMutableTreeNode parent) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(anything);
		parent.add(node);
		return node;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		LogicMain m_pDocument = LogicMain.getDocument();
		java.util.List<Table> tables = null;
		java.util.List<Field> fieldlist = null;


		if (m_pDocument.getTbArray() != null) {
			tables = m_pDocument.getTbArray();
			//			System.out.println("we can get the list of tables");
			unclearTables = false;
		} else {
			unclearTables = true;
		}
		if (m_pDocument.getTbEntity() != null) {
			if (m_pDocument.getTbEntity().getFieldArray() != null) {
				unclearFields = false;
				unclearTables = false;
				//				System.out.println("we can get the list of fields");
			} else {
				unclearFields = true;
			}
		} else {
		}


		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		moving = node;

		if (moving != null) {

			if (!unclearTables) {
				for (int i =0; i < tables.size(); i++) {
					Table checkTable = tables.get(i);
					if (moving.getUserObject().equals(checkTable.getTableName())) {
						tableNode = moving;
						movingTable = checkTable;
						updateTable();
						break;
					}
				}
			}
			if (!unclearTables && !unclearFields) {

				if (!moving.isNodeDescendant(tableNode)) {
					System.out.println("On affiche la view de la nouvelle table parent");
				}
			}
		}

	}
	
	private void updateTree() {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.reload();
	}
	private void updateTable() {
		if (centerPanel.getRightComponent() == panelright2)
			centerPanel.setRightComponent(panelright);
		LogicMain m_pDocument = LogicMain.getDocument();
		m_pDocument.loadTables();
		java.util.List<Table> tables = m_pDocument.getTbArray();
		for (int i = 0; i < tables.size(); i++) {
			if ( moving.getUserObject().equals(tables.get(i).getTableName()) )
				m_pDocument.setEditTable((String)moving.getUserObject()); 
		}
		if (m_pDocument.getTbEntity() != null) {
			java.util.List<Field> fieldList = m_pDocument.getTbEntity().getFieldArray();
			Field itemTable = new Field();
			while (tableModel.getRowCount()!=0) {
				tableModel.removeRow(0);
			}
			if (fieldList!=null){
				if (is_foreignkey && is_primarykey) {
					is_foreignkey = false;
					is_primarykey = false;
				}
				for (int i=0; i < fieldList.size(); i++) {
					itemTable = fieldList.get(i);
					String[] row = {
							(String) itemTable.fillTable(itemTable, 0, is_foreignkey),
							(String) itemTable.fillTable(itemTable, 1, is_foreignkey),
							(String) itemTable.fillTable(itemTable, 2, is_foreignkey),
							(String) itemTable.fillTable(itemTable, 3, is_foreignkey),
							(String) itemTable.fillTable(itemTable, 4, is_foreignkey)
					};
					tableModel.addRow(row);
				}
				is_foreignkey = false;
				is_mandatory = false;
				is_primarykey = false;
			}
		}
	}
	private void updateView() {
	}
}


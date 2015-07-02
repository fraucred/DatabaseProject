package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;

import entity.Table;
import logic.LogicMain;

public class MainWindow implements ActionListener {

	private JFrame mainFrame,generalPopup;
	private JMenuBar menuBar;
	private JMenu menu1, menu2, menu3, menu4, menu5, menu6;
	private JMenuItem exitItem,createItem,openItem,newTabItem,modTabItem,openTabItem,delTabItem;
	private JMenuItem addFieldItem,modFieldItem,delFieldItem,addRecItem,modRecItem,delRecItem,queryRecItem;
	private JMenuItem aboutItem;
	private JPanel mainPanel;
	private JScrollPane panelleft,panelright;
	private JToolBar toolbar;
	private JButton newFile, openFile;
	private JSplitPane centerPanel;
	private JLabel statusLabel;
	private String databaseName;
//	private ActionListener validCreateListener;
//	private ActionListener cancelListener;

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
		panelleft = new JScrollPane();
		panelright = new JScrollPane();
		toolbar = new JToolBar();
		newFile = new JButton(new ImageIcon("Images/new.png"));
		openFile = new JButton(new ImageIcon("Images/open.png"));
		exitItem = new JMenuItem("Exit(X)");
		createItem = new JMenuItem("Create Database(C)");
		openItem = new JMenuItem("Open Database(O)");
		newTabItem = new JMenuItem("New Table(N)");
		modTabItem = new JMenuItem("Modify Table(M)");
		openTabItem = new JMenuItem("Open Table(O)");
		delTabItem = new JMenuItem("Delete Table(D)");
		addFieldItem = new JMenuItem("Add Field(A)");
		modFieldItem = new JMenuItem("Modify Field(M)");
		delFieldItem = new JMenuItem("Delete Field(D)");
		addRecItem = new JMenuItem("Add Record(A)");
		modRecItem = new JMenuItem("Modify Record(M)");
		delRecItem = new JMenuItem("Delete Record(D)");
		queryRecItem = new JMenuItem("Query Record(Q)");
		aboutItem = new JMenuItem("About Database Management System (DMBS)(H)");

	}

	@SuppressWarnings("deprecation")
	private void launch() {

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		mainFrame.setJMenuBar(menuBar);
		generalPopup.setSize(300, 200);
		//mainFrame.pack();

		menu1.setMnemonic('S');
		menu2.setMnemonic('D');
		menu3.setMnemonic('T');
		menu4.setMnemonic('F');
		menu5.setMnemonic('R');
		menu6.setMnemonic('H');

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
		openTabItem.setEnabled(false);
		openTabItem.setMnemonic('O');
		menu3.add(delTabItem);
		delTabItem.addActionListener(this);
		delTabItem.setEnabled(false);
		delTabItem.setMnemonic('D');
		menu4.add(addFieldItem);
		addFieldItem.addActionListener(this);
		addFieldItem.setMnemonic('A');
		menu4.add(modFieldItem);
		modFieldItem.addActionListener(this);
		modFieldItem.setEnabled(false);
		modFieldItem.setMnemonic('M');
		menu4.add(delFieldItem);
		delFieldItem.addActionListener(this);
		delFieldItem.setEnabled(false);
		delFieldItem.setMnemonic('D');
		menu5.add(addRecItem);
		addRecItem.addActionListener(this);
		addRecItem.setMnemonic('A');
		menu5.add(modRecItem);
		modRecItem.addActionListener(this);
		modRecItem.setEnabled(false);
		modRecItem.setMnemonic('M');
		menu5.add(delRecItem);
		delRecItem.addActionListener(this);
		delRecItem.setEnabled(false);
		delRecItem.setMnemonic('D');
		menu5.add(queryRecItem);
		queryRecItem.addActionListener(this);
		queryRecItem.setEnabled(false);
		queryRecItem.setMnemonic('Q');
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
		centerPanel.disable();
		centerPanel.setLeftComponent(panelleft);
		centerPanel.setRightComponent(panelright);

		//mainPanel.add(menuBar,BorderLayout.PAGE_START);
		mainPanel.add(toolbar,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(statusLabel,BorderLayout.SOUTH);
		//		mainPanel.add(panelright);
		
	}

	public static void main(String args[]) {

		MainWindow project = new MainWindow();
		project.launch();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//		if (arg0.getSource() != newFile && arg0.getSource() != openFile) {
		//			statusLabel.setText(((JMenuItem) arg0.getSource()).getText());
		//		}
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
			openTable();
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
			modRecord();
		}
		if (arg0.getSource() == delRecItem) {
			delRecord();
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

	private void queryRecord() {

		statusLabel.setText("Querying record");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Query a record");
		generalPopup.setVisible(true);
	}

	private void delRecord() {

		statusLabel.setText("Deleting record");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Delete a record");
		generalPopup.setVisible(true);
	}

	private void modRecord() {

		statusLabel.setText("Modifying record");
		JPanel panel = new JPanel(new BorderLayout());
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Modify a record");
		generalPopup.setVisible(true);
	}

	private void addRecord() {

		statusLabel.setText("Adding a new record");
		JPanel panel = new JPanel(new BorderLayout());
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
		JComboBox dataTypeBox = getComboBox("addField");
		JLabel nameLabel = new JLabel("Name");
		JLabel dataTypeLabel = new JLabel("Data Type");
		JLabel valeurParDefo = new JLabel("Default Value");
		JTextField nameField = new JTextField(10), defaultField = new JTextField(10);
		JCheckBox primary = new JCheckBox("Primary Key");
		final JCheckBox mandatory = new JCheckBox("Not NULL");
		JPanel buttonPanel = getButtonPanel(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				generalPopup.setVisible(false);
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
			}
		});
		mandatory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		checkPanel.add(primary);
		checkPanel.add(mandatory);
		
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

	private void openTable() {

		statusLabel.setText("Opening table");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel openTablePanel = new JPanel();
		final JComboBox tableNameBox = getComboBox("openTable");
		JPanel buttonPanel = getButtonPanel(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		openTablePanel.add(tableNameBox);
		panel.add(openTablePanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		setDesignPanel(openTablePanel, "Please choose the table you want to open");
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Open a table");
		generalPopup.setVisible(true);
	}

	private void modTable() {	// function not yet implemented

		statusLabel.setText("Modifying table");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel chooseTablePanel = new JPanel();
		final JComboBox tableNameBox = getComboBox("modTable");
		JPanel buttonPanel = getButtonPanel(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(tableNameBox.getSelectedItem());
			}
		});
		
		chooseTablePanel.add(tableNameBox);
		panel.add(chooseTablePanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		
		setDesignPanel(chooseTablePanel, "Choose the table you want to modify");
		generalPopup.setContentPane(panel);
		generalPopup.setTitle("Modify a table");
		generalPopup.setVisible(true);
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
//						AfxMessagstrError); //popup here JOptionPanel
						m_pDocument.setError("");
					}
					else
					{
						if (m_pDocument.getDatabaseName() != null)
						{
//							CreateDatabaseTree(m_pDocument.getDatabaseName()); arbre
							mainFrame.setTitle(m_pDocument.getDatabaseName());
						}
					}
				}
			
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
//						AfxMessagstrError); //popup here JOptionPanel
						m_pDocument.setError("");
					}
					else
					{
						if (m_pDocument.getDatabaseName() != null)
						{
//							CreateDatabaseTree(m_pDocument.getDatabaseName()); arbre
							mainFrame.setTitle(m_pDocument.getDatabaseName());
						}
					}
				}
				
			}
		});
		JPanel openPanel = new JPanel();
//		JButton okayButton = new JButton("Accept"), cancelButton = new JButton("Cancel");
		
		
//		dbNameBox = getComboBox();
		// dbNameBox.getSelectedItem()
		// to acccess the selected object
		
		openPanel.add(dbNameBox);
//		buttonPanel.add(okayButton);
//		buttonPanel.add(cancelButton);
		
		openPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Please choose the database you want to open :"),
				BorderFactory.createEmptyBorder(10,10,10,10)));
//		buttonPanel.setBorder(BorderFactory.createCompoundBorder(
//				BorderFactory.createTitledBorder(""),
//				BorderFactory.createEmptyBorder(10,10,10,10)));
		
//		okayButton.addActionListener();
//		cancelButton.addActionListener(cancelListener);
		
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
			comboBox.addItem("DOUBLE");
			comboBox.addItem("DATETIME");
		}
		return comboBox;
	}	
	
// create database done!
	private void createDatabase() {

		statusLabel.setText("Creating database");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		JPanel createPanel = new JPanel();
		JButton okayButton = new JButton("ok"), cancelButton = new JButton("Cancel");
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
//							CreateDatabaseTree(m_pDocument.getDatabaseName()); arbre
							mainFrame.setTitle(m_pDocument.getDatabaseName());
						}
					}
				}
			}
		});
		
		buttonPanel.add(cancelButton);
		
//		okayButton.addActionListener(validCreateListener);
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
}

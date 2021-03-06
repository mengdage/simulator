package gwu.csci.arc.gui;

import gwu.csci.arc.CPU;
import gwu.csci.arc.IndexRegister;
import gwu.csci.arc.isa.AIR;
import gwu.csci.arc.isa.AIX;
import gwu.csci.arc.isa.CNVRT;
import gwu.csci.arc.isa.FADD;
import gwu.csci.arc.isa.FOUT;
import gwu.csci.arc.isa.FSUB;
import gwu.csci.arc.isa.JCC;
import gwu.csci.arc.isa.JMP;
import gwu.csci.arc.isa.LDA;
import gwu.csci.arc.isa.LDFR;
import gwu.csci.arc.isa.LDR;
import gwu.csci.arc.isa.LDX;
import gwu.csci.arc.isa.SIR;
import gwu.csci.arc.isa.SIX;
import gwu.csci.arc.isa.SMR;
import gwu.csci.arc.isa.SOB;
import gwu.csci.arc.isa.STFR;
import gwu.csci.arc.isa.STIR;
import gwu.csci.arc.isa.STIX;
import gwu.csci.arc.isa.STR;
import gwu.csci.arc.isa.STX;
import gwu.csci.arc.isa.VADD;
import gwu.csci.arc.isa.VSUB;
import gwu.csci.arc.test.Initialization;
import gwu.csci.arc.test.TestProgram1;
import gwu.csci.arc.utility.Converter;
import gwu.csci.arc.utility.SAssembler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import javax.swing.JCheckBox;

public class UI extends JFrame {
	
	// main UI
	static UI frame;
	
	CPU cpu = CPU.getInstance();
	IndexRegister xr;
	Initialization init = new Initialization();
	static boolean ifInitial = false;
	static boolean ifRandom = true;
	
	// for instruction opcode & address recognition uses
	char[] Instruction = new char[18];
	char[] Opcode = new char[6];
	char[] address = new char[12];
	char[] address_mem = new char[12];
	boolean flag = false;
	String ins = "";

	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JLabel lblR;
	private JTextField SetTxt_R0;
	private JTextField SetTxt_R1;
	private JTextField SetTxt_R2;
	private JTextField SetTxt_R3;
	private JButton SbmBtn_R0;
	private JButton SbmBtn_R1;
	private JButton SbmBtn_R2;
	private JButton SbmBtn_R3;
	private JLabel lblValue;
	private JLabel lblMemoryInput;
	private JLabel lblAddressInput;
	private JTextField SetTxt_Addr;
	private JLabel lblNewLabel_3;
	private JButton SbmBtn_Ins;
	private JLabel lblPc;
	private JTextField SetTxt_PC;
	private JButton SbmBtn_SglStp;
	private JLabel lblR_1;
	private JLabel lblR_2;
	private JLabel lblR_3;
	private JLabel lblR_4;
	private JLabel lblValue_1;
	private JLabel lblPc_1;
	private JLabel lblX;
	private JLabel lblX_1;
	private JLabel lblX_2;
	private JLabel lblMar;
	private JLabel lblMbr;
	private JLabel lblValue_2;
	private JLabel lblOutput;
	private JTextArea DspTxt_Cns;
	private JLabel DspTxt_R0;
	private JLabel DspTxt_R1;
	private JLabel DspTxt_R2;
	private JLabel DspTxt_R3;
	private JLabel DspTxt_PC;
	private JLabel DspTxt_X1;
	private JLabel DspTxt_X2;
	private JLabel DspTxt_X3;
	private JLabel DspTxt_MAR;
	private JLabel DspTxt_MBR;
	private JScrollPane scrollPane;
	private JTextArea SetTxt_Mem;
	private JScrollPane scrollPane_1;
	private JTextArea SetTxt_Ins;
	private JScrollPane scrollPane_2;
	private JButton SbmBtn_PC;
	private JCheckBox chckbxPrepreparation;
	private JTextArea DspTxt_Log;
	private JLabel lblLog;
	private JScrollPane scrollPane_3;
	private JTextField SetTxt_PI;
	private JLabel lblProgramInput;
	private JButton SbmBtn_PI;
	
	AIR air = new AIR(cpu);
	AIX aix = new AIX(cpu);
	STR str = new STR(cpu);
	gwu.csci.arc.isa.IN in = new gwu.csci.arc.isa.IN(cpu);
	SOB sob = new SOB(cpu);
	SIX six = new SIX(cpu);
	LDR ldr = new LDR(cpu);
	SMR smr = new SMR(cpu);
	SIR sir = new SIR(cpu);
	JMP jmp = new JMP(cpu);
	JCC jcc = new JCC(cpu);
	gwu.csci.arc.isa.OUT out = new gwu.csci.arc.isa.OUT(cpu);
	STIR stir = new STIR(cpu);
	STIX stix = new STIX(cpu);
	FADD fadd = new FADD(cpu);
	FSUB fsub = new FSUB(cpu);
	LDFR ldfr = new LDFR(cpu);
	STFR stfr = new STFR(cpu);
	FOUT fout = new FOUT(cpu);
	VADD vadd = new VADD(cpu);
	VSUB vsub = new VSUB(cpu);
	CNVRT cnvrt = new CNVRT(cpu);
	private JButton btnRun;
	private JButton btnProgram;
	
	private int[] numbers = new int[21];
	private int count = 0;
	
	// program 2 components
	private JButton btnBrowse;
	private JLabel lblReadProgram;

	private JLabel lblProgramInput_1;
	private JTextField SetTxt_P2I;
	private JButton SbmBtn_P2I;
	private JButton btnProgram2;
	
	// assembly code read from file
	String program2_2 = "";
	
	String sentence = "";
	char[] sentence_char, Ins_char;
	String keyword = "";
	int snt_count = 0, word_count = 0, char_count = 0;
	private JButton btnBrowse_1;
	private JLabel lblNewLabel_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UI frame = new UI(); //changed to global in order to return instance
					frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		
		setTitle("Simulator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(15, 120, 1280, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{32, 154, 31, 61, 114, 186, 34, 127, 117, 104, 22, 102, 95, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 34, 0, 0, 0, 44, 0, 34, 34, 34, 34, 34, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblValue = new JLabel("Value:");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 1;
		gbc_lblValue.gridy = 0;
		contentPane.add(lblValue, gbc_lblValue);
		
		lblMemoryInput = new JLabel("Memory Input:");
		GridBagConstraints gbc_lblMemoryInput = new GridBagConstraints();
		gbc_lblMemoryInput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblMemoryInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblMemoryInput.gridx = 4;
		gbc_lblMemoryInput.gridy = 0;
		contentPane.add(lblMemoryInput, gbc_lblMemoryInput);
		
		lblNewLabel_3 = new JLabel("Instruction Input:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 7;
		gbc_lblNewLabel_3.gridy = 0;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblLog = new JLabel("Log:");
		GridBagConstraints gbc_lblLog = new GridBagConstraints();
		gbc_lblLog.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblLog.insets = new Insets(0, 0, 5, 5);
		gbc_lblLog.gridx = 11;
		gbc_lblLog.gridy = 0;
		contentPane.add(lblLog, gbc_lblLog);
		
		JLabel lblNewLabel_1 = new JLabel("R0:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
				
		SetTxt_R0 = new JTextField();
		SetTxt_R0.setToolTipText("Set value of R0");
		SetTxt_R0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String Current = SetTxt_R0.getText();
				
				// restrict input size and format for R0
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		
		GridBagConstraints gbc_SetTxt_R0 = new GridBagConstraints();
		gbc_SetTxt_R0.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R0.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R0.gridx = 1;
		gbc_SetTxt_R0.gridy = 1;
		contentPane.add(SetTxt_R0, gbc_SetTxt_R0);
		SetTxt_R0.setColumns(5);
		
		SbmBtn_R0 = new JButton("Submit");
		SbmBtn_R0.setToolTipText("Submit R0");
		SbmBtn_R0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				// overwrite R0
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R0.getText().toCharArray();
				char[] id = {'0', '0'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R0.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R0 = new GridBagConstraints();
		gbc_SbmBtn_R0.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R0.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R0.gridx = 2;
		gbc_SbmBtn_R0.gridy = 1;
		contentPane.add(SbmBtn_R0, gbc_SbmBtn_R0);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 4;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		SetTxt_Mem = new JTextArea();
		SetTxt_Mem.setToolTipText("Set memory value");
		SetTxt_Mem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {		
				String Current = SetTxt_Mem.getText();
				
				// restrict input size and format for memory input
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		SetTxt_Mem.setWrapStyleWord(true);
		SetTxt_Mem.setLineWrap(true);
		scrollPane_1.setViewportView(SetTxt_Mem);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridheight = 4;
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.gridx = 7;
		gbc_scrollPane_2.gridy = 1;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);
		
		SetTxt_Ins = new JTextArea();
		SetTxt_Ins.setColumns(18);
		scrollPane_2.setViewportView(SetTxt_Ins);
		SetTxt_Ins.setToolTipText("Set 18-bit instruction code");
		SetTxt_Ins.setWrapStyleWord(true);
		SetTxt_Ins.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
//				String Current = SetTxt_Ins.getText();
//				if (Current.length() == 18) e.setKeyChar((char) 00);
//				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		SetTxt_Ins.setLineWrap(true);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridheight = 14;
		gbc_scrollPane_3.gridx = 11;
		gbc_scrollPane_3.gridy = 1;
		contentPane.add(scrollPane_3, gbc_scrollPane_3);
		
		DspTxt_Log = new JTextArea();
		DspTxt_Log.setToolTipText("Log output");
		DspTxt_Log.setEditable(false);
		scrollPane_3.setViewportView(DspTxt_Log);
		DspTxt_Log.setWrapStyleWord(true);
		DspTxt_Log.setLineWrap(true);
		
		lblNewLabel_2 = new JLabel("R1:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		SetTxt_R1 = new JTextField();
		SetTxt_R1.setToolTipText("Set value of R1");
		SetTxt_R1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_R1.getText();
				
				// restrict input size and format for R1
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_R1 = new GridBagConstraints();
		gbc_SetTxt_R1.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R1.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R1.gridx = 1;
		gbc_SetTxt_R1.gridy = 2;
		contentPane.add(SetTxt_R1, gbc_SetTxt_R1);
		SetTxt_R1.setColumns(5);
		
		SbmBtn_R1 = new JButton("Submit");
		SbmBtn_R1.setToolTipText("Submit R1");
		SbmBtn_R1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R1
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R1.getText().toCharArray();
				char[] id = {'0', '1'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R1.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R1 = new GridBagConstraints();
		gbc_SbmBtn_R1.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R1.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R1.gridx = 2;
		gbc_SbmBtn_R1.gridy = 2;
		contentPane.add(SbmBtn_R1, gbc_SbmBtn_R1);
		
		JLabel lblNewLabel = new JLabel("R2:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		SetTxt_R2 = new JTextField();
		SetTxt_R2.setToolTipText("Set value of R2");
		SetTxt_R2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_R2.getText();
				
				// restrict input size and format for R2
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_R2 = new GridBagConstraints();
		gbc_SetTxt_R2.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R2.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R2.gridx = 1;
		gbc_SetTxt_R2.gridy = 3;
		contentPane.add(SetTxt_R2, gbc_SetTxt_R2);
		SetTxt_R2.setColumns(5);
		
		SbmBtn_R2 = new JButton("Submit");
		SbmBtn_R2.setToolTipText("Submit R2");
		SbmBtn_R2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R2
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R2.getText().toCharArray();
				char[] id = {'1', '0'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R2.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R2 = new GridBagConstraints();
		gbc_SbmBtn_R2.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R2.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R2.gridx = 2;
		gbc_SbmBtn_R2.gridy = 3;
		contentPane.add(SbmBtn_R2, gbc_SbmBtn_R2);
		
		lblR = new JLabel("R3:");
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.anchor = GridBagConstraints.EAST;
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 0;
		gbc_lblR.gridy = 4;
		contentPane.add(lblR, gbc_lblR);
		
		SetTxt_R3 = new JTextField();
		SetTxt_R3.setToolTipText("Set value of R3");
		SetTxt_R3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_R3.getText();
				
				// restrict input size and format for R3
				if (Current.length() == 18) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		GridBagConstraints gbc_SetTxt_R3 = new GridBagConstraints();
		gbc_SetTxt_R3.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_R3.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_R3.gridx = 1;
		gbc_SetTxt_R3.gridy = 4;
		contentPane.add(SetTxt_R3, gbc_SetTxt_R3);
		SetTxt_R3.setColumns(5);
		
		SbmBtn_R3 = new JButton("Submit");
		SbmBtn_R3.setToolTipText("Submit R3");
		SbmBtn_R3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite R3
				char[] Current = new char[18];
				char[] Current_dsp = new char[18];
				Current = SetTxt_R3.getText().toCharArray();
				char[] id = {'1', '1'};
				
				cpu.writeGPR(Current, id, Current.length);
				
				cpu.readGPR(Current_dsp, id, Current_dsp.length);
				DspTxt_R3.setText(new String(Current_dsp));
			}
		});
		GridBagConstraints gbc_SbmBtn_R3 = new GridBagConstraints();
		gbc_SbmBtn_R3.anchor = GridBagConstraints.WEST;
		gbc_SbmBtn_R3.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_R3.gridx = 2;
		gbc_SbmBtn_R3.gridy = 4;
		contentPane.add(SbmBtn_R3, gbc_SbmBtn_R3);
		
		lblAddressInput = new JLabel("Address Input:");
		GridBagConstraints gbc_lblAddressInput = new GridBagConstraints();
		gbc_lblAddressInput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblAddressInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddressInput.gridx = 4;
		gbc_lblAddressInput.gridy = 4;
		contentPane.add(lblAddressInput, gbc_lblAddressInput);
		
		SetTxt_Addr = new JTextField();
		SetTxt_Addr.setToolTipText("Set address value");
		SetTxt_Addr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_Addr.getText();
				
				// restrict input size and format for address 
				if (Current.length() == 12) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		
		lblProgramInput_1 = new JLabel("Program 2 Input:");
		GridBagConstraints gbc_lblProgramInput_1 = new GridBagConstraints();
		gbc_lblProgramInput_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProgramInput_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblProgramInput_1.gridx = 1;
		gbc_lblProgramInput_1.gridy = 5;
		contentPane.add(lblProgramInput_1, gbc_lblProgramInput_1);
		GridBagConstraints gbc_SetTxt_Addr = new GridBagConstraints();
		gbc_SetTxt_Addr.gridwidth = 2;
		gbc_SetTxt_Addr.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_Addr.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_Addr.gridx = 4;
		gbc_SetTxt_Addr.gridy = 5;
		contentPane.add(SetTxt_Addr, gbc_SetTxt_Addr);
		SetTxt_Addr.setColumns(10);
		
		SbmBtn_Ins = new JButton("Submit");
		SbmBtn_Ins.setToolTipText("Submit instruction");
		SbmBtn_Ins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SAssembler sa = new SAssembler();
				char[] m_code = new char[18];
				char[] addr = new char[12];
				char[] addr2 = new char[12];
				
				String ins = SetTxt_Ins.getText();
				String[] split = ins.split("\n");
				
				for (int i = 0; i < split.length; i++)
				{	
					sa.assembler(m_code, split[i]);
					cpu.writeIns(m_code, m_code.length, addr);
					if(i == 0) {
						for(int j =0; j < 12; j++) {
							addr2[j] = addr[j];
						}
					}
				}
				DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction Submitted.\n" + "It is written into Memory at: " + new String(addr2) + ".\n");
				
				
//				for (int i = 0; i < 18; i++) Instruction[i] = '0';
//				Instruction = SetTxt_Ins.getText().toCharArray();
//				
//				opcode_check();
//				
//				// get address if no instruction error
//				if (flag == true)
//					for (int i = 6; i < 18; i++) address[i-6] = Instruction[i];
//				
//				
//				if (flag == false) DspTxt_Cns.setText(DspTxt_Cns.getText() + "Fail: Instruction Error!\n\n");
//				else
//				{ 
//					cpu.writeIns(Instruction, Instruction.length, address_mem);
//					//instruction_run();
//					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction Submitted.\n"+"The Instruction ("+ ins +" "+ new String(address)+ ") is written into Memory at: " + new String(address_mem) + ".\n");
//				}
			}
		});
		
		btnProgram = new JButton("Program 1");
		btnProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TestProgram1 test = new TestProgram1();
				//test.run();
				
				String program1 = "AIR 3 20;AIX 3 100;IN 1 0;STR 1 3 0 100;AIX 3 3;SOB 3 0 0 21;AIX 2 100;AIR 3 20;SIX 3 3;IN 1 0;STR 1 0 0 120;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;SIR 3 1;SIX 3 3;LDR 1 3 0 100;SMR 1 0 0 120;SMR 1 2 0 97;JCC 1 0 0 84;SOB 3 0 0 63;JMP 0 0 99;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;JMP 0 0 78;LDR 0 0 0 120;OUT 0 1;LDR 0 2 0 94;OUT 0 1";
				String program2 = "STIR 3 20;AIX 3 100;IN 1 0;STR 1 3 0 100;AIX 3 3;SOB 3 0 0 9;AIX 2 100;AIR 3 20;AIX 1 100;LDR 1 1 0 100;OUT 1 1;AIX 1 3;SOB 3 0 0 30 ;AIR 3 20;SIX 3 3;IN 1 0;STR 1 0 0 120;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;SIR 3 1;SIX 3 3;LDR 1 3 0 100;SMR 1 0 0 120;SMR 1 2 0 97;JCC 1 0 0 90;SOB 3 0 0 69;JMP 0 0 105;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;JMP 0 0 84;LDR 0 2 0 94;OUT 0 1;LDR 0 0 0 120;OUT 0 1";
				String program3 = "STIR 3 20;STIX 3 100;IN 1 0;STR 1 3 0 100;AIX 3 3;SOB 3 0 0 9;STIX 2 100;STIR 3 20;STIX 1 100;LDR 1 1 0 100;OUT 1 1;AIX 1 3;SOB 3 0 0 30 ;STIR 3 20;SIX 3 3;IN 1 0;STR 1 0 0 120;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;SIR 3 1;SIX 3 3;LDR 1 3 0 100;SMR 1 0 0 120;SMR 1 2 0 97;JCC 1 0 0 90;SOB 3 0 0 69;JMP 0 0 105;LDR 1 3 0 100;STR 1 2 0 94;SMR 1 0 0 120;STR 1 2 0 97;JMP 0 0 84;LDR 0 2 0 94;OUT 0 1;LDR 0 0 0 120;OUT 0 1";
				String[] instruction = program3.split(";");
				SAssembler sa = new SAssembler();
				char[] m_code = new char[18];
				char[] addr = new char[12];
				String m_code_string = "";
				//write the program into memory
				for (int i = 0; i < instruction.length; i++) {
					sa.assembler(m_code, instruction[i]);
					cpu.writeIns(m_code, m_code.length, addr);
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction Submitted.\n"+"The Instruction ("+ ins +" "+ instruction[i]+ ") is written into Memory at: " + new String(addr) + ".\n");
					m_code_string += new String(m_code) + "\n";
					System.out.println(m_code_string);
				}
			}
		});
		GridBagConstraints gbc_btnProgram = new GridBagConstraints();
		gbc_btnProgram.insets = new Insets(0, 0, 5, 5);
		gbc_btnProgram.gridx = 7;
		gbc_btnProgram.gridy = 5;
		contentPane.add(btnProgram, gbc_btnProgram);
		GridBagConstraints gbc_SbmBtn_Ins = new GridBagConstraints();
		gbc_SbmBtn_Ins.anchor = GridBagConstraints.NORTHEAST;
		gbc_SbmBtn_Ins.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_Ins.gridx = 9;
		gbc_SbmBtn_Ins.gridy = 5;
		contentPane.add(SbmBtn_Ins, gbc_SbmBtn_Ins);
		
		chckbxPrepreparation = new JCheckBox("Prepreparation");
		chckbxPrepreparation.setSelected(true);
		chckbxPrepreparation.setToolTipText("Load predefined program (Y/N)");
		chckbxPrepreparation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ifRandom == false) {
					ifRandom = true;
				}
				else {
					ifRandom = false;
				}
			}
		});
		
		SetTxt_PC = new JTextField();
		SetTxt_PC.setToolTipText("Set PC value");
		SetTxt_PC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String Current = SetTxt_PC.getText();
				
				// restrict input size and format for PC
				if (Current.length() == 12) e.setKeyChar((char) 00);
				if ((e.getKeyChar() != '0') && (e.getKeyChar() != '1')) e.setKeyChar((char) 00);
			}
		});
		
		SbmBtn_P2I = new JButton("Submit");
		SbmBtn_P2I.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyword = SetTxt_P2I.getText();
				
				int c;
				char[] ch = new char[18];
				String binWord = "";
				
				String pointer = "010000000000";
				String pointer_len = "010001011000";
				
				for (int i = 0; i < keyword.length(); i++)
				{
					c = keyword.charAt(i);
					Converter.converterI2S(c, ch);
					binWord += new String(ch);
				}
				Converter.converterI2S(keyword.length(), ch);
				
				cpu.writeMem(binWord.toCharArray(), binWord.length(), pointer.toCharArray());
				cpu.writeMem(ch, ch.length, pointer_len.toCharArray());
				DspTxt_Cns.setText(DspTxt_Cns.getText() + "Keyword: "+ keyword +", has been written into memory.\n");
			}
		});
		
		SetTxt_P2I = new JTextField();
		GridBagConstraints gbc_SetTxt_P2I = new GridBagConstraints();
		gbc_SetTxt_P2I.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_P2I.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_P2I.gridx = 1;
		gbc_SetTxt_P2I.gridy = 6;
		contentPane.add(SetTxt_P2I, gbc_SetTxt_P2I);
		SetTxt_P2I.setColumns(10);
		GridBagConstraints gbc_SbmBtn_P2I = new GridBagConstraints();
		gbc_SbmBtn_P2I.anchor = GridBagConstraints.NORTH;
		gbc_SbmBtn_P2I.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_P2I.gridx = 2;
		gbc_SbmBtn_P2I.gridy = 6;
		contentPane.add(SbmBtn_P2I, gbc_SbmBtn_P2I);
		
		lblProgramInput = new JLabel("Program 1 Input:");
		GridBagConstraints gbc_lblProgramInput = new GridBagConstraints();
		gbc_lblProgramInput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProgramInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblProgramInput.gridx = 4;
		gbc_lblProgramInput.gridy = 6;
		contentPane.add(lblProgramInput, gbc_lblProgramInput);
		
		lblPc = new JLabel("PC:");
		GridBagConstraints gbc_lblPc = new GridBagConstraints();
		gbc_lblPc.anchor = GridBagConstraints.EAST;
		gbc_lblPc.insets = new Insets(0, 0, 5, 5);
		gbc_lblPc.gridx = 6;
		gbc_lblPc.gridy = 6;
		contentPane.add(lblPc, gbc_lblPc);
		GridBagConstraints gbc_SetTxt_PC = new GridBagConstraints();
		gbc_SetTxt_PC.gridwidth = 2;
		gbc_SetTxt_PC.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_PC.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_PC.gridx = 7;
		gbc_SetTxt_PC.gridy = 6;
		contentPane.add(SetTxt_PC, gbc_SetTxt_PC);
		SetTxt_PC.setColumns(10);
		GridBagConstraints gbc_chckbxPrepreparation = new GridBagConstraints();
		gbc_chckbxPrepreparation.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrepreparation.gridx = 9;
		gbc_chckbxPrepreparation.gridy = 6;
		contentPane.add(chckbxPrepreparation, gbc_chckbxPrepreparation);
		
		SbmBtn_PC = new JButton("Submit");
		SbmBtn_PC.setToolTipText("Submit PC");
		SbmBtn_PC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// overwrite PC
				char[] Current = new char[12];
				char[] Current_dsp = new char[12];
				Current = SetTxt_PC.getText().toCharArray();
				
				cpu.writePC(Current, Current.length);
				
				cpu.readPC(Current_dsp, Current_dsp.length);
				DspTxt_PC.setText(new String(Current_dsp));
			}
		});
		
		SetTxt_PI = new JTextField();
		SetTxt_PI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//if ((e.getExtendedKeyCode() < 48) || (e.getExtendedKeyCode() > 57)) e.setKeyChar((char)00);
			}
		});
		
		lblReadProgram = new JLabel("Read Program 2 File:");
		GridBagConstraints gbc_lblReadProgram = new GridBagConstraints();
		gbc_lblReadProgram.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblReadProgram.insets = new Insets(0, 0, 5, 5);
		gbc_lblReadProgram.gridx = 1;
		gbc_lblReadProgram.gridy = 7;
		contentPane.add(lblReadProgram, gbc_lblReadProgram);
		
		lblNewLabel_4 = new JLabel("Read Program 2 Ins.:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 7;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		SetTxt_PI.setToolTipText("Set input value for program");
		GridBagConstraints gbc_SetTxt_PI = new GridBagConstraints();
		gbc_SetTxt_PI.gridwidth = 2;
		gbc_SetTxt_PI.insets = new Insets(0, 0, 5, 5);
		gbc_SetTxt_PI.fill = GridBagConstraints.HORIZONTAL;
		gbc_SetTxt_PI.gridx = 4;
		gbc_SetTxt_PI.gridy = 7;
		contentPane.add(SetTxt_PI, gbc_SetTxt_PI);
		SetTxt_PI.setColumns(10);
		
		GridBagConstraints gbc_SbmBtn_PC = new GridBagConstraints();
		gbc_SbmBtn_PC.anchor = GridBagConstraints.EAST;
		gbc_SbmBtn_PC.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_PC.gridx = 8;
		gbc_SbmBtn_PC.gridy = 7;
		contentPane.add(SbmBtn_PC, gbc_SbmBtn_PC);
		
		SbmBtn_PI = new JButton("Submit");
		SbmBtn_PI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0, j = 0;
				String content = SetTxt_PI.getText();
				String[] convert = content.split(" ");
				int length = convert.length;
				if(length >= 21) {
					for (int k = 0; k < 20; k++) {
						numbers[k] = Integer.parseInt(convert[k]);
					}
					numbers[20] = Integer.parseInt(convert[length-1]);
				} else {
					for (int k = 0; k < 20; k++) {
						if (k >= length-2) {
							numbers[k] = Integer.parseInt(convert[length-2]);
						} else {
						numbers[k] = Integer.parseInt(convert[k]);
						}
					}
					numbers[20] = Integer.parseInt(convert[length-1]);
				}
				count = 0;
				
			}
		});
		
		btnBrowse = new JButton("Browse...");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(System.getProperty("user.dir")); 
				File f;
				
				int get_File = fc.showOpenDialog(null);
				
				if (get_File == JFileChooser.CANCEL_OPTION)
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "File fetching canceled.\n");
				
				if (get_File == JFileChooser.ERROR_OPTION)
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "File fetching failed. An Error occured while fetcing\n");
				
				if (get_File == JFileChooser.APPROVE_OPTION)
				{
					f = fc.getSelectedFile();
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "File fetching succeed: " + f.getName() + ".\n");
					
					try
					{
						String temp;
						FileReader fd = new FileReader(f);
						BufferedReader bufferedReader = new BufferedReader(fd);
						
						 while((temp = bufferedReader.readLine()) != null)
						 {
				                sentence += temp;
						 }
						 
						 bufferedReader.close();
						 
						 DspTxt_Cns.setText(DspTxt_Cns.getText() + sentence + "\n");
						 
						 // get each char in the paragraph
						 sentence_char = new char[sentence.length()];
						 sentence_char = sentence.toCharArray();
						 
						 String binSentence = "";
						 int c;
						 char[] ch = new char[18];
						 
						 for (int i = 0; i < sentence_char.length; i++)
						 {
							 c = sentence_char[i];
							 Converter.converterI2S(c, ch);
							 binSentence += new String(ch);
						 }
	
						 String pointer = "001000000000";
//						 cpu.writeMem(ascii_char, ascii_char.length, pointer);
						 cpu.writeMem(binSentence.toCharArray(), binSentence.length(), pointer.toCharArray());
						 DspTxt_Cns.setText(DspTxt_Cns.getText() + "The file has been written into memory at: " + new String(pointer) + ".\n");
					}
					
					catch (FileNotFoundException e1)
					{
						DspTxt_Cns.setText(DspTxt_Cns.getText() + "File Not Found.\n");
					}
					
					catch (IOException e1)
					{
						DspTxt_Cns.setText(DspTxt_Cns.getText() + "I/O Error.\n");
					}
				}
			}
		});
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowse.gridx = 1;
		gbc_btnBrowse.gridy = 8;
		contentPane.add(btnBrowse, gbc_btnBrowse);
		
		btnProgram2 = new JButton("Program 2");
		btnProgram2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String program2 = "STIX 3 0;STIR 1 8;AIX 3 64;SOB 1 0 0 9;STIX 2 0;STIR 1 16;AIX 2 64;SOB 1 0 0 21;STIX 1 0;STIR 1 17;AIX 1 64;SOB 1 0 0 33;STIR 2 1;STR 2 1 0 0;STR 2 1 0 3;LDR 2 1 0 0;SIR 2 7;STIR 0 127;AIR 0 127;AIR 0 58;STR 0 1 0 29;JCC 3 1 1 30;LDR 2 3 0 0;SMR 2 2 0 0;STIR 0 117;AIR 0 0;STR 0 1 0 29;JCC 3 1 1 30;STIR 2 0;STR 2 1 0 15;STIX 2 0;STIR 1 16;AIX 2 64;SOB 1 0 0 99;STIR 0 127;AIR 0 74;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 24;SIR 2 1;SMR 2 1 0 15;STIR 0 127;AIR 0 35;STR 0 1 0 29;JCC 3 1 1 30;LDR 2 1 0 15;AIR 2 1;STR 2 1 0 15;AIX 2 3;STIR 0 127;AIR 0 74;STR 0 1 0 29;JMP 1 1 30;LDR 3 1 0 0;OUT 3 1;LDR 3 1 0 3;OUT 3 1;STIR 2 0;STR 2 1 0 15;STIX 2 0;STIR 1 16;AIX 2 64;STIR 0 127;AIR 0 59;STR 0 1 0 29;SOB 1 1 1 30;LDR 2 3 0 0;SIR 2 32;STIR 0 127;AIR 0 127;AIR 0 4;STR 0 1 0 29;JCC 3 1 1 30;LDR 2 3 0 0;SIR 2 46;STIR 0 127;AIR 0 127;AIR 0 28;STR 0 1 0 29;JCC 3 1 1 30;STIR 0 127;AIR 0 127;AIR 0 43;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 3;AIR 2 1;STR 2 1 0 3;STIR 0 127;AIR 0 127;AIR 0 43;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 0;AIR 2 1;STR 2 1 0 0;STIR 2 0;STR 2 1 0 3;AIX 3 3;STIR 0 48;AIR 0 0;STR 0 1 0 29;JMP 1 1 30;LDR 2 1 0 3;";

				String[] instruction = program2.split(";");
				SAssembler sa = new SAssembler();
				char[] m_code = new char[18];
				char[] addr = new char[12];
				String m_code_string = "";
				for (int i = 0; i < instruction.length; i++) {
					sa.assembler(m_code, instruction[i]);
					cpu.writeIns(m_code, m_code.length, addr);
					m_code_string += new String(m_code) + "\n";
				}
				System.out.println(m_code_string);
				
				
				
			}
		});
		
		btnBrowse_1 = new JButton("Browse...");
		btnBrowse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(System.getProperty("user.dir")); 
				File f;
				
				int get_File = fc.showOpenDialog(null);
				
				if (get_File == JFileChooser.CANCEL_OPTION)
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction fetching canceled.\n");
				
				if (get_File == JFileChooser.ERROR_OPTION)
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction fetching failed. An Error occured while fetcing\n");
				
				if (get_File == JFileChooser.APPROVE_OPTION)
				{
					f = fc.getSelectedFile();
					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction fetching succeed: " + f.getName() + ".\n");
					
					try
					{
						String temp;
						FileReader fd = new FileReader(f);
						BufferedReader bufferedReader = new BufferedReader(fd);
						
						 while((temp = bufferedReader.readLine()) != null)
						 {
				                program2_2 += temp;
						 }
						 
						 bufferedReader.close();
						 
						 String[] instruction = program2_2.split(";");
							SAssembler sa = new SAssembler();
							char[] m_code = new char[18];
							char[] addr = new char[12];
							String m_code_string = "";
							for (int i = 0; i < instruction.length; i++) {
								sa.assembler(m_code, instruction[i]);
								cpu.writeIns(m_code, m_code.length, addr);
								m_code_string += new String(m_code) + "\n";
							}
							System.out.println(m_code_string);
					}
					
					catch (FileNotFoundException e1)
					{
						DspTxt_Cns.setText(DspTxt_Cns.getText() + "Instruction Not Found.\n");
					}
					
					catch (IOException e1)
					{
						DspTxt_Cns.setText(DspTxt_Cns.getText() + "I/O Error.\n");
					}
			}
				
			}
		});
		GridBagConstraints gbc_btnBrowse_1 = new GridBagConstraints();
		gbc_btnBrowse_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBrowse_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowse_1.gridx = 2;
		gbc_btnBrowse_1.gridy = 8;
		contentPane.add(btnBrowse_1, gbc_btnBrowse_1);
		GridBagConstraints gbc_btnProgram2 = new GridBagConstraints();
		gbc_btnProgram2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnProgram2.gridwidth = 2;
		gbc_btnProgram2.insets = new Insets(0, 0, 5, 5);
		gbc_btnProgram2.gridx = 3;
		gbc_btnProgram2.gridy = 8;
		contentPane.add(btnProgram2, gbc_btnProgram2);
		SbmBtn_PI.setToolTipText("Submit program input");
		GridBagConstraints gbc_SbmBtn_PI = new GridBagConstraints();
		gbc_SbmBtn_PI.anchor = GridBagConstraints.NORTHEAST;
		gbc_SbmBtn_PI.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_PI.gridx = 5;
		gbc_SbmBtn_PI.gridy = 8;
		contentPane.add(SbmBtn_PI, gbc_SbmBtn_PI);
		
		SbmBtn_SglStp = new JButton("Single Step");
		SbmBtn_SglStp.setToolTipText("Run program in single step");
		SbmBtn_SglStp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] Current = new char[18];
				char[] pc = new char[12];
				char[] X1 = new char[12], X2 = new char[12], X3 = new char[12], MAR = new char[12], MBR = new char[18];
				char[] id;
				
				//LDX ldx = new LDX(cpu);
				//ldx.start();
				//init.toRun();	
				
				cpu.readPC(pc, pc.length);
				cpu.readMem(Instruction, Instruction.length, pc);
				
				//opcode_check();
				int ins_code= Converter.conveterS2I(Instruction, 6);
				switch (ins_code) {
				case 1:
					ldr.start();
					break;
				case 2:
					str.start();
					break;
				case 5:
					smr.start();
					break;
				case 7:
					sir.start();
					break;
				case 6:
					air.start();
					break;
				case 10:
					jcc.start();
					break;
				case 11:
					jmp.start();
					break;
				case 14:
					sob.start();
					break;
				case 22:
					aix.start();
					break;
				case 23:
					six.start();
					break;
				case 27:
					fadd.start();
					break;
				case 28:
					fsub.start();
					break;
				case 29:
					vadd.start();
					break;
				case 30:
					vsub.start();
					break;
				case 31:
					cnvrt.start();
					break;
				case 40:
					ldfr.start();
					break;
				case 41: stfr.start();
					break;
				case 42:
					stir.start();
					break;
				case 43:
					stix.start();
					break;
				case 49:
					in.start();
					break;
				case 50:
					out.start();
					break;
				case 52:
					fout.start();
					break;
				default:
					break;
				}
//				if (flag == false) { 
//					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Fail: Unrecognized Instuction!\n");
//				}
//				else { 
//					instruction_run();
//				}
				
				
				//status update
				
				// R0 operation
				id = new char[] {'0', '0'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R0.setText(new String(Current));
				
				// R1 operation
				id = new char[] {'0', '1'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R1.setText(new String(Current));
				
				// R2 operation
				id = new char[] {'1', '0'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R2.setText(new String(Current));
				
				// R3 operation
				id = new char[] {'1', '1'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R3.setText(new String(Current));
				
				// PC operation
				cpu.readPC(pc, pc.length);
				DspTxt_PC.setText(new String(pc));
				
				// X1 operation
				id = new char[] {'0', '1'};
				
				cpu.readXR(X1, id, xr.getLength());
				DspTxt_X1.setText(new String(X1));
				
				// X2 operation
				id = new char[] {'1', '0'};
				
				cpu.readXR(X2, id, xr.getLength());
				DspTxt_X2.setText(new String(X2));
				
				// X3 operation
				id = new char[] {'1', '1'};
				
				cpu.readXR(X3, id, xr.getLength());
				DspTxt_X3.setText(new String(X3));
				
				// MAR operation
				MAR = cpu.getMAR();
				DspTxt_MAR.setText(new String(MAR));
				
				// MBR operation
				MBR = cpu.getMBR();
				DspTxt_MBR.setText(new String(MBR));
			}
		});
		GridBagConstraints gbc_SbmBtn_SglStp = new GridBagConstraints();
		gbc_SbmBtn_SglStp.fill = GridBagConstraints.HORIZONTAL;
		gbc_SbmBtn_SglStp.insets = new Insets(0, 0, 5, 5);
		gbc_SbmBtn_SglStp.gridx = 8;
		gbc_SbmBtn_SglStp.gridy = 8;
		contentPane.add(SbmBtn_SglStp, gbc_SbmBtn_SglStp);
		
		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String fpr = "000000000000000011";
//				char[] fri = {'0', '0'};
//				cpu.writeFR(fpr.toCharArray(), fri, fpr.length());
				String add_v1 = "001000000000";
				char[] addBuffer = add_v1.toCharArray();
				String f_3_5 = "000000101110000000";
				cpu.writeMem(f_3_5.toCharArray(), f_3_5.length(), add_v1.toCharArray());
				char[] Current = new char[18];
				char[] pc = new char[12];
				char[] X1 = new char[12], X2 = new char[12], X3 = new char[12], MAR = new char[12], MBR = new char[18];
				char[] id;
				
				//LDX ldx = new LDX(cpu);
				//ldx.start();
				//init.toRun();

				
				
				cpu.readPC(pc, pc.length);
				cpu.readMem(Instruction, Instruction.length, pc);
				
				//opcode_check();
				int ins_code= Converter.conveterS2I(Instruction, 6);
				while(ins_code != 0) {
					switch (ins_code) {
					case 1:
						ldr.start();
						break;
					case 2:
						str.start();
						break;
					case 5:
						smr.start();
						break;
					case 7:
						sir.start();
						break;
					case 6:
						air.start();
						break;
					case 10:
						jcc.start();
						break;
					case 11:
						jmp.start();
						break;
					case 14:
						sob.start();
						break;
					case 22:
						aix.start();
						break;
					case 23:
						six.start();
						break;
					case 27:
						fadd.start();
						break;
					case 28:
						fsub.start();
						break;
					case 29:
						vadd.start();
						break;
					case 30:
						vsub.start();
						break;
					case 31:
						cnvrt.start();
						break;
					case 40:
						ldfr.start();
						break;
					case 41: stfr.start();
						break;
					case 42:
						stir.start();
						break;
					case 43:
						stix.start();
						break;
					case 49:
						in.start();
						break;
					case 50:
						out.start();
						break;
					case 52:
						fout.start();
						break;
					default:
						break;
					}
//				if (flag == false) { 
//					DspTxt_Cns.setText(DspTxt_Cns.getText() + "Fail: Unrecognized Instuction!\n");
//				}
//				else { 
//					instruction_run();
//				}
				
				
				//status update
				
				// R0 operation
				id = new char[] {'0', '0'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R0.setText(new String(Current));
				
				// R1 operation
				id = new char[] {'0', '1'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R1.setText(new String(Current));
				
				// R2 operation
				id = new char[] {'1', '0'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R2.setText(new String(Current));
				
				// R3 operation
				id = new char[] {'1', '1'};
				
				cpu.readGPR(Current, id, Current.length);
				DspTxt_R3.setText(new String(Current));
				
				// PC operation
				cpu.readPC(pc, pc.length);
				DspTxt_PC.setText(new String(pc));
				
				// X1 operation
				id = new char[] {'0', '1'};
				
				cpu.readXR(X1, id, xr.getLength());
				DspTxt_X1.setText(new String(X1));
				
				// X2 operation
				id = new char[] {'1', '0'};
				
				cpu.readXR(X2, id, xr.getLength());
				DspTxt_X2.setText(new String(X2));
				
				// X3 operation
				id = new char[] {'1', '1'};
				
				cpu.readXR(X3, id, xr.getLength());
				DspTxt_X3.setText(new String(X3));
				
				// MAR operation
				MAR = cpu.getMAR();
				DspTxt_MAR.setText(new String(MAR));
				
				// MBR operation
				MBR = cpu.getMBR();
				DspTxt_MBR.setText(new String(MBR));
				
				
				
				cpu.readPC(pc, pc.length);
				cpu.readMem(Instruction, Instruction.length, pc);
				
				//opcode_check();
				ins_code= Converter.conveterS2I(Instruction, 6);
				}
			}
		});
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.insets = new Insets(0, 0, 5, 5);
		gbc_btnRun.gridx = 9;
		gbc_btnRun.gridy = 8;
		contentPane.add(btnRun, gbc_btnRun);
		
		lblValue_1 = new JLabel("Value:");
		GridBagConstraints gbc_lblValue_1 = new GridBagConstraints();
		gbc_lblValue_1.anchor = GridBagConstraints.SOUTH;
		gbc_lblValue_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue_1.gridx = 1;
		gbc_lblValue_1.gridy = 9;
		contentPane.add(lblValue_1, gbc_lblValue_1);
		
		lblValue_2 = new JLabel("Value:");
		GridBagConstraints gbc_lblValue_2 = new GridBagConstraints();
		gbc_lblValue_2.anchor = GridBagConstraints.SOUTH;
		gbc_lblValue_2.gridwidth = 2;
		gbc_lblValue_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue_2.gridx = 3;
		gbc_lblValue_2.gridy = 9;
		contentPane.add(lblValue_2, gbc_lblValue_2);
		
		lblOutput = new JLabel("Output:");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 5;
		gbc_lblOutput.gridy = 9;
		contentPane.add(lblOutput, gbc_lblOutput);
		
		lblR_1 = new JLabel("R0:");
		GridBagConstraints gbc_lblR_1 = new GridBagConstraints();
		gbc_lblR_1.anchor = GridBagConstraints.EAST;
		gbc_lblR_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_1.gridx = 0;
		gbc_lblR_1.gridy = 10;
		contentPane.add(lblR_1, gbc_lblR_1);
		
		DspTxt_R0 = new JLabel("0");
		DspTxt_R0.setToolTipText("Display value of R0");
		GridBagConstraints gbc_DspTxt_R0 = new GridBagConstraints();
		gbc_DspTxt_R0.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R0.gridx = 1;
		gbc_DspTxt_R0.gridy = 10;
		contentPane.add(DspTxt_R0, gbc_DspTxt_R0);
		
		lblX = new JLabel("X1:");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.EAST;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 2;
		gbc_lblX.gridy = 10;
		contentPane.add(lblX, gbc_lblX);
		
		DspTxt_X1 = new JLabel("0");
		DspTxt_X1.setToolTipText("Display value of X1");
		GridBagConstraints gbc_DspTxt_X1 = new GridBagConstraints();
		gbc_DspTxt_X1.gridwidth = 2;
		gbc_DspTxt_X1.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_X1.gridx = 3;
		gbc_DspTxt_X1.gridy = 10;
		contentPane.add(DspTxt_X1, gbc_DspTxt_X1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 5;
		gbc_scrollPane.gridy = 10;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		DspTxt_Cns = new JTextArea();
		DspTxt_Cns.setToolTipText("Data output");
		scrollPane.setViewportView(DspTxt_Cns);
		DspTxt_Cns.setEditable(false);
		DspTxt_Cns.setWrapStyleWord(true);
		DspTxt_Cns.setLineWrap(true);
		
		lblR_2 = new JLabel("R1:");
		GridBagConstraints gbc_lblR_2 = new GridBagConstraints();
		gbc_lblR_2.anchor = GridBagConstraints.EAST;
		gbc_lblR_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_2.gridx = 0;
		gbc_lblR_2.gridy = 11;
		contentPane.add(lblR_2, gbc_lblR_2);
		
		DspTxt_R1 = new JLabel("0");
		DspTxt_R1.setToolTipText("Display value of R1");
		GridBagConstraints gbc_DspTxt_R1 = new GridBagConstraints();
		gbc_DspTxt_R1.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R1.gridx = 1;
		gbc_DspTxt_R1.gridy = 11;
		contentPane.add(DspTxt_R1, gbc_DspTxt_R1);
		
		lblX_1 = new JLabel("X2:");
		GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
		gbc_lblX_1.anchor = GridBagConstraints.EAST;
		gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_1.gridx = 2;
		gbc_lblX_1.gridy = 11;
		contentPane.add(lblX_1, gbc_lblX_1);
		
		DspTxt_X2 = new JLabel("0");
		DspTxt_X2.setToolTipText("Display value of X2");
		GridBagConstraints gbc_DspTxt_X2 = new GridBagConstraints();
		gbc_DspTxt_X2.gridwidth = 2;
		gbc_DspTxt_X2.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_X2.gridx = 3;
		gbc_DspTxt_X2.gridy = 11;
		contentPane.add(DspTxt_X2, gbc_DspTxt_X2);
		
		lblR_3 = new JLabel("R2:");
		GridBagConstraints gbc_lblR_3 = new GridBagConstraints();
		gbc_lblR_3.anchor = GridBagConstraints.EAST;
		gbc_lblR_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_3.gridx = 0;
		gbc_lblR_3.gridy = 12;
		contentPane.add(lblR_3, gbc_lblR_3);
		
		DspTxt_R2 = new JLabel("0");
		DspTxt_R2.setToolTipText("Display value of R2");
		GridBagConstraints gbc_DspTxt_R2 = new GridBagConstraints();
		gbc_DspTxt_R2.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R2.gridx = 1;
		gbc_DspTxt_R2.gridy = 12;
		contentPane.add(DspTxt_R2, gbc_DspTxt_R2);
		
		lblX_2 = new JLabel("X3:");
		GridBagConstraints gbc_lblX_2 = new GridBagConstraints();
		gbc_lblX_2.anchor = GridBagConstraints.EAST;
		gbc_lblX_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_2.gridx = 2;
		gbc_lblX_2.gridy = 12;
		contentPane.add(lblX_2, gbc_lblX_2);
		
		DspTxt_X3 = new JLabel("0");
		DspTxt_X3.setToolTipText("Display value of X3");
		GridBagConstraints gbc_DspTxt_X3 = new GridBagConstraints();
		gbc_DspTxt_X3.gridwidth = 2;
		gbc_DspTxt_X3.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_X3.gridx = 3;
		gbc_DspTxt_X3.gridy = 12;
		contentPane.add(DspTxt_X3, gbc_DspTxt_X3);
		
		lblR_4 = new JLabel("R3:");
		GridBagConstraints gbc_lblR_4 = new GridBagConstraints();
		gbc_lblR_4.anchor = GridBagConstraints.EAST;
		gbc_lblR_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_4.gridx = 0;
		gbc_lblR_4.gridy = 13;
		contentPane.add(lblR_4, gbc_lblR_4);
		
		DspTxt_R3 = new JLabel("0");
		DspTxt_R3.setToolTipText("Display value of R3");
		GridBagConstraints gbc_DspTxt_R3 = new GridBagConstraints();
		gbc_DspTxt_R3.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_R3.gridx = 1;
		gbc_DspTxt_R3.gridy = 13;
		contentPane.add(DspTxt_R3, gbc_DspTxt_R3);
		
		lblMar = new JLabel("MAR:");
		GridBagConstraints gbc_lblMar = new GridBagConstraints();
		gbc_lblMar.anchor = GridBagConstraints.EAST;
		gbc_lblMar.insets = new Insets(0, 0, 5, 5);
		gbc_lblMar.gridx = 2;
		gbc_lblMar.gridy = 13;
		contentPane.add(lblMar, gbc_lblMar);
		
		DspTxt_MAR = new JLabel("0");
		DspTxt_MAR.setToolTipText("Display value of MAR");
		GridBagConstraints gbc_DspTxt_MAR = new GridBagConstraints();
		gbc_DspTxt_MAR.gridwidth = 2;
		gbc_DspTxt_MAR.insets = new Insets(0, 0, 5, 5);
		gbc_DspTxt_MAR.gridx = 3;
		gbc_DspTxt_MAR.gridy = 13;
		contentPane.add(DspTxt_MAR, gbc_DspTxt_MAR);
		
		lblPc_1 = new JLabel("PC:");
		GridBagConstraints gbc_lblPc_1 = new GridBagConstraints();
		gbc_lblPc_1.anchor = GridBagConstraints.EAST;
		gbc_lblPc_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblPc_1.gridx = 0;
		gbc_lblPc_1.gridy = 14;
		contentPane.add(lblPc_1, gbc_lblPc_1);
		
		DspTxt_PC = new JLabel("0");
		DspTxt_PC.setToolTipText("Display value of PC");
		GridBagConstraints gbc_DspTxt_PC = new GridBagConstraints();
		gbc_DspTxt_PC.insets = new Insets(0, 0, 0, 5);
		gbc_DspTxt_PC.gridx = 1;
		gbc_DspTxt_PC.gridy = 14;
		contentPane.add(DspTxt_PC, gbc_DspTxt_PC);
		
		lblMbr = new JLabel("MBR:");
		GridBagConstraints gbc_lblMbr = new GridBagConstraints();
		gbc_lblMbr.anchor = GridBagConstraints.EAST;
		gbc_lblMbr.insets = new Insets(0, 0, 0, 5);
		gbc_lblMbr.gridx = 2;
		gbc_lblMbr.gridy = 14;
		contentPane.add(lblMbr, gbc_lblMbr);
		
		DspTxt_MBR = new JLabel("0");
		DspTxt_MBR.setToolTipText("Display value of MBR");
		GridBagConstraints gbc_DspTxt_MBR = new GridBagConstraints();
		gbc_DspTxt_MBR.gridwidth = 2;
		gbc_DspTxt_MBR.insets = new Insets(0, 0, 0, 5);
		gbc_DspTxt_MBR.gridx = 3;
		gbc_DspTxt_MBR.gridy = 14;
		contentPane.add(DspTxt_MBR, gbc_DspTxt_MBR);
	}
	
	// get the first six digits of an instruction
	// and check if it is an opcode
	private void opcode_check()
	{
		// get opcode from instruction
		for (int i = 0; i < 6; i++) Opcode[i] = Instruction[i];
		
		// if opcode starts w/ 1
		if (Opcode[0] == '1')
		{
			// and ends w/ 1
			if (Opcode[5] == '1')
			{
				// and in-betweens are 0
				for (int i = 1; i < 5; i++)
				{
					if (Opcode[i] == '0') flag = true;
					else
					{
						flag = false;
						break;
					}
				}
				
				if (flag == true) ins = "LDX";
			}
			
			// and ends w/ 10
			if ((Opcode[4] == '1') && (Opcode[5] == '0'))
			{
				for (int i = 1; i < 4; i++)
				{
					// and in-betweens are 0
					if (Opcode[i] == '0') flag = true;
					else
					{
						flag = false;
						break;
					}
				}
				
				if (flag == true) ins = "STX";
			}
		}
		
		// if opcode starts w/ four 0
		boolean mark = true;
		
		for (int i = 0; i < 4; i++)
		{
			if (Opcode[i] != '0')
			{
				mark = false;
				break;
			}
		}
		
		if (mark == true)
		{
			// and ends w/ 01, 10, 11
			if ((Opcode[4] == '0') && (Opcode[5] == '1'))
			{
				flag = true;
				ins = "LDR";
			}
			
			if ((Opcode[4] == '1') && (Opcode[5] == '0'))
			{
				flag = true;
				ins = "STR";
			}
			
			if ((Opcode[4] == '1') && (Opcode[5] == '1'))
			{
				flag = true;
				ins = "LDA";
			}
		}
	}
	
	// run instruction, write in memory, and display result
	private void instruction_run()
	{
		if (ins == "LDR")
		{
			if(ifInitial) {
				init.preRun_LDR();
			}
			LDR ldr = new LDR(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			ldr.start();
		}
		else if (ins == "STR")
		{
			if(ifInitial) {
				init.preRun_STR();
			}
			STR str = new STR(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			str.start();
		}
		else if (ins == "LDA")
		{
			if(ifInitial) {
				init.preRun_LDA();
			}
			LDA lda = new LDA(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			lda.start();
		}
		else if (ins == "LDX")
		{
			if(ifInitial) {
				init.preRun_LDX();
			}
			LDX ldx = new LDX(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			ldx.start();
		}
		
		if (ins == "STX")
		{
			if(ifInitial) {
				init.preRun_STX();
			}
			STX stx = new STX(cpu);
			DspTxt_Cns.setText(DspTxt_Cns.getText() + "Begin to run " + ins + ".\n");
			stx.start();
		}
		
		//cpu.writeIns(Opcode, Opcode.length, address_mem);
		DspTxt_Cns.setText(DspTxt_Cns.getText() + "Success: " + ins + " " + new String(address) + ".\n\n");
	}
	
	// return UI instance in order to have print interfaces
	public static UI getUIinstance()
	{
		if (frame != null)
			return frame;
		
		return new UI();
	}

	// interfaces for printing usage
	public void print2console(String content)
	{
		String Current = DspTxt_Cns.getText();
		DspTxt_Cns.setText(Current + content + "\n");
	}
	
	public void print2console(int content)
	{
		String Current = DspTxt_Cns.getText();
		DspTxt_Cns.setText(Current + content + "\n");
	}
	
	// for long type only
/*
	public void print2console(long content)
	{
		String Current = DspTxt_Cns.getText();
		DspTxt_Cns.setText(Current + content + "\n");
	}
*/
	public void print2log(String content)
	{
		String Current = DspTxt_Log.getText();
		DspTxt_Log.setText(Current + content + "\n");
	}
	
	public void print2log(int content)
	{
		String Current = DspTxt_Log.getText();
		DspTxt_Log.setText(Current + content + "\n");
	}
	
	// program input interface
	public int getPI()
	{
		String content = SetTxt_PI.getText();
		
	//	long PI = Long.valueOf(content);
		int PI = Integer.valueOf(content);
	
		return PI;
	}
	public int returnPI() {
		if(ifRandom) {
			Random random = new Random();
			
			return random.nextInt(100);
		} else {
			if(count >= 21)
			{
				count = 0;
			}
				int value = numbers[count];
				count++;
				
				return value;
		}
		
	}	
}
package scripts.null_lines.aiothiever;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import scripts.null_lines.aiothiever.tasks.Task;

public class GUI {
	private JFrame frmBoimanpp;
	private JTextField foodTextField;
	private boolean start;

	public GUI() {
		this.start = false;
		this.leftClick = false;

		initialize();
	}

	private boolean leftClick;
	private JTextField eatValue;

	private void initialize() {
		this.frmBoimanpp = new JFrame();
		this.frmBoimanpp.setTitle("Null AIO Thiever V" + NullAIOThiever.version);
		this.frmBoimanpp.setBounds(100, 100, 450, 300);
		this.frmBoimanpp.setDefaultCloseOperation(2);
		this.frmBoimanpp.getContentPane().setLayout(null);

		this.foodTextField = new JTextField();
		this.foodTextField.setBounds(75, 26, 86, 20);
		this.frmBoimanpp.getContentPane().add(this.foodTextField);
		this.foodTextField.setColumns(10);

		JLabel lblFoodName = new JLabel("Food name:");
		lblFoodName.setBounds(10, 27, 109, 14);
		this.frmBoimanpp.getContentPane().add(lblFoodName);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Action performed");
				GUI.this.start = true;
				for (Task t : NullAIOThiever.taskList) {
					t.init();
				}
				GUI.this.frmBoimanpp.setVisible(false);
			}
		});

		btnStart.setBounds(170, 140, 89, 23);
		this.frmBoimanpp.getContentPane().add(btnStart);

		JLabel lblLeftClick = new JLabel("Left click: ");
		lblLeftClick.setBounds(196, 27, 63, 14);
		this.frmBoimanpp.getContentPane().add(lblLeftClick);

		JCheckBox leftClickBox = new JCheckBox("");
		leftClickBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI.this.leftClick = true;
			}
		});

		leftClickBox.setBounds(247, 23, 97, 23);
		this.frmBoimanpp.getContentPane().add(leftClickBox);

		this.eatValue = new JTextField();
		this.eatValue.setText("5");
		this.eatValue.setBounds(75, 57, 36, 20);
		this.frmBoimanpp.getContentPane().add(this.eatValue);
		this.eatValue.setColumns(10);

		JLabel lblEatHp = new JLabel("Eat HP:");
		lblEatHp.setBounds(10, 60, 46, 14);
		this.frmBoimanpp.getContentPane().add(lblEatHp);

		JLabel lblNpcName = new JLabel("npc name:");
		lblNpcName.setBounds(10, 85, 63, 14);
		this.frmBoimanpp.getContentPane().add(lblNpcName);

		this.npcNames = new JTextField();
		this.npcNames.setBounds(75, 82, 86, 20);
		this.frmBoimanpp.getContentPane().add(this.npcNames);
		this.npcNames.setColumns(10);

		JLabel itemsToDroplbl = new JLabel("drop items:");
		itemsToDroplbl.setBounds(196, 85, 63, 14);
		this.frmBoimanpp.getContentPane().add(itemsToDroplbl);

		this.dropItemsText = new JTextField();
		this.dropItemsText.setBounds(258, 82, 86, 20);
		this.frmBoimanpp.getContentPane().add(this.dropItemsText);
		this.dropItemsText.setColumns(10);
		
		this.frmBoimanpp.setVisible(true);
	}

	private JTextField npcNames;
	private JTextField dropItemsText;

	public String getFoodText() {
		return this.foodTextField.getText();
	}

	public boolean start() {
		return this.start;
	}

	public boolean leftClick() {
		return this.leftClick;
	}

	public int eatValue() {
		return Integer.parseInt(this.eatValue.getText());
	}

	public String getNames() {
		return this.npcNames.getText();
	}

	public String getDropString() {
		return this.dropItemsText.getText();
	}

	public List<String> items(String s) {
		List<String> item = new ArrayList<String>();

		int charPos = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == ',') {
				item.add(s.substring((charPos == 0) ? 0 : (charPos + 1), i));
				charPos = i;
			}
		}
		if (charPos != 0) {
			item.add(s.substring(charPos + 1, s.length()));
		} else {
			item.add(s);
		}

		return item;
	}
}

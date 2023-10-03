import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.*;
import java.awt.*;

public class Mastermind {

	static ArrayList<Icon> computerChoice = new ArrayList<>(4);
	static ArrayList<Icon> userChoice = new ArrayList<>(4);
	static boolean turn = true;
	static ArrayList<Icon> allIcons;
	static int num = 0, count = 0;
	static JFrame frame;
	static JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10;
	static Icon lGreen, lOrange, lBlue, lLightBlue, lRed, lYellow, bGreen, bOrange, bBlue, bLightBlue, bRed, bYellow, ex, plus, ok, backspace, gameOver, win, restart, mind;
	static JButton button1, button2, button3, button4, button5, button6, button7, button8, button9;
	static JLabel label1, label2, label3;
	
	public static void restartGame() {
		if(userChoice.size()>0) userChoice.clear();
		if(computerChoice.size()>0) computerChoice.clear();
		turn = true;
		num = 0;
		count = 0;
		Component[] a = panel1.getComponents();
		for(int i=0; i<5; i++) {
			JPanel b = (JPanel)a[i];
			b.removeAll();
		}
		panel1.revalidate();
		panel1.repaint();
		
		panel3.removeAll();
		panel3.add(label1);
		panel3.revalidate();
		panel3.repaint();
		
		computer();
	}
	
	public static void computer() {
		List<Integer> cpuChoice = IntStream.range(0, 5).boxed().collect(Collectors.toList());
        Collections.shuffle(cpuChoice);
        cpuChoice.removeIf(n -> (cpuChoice.indexOf(n)>3));
        
        for(int i=0; i<4; i++) {
        	computerChoice.add(allIcons.get(cpuChoice.get(i)));
        }
	}
	
	public static void GameOver(JLabel label) {
		userChoice.clear();
		panel3.remove(label1);
		panel3.add(label);
		for(int i=0; i<4; i++) {
			panel3.add(new JLabel("     "));
			panel3.add(new JLabel(computerChoice.get(i)));
		}
	}
	
	public static void showResults() {
		if(!turn) {
			Component[] a = panel1.getComponents();
			JPanel b = (JPanel)a[count-1];
			b.add(new JLabel("          "));
			if(computerChoice.equals(userChoice)) {
				for(int i=0; i<4; i++) {
					b.add(new JLabel("     "));
					b.add(new JLabel(ex));
				}
				GameOver(label3);
			}else{
				ArrayList<Icon> uniques = new ArrayList<>();
				for(int i=0; i<4; i++) {
						if(computerChoice.get(i) == userChoice.get(i)) {
							b.add(new JLabel("     "));
							b.add(new JLabel(ex));
							uniques.add(userChoice.get(i));
						}
				}
				for(int i=0; i<4; i++) {
					if(computerChoice.contains(userChoice.get(i))) {
						if(!uniques.contains(userChoice.get(i))) {
							b.add(new JLabel("     "));
							b.add(new JLabel(plus));
							uniques.add(userChoice.get(i));
						}
					}
				}
				uniques.clear();
				if(count==6) GameOver(label2);
				else turn = true;
			}
			panel1.revalidate();
		}
	}
	
	public static void removeFromPanel() {
		if(num>0 && count>0 && userChoice.size()>0) {
			Component[] a = panel1.getComponents();
			JPanel b = (JPanel)a[count-1];
			Component[] d = b.getComponents();
			b.remove(d[d.length-1]);
			b.remove(d[d.length-2]);
			panel1.revalidate();
			panel1.repaint();
			userChoice.remove(userChoice.get(userChoice.size()-1));
			num--;
			turn = true;
		}
	}

	public static void addToPanel(Icon icon) {
		if(num<25) num++;
		
		if(num<=4 && turn) {
			panel5.add(new JLabel("     "));
			panel5.add(new JLabel(icon));
			count = 1;
			userChoice.add(icon);
			if(num==4) turn = false;
		}
		else if(num<=8 && turn) {
			if(userChoice.size()>0 && count==1) userChoice.clear();
			panel6.add(new JLabel("     "));
			panel6.add(new JLabel(icon));
			count = 2;
			userChoice.add(icon);
			if(num==8) turn = false;
		}
		else if(num<=12 && turn) {
			if(userChoice.size()>0 && count==2) userChoice.clear();
			panel7.add(new JLabel("     "));
			panel7.add(new JLabel(icon));
			count = 3;
			userChoice.add(icon);
			if(num==12) turn = false;
		}
		else if(num<=16 && turn) {
			if(userChoice.size()>0 && count==3) userChoice.clear();
			panel8.add(new JLabel("     "));
			panel8.add(new JLabel(icon));
			count = 4;
			userChoice.add(icon);
			if(num==16) turn = false;
		}
		else if(num<=20 && turn) {
			if(userChoice.size()>0 && count==4) userChoice.clear();
			panel9.add(new JLabel("     "));
			panel9.add(new JLabel(icon));
			count = 5;
			userChoice.add(icon);
			if(num==20) turn = false;
		}
		else if(num<=24 && turn) {
			if(userChoice.size()>0 && count==5) userChoice.clear();
			panel10.add(new JLabel("     "));
			panel10.add(new JLabel(icon));
			count = 6;
			userChoice.add(icon);
			if(num==24) turn = false;
		}
		panel1.revalidate();
	}
	public static void main(String[] args) {
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout(5, 5));
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(6, 0));
		panel1.setBackground(Color.DARK_GRAY);
		
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.setBackground(Color.DARK_GRAY);
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.setBackground(Color.DARK_GRAY);
		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.setBackground(Color.DARK_GRAY);
		panel5 = new JPanel();
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.setBackground(Color.GRAY);
		panel6 = new JPanel();
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel6.setBackground(Color.GRAY);
		panel7 = new JPanel();
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
		panel7.setBackground(Color.GRAY);
		panel8 = new JPanel();
		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel8.setBackground(Color.GRAY);
		panel9 = new JPanel();
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));
		panel9.setBackground(Color.GRAY);
		panel10 = new JPanel();
		panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));
		panel10.setBackground(Color.GRAY);
		
		lGreen = new ImageIcon("green.png");
		lOrange = new ImageIcon("orange.png");
		lBlue = new ImageIcon("blue.png");
		lLightBlue = new ImageIcon("light-blue.png");
		lRed = new ImageIcon("red.png");
		lYellow = new ImageIcon("yellow.png");
		
		allIcons = new ArrayList<Icon>(Arrays.asList(lGreen, lOrange, lBlue, lLightBlue, lRed, lYellow));
		computer();
		
		ex = new ImageIcon("ex.png");
		plus = new ImageIcon("plus.png");
		
		bGreen = new ImageIcon("green-chrome-button.png");
		bOrange = new ImageIcon("orange-chrome.png");
		bBlue = new ImageIcon("blue-chrome-button.png");
		bLightBlue = new ImageIcon("light-blue-chrome-button.png");
		bRed = new ImageIcon("red-chrome.png");
		bYellow = new ImageIcon("yellow-chrome-button.png");
		
		backspace = new ImageIcon("backspace.png");
		gameOver = new ImageIcon("game_over.png");
		win = new ImageIcon("win.png");
		restart = new ImageIcon("restart.png");
		ok = new ImageIcon("ok.png");
		mind = new ImageIcon("mind.png");
		
		label1 = new JLabel(mind);
		label2 = new JLabel(gameOver);
		label3 = new JLabel(win);
		
		button1 = new JButton(bGreen);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.setFocusPainted(false);
		button1.addActionListener(e -> addToPanel(lGreen));
		
		button2 = new JButton(bOrange);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setFocusPainted(false);
		button2.addActionListener(e -> addToPanel(lOrange));
		
		button3 = new JButton(bBlue);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setFocusPainted(false);
		button3.addActionListener(e -> addToPanel(lBlue));
		
		button4 = new JButton(bLightBlue);
		button4.setBorderPainted(false);
		button4.setContentAreaFilled(false);
		button4.setFocusPainted(false);
		button4.addActionListener(e -> addToPanel(lLightBlue));
		
		button5 = new JButton(bRed);
		button5.setBorderPainted(false);
		button5.setContentAreaFilled(false);
		button5.setFocusPainted(false);
		button5.addActionListener(e -> addToPanel(lRed));
		
		button6 = new JButton(bYellow);
		button6.setBorderPainted(false);
		button6.setContentAreaFilled(false);
		button6.setFocusPainted(false);
		button6.addActionListener(e -> addToPanel(lYellow));
		
		button7 = new JButton(ok);
		button7.setBorderPainted(false);
		button7.setContentAreaFilled(false);
		button7.setFocusPainted(false);
		button7.addActionListener(e -> showResults());
		
		button8 = new JButton(backspace);
		button8.setBorderPainted(false);
		button8.setContentAreaFilled(false);
		button8.setFocusPainted(false);
		button8.addActionListener(e -> removeFromPanel());
				
		button9 = new JButton(restart);
		button9.setBorderPainted(false);
		button9.setContentAreaFilled(false);
		button9.setFocusPainted(false);
		button9.addActionListener(e -> restartGame());
		
		panel1.add(panel5);
		panel1.add(panel6);
		panel1.add(panel7);
		panel1.add(panel8);
		panel1.add(panel9);
		panel1.add(panel10);
		
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(button4);
		panel2.add(button5);
		panel2.add(button6);
		
		panel3.add(label1);
		
		panel4.add(button9);
		panel4.add(button8);
		panel4.add(button7);
		
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.SOUTH);
		frame.add(panel3, BorderLayout.NORTH);
		frame.add(panel4, BorderLayout.EAST);
		
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

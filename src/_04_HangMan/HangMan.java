package _04_HangMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Stack;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HangMan implements KeyListener{
	Stack<String> words = new Stack<String>();
	int numWords;
	int lettersLeft;
	ArrayList<Character> lettersGuessed = new ArrayList<Character>();
	public String currentWord;
	public static String shownWord = "";
	JFrame frame = new JFrame("HangMan");
	JPanel panel = new JPanel();
	JLabel label = new JLabel("");
	public HangMan() {
		
		
		while(true) { //keep looping until user enters integer for number of words
			try {
				numWords = Integer.parseInt(JOptionPane.showInputDialog("How many Words?"));
				break;
			} 
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Please enter a number between 0 and " + Utilities.getTotalWordsInFile("dictionary.txt"));
			}
		}
		for (int i = 0; i < numWords;i++) { //push that many words to a stack.
			String newWord = Utilities.readRandomLineFromFile("dictionary.txt");
			if (!words.contains(newWord)) {
				words.push(newWord);
			}
		}
		lettersGuessed.add('w');
		lettersGuessed.add('r');
		//label.setFont(Font.MONOSPACED);
		panel.add(label);
		frame.add(panel);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.pack();
		playGame();
	}
	public void showWord(String word, ArrayList<Character> keysTyped) {
		shownWord = "";
		lettersLeft = 0;
		for(int i = 0; i < word.length();i++) {
				if(keysTyped.contains(word.charAt(i))){
					shownWord += word.charAt(i);
					System.out.println("Guessed correctly:"+word.charAt(i));
				}
				else {
					shownWord += "_ ";
					lettersLeft++;
				}
		}
		label.setText(shownWord);
		frame.pack();

		System.out.println(keysTyped);
		if(lettersLeft == 0 && words.size() > 0){ //word is solved, restart
			playGame();
			
		}
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(!lettersGuessed.contains(e.getKeyChar())){
			lettersGuessed.add(e.getKeyChar());
		}
		
		System.out.println(lettersGuessed);
		showWord(currentWord, lettersGuessed);
		
	}
	public static void main(String[] args) {
		new HangMan();
	}
	
	public void playGame() {
		lettersGuessed = new ArrayList<Character>();
		currentWord = words.pop();
		System.out.println("Playing Game. Word is  " + currentWord);
		showWord(currentWord, lettersGuessed);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

import java.io.*;
import java.util.*;

public class DFA {
	public int[][] Transition = new int[20][20];
	public int alphaNum, stateNum;
	public ArrayList<Character> alphabet;
	public Set<Integer> finalstates;
	public int init_state,INIT;

	public void buildDFA() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please enter the size of alphabet");
		alphaNum = Integer.parseInt(br.readLine());
		System.out.println("please enter the number of states");
		stateNum = Integer.parseInt(br.readLine());
		System.out.println("please enter all the alphabets without space");
		String alphabets = br.readLine();

		alphabet = new ArrayList<Character>();
		for (int i = 0; i < alphabets.length(); i++) {
			alphabet.add(alphabets.charAt(i));
		}
		
		System.out.println("Enter the transition table like this(replace the non-state with space)");
		System.out.println("-----------");
		System.out.println("  a b c d");
		System.out.println("1:2 3 4 5");
		System.out.println("-----------");
		System.out.print("  ");
		for (int i = 0; i < alphabets.length(); i++) {
			System.out.print(alphabets.charAt(i));
			System.out.print(" ");
		}
		System.out.println();
		
		for (int i = 0; i < stateNum; i++) {
			String line = br.readLine();
			int state = Integer.parseInt("" + line.charAt(0));
			for (int j = 0; j < alphaNum; j++) {
				if(line.charAt(j * 2 + 2)==' ') {
					Transition[state][j] = 0;
					continue;
				}
				int next_state = Integer.parseInt("" + line.charAt(j * 2 + 2));
				Transition[state][j] = next_state;
			}
		}

		finalstates = new HashSet<Integer>();
		System.out.println("Enter the final states with space");
		String final_states = br.readLine();
		String ss[] = final_states.split(" ");
		for (String tmp : ss) {
			int fin_sta = Integer.parseInt(tmp);
			finalstates.add(fin_sta);
		}
		System.out.println("Enter the starting state");
		init_state = Integer.parseInt(br.readLine());
		INIT=init_state;
	}

	public void checkDFA() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the input strings,tpye '.' to end input.");
		String input = br.readLine();
		while(input.charAt(0)!='.') {
			init_state=INIT;
			int index = 0;
			while (index < input.length()) {
				char tmp = input.charAt(index);
				int next_state = alphabet.indexOf(tmp);
				init_state = Transition[init_state][next_state];
				if (init_state == 0)
					break;
				index++;
			}
			if (finalstates.contains(init_state))
				System.out.println("Your input string is accepted by the DFA");
			else
				System.out.println("Your input string is rejected by  the DFA");
			input = br.readLine();
		}
	}
	public static void main(String args[]) throws Exception {
		DFA detect = new DFA();
		detect.buildDFA();
		detect.checkDFA();
	}
}

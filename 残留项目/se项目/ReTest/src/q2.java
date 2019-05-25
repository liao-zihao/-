
import java.util.*;
import java.io.*;
public class q2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("please enter the number of states:");
        int statenum=input.nextInt();
        System.out.println("please enter your state name :");
        Character [] statename=new Character [statenum];
        for(int i=0;i<statenum;i++)
        {
            statename[i]=input.next().charAt(0);
        }
        System.out.println("please enter the number of final state:");
        int final_statenum=input.nextInt();
        while(final_statenum>statenum)
        {
            System.out.println("Wrong input please enter correct number of final states:");
            final_statenum=input.nextInt();
        }
        Set<Character> finalstate = new HashSet<Character>();
        Set<Character> set = new HashSet<>(Arrays.asList(statename));
        for(Character s :set) System.out.println(s);
        System.out.println("please enter the final state:");
        for(int i=0;i<final_statenum;i++)
        {
            char finals=input.next().charAt(0);
            finalstate.add(finals);
            //for(String s :set) System.out.println(s);
            boolean flag1 = set.contains(finals);
            System.out.println("flag= "+flag1);
            while(flag1==false)
            {
                System.out.println("Wrong input final state ,please enter again");
                finals=input.next().charAt(0);
                finalstate.add(finals);
            }
            
        }
        // state condition
        System.out.println("please enter the number of alphabets:");
        int alphanum=input.nextInt();
        System.out.println("please enter your alphabets:");
        char alphabet[]=new char[alphanum+1];
        for(int i=0;i<alphanum;i++)
        {
            alphabet[i]=input.next().charAt(0);
        }
        String transtable[][]=new String[statenum+1][alphanum+1];
        System.out.println("enter the transition table:");
        for(int i=0;i<=statenum;i++)
        {
            System.out.println("  "+alphabet[i]);
            for(int j=0;j<alphanum;j++)
            {}
        }
    }}
  
package events;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import com.typewriter.MarkovGen;
import com.typewriter.ProbabilityGenerator;

import net.dv8tion.jda.api.events.message.*;

public class pingEvent extends ListenerAdapter{

    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMessage().getContentRaw().contains("ping")){
            String[] split = event.getMessage().getContentRaw().split(" ");
            if(split.length < 3){
                event.getChannel().sendMessage("Give an mOrder and size").queue();
                return;
            }
            int mOrder = Integer.parseInt(split[1]);
            int size = Integer.parseInt(split[2]);

            if(mOrder > size){
                event.getChannel().sendMessage("mOrder must be less than size").queue();
                return;
            }

            ArrayList<String> input = readFile();

            MarkovGen<String> markov = new MarkovGen<String>(mOrder);

            markov.train(input);

            ArrayList<String> output = markov.generate(size);
            String outputString = ArrayToString(output);

            event.getChannel().sendMessage(outputString).queue();



        }
        
        if(event.getMessage().getContentRaw().contains("type")){
            String[] split = event.getMessage().getContentRaw().split(" ");
            if(split.length < 3){
                event.getChannel().sendMessage("Give an mOrder and size").queue();
                return;
            }
            int mOrder = Integer.parseInt(split[1]);
            int size = Integer.parseInt(split[2]);

            if(mOrder > size){
                event.getChannel().sendMessage("mOrder must be less than size").queue();
                return;
            }

            //ArrayList<String> input = readFile();
            ArrayList<Character> inputChar = readFileAsChar();

            //MarkovGen<String> markov = new MarkovGen<String>(mOrder);

            MarkovGen<Character> markov = new MarkovGen<Character>(mOrder);

            //markov.train(input);
            markov.train(inputChar);

            //ArrayList<String> output = markov.generate(size);
            //String outputString = ArrayToString(output);

            ArrayList<Character> output = markov.generate(size);
            String outputString = ArrayToCharString(output);

            event.getChannel().sendMessage(outputString).queue();
        }
    }

    public String ArrayToCharString(ArrayList<Character> input){
        String output = "";
        for(Character s : input){
            output += s;
        }
        return output;
    }

    public String ArrayToString(ArrayList<String> input){
        String output = "";
        for(String s : input){
            output += s + " ";
        }
        return output;
    }

    public ArrayList<Character> readFileAsChar(){
        //open poe.txt and turn into an arraylist of strings of words
        String filepath = "demo/src/poe.txt";
        ArrayList<Character> wordList = new ArrayList<Character>();
        File file = new File(filepath);
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                for(int i = 0; i < line.length(); i++){
                    wordList.add(line.charAt(i));
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return wordList;
        
    }


    public ArrayList<String> readFile(){
        //open poe.txt and turn into an arraylist of strings of words
        String filepath = "demo/src/poe.txt";
        File file = new File(filepath);
        ArrayList<String> wordList = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                ArrayList<String> words = cleanAndParseLine(line);
                for(String s : words){
                    wordList.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(wordList.size());

        return wordList;
        
    }

    public ArrayList<String> cleanAndParseLine(String line){
        ArrayList<String> words = new ArrayList<String>();
        String[] split = line.split(" ");
        for(String s : split){
            words.add(s);
        }

        for(int i = 0; i < words.size(); i++){
            String word = words.get(i);
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            word = word.toLowerCase();
            words.set(i, word);
        }

        return words;
    }

    
    
}
package model.levels;

import model.Level;
import model.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Hanis Saley
 * version 1.1
 */

public class Level5 extends Level {

    private HashMap <String, Integer> map = new HashMap<>();
    private ArrayList<String> mathProblemsLevel5;

    public Level5(String lvlName) {
        super(lvlName);
        setMonster(new Monster("Valyei",140));
    }

    @Override
    public double generateMathQuestion() {
        try{
            mathProblemsLevel5 = getMathProblemsLevel5("files/MathProblemLevel5.txt");
        }catch (FileNotFoundException e ){
            e.printStackTrace();
        }
        for(int i = 0; i< mathProblemsLevel5.size(); i++){
            String question = mathProblemsLevel5.get(i).substring(0, mathProblemsLevel5.get(i).indexOf('='));
            int answer = Integer.parseInt(mathProblemsLevel5.get(i).substring(mathProblemsLevel5.get(i).indexOf('=')+ 2));
            map.put(question,answer);
        }

        String question = generateRandomQuestion();
        System.out.println(question+ " = ?");
        return map.get(question);
    }


    private ArrayList<String> getMathProblemsLevel5(String file) throws FileNotFoundException{
        Scanner in = new Scanner(new File(file));

        ArrayList<String> mathProblemsLevel5 = new ArrayList<>();
        while(in.hasNextLine()){
            mathProblemsLevel5.add(in.nextLine());
        }
        in.close();
        return mathProblemsLevel5;
    }

    private String generateRandomQuestion(){
        Random random = new Random();
        int index = random.nextInt(mathProblemsLevel5.size());
        return mathProblemsLevel5.get(index).substring(0,mathProblemsLevel5.get(index).indexOf('='));
    }

//    @Override
//    public double generateMathQuestion() {
//        int a, b;
//        a = (int)(Math.random() * 20 + 1);
//        b = (int)(Math.random() * 20 + 1);
//        if(a > b){
//            System.out.println(a + " / " + b + " = ?");
//            System.out.println("round down the answer for the time being");
//            System.out.println("in a>b");
//            return a/b;
//        }
//        else{
//            System.out.println(b +" / " + a + " = ?");
//            System.out.println("round down the answer for the time being"); //läsa från fil eller fixa så att man ska skriva in double?
//            System.out.println("in a < b");
//            return b/a;
//        }
//    }
}

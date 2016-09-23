/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomente3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author faielomente
 */
public class Lomente3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        String file = "file.in_b.txt";
        String line;
        String loop = "";
        BufferedReader reader = readFile(file);
        Stack braces = new Stack();
        line = reader.readLine();
        
        while(line != null){
            
            if(line.contains("for")){
                if(line.contains("{"))
                    braces.push(line);
                
                while(!braces.isEmpty()){
                    loop += line;
                    line = reader.readLine();
                    
                    if(line.contains("{"))
                        braces.push("{");
                    else if(line.contains("}"))
                        braces.pop();
                    
                }
                loop = loop.replaceAll("\\s{2}", "");
                System.out.println(loop);
                ArrayList tokens = tokenizer(loop);
                loop = "";
                break;
            }
            line = reader.readLine();
        }
        
    }
    
    public static ArrayList tokenizer(String loop){
        
        StringTokenizer tokenizer;
        ArrayList<String> tokens = new ArrayList<String>();
        

        tokenizer = new StringTokenizer(loop, ";");

        while (tokenizer.hasMoreTokens()){

            String token = tokenizer.nextToken();

            System.out.println(token);
            if (token.contains("for") == true){

                String temp = token.substring(token.indexOf('('));
                tokens.add(temp.substring(temp.indexOf(' ')));

            }
            else if (token.contains("){") || token.contains(") {") || (token.charAt(token.length()-1) == ')')){

                tokens.add(token.substring(0, token.indexOf(")")));

            }
            else{

                tokens.add(token);

            }

        }
        
        return tokens;
    }
    
    public static BufferedReader readFile(String file) throws FileNotFoundException{
        
        BufferedReader br = new BufferedReader(new FileReader(new File(file)));
        
        return br;
        
    }
}

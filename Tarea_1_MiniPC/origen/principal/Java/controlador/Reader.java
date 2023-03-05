/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alina
 */
public class Reader {
    //Lee el archivo con las instrucciones
    public ArrayList<String> readFile (File fileName){
        ArrayList<String> fileLines = new ArrayList<String>();
        if (validateFile (fileName)){
            try {
                BufferedReader prompter = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = prompter.readLine()) != null) {
                    fileLines.add(line); //linea leida del archivo
                    validateSyntax(line);
                    System.out.println(line);
                }
                prompter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileLines;
    }
           
    //Verificar que el archivo exista y que no este en blanco
    public boolean validateFile (File fileName){
        if (!fileName.getName().endsWith(".asm")) {
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no es de tipo .asm", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } if (!fileName.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no existe", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!fileName.isFile()) {
            JOptionPane.showMessageDialog(null, "La ruta no corresponde a un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (fileName.length() == 0){
            JOptionPane.showMessageDialog(null, "El archivo seleccionado esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }  
        return true;
    }
    
    //Verifica que la sintaxis de las instrucciones del archivo sean validas 
    public boolean validateSyntax(String instruction) {
        //instruction = "store cX";
        instruction = instruction.toUpperCase();
        String[] tokens = instruction.split("\\s+");
        
        // Validación de la sintaxis para cada instrucción 
        if (tokens[0].equals("MOV")){
            if (tokens.length == 3 && isRecord(tokens[1]) && isOperator(tokens[2])) {
                return true;
            }         
        }else if ((tokens[0].equals("ADD")) || (tokens[0].equals("SUB")) 
                || (tokens[0].equals("LOAD"))|| (tokens[0].equals("STORE"))){
            if (tokens.length == 2 && isRecord(tokens[1])){ //&& isOperator(tokens[2])) {
                return true;
            }
        }else {
            JOptionPane.showMessageDialog(null, "La instruccion "+ instruction +" no es valida " , "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null, "La instruccion "+ instruction +" no es valida ", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Verifica que el string ingresado sea un registro valido
    private boolean isRecord(String token) {
        if (token.endsWith(",")) {
            token = token.substring(0, token.length() - 1);
        }
        return token.matches("(AX|BX|CX|DX)");
    }

    // Verifica que el string es un operando válido (registro o entero negativo o positivo)
    private boolean isOperator(String token) {
        return token.matches("-?\\d+|\\[(AX|BX|CX|DX)\\]");
    }


    
}//final de la clase



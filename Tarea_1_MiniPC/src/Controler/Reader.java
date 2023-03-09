package Controler; //CAMBIAR NOMBRE DE PAQUETE

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
    ArrayList<String> fileLines = new ArrayList<>();
    int pos = 0;
    int cant_lines = 0;
    
    public ArrayList<String> readFile (File fileName){
        if (validateFile (fileName)){
            try {
                BufferedReader prompter = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = prompter.readLine()) != null) {
                    fileLines.add(line); //linea leida del archivo
                    validateSyntax(line);
                    cant_lines++;
                }
                prompter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileLines;
    }

    
    //Verifica que el archivo seleccionado cumpla con todos los requisitos
    public void verify (File fileName){
        if (validateFile (fileName)){
            //LEA
            //validateSyntax(" ");
        }
    }
            
    //Verificar que el archivo exista y que no este en blanco
    public boolean validateFile (File fileName){
        if (!fileName.getName().endsWith(".asm")) {
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no es de tipo .asm", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!fileName.exists()) {
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
            if (tokens.length == 3 && esRegistro(tokens[1]) && esOperando(tokens[2])) {
                return true;
            }         
        }else if ((tokens[0].equals("ADD")) || (tokens[0].equals("SUB")) 
                || (tokens[0].equals("LOAD"))|| (tokens[0].equals("STORE"))){
            if (tokens.length == 2 && esRegistro(tokens[1])){ //&& esOperando(tokens[2])) {
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
    private boolean esRegistro(String token) {
        if (token.endsWith(",")) {
            token = token.substring(0, token.length() - 1);
        }
        return token.matches("(AX|BX|CX|DX)");
    }

    // Verifica que el string es un operando válido (registro o entero negativo o positivo)
    private boolean esOperando(String token) {
        return token.matches("-?\\d+|\\[(AX|BX|CX|DX)\\]");
    }
    
    public String getLine(){
        String line;
        if (pos < cant_lines){
            line = fileLines.get(pos);
            pos++;
            return line;
        }else{
            return null;
        }
    }
    
    public void setParams(){
        pos = 0;
        cant_lines = 0;
    }
    
}//final de la clase


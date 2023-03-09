package Controler;

public class Convert {
    
    // Pasa a bianrio la instruccion
    public String changeFormat(String line) {
        String result = "";
        String newLine = "";
        if (line.contains(",")) {
            newLine = line.replace(",", "");
        }else{
            newLine = line;
        }
        String[] comands = newLine.split(" ");
        int i = 2;
        // cambio a binario de las instrucciones
        switch (comands[0]) {
            case "LOAD": comands[0] = comands[0].replace("LOAD", "0001 "); break;
            case "STORE": comands[0] = comands[0].replace("STORE", "0010 "); break;
            case "MOV": comands[0] = comands[0].replace("MOV", "0011 "); break;
            case "SUB": comands[0] = comands[0].replace("SUB", "0100 "); break;
            case "ADD": comands[0] = comands[0].replace("ADD", "0101 "); break;
        }
        // cambio a binario de los registros
        switch (comands[1]){
            case "AX": comands[1]= comands[1].replace("AX", "0001 "); break;
            case "BX": comands[1]= comands[1].replace("BX", "0010 "); break;
            case "CX": comands[1]= comands[1].replace("CX", "0011 "); break;
            case "DX": comands[1]= comands[1].replace("DX", "0100 "); break;
        }
        if(comands.length  == 2){
            result = "00000000";
        }
        //cambio a binario de los numeros
        else{
            //System.out.println("i: " + comands[i]);
            int a = Integer.parseInt(comands[i]);
            if(a < 0){
                result = "1";
            }
            else{
                result = "0";
            }
            a = Math.abs(a);
            String bin = Integer.toBinaryString(a);
            
            while(bin.length() < 7){
                bin = "0" + bin;
            }
            result = result + bin;
        }

        return comands[0] + comands[1] + result;
    }
    
    public String getIR(String line){
        String result = "";
        String newLine = "";
        if (line.contains(",")) {
            newLine = line.replace(",", "");
        }else{
            newLine = line;
        }
        String[] comands = newLine.split(" ");
        // cambio a binario de las instrucciones
        switch (comands[0]) {
            case "LOAD": comands[0] = comands[0].replace("LOAD", "0001 "); break;
            case "STORE": comands[0] = comands[0].replace("STORE", "0010 "); break;
            case "MOV": comands[0] = comands[0].replace("MOV", "0011 "); break;
            case "SUB": comands[0] = comands[0].replace("SUB", "0100 "); break;
            case "ADD": comands[0] = comands[0].replace("ADD", "0101 "); break;
        }
        return comands[0];
    }
}

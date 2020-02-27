/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class findIndexInTheDictionary {
    private static final int HTTP_COD_SUCESSO     = 200;
    private static final int HTTP_COD_BAD_REQUEST = 400;
    private static int returnFunction[]     = new int[2];

    public static boolean check (int[] v, int value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] != value) {
                return true;
            }
        }
        return false;
    }
    
    public static int[] getIndex(String searchedWord) throws IOException{
        try {
            boolean encontrado = false;
            int index               = -1;
            int numberOfDeadKittens = 0;
            
            returnFunction[0] = index;
            returnFunction[1] = numberOfDeadKittens;

            URL url = null;
            boolean repetiu_valor = false;
            int randomInt, num = 0;
            int total_lista = 127582;
            int i = 0;

            while(!encontrado){
                index++;

                if (index <= 100) {
                    // linear search (teria retornado 70001 gatinhos mortos)
                    url = new URL("http://teste.criainovacao.com.br/api/Dicionario/"+index);
                    System.out.println("index:"+index);

                    numberOfDeadKittens++;
                    returnFunction[1] = numberOfDeadKittens;

                } else {

                    if (index > Math.floorDiv(total_lista, 2)) {
                        num = ThreadLocalRandom.current().nextInt(index, total_lista);
                        int[] vec = new int[i];
                        // valores repetidos não são utilizados
                        if (findIndexInTheDictionary.check(vec, num)) {
                            url = new URL("http://teste.criainovacao.com.br/api/Dicionario/"+num);
                            System.out.println("num:"+num);
                        }
                    } else if (index < Math.floorDiv(total_lista, 2)) {
                        num = ThreadLocalRandom.current().nextInt(index++, Math.floorDiv(total_lista, 2));
                        int[] vec = new int[i];
                        if (findIndexInTheDictionary.check(vec, num)) {
                            url = new URL("http://teste.criainovacao.com.br/api/Dicionario/" + num);
                            System.out.println("num:" + num);
                        }
                    }

//                    vec[i] = num;
                    i++;
                    numberOfDeadKittens++;
                    returnFunction[1] = numberOfDeadKittens;
                }

                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

                String retornoJson = bufferedReader.readLine();

                if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                    if (con.getResponseCode() == HTTP_COD_BAD_REQUEST) {
                        throw new RuntimeException("A palavra " + searchedWord + " não foi encontrada no Dicionário!");
                    }else{
                        throw new RuntimeException("HTTP error code : "+ con.getRequestMethod());
                    }
                }else if(retornoJson.equals(searchedWord)){
                    returnFunction[0] = index;
                    encontrado = true;
                }else if(retornoJson.equals("\"Message\":\"The request is invalid.\"")){
                    throw new RuntimeException("A palavra " + searchedWord + " não foi encontrada no Dicionário!");
                }

                con.disconnect();
            }
            return returnFunction;

        } catch (IOException | RuntimeException e) {
            return returnFunction;
        }
    }
}

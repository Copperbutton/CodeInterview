import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Question 1: Decoder
public class Solution {
    // For testing purpose: writing data into the file
    private void writeData(String[] inputData, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String str: inputData) {
            bufferedWriter.write(str);
            bufferedWriter.newLine(); 
        }
        bufferedWriter.close();
    }
    
    private class EncodedData {
        int xCoord;
        int yCoord;
        // Using list since we don't know the intial size of the data
        List<char[]> data;
        
        public EncodedData() {
            data = new ArrayList<char[]> ();
        }
        
        public char getCharAtIndex() throws Exception {
            // Add data validations
            if (data.size() == 0) {
                throw new Exception("Data not initiated");
            }
            
            if (data.size() < yCoord + 1 || data.get(0).length < xCoord + 1) {
                throw new Exception("X/Y coordination out fo range");
            }
            
            return data.get(data.size() - 1 - yCoord)[xCoord];
        }
    }
    
    
    // Part I: Read data from the file
    private char decode(String path) throws Exception {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        EncodedData encodedData = new EncodedData();
        
        // Read coordinations
        String line = bufferedReader.readLine();
        System.out.println(line);
        
        // Remove the [] and space in the string
        String[] coordinations = line.replaceAll("[\\[\\]\\s]", "").split(",");
        encodedData.xCoord = Integer.valueOf(coordinations[0]);
        encodedData.yCoord = Integer.valueOf(coordinations[1]);
                
        line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            encodedData.data.add(line.toCharArray());
            line = bufferedReader.readLine();
        }
        
        bufferedReader.close();
        return encodedData.getCharAtIndex();
    }
    
    // Part II
    private String decodeMatrixArray(String path) throws Exception {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        // Assuming 10 digit passcode
        EncodedData[] encodedDataArray = new EncodedData[10];
        
        String line = bufferedReader.readLine();
        EncodedData encodedData = new EncodedData();
        while (line != null) {
            System.out.println(line);
            
            char[] data = line.replaceAll(" ", "").toCharArray();
            if (data[0] >= 'A' && data[0] <= 'Z') {
                encodedData.data.add(data);
            } else if (data[0] == '[') {
                encodedData.xCoord = data[1] - '0';
                encodedData.yCoord = data[3] - '0';
            } else {
               if (encodedData.data.size() > 0) {
                   encodedData = new EncodedData();
               } 
               int index = Integer.valueOf(line);
               System.out.println("Index: " + index);
               encodedDataArray[index] = encodedData;
            }
            
            line = bufferedReader.readLine();
        }
        
        StringBuffer result = new StringBuffer();
        for(EncodedData data: encodedDataArray) {
            if(data == null) {
                break;
            }
            char ch = data.getCharAtIndex();
            System.out.println(ch);
            result.append(ch);
        }
        return result.toString();
    }
    
    // Part III: almost the same as Part II
        private String decodeFirstPassWord(String path) throws Exception {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Assuming 10 digit passcode
            EncodedData[] encodedDataArray = new EncodedData[10];
            
            String line = bufferedReader.readLine();
            EncodedData encodedData = new EncodedData();
            while (line != null) {
                System.out.println(line);
                
                char[] data = line.replaceAll(" ", "").toCharArray();
                if (data[0] >= 'A' && data[0] <= 'Z') {
                    encodedData.data.add(data);
                } else if (data[0] == '[') {
                    encodedData.xCoord = data[1] - '0';
                    encodedData.yCoord = data[3] - '0';
                } else {
                    if (encodedData.data.size() > 0) {
                        encodedData = new EncodedData();
                    } 
                    int index = Integer.valueOf(line);
                    
                    // Sart to repeat, break here
                    if (encodedDataArray[index] != null) {
                        break;
                    }
                    encodedDataArray[index] = encodedData;
                }
                
                line = bufferedReader.readLine();
            }
        
            StringBuffer result = new StringBuffer();
            for(EncodedData data: encodedDataArray) {
                if(data == null) {
                    break;
                }
                char ch = data.getCharAtIndex();
                System.out.println(ch);
                result.append(ch);
            }
            return result.toString();
        }
    

 public static void main(String[] args) {
     Solution solution = new Solution();
     
     // Test part 1: read data with one matrix
     try {
        String[] testData1 = new String[] {
            "[0, 1]",
            "HUTQW3",
            "NLEVCU"
        };
        
        solution.writeData(testData1, "testFile1.txt");
        System.out.println(solution.decode("testFile1.txt"));   
     } catch (Exception e) {
        System.out.println(e);
     }
   
   // Test part II: read file with multiple matrix
   try {
          String[] testData2 = new String[] {
            "1",
            "[5, 6]",
            "RXIBDIBF",
            "DVMPXTBG",
            "BMWAERXR",
            "UPEIJGMW",
            "YTALDXDH",
            "JNPMOUEJ",
            "XDRHCHWG",
            "0",
            "[0, 1]",
            "HUTQW3",
            "NLEVCU"
        };
        
        solution.writeData(testData2, "testFile2.txt");
        System.out.println(solution.decodeMatrixArray("testFile2.txt"));   
   } catch (Exception e) {
       System.out.println(e);
   }
   
   
     // Test part III: read multiple files
    try {
            String[] testData3 = new String[] {
               "2",
                "[5, 6]",
                "RXIBDSBF",
                "DVMPXTBG",
                "BMWAERXR",
                "UPEIJGMW",
                "YTALDXDH",
                "JNPMOUEJ",
                "XDRHCHWG",
                "0",
                "[0, 1]",
                "IUTQWC",
                "NLEVCU",
                "1",
                "[2, 1]",
                "ABCD",
                "EFRT",
                "ASNF",
                "ASFF",
                "3",
                "[2, 1]",
                "ABCD",
                "EFRT",
                "ASTF",
                "ASFF",
                "4",
                "[3, 3]",
                "ABCA",
                "EFRT",
                "ASMF",
                "ASFF",
                "0",
                "[0, 0]",
                "W",
                "1",
                "[0, 0]",
                "R",
                "2",
                "[0, 0]",
                "O",
                "3",
                "[0, 0]",
                "N",
                "4",
                "[0, 0]",
                "G"
            };
            
            solution.writeData(testData3, "testFile3.txt");
            System.out.println(solution.decodeFirstPassWord("testFile3.txt"));   
    } catch (Exception e) {
        System.out.println(e);
    }
   }
}

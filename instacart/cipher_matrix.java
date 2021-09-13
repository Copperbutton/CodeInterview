// Cipher Matrix

public class Solution {
    public char[][] createCipherMatrix(String key, char original, char replace) {
        boolean[] usedChar = new boolean[26];
        // original can't be used any more
        if (original >= 'a') {
            usedChar[original - 'a'] = true;   
        }
        
        char[][] cipher = new char[5][5];    
        int index = 1;   
        for (char ch: key.toCharArray()) { 
            System.out.println("ch:" + ch);   
            if (original >= 'a' && ch == original) {
                ch = replace;
            }
            
            // Skip the used ones.
            if (usedChar[ch - 'a']) {
                continue;
            }
            
            System.out.println("index:" + index);
            cipher[(index - 1)/5][(index - 1)%5] = (char)('A' + ch - 'a');
            usedChar[ch - 'a'] = true;
            index ++;
        }
        
        // Go over the remaining letters in the matrix
        for (int i = 0; i < 26; i++) {
            if(!usedChar[i]) {
                System.out.println("i: " + i);
                System.out.println("index: " + index);
                cipher[(index - 1)/5][(index-1)%5] = (char)('A' + i);
                index ++;
            } else {
                System.out.println("Skip: " + i);
            }
        }
        
        return cipher;
    }
    
    private class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;   
        }    
    }
    public String cipherDecrypt(String encryptedString, String key) {
        char[][] cipherMatrix = createCipherMatrix(key, 'j', 'i');
        printMatrix(cipherMatrix);
        
        // Create a map to decide the location of the characters
        Pair[] charLocation = new Pair[26];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                charLocation[cipherMatrix[i][j] - 'A'] = new Pair(i, j);   
            }
        }
        
        char[] encryptedStringChars = encryptedString.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        // Can add validation: if key.length()%2 != 0, throw exception
        for (int i = 0; i < encryptedStringChars.length/2; i++) {
            int first = encryptedStringChars[i*2] - 'A';
            int second = encryptedStringChars[i*2 + 1] - 'A';
            System.out.println("first: " + first + ", second: " + second);
            System.out.println("firstChar: " + encryptedStringChars[i*2] + ", secondChar: " + encryptedStringChars[i*2 + 1]);
            
            // Add validation here: if charLocation[first] or charLocation[second] == 0 -> char not in the cipher matrix

            char firstDecrpt; 
            char secondDecrpt;
            
            // In the same col: take the one above
            if (charLocation[first].y == charLocation[second].y) {
                // If less than 0, (+5)%5 will get the right one, say (0 - 1 + 5)%5 = 4
                int firstDecrptRow = (charLocation[first].x - 1 + 5) % 5;
                firstDecrpt = cipherMatrix[firstDecrptRow][charLocation[first].y];
                
                int secondDecptRow = (charLocation[second].x - 1 + 5) % 5;
                secondDecrpt = cipherMatrix[secondDecptRow][charLocation[second].y];
            // in the same row, take the one on the left
            } else if(charLocation[first].x == charLocation[second].x) {
                
                // If less than 0, same as above
                int firstDecrptCol = (charLocation[first].y - 1 + 5) % 5;
                firstDecrpt = cipherMatrix[charLocation[first].x][firstDecrptCol];
                
                int secondDecptCol = (charLocation[second].y - 1 + 5) % 5;
                secondDecrpt = cipherMatrix[charLocation[second].x][secondDecptCol];
            // Neigher case
            } else {
                firstDecrpt = cipherMatrix[charLocation[first].x][charLocation[second].y];
                secondDecrpt = cipherMatrix[charLocation[second].x][charLocation[first].y];
            }
            System.out.println("firstDecrpt:" + firstDecrpt + ", secondDecrpt: " + secondDecrpt);
            
            stringBuffer.append(firstDecrpt);
            stringBuffer.append(secondDecrpt);
        }
        return stringBuffer.toString();
    }
    
    public void printMatrix(char[][] cipher) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(cipher[i][j] + ",");       
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // test part 1:
        String key = "instacart";
        char[][] cipher = solution.createCipherMatrix(key, 'j', 'i');
        solution.printMatrix(cipher);
        
        
        // Test part II
        String result = solution.cipherDecrypt("RMROENAQUEANWRBS", "instacart");
        System.out.println(result);
    }
}

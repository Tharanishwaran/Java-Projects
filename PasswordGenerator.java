import org.apache.commons.lang3.RandomStringUtils; //apache commons libray file imported

public class PasswordGenerator {

    public static String generatePassword(int length) {
       
        return RandomStringUtils.random(length, true, true, true, true); 
    }

    public static void main(String[] args) {
        
        String password = generatePassword(12); 
        
        System.out.println(password); 
    
    }

}

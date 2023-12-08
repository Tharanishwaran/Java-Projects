public class PasswordGenerator {

    public static String generatePassword(int length) {
        StringBuilder builder = new StringBuilder();
      
        Random random = new SecureRandom();
        
        for (int i = 0; i < length; i++) {
        
            int type = random.nextInt(4);
            
            switch (type) {
                case 0:
                    builder.append((char) (random.nextInt(26) + 'a'));
                    break;
            
                case 1:
                    builder.append((char) (random.nextInt(26) + 'A'));
                    break;
                
                case 2:
                    builder.append((char) (random.nextInt(10) + '0'));
                    break;
                
                case 3:
                    builder.append((char) (random.nextInt(33) + ' '));
                    break;
            }
        }
        
        return builder.toString();
    }

    public static void main(String[] args) {
        
        String password = generatePassword(10);
        
        System.out.println(password);
    }
}

package govtech.apiutils;


import java.security.SecureRandom;

public class ApiUtils {


    public static final String baseURL="http://167.99.65.170/";
    public static final String  testDataPath=System.getProperty("user.dir")+"/src/test/resources/testdata/";


    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String AB1 = "0123456789";
    static SecureRandom rnd = new SecureRandom();

   public static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static String randomNumbers( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB1.charAt( rnd.nextInt(AB1.length()) ) );
        return sb.toString();
    }

    public static void main(String[] args) {
        //sendPOST();
    }
}



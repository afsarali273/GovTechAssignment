 package govtech.utils;
import org.apache.log4j.Logger;

 public class Log {
 // Initialize Log4j logs
 private static Logger Log = Logger.getLogger(govtech.utils.Log.class.getName());
 // Need to create these methods, so that they can be called
 public static void info(String message) {
         Log.info(message);
         }
 public static void warn(String message) {
     Log.warn(message);
     }
 public static void error(String message) {
     Log.error(message);
     }
 public static void fatal(String message) {
     Log.fatal(message);
     }
 public static void debug(String message) {
     Log.debug(message);
     }
 }
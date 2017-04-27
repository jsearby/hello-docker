package fr.learner;

import org.glassfish.grizzly.http.server.HttpServer;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

/**
 * Very small http server displaying time.<BR>
 * 
 * @author James Searby
 */
public class Hello {

  private static boolean      mEnding = false;
  private static final Logger logger  = LogManager.getLogger(Hello.class);

  /**
   * Program entry point
   * 
   * @param pArgs
   *          String[] arguments
   */
  public static void main(String[] pArgs) {
    logger.info("Entering application");
    Hello lMain = new Hello();
    lMain.createServer();
  }

  /**
   * Create basic HTTP server with two page (/time and /stop)
   */
  private void createServer() {
    HttpServer server = HttpServer.createSimpleServer();
    server.getServerConfiguration().addHttpHandler(new timeHTTPHandler(),
        new String[]{"/time"});
    server.getServerConfiguration().addHttpHandler(new stopHTTPHandler(),
        new String[]{"/stop"});
    server.getServerConfiguration().addHttpHandler(new logHTTPHandler(),
        new String[]{"/log"});
    try {
      server.start();
      logger.info("Application ready");
      while (!mEnding) {
        Thread.sleep(10);
      }
    } catch (Exception e) {
      logger.error(e);
    }
  }

  /**
   * Handler for /time page display
   */
  private class timeHTTPHandler extends HttpHandler {
    public void service(Request request, Response response) throws Exception {
      logger.info("Handling /time request");

      final SimpleDateFormat format = new SimpleDateFormat(
          "EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
      final String date = format.format(new Date(System.currentTimeMillis()));

      String lMyIp = Inet4Address.getLocalHost().getHostAddress();
      String lContent = "My IP is " + lMyIp + "\n";
      lContent += "Current date is " + date + "\n";
      lContent += "Greeting " + System.getenv("GREETING") + "\n";

      response.setContentType("text/plain");
      response.setContentLength(lContent.length());
      response.getWriter().write(lContent);
      response.getWriter().flush();
    }
  }

  /**
   * Handler for /time page display
   */
  private class logHTTPHandler extends HttpHandler {
    public void service(Request request, Response response) throws Exception {
      logger.info("Handling /log request");

      final SimpleDateFormat format = new SimpleDateFormat(
          "EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
      final String date = format.format(new Date(System.currentTimeMillis()));

      String lMyIp = Inet4Address.getLocalHost().getHostAddress();
      String lContent = "My IP is " + lMyIp + "\n";
      lContent += "Current date is " + date + "\n";
      lContent += "Greeting " + System.getenv("GREETING") + "\n";
      try {
        // Extract number of line to log (default 1)
        String lNbLineStr = request.getParameter("line");
        int lNbLine=1;
        if (lNbLineStr!=null && lNbLineStr.length()>0) {
          lNbLine = Integer.parseInt(lNbLineStr);
        }
        // Extract length of line to log (default 50)
        String lLenghtStr = request.getParameter("len");
        int lLength=50;
        if (lLenghtStr!=null && lLenghtStr.length()>0) {
          lLength = Integer.parseInt(lLenghtStr)/10;
        }
        // Generate the logs
        for (int lI = 0; lI < lNbLine; lI++) {
          StringBuilder lSB=new StringBuilder();
          lSB.append("Looging " + lI + "  " + System.currentTimeMillis());
          for (int lJ = 0; lJ < lLength; lJ++) {
            lSB.append(" aaaaaaaa ");
          }
          logger.info(lSB.toString());
        }
      } catch (Exception lE) {
        logger.error(lE);
      }
      response.setContentType("text/plain");
      response.setContentLength(lContent.length());
      response.getWriter().write(lContent);
      response.getWriter().flush();
    }
  }

  /**
   * Handler for /stop page display
   */
  private class stopHTTPHandler extends HttpHandler {
    public void service(Request request, Response response) throws Exception {
      logger.trace("Handling /stop request");
      System.out.println("Handling /stop request");

      String lMyIp = Inet4Address.getLocalHost().getHostAddress();
      String lContent = "My IP is " + lMyIp + "\n";
      lContent += "Greeting " + System.getenv("GREETING") + "\n";
      lContent += "Ending \n";

      response.setContentType("text/plain");
      response.setContentLength(lContent.length());
      response.getWriter().write(lContent);
      response.getWriter().flush();
      mEnding = true;
    }
  }
}
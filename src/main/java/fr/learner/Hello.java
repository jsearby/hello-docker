package fr.learner;

import org.glassfish.grizzly.http.server.HttpServer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

/**
 * Very small http server
 * @author James
 */
public class Hello {

	private static boolean mEnding=false;
	
	/**
	 * Program entry point
	 * @param pArgs String[] arguments
	 */
	public static void main(String[] pArgs) {
		System.out.println("Bonjour");
		Hello lMain = new Hello();
		lMain.createServer();
	}

	/**
	 * Create basic HTTP server with two page (/time and /stop) 
	 */
	private void createServer() {
		HttpServer server = HttpServer.createSimpleServer();
		server.getServerConfiguration().addHttpHandler(new timeHTTPHandler(), new String[] { "/time" });
		server.getServerConfiguration().addHttpHandler(new stopHTTPHandler(), new String[] { "/stop" });
		try {
			server.start();
			System.out.println("Send request to /stop");
			while (!mEnding) {
				Thread.sleep(10);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Handler for /time page display
	 */
	private class timeHTTPHandler extends HttpHandler {
		public void service(Request request, Response response) throws Exception {
			final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
			final String date = format.format(new Date(System.currentTimeMillis()));
			response.setContentType("text/plain");
			response.setContentLength(date.length());
			response.getWriter().write(date);
		}
	}

	/**
   * Handler for /stop page display
   */
	private class stopHTTPHandler extends HttpHandler {
		public void service(Request request, Response response) throws Exception {
			response.setContentType("text/plain");
			response.setContentLength("Ending".length());
			response.getWriter().write("Ending");
			mEnding=true;
		}
	}
}
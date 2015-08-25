package rs.fon.is.stockTrading.logic;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rs.fon.is.stockTrading.sysop.Faktorizuj;

import javax.ws.rs.core.Context;

@Path("/nmf")
public class Kontroler {
	
	@Context 
	ServletContext context;
	
	@GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public String analiziraj(@QueryParam(value = "numFeatures") final int numOfFeatures, @QueryParam(value = "t") 
      final List<String> ticker) {
		
		String[] tickers = new String[ticker.size()];
		for (int i = 0; i < ticker.size(); i++) {
			tickers[i] = ticker.get(i);
		}
		
	    try {
	    	
	    	//return context.getRealPath("/WEB-INF/tickers.properties");
		 return Faktorizuj.faktorizuj(tickers, numOfFeatures, context.getRealPath("/WEB-INF/tickers.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			return "Greska!";
		} 
	  }


}

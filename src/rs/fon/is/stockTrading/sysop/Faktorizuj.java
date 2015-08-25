package rs.fon.is.stockTrading.sysop;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import java.util.List;

import Jama.Matrix;
import rs.fon.is.stockTrading.core.CSVOps;
import rs.fon.is.stockTrading.core.Feature;
import rs.fon.is.stockTrading.core.FeatureFactory;
import rs.fon.is.stockTrading.core.Result;
import rs.fon.is.stockTrading.core.TickerLoader;
import rs.fon.is.stockTrading.core.Utils;

public class Faktorizuj {
	
	
	public static String faktorizuj(String[] kompanije, int numOfFeatures, String prop) throws URISyntaxException, IOException {
		
		List<String[]> listaGoog = CSVOps.readFromAPI("http://ichart.yahoo.com/table.csv?s=GOOG");

		String[] tickers = new String[kompanije.length];
		TickerLoader.getInstance().setPropPath(prop);
		TickerLoader.getInstance().loadProperties();
		
		for (int i = 0; i < tickers.length; i++) {
			
			tickers[i] = TickerLoader.getInstance().get(kompanije[i]);
		}

		Matrix data = CSVOps.createDataMatrix(tickers, listaGoog.size() - 1);

		Result resset = Utils.factorize(data, numOfFeatures, 7000);

		String[] datumi = new String[listaGoog.size() - 1];
		for (int i = 1; i < listaGoog.size(); i++) {
			datumi[i - 1] = listaGoog.get(i)[0];
		}
		
		for (int i = 0; i < kompanije.length; i++) {
			kompanije[i] = kompanije[i] + "\n(" + tickers[i] + ")";
		}

		List<Feature> lista = FeatureFactory.createFeatures(resset.getH(), resset.getW(), kompanije, datumi,
				numOfFeatures);


		PrintWriter writer = new PrintWriter("data.json", "UTF-8");
		writer.print("data = ");
		writer.println(Utils.featuresToJson(lista, datumi[datumi.length-1], datumi[0]));
		writer.close();
		
		return Utils.featuresToJson(lista, datumi[datumi.length-1], datumi[0]);
		

	}

}

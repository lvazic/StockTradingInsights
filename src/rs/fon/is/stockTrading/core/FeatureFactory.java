package rs.fon.is.stockTrading.core;

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import Jama.Matrix;

public class FeatureFactory {

	public static List<Feature> createFeatures(Matrix H, Matrix W, String[] tickers, String[] dates,
			int numberOfFeatures) {

		List<Feature> list = new LinkedList<>();
		DecimalFormat df = new DecimalFormat("#.##");

		for (int i = 0; i < numberOfFeatures; i++) {

			Feature f = new Feature();
			list.add(f);

			f.setRedniBroj(i + 1);

			double[][] h = H.getArray();
			
			double sumStock = 0;

			for (int j = 0; j < h[i].length; j++) {

				sumStock += h[i][j];
				
			}

			for (int j = 0; j < h[i].length; j++) {

				String num = df.format((h[i][j]/sumStock)*100);
				double d = Double.parseDouble(num);

				Entry<String, Double> entry = new AbstractMap.SimpleEntry<String, Double>(tickers[j], d) ;

				f.getAkcije().add(entry);
			}
			f.sortiraj("akcije");

			double[][] w = W.getArray();
			
			


			for (int j = 0; j < dates.length; j++) {

				String num = df.format(w[j][i]);
				double d = Double.parseDouble(num);
				Entry<String, Double> entry = new AbstractMap.SimpleEntry<String, Double>(dates[j], d);

				f.getDatumi().add(entry);

			}

			f.sortiraj("datumi");

		}

		return list;

	}

}

package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import Jama.Matrix;

import com.opencsv.CSVReader;

public class CSVOps {

	public static List<String[]> readFromAPI(String url) {

		try {

			URL stockURL = new URL(url);
			BufferedReader br = new BufferedReader(new InputStreamReader(stockURL.openStream()));
			CSVReader reader = new CSVReader(br);
			List<String[]> res = reader.readAll();
			reader.close();
			return res;

		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Neispravan URL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("API servis nije dostupan");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greška tokom čitanja podataka sa API servisa");
		}

		return null;
	}

	public static Matrix createDataMatrix(String[] tickers, int datumi) {

		Matrix data = new Matrix(datumi, tickers.length);

		int n = 0;

		while (n < 5) {

			try {

				n++;

				for (int i = 0; i < tickers.length; i++) {
					String url = "http://ichart.yahoo.com/table.csv?s=" + tickers[i];
					Matrix vector = Utils.vectorizeVolume(readFromAPI(url));
					data.setMatrix(0, data.getRowDimension() - 1, i, i, vector);

				}
				break;
			} catch (Exception e) {

			}
		}
		return data;

	}

}

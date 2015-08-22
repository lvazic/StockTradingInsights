package core;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Jama.Matrix;

public class Utils {

	public static Result factorize(Matrix D, int numOfFeatures,
			int numOfIterations) {

		int n = numOfIterations;

		Matrix W = Matrix.random(D.getRowDimension(), numOfFeatures);
		Matrix H = Matrix.random(numOfFeatures, D.getColumnDimension());
		double initialCost = costFunction(D, W.times(H));
		System.out.println("Initial cost: " + initialCost);

		while (n > 0) {
			if (costFunction(D, W.times(H)) == 0) {
				return new Result(W, H, costFunction(D, W.times(H)),
						initialCost, numOfIterations - n);
			}
			Matrix Hn = W.transpose().times(D);
			Matrix Hd = W.transpose().times(W).times(H);
			// H = Hd.inverse().times(H.times(Hn));
			H = (H.arrayTimesEquals(Hn)).arrayRightDivideEquals(Hd);
			// H = Hd.solve(H.times(Hn));

			Matrix Wn = D.times(H.transpose());
			Matrix Wd = W.times(H).times(H.transpose());

			// W = Wd.inverse().times(W.times(Wn));
			// W = Wd.solve(W.times(Wn));
			W = (W.arrayTimesEquals(Wn)).arrayRightDivideEquals(Wd);
			n--;

		}

		return new Result(W, H, costFunction(D, W.times(H)), initialCost,
				numOfIterations - n);

	}

	public static double costFunction(Matrix A, Matrix B) {
		double dif = 0;

		Matrix C = A.minus(B);

		double[][] matrix = C.getArray();

		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {

				matrix[r][c] = matrix[r][c] * matrix[r][c];
				dif += matrix[r][c];
			}

		}

		return dif;

	}

	public static double costFunction(double[][] a, double[][] b) {
		double dif = 0;

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {

				dif += Math.pow((a[i][j] - b[i][j]), 2);
			}

		}

		return dif;

	}

	public static Matrix vectorizeVolume(List<String[]> stock) {

		Matrix volumeVector = new Matrix(stock.size() - 1, 1);

		for (int i = 1; i < stock.size(); i++) {
			volumeVector.set(i - 1, 0,
					Double.parseDouble(stock.get(i)[stock.get(i).length - 2]));

		}

		return volumeVector;

	}
	
	
	public static String featuresToJson(List<Feature> lista, String minDatum, String maxDatum) {
		
		JsonObject objekat = new JsonObject();
		JsonArray features = new JsonArray();
		
		objekat.addProperty("startDate", minDatum);
		objekat.addProperty("endDate", maxDatum);


		for (int i = 0; i < lista.size(); i++) {

			JsonObject feature = new JsonObject();

			feature.addProperty("id", lista.get(i).getRedniBroj());

			JsonArray jsonAkcije = new JsonArray();
			JsonArray jsonDatumi = new JsonArray();

			for (int j = 0; j < lista.get(i).getAkcije().size(); j++) {

				JsonObject akcija = new JsonObject();
//				akcija.addProperty(lista.get(i).getAkcije().get(j).getKey(),
//						lista.get(i).getAkcije().get(j).getValue());

				akcija.addProperty("ticker", lista.get(i).getAkcije().get(j).getKey());
				akcija.addProperty("weight", lista.get(i).getAkcije().get(j).getValue());

				jsonAkcije.add(akcija);
			}

			for (int j = 0; j < 5; j++) {

				JsonObject datum = new JsonObject();
//				datum.addProperty(lista.get(i).getDatumi().get(j).getKey(), lista.get(i).getDatumi().get(j).getValue());

				datum.addProperty("date", lista.get(i).getDatumi().get(j).getKey());
				datum.addProperty("weight", lista.get(i).getDatumi().get(j).getValue());
				jsonDatumi.add(datum);
			}

			feature.add("akcije", jsonAkcije);
			feature.add("datumi", jsonDatumi);
			features.add(feature);

			System.out.println("FEATURE " + lista.get(i).getRedniBroj());
			System.out.println(lista.get(i).getAkcije());
			System.out.println("\n");
			System.out.println(lista.get(i).getDatumi());
			System.out.println("\n");
			System.out.println("\n");

		}
		
		objekat.add("features", features);

		return objekat.toString();
	}

	

}

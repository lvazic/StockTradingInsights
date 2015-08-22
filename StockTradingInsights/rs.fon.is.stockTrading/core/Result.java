package core;
import Jama.Matrix;

public class Result {
	private Matrix W;
	private Matrix H;
	private double cost;
	private double initialCost;
	private int numOfIterations;


	public Result(Matrix w, Matrix h, double cost, double initialCost,
			int numOfIterations) {
		W = w;
		H = h;
		this.cost = cost;
		this.initialCost = initialCost;
		this.numOfIterations = numOfIterations;
	}

	public Matrix getW() {
		return W;
	}

	public void setW(Matrix w) {
		W = w;
	}

	public Matrix getH() {
		return H;
	}

	public void setH(Matrix h) {
		H = h;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getInitialCost() {
		return initialCost;
	}

	public void setInitialCost(double initialCost) {
		this.initialCost = initialCost;
	}

	public int getNumOfIterations() {
		return numOfIterations;
	}

	public void setNumOfIterations(int numOfIterations) {
		this.numOfIterations = numOfIterations;
	}

}

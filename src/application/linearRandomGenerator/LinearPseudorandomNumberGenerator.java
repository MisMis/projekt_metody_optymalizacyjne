package application.linearRandomGenerator;

import java.util.List;
import application.IAlgorithm;
import application.Point;

public class LinearPseudorandomNumberGenerator implements IAlgorithm {
	private long a;
	private long b;
	private long m;
	private long x;
	
	public LinearPseudorandomNumberGenerator(long a, long b, long m,long seed) {
		this.a = a;
		this.b = b;
		this.m = m;
		this.x=seed;
	}

	public long nextInt() {
		x=((a*x)+b)%m;
		return x;
	}
	public double nextFrom0To1() {
		return (double)nextInt()/m;
	}
	@Override
	public List<Point> nextList() {
		return null;
	}

	@Override
	public double nextValue() {
		return nextFrom0To1();
	}

	@Override
	public int getAlgType() {
		return 1;
	}
}

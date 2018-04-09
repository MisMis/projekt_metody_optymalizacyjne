package application.linearRandomGenerator;

public class LinearPseudorandomNumberGenerator {
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
}

public class Polynomial {
    double [] coef;

    public Polynomial() {
	coef = new double[0];
    }

    public Polynomial(double [] input) {
	int len = input.length;
	coef = new double[len];
	int i = 0;
	while (i < len) {
	    coef[i] += input[i];
	    i++;
	}
    }

    public Polynomial add(Polynomial input) {
	int len1 = input.coef.length;
	int len2 = coef.length;
	double [] sol = new double[Math.max(len1, len2)];
	int i = 0;
	while (i < len2) {
	    sol[i] += coef[i];
	    i++;
	}
	i = 0;
	while (i < len1) {
	    sol[i] += input.coef[i];
	    i++;
	}
	i = 0;
	return new Polynomial(sol);
    }

    public double evaluate(double x) {
	double ans = 0;
	int i = 0;
	int len = coef.length;
	while (i < len) {
	    ans = ans + Math.pow(x, i) * coef[i];
	    i++;
	}
	return ans;
    }

    public boolean hasRoot(double x) {
	double ans = 0;
	int i = 0;
	int len = coef.length;
	while (i < len) {
	    ans = ans + Math.pow(x, i) * coef[i];
	    i++;
	}
	if (ans == 0.0)
	    return true;
	return false;
    }
}

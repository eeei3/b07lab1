import java.io.FileWriter;
import java.util.HashMap;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;

public class Polynomial {
    double [] nonzcoef;
    int [] expo;


    public Polynomial() {
	nonzcoef = new double[0];
	expo = new int[0];
    }

    public Polynomial(File fp) {
	try {
		BufferedReader input = new BufferedReader(new FileReader(fp));
		String tmp = input.readLine();
		tmp = tmp.replaceAll("-", "+-");
		String p = "[+]";
		String var = "[x]";
		String[] terms = tmp.split(p);
		int j = terms.length;
		for (String i: terms) {
			if (i.isEmpty())
				j--;

		}
		nonzcoef = new double[j];
		expo = new int[j];

		j = 0;

		for (String i : terms) {
			if (i.contains("x")) {
				if (i.split(var).length == 2) {
					if (i.split(var)[0].equals("-"))
						nonzcoef[j] = -1;
					else if (!i.split(var)[0].isEmpty())
						nonzcoef[j] = Double.parseDouble(i.split(var)[0]);
					else
						nonzcoef[j] = 1;
					expo[j] = Integer.parseInt(i.split(var)[1]);
				}
				else {
					if (i.startsWith(i.split(var)[0])) {
						if (Double.parseDouble(i.split(var)[0]) == 0)
							j--;
						else {
							if (i.split(var)[0].equals("-"))
								nonzcoef[j] = -1;
							else
								nonzcoef[j] = Double.parseDouble(i.split(var)[0]);
							expo[j] = 1;
							}
						}
					else {
						nonzcoef[j] = 1;
						expo[j] = Integer.parseInt(i.split(var)[0]);
					}
				}
			}
			else if (!(i.isEmpty())) {
				if (Double.parseDouble(i) == 0)
					j--;
				else {
					nonzcoef[j] = Double.parseDouble(i);
					expo[j] = 0;
				}
			}
			else {
				j--;
			}
			j++;
		}
	}
	catch (IOException e) {
		nonzcoef = new double[0];
		expo = new int[0];
		}
    }

    public Polynomial(HashMap<Integer, Double> input) {
		if (input != null) {
			nonzcoef = new double[input.size()];
			expo = new int[input.size()];
			int j = 0;
			for (int i : input.keySet()) {
				nonzcoef[j] = input.get(i);
				expo[j] = i;
				j++;
			}
		}
		else {
			nonzcoef = new double[0];
			expo = new int[0];
		}
    }

    public Polynomial(double [] input) {
		if (input != null) {
			int i = 0;
			int newlen = 0;
			while (i < input.length) {
				if (input[i] != 0) {
					newlen++;
				}
				i++;
			}
			nonzcoef = new double[newlen];
			expo = new int[newlen];
			int j = 0;
			i = 0;
			while (i < input.length) {
				if (input[i] != 0) {
					nonzcoef[j] += input[i];
					expo[j] += i;
					j++;
				}
				i++;
			}
		}
		else {
			nonzcoef = new double[0];
			expo = new int[0];
		}
    }

    private HashMap<Integer, Double> polytohash(Polynomial input) {
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		int len = input.expo.length;
		int i = 0;
		while (i < len) {
	    	res.put(input.expo[i], input.nonzcoef[i]);
		}
		return res;
    }

    public Polynomial add(Polynomial input) {
		if (input == null)
			return null;
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		int len1 = input.expo.length;
		int len2 = expo.length;
		int i = 0;
		while (i < len1) {
	    	if (res.get(input.expo[i]) == null)
				res.put(input.expo[i], input.nonzcoef[i]);
	    	else {
				res.replace(input.expo[i], res.get(i) + input.nonzcoef[i]);
	    	}
	    	i++;
		}
		i = 0;
		while (i < len2) {
	    	if (res.get(this.expo[i]) == null)
				res.put(this.expo[i], this.nonzcoef[i]);
	    	else {
				double a = res.get(i) + this.nonzcoef[i];
				if (a != 0.0)
					res.replace(this.expo[i], a);
				else
					res.remove(this.expo[i]);
			}
	    	i++;
		}
		if (res.size() == 0)
			res.put(0, 0.0);
		return new Polynomial(res);
    }

    public double evaluate(double x) {
		double ans = 0;
		int i = 0;
		int len = expo.length;
		while (i < len) {
			if (expo[i] == 1) {
				ans = ans + x * nonzcoef[i];
			}
			else {
				ans = ans + Math.pow(x, expo[i]) * nonzcoef[i];
			}
	    	i++;
		}
		return ans;
    }

    public Polynomial multiply(Polynomial input) {
		if (input == null)
			return null;
		HashMap<Integer, Double> res = new HashMap<Integer, Double>();
		int i = 0;
		while (i < expo.length) {
			int j = 0;
			while (j < input.expo.length){
				if (res.containsKey(input.expo[j] + expo[i])) {
					double a = res.get(input.expo[j] + expo[i]) + nonzcoef[i] * input.nonzcoef[j];
					if (a != 0.0)
						res.put(input.expo[j] + expo[i], a);
					else
						res.remove(input.expo[j] + expo[i]);
				}
				else
					res.put(input.expo[j] + expo[i], nonzcoef[i] * input.nonzcoef[j]);
				j++;
			}
			i++;
		}

		return new Polynomial(res);
    }

    public void saveToFile(String file) {
		try {
			File nfile = new File(file);
			if (!nfile.createNewFile())
				;
			FileWriter nwriter = new FileWriter(file, false);
			int i = 0;
			while (i < expo.length) {
				if (i != 0) {
					if (nonzcoef[i] > 0) {
						nwriter.write("+");
					}
				}
				nwriter.write(String.valueOf(nonzcoef[i]));
				if (expo[i] != 0) {
					nwriter.write("x");
					nwriter.write(String.valueOf(expo[i]));
				}
				i++;
			}
			if (i == 0) {
				nwriter.write("0");
			}
			nwriter.close();
		}
		catch (IOException e) {
			return;
		}
    }

    public boolean hasRoot(double x) {
		double ans = 0;
		int i = 0;
		int len = nonzcoef.length;
		while (i < len) {
	    	ans = ans + Math.pow(x, expo[i]) * nonzcoef[i];
	    	i++;
		}
		if (ans == 0.0)
	    	return true;
		return false;
    }
}

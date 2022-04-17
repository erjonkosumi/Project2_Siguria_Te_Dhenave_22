public class LetterFrequencyUtils {

    public static int ALPHABET_COUNT = 26;

    
    public static double ENG_IC_LOWER_BOUND = 0.061;
    public static double ENG_IC_UPPER_BOUND = 0.071;
    public static double ENG_IC = 0.0686;


    public static double A = 8.167;
    public static double B = 1.492;
    public static double C = 2.782;
    public static double D = 4.253;
    public static double E = 12.702;
    public static double F = 2.228;
    public static double G = 2.015;
    public static double H = 6.094;
    public static double I = 6.966;
    public static double J = 0.153;
    public static double K = 0.772;
    public static double L = 4.025;
    public static double M = 2.406;
    public static double N = 6.749;
    public static double O = 7.507;
    public static double P = 1.929;
    public static double Q = 0.095;
    public static double R = 5.987;
    public static double S = 6.327;
    public static double T = 9.056;
    public static double U = 2.758;
    public static double V = 0.978;
    public static double W = 2.360;
    public static double X = 0.150;
    public static double Y = 1.974;
    public static double Z = 0.074;

    public static double[] FREQUENCIES = {
            A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
    };

    public static int[] countCharacters(String message) {
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();
        int[] counts = new int[ALPHABET_COUNT];
        for (char c : message.toCharArray())
            counts[c - 'A']++;
        return counts;
    }

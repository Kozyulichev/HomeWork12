import java.util.Arrays;

public class OhMyGod {
    public static final int SIZE = 10_000_000;
    public static final int HALF = SIZE / 2;

    public static void main(String[] args) {

        runFirstTheard();
        runSecondTheard();
    }

    public static float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
        return arr;
    }

    public static void runFirstTheard() {
        float[] first = new float[SIZE];

        Arrays.fill(first, 1);

        long a = System.currentTimeMillis();

        calculate(first);

        System.out.println("Метод выполнился за: " + (System.currentTimeMillis() - a) + " мл.сек");
    }

    public static void runSecondTheard() {

        float[] second = new float[SIZE];

        float[] second1 = new float[HALF];
        float[] second2 = new float[HALF];

        Arrays.fill(second, 1);

        long a = System.currentTimeMillis();

        System.arraycopy(second, 0, second1, 0, HALF);
        System.arraycopy(second, HALF, second2, 0, HALF);

        Thread t1 = new Thread(() -> {
            calculate(second1);
        });


        Thread t2 = new Thread(() -> {
            calculate(second2);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(second1, 0, second, 0, HALF);
        System.arraycopy(second1, 0, second, HALF, HALF);

        System.out.println("Второй метод выполнился за: " + (System.currentTimeMillis() - a) + " мл.сек");

    }


}

package MyPackage;

public class Task {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/2;
    double[] arr = new double[SIZE];
    double[] arr1 = new double[HALF];
    double[] arr2 = new double[HALF];

    public void creatMass(){

        for(int i = 0; i< arr.length; i++){
                arr[i] = 1;
            }
        long start = System.currentTimeMillis();
            for(int i = 0; i< SIZE; i++){
                arr[i] =(float)(arr[i]) * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5)* Math.cos(0.4f+i/2);
            }

        long end = System.currentTimeMillis();
        System.out.println("Время выполнения = "+(double)(end - start)/1000);
    }

    public void creatMass2(){

        long start = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, HALF );
        System.arraycopy(arr, HALF, arr2, 0, HALF );

        Thread potok = new Thread(new Runnable() {
            public void run() {
            for(int i = 0; i< arr1.length; i++){
              arr1[i] =(float)(arr[i]) * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5)* Math.cos(0.4f+i/2);
            }
            }

        });

        Thread potok2 = new Thread(new Runnable() {
            public void run() {
            for(int i = 0; i< arr2.length; i++){
               arr2[i] =(float)(arr[i]) * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5)* Math.cos(0.4f+i/2);
            }
                System.arraycopy(arr1, 0, arr, 0, HALF );
                System.arraycopy(arr2, 0, arr, HALF, HALF );
            }
        });
        potok.start();
        potok2.start();

        long end = System.currentTimeMillis();
        System.out.println("Время выполнения = "+(double)(end - start)/1000);
    }

}

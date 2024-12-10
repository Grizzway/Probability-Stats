package PlotterProgram;

import java.util.function.Function;

public class Tests {

    public static void main(String[] args) {
        Plotter plotter = new Plotter();

        //First Configuration
        plotter.generateData(0, 20, 0.1, "data1.csv", 2, 1, 0);
        plotter.saltData("data1.csv", "salted_data1.csv", 0.5);
        plotter.smoothData("salted_data1.csv", "smoothed_data1.csv", 3);

        //Second Configuration
        plotter.generateData(-10, 20, 0.25, "data2.csv", 2, 1, 0);
        plotter.saltData("data2.csv", "salted_data2.csv", 1);
        plotter.smoothData("salted_data2.csv", "smoothed_data2.csv", 3);

        //Third Configuration
        plotter.generateData(0, 100, 0.1, "data3.csv", 3, 2, 1);
        plotter.saltData("data3.csv", "salted_data3.csv", 10);
        plotter.smoothData("salted_data3.csv", "smoothed_data3.csv", 3);

        //Over-smoothing Test
        plotter.generateData(0, 200, 0.5, "oversmooth.csv", 3, 2, 1);
        plotter.saltData("oversmooth.csv", "salted_oversmooth.csv", 5);
        plotter.smoothData("salted_oversmooth.csv", "1_smoothed_oversmooth.csv", 6);
        plotter.smoothData("1_smoothed_oversmooth.csv", "2_smoothed_oversmooth.csv", 5);
        plotter.smoothData("2_smoothed_oversmooth.csv", "3_smoothed_oversmooth.csv", 4);
        plotter.smoothData("3_smoothed_oversmooth.csv", "4_smoothed_oversmooth.csv", 3);

        //Second Over-smoothing Test
        plotter.smoothDataMultipleTimes("oversmooth.csv", "final_smoothed_output.csv", 4, 100);

        //Plotting Graphs Using JFreeChart
        plotter.plotData("data1.csv", "Original Data");
        plotter.plotData("salted_data1.csv", "Salted Data");
        plotter.plotData("smoothed_data1.csv", "Smoothed Data");
    }
}

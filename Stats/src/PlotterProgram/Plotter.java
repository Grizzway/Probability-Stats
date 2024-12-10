package PlotterProgram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Plotter {

    /**
     * Generates data based on a given mathematical function, stores it, and exports it as a CSV file.
     *
     * @param start     the starting value of the range.
     * @param end       the ending value of the range.
     * @param increment the step size for generating data points.
     * @param filePath  the file path to save the generated CSV file.
     */
    public void generateData(double start, double end, double increment, String filePath, double a, double b, double c) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (double x = start; x <= end; x += increment) {
            xValues.add(x);
            yValues.add(squareRootFunction(x, a, b, c));
        }

        writeCsv(filePath, xValues, yValues);
        System.out.println("Saved data to " + filePath);
    }

    /**
     * Helper function to apply the square root formula with parameters a, b, and c.
     * The input x is converted to its absolute value before applying the formula.
     *
     * @param x  the input value for the function.
     * @param a  the scaling factor.
     * @param b  the shift inside the square root.
     * @param c  the vertical shift.
     * @return   the result of the square root function after taking the absolute value of x.
     */
    public double squareRootFunction(double x, double a, double b, double c) {
        return a * Math.sqrt(Math.abs(x) + b) + c;
    }

    /**
     * Adds random noise to the y-values of data from an input CSV file and exports the modified data to a new CSV file.
     *
     * @param inputFilePath  the file path of the input CSV file.
     * @param outputFilePath the file path to save the salted data.
     * @param range          the range for adding random noise (e.g., +/- range).
     */
    public void saltData(String inputFilePath, String outputFilePath, double range) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                xValues.add(Double.parseDouble(values[0]));
                double y = Double.parseDouble(values[1]);
                yValues.add(y + (Math.random() * range * 2 - range));
            }
            writeCsv(outputFilePath, xValues, yValues);
            System.out.println("Salted data saved to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("saltData(): Error reading or writing files: " + e.getMessage());
        }
    }

    /**
     * Smooths the y-values of data from an input CSV file using a moving average and exports the smoothed data.
     *
     * @param inputFilePath  the file path of the input CSV file.
     * @param outputFilePath the file path to save the smoothed data.
     * @param windowSize     the number of values to consider on each side for the moving average.
     */
    public void smoothData(String inputFilePath, String outputFilePath, int windowSize) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                xValues.add(Double.parseDouble(values[0]));
                yValues.add(Double.parseDouble(values[1]));
            }

            List<Double> smoothedYValues = smoothYValues(yValues, windowSize);

            writeCsv(outputFilePath, xValues, smoothedYValues);
            System.out.println("Smoothed data saved to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("smoothData(): Error reading or writing files: " + e.getMessage());
        }
    }

    /**
     * Applies a moving average smoothing to a list of y-values.
     *
     * @param yValues    the original list of y-values.
     * @param windowSize the number of values to consider on each side for smoothing.
     * @return a new list containing the smoothed y-values.
     */
    private List<Double> smoothYValues(List<Double> yValues, int windowSize) {
        List<Double> smoothedYValues = new ArrayList<>();

        for (int i = 0; i < yValues.size(); i++) {
            double sum = 0;
            int count = 0;

            for (int j = -windowSize; j <= windowSize; j++) {
                int index = i + j;
                if (index >= 0 && index < yValues.size()) {
                    sum += yValues.get(index);
                    count++;
                }
            }

            smoothedYValues.add(sum / count);
        }

        return smoothedYValues;
    }

    /**
     * Smooths the data multiple times by calling the original smoothData method iteratively.
     *
     * @param inputFilePath  the file path of the input CSV file.
     * @param outputFilePath the file path to save the final smoothed data.
     * @param windowSize     the number of values to consider on each side for the moving average.
     * @param iterations     the number of times to apply smoothing.
     */
    public void smoothDataMultipleTimes(String inputFilePath, String outputFilePath, int windowSize, int iterations) {
        String tempInputFile = inputFilePath;
        String tempOutputFile = "temp_output.csv";

        try {
            for (int i = 0; i < iterations; i++) {
                if (i == iterations - 1) {
                    smoothData(tempInputFile, outputFilePath, windowSize);
                } else {
                    smoothData(tempInputFile, tempOutputFile, windowSize);
                    tempInputFile = tempOutputFile;
                }
            }
            System.out.println("Final smoothed data saved to " + outputFilePath);
        } catch (Exception e) {
            System.err.println("smoothDataMultipleTimes(): Error during smoothing: " + e.getMessage());
        } finally {
            new File(tempOutputFile).delete();
        }
    }

    public void plotData(String inputFilePath, String PlotTitle) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                xValues.add(Double.parseDouble(values[0]));
                yValues.add(Double.parseDouble(values[1]));
            }

            XYSeries series = new XYSeries("Data Points");
            for (int i = 0; i < xValues.size(); i++) {
                series.add(xValues.get(i), yValues.get(i));
            }

            XYSeriesCollection dataset = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createScatterPlot(
                    PlotTitle,
                    "X-Axis",
                    "Y-Axis",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
            xAxis.setAutoRangeIncludesZero(false);

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Data Plot");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ChartPanel(chart));
                frame.pack();
                frame.setVisible(true);
            });

        } catch (IOException e) {
            System.err.println("plotData(): Error reading file: " + e.getMessage());
        }
    }

    /**
     * Writes x and y values to a CSV file with headers, formatting values to 3 decimal places.
     *
     * @param filePath the file path to save the CSV file.
     * @param xValues  the list of x values.
     * @param yValues  the list of y values.
     */
    private void writeCsv(String filePath, List<Double> xValues, List<Double> yValues) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("x,y");
            for (int i = 0; i < xValues.size(); i++) {
                String formattedX = String.format("%.3f", xValues.get(i));
                String formattedY = String.format("%.3f", yValues.get(i));
                pw.println(formattedX + "," + formattedY);
            }
        } catch (IOException e) {
            System.err.println("writeCsv(): Error writing to file: " + e.getMessage());
        }
    }

}

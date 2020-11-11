package calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Statistics {

    private static List<BigDecimal> dataSet = new ArrayList<BigDecimal>();

    public static void main(String[] args) {
        Statistics thisClass = new Statistics();

        dataSet = thisClass.generateSample(50, 100);
        System.out.println("dataSet             = " + dataSet);
        System.out.println("sort(dataSet, asc)  = " + thisClass.sortSample(dataSet, "asc"));
        System.out.println("Sum                 = " + thisClass.calcSum(dataSet));
        System.out.println("Mean                = " + thisClass.calcMean(dataSet));
        System.out.println("Median              = " + thisClass.calcMedian(dataSet));
        System.out.println("Mode                = " + thisClass.calcMode(dataSet));
        System.out.println("Min                 = " + thisClass.calcMin(dataSet));
        System.out.println("Max                 = " + thisClass.calcMax(dataSet));
        System.out.println("Range                 = " + thisClass.calcRange(dataSet));
        
    }

    public List<BigDecimal> generateSample(int sampleSize, int multiplier) {
        List<BigDecimal> sample = new ArrayList<BigDecimal>();
        for (int i = 0; i < sampleSize; i++) {
            Double aRandomDouble = Math.random();
            aRandomDouble = aRandomDouble * multiplier;
            BigDecimal aBDecimal = BigDecimal.valueOf(aRandomDouble).setScale(4, RoundingMode.CEILING);
            sample.add(aBDecimal);
        }
        return sample;
    }

    public List<BigDecimal> sortSample(List<BigDecimal> sample, String sortOrder) {
        int sortOrderInt = 0;
        switch (sortOrder) {
            case "asc":
                sortOrderInt = 1;
                break;
            case "1":
                sortOrderInt = 1;
                break;
            case "des":
                sortOrderInt = -1;
                break;
            case "-1":
                sortOrderInt = -1;
                break;
            default:
                sortOrderInt = 1;
        }
        boolean notSorted = true;
        while (notSorted) {
            notSorted = false;
            for (int i = 0; i < sample.size() - 1; i++) {
                BigDecimal x = sample.get(i);
                BigDecimal y = sample.get(i + 1);
                if (x.compareTo(y) == sortOrderInt) {
                    // Swap indexes
                    sample.set(i, y);
                    sample.set(i + 1, x);
                    // Check again
                    notSorted = true;
                }
            }
        }
        return sample;

    }

    public BigDecimal calcSum(List<BigDecimal> sample) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < sample.size(); i++) {
            total = total.add(sample.get(i));
        }
        return total;
    }

    public BigDecimal calcMean(List<BigDecimal> sample) {
        BigDecimal total = calcSum(sample);
        return total.divide(new BigDecimal(sample.size()));
    }

    
    
    
    
    
    
    public BigDecimal calcMedian(List<BigDecimal> sample) {
        // Sort asc
        // Get middle number, or half of the two middle numbers
        return null;
    }

    public BigDecimal calcMode(List<BigDecimal> sample) {
        // Count appearance of each value
        // Return most common value
        return null;
    }

    public BigDecimal calcMin(List<BigDecimal> sample) {
        return null;
    }

    public BigDecimal calcMax(List<BigDecimal> sample) {
        return null;
    }
    
    public BigDecimal calcRange(List<BigDecimal> sample) {
        return null;
    }
    
    
    
    
    
    
    
    public BigDecimal calcStd(BigDecimal value, BigDecimal mean, Integer stdev) {
        BigDecimal temp = value.subtract(mean);
        temp = temp.divide(BigDecimal.valueOf(stdev), RoundingMode.CEILING);
        System.out.println(value+" is "+temp+" SD above/below the mean");
        return temp;
    }
    
    
    
}

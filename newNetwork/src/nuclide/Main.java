package nuclide;

import java.io.*;
import java.util.Random;

public class Main
{
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]) > 3 ? Integer.parseInt(args[0]) : 3;
        int y = Integer.parseInt(args[1]);
        int yInput = Integer.parseInt(args[2]);
        int yOutput = Integer.parseInt(args[3]);
        int learn = Integer.parseInt(args[4]);
        float learningRate = Float.parseFloat(args[5]);
        float dataRange = Float.parseFloat(args[6]);
        String testName = args[7];
        String expectationsName = args[8];
        Random random = new Random();
        Neuron[][] map = new Neuron[x][];
        map[0] = new Neuron[yInput];
        map[x - 1] = new Neuron[yOutput];
        for (int i=0;i<map[0].length;i++)
            map[0][i] = new Neuron();
        for (int i = 1; i < x; i++)
        {
            if (i != x - 1)
                map[i]=new Neuron[y];
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Neuron();
                map[i][j].learningRate = learningRate;
                map[i][j].inputs = map[i - 1];
                map[i][j].weights = new float[map[i - 1].length];
                for (int n = 0; n < map[i][j].weights.length; n++)
                    map[i][j].weights[n] = random.nextFloat();
            }
        }
        try
        {
            BufferedReader test = new BufferedReader(new FileReader(testName));
            BufferedReader expectations = new BufferedReader(new FileReader(expectationsName));
            String line;
            String[] vectorInput;
            int output;
            float[] numberVectorInput;
            for (int counter = 0; counter <= learn; counter++)
            {
                if ((line = test.readLine()) == null)
                {
                    test = new BufferedReader(new FileReader(testName));
                    expectations = new BufferedReader(new FileReader(expectationsName));
                    line = test.readLine();
                }
                vectorInput = line.split(",");
                for(int i=0;i<map[0].length;i++)
                    map[0][i].output = Float.parseFloat(vectorInput[i]) / (dataRange);
                for(int i=1;i<map.length;i++)
                    for (int j=0;j<map[i].length;j++)
                            map[i][j].performIteration();
                line = expectations.readLine();
                output = Integer.parseInt(line);
                for(int i=map.length-1;i>0;i--)
                    for(int j=0;j<map[i].length;j++)
                    {
                        if (i == map.length - 1) {
                            map[i][j].error = -((output == j ? 1 : 0) - map[i][j].output);
                         //   System.out.println(map[i][j].output);
                        }
                        map[i][j].performLearning();
                    }
                System.out.println("Learning progress:"+(counter/(float)learn)*100+"%");
            }
            System.out.println("Writing...");
            test = new BufferedReader(new FileReader(testName));
            expectations = new BufferedReader(new FileReader(expectationsName));
            PrintWriter testData = new PrintWriter("trainPredictions", "UTF-8");
            PrintWriter realData = new PrintWriter("actualTestPredictions", "UTF-8");
            int max=0;
            while ((line=test.readLine())!=null)
            {
                vectorInput = line.split(",");
                for(int i=0;i<map.length;i++)
                    for(int j = 0; j<map[i].length;j++)
                        if(i==0)
                            map[i][j].output = Float.parseFloat(vectorInput[j])/(dataRange);
                        else
                            map[i][j].performIteration();
                line = expectations.readLine();
                output = Integer.parseInt(line);
                for (int i = 0; i < map[map.length-1].length; i++)
                    if(map[map.length-1][i].output>map[map.length-1][max].output)
                        max=i;
                realData.println(output);
                testData.println(max);
            }
            testData.close();
            realData.close();
            System.out.println("Done!");
            test = new BufferedReader(new FileReader(testName));
            expectations = new BufferedReader(new FileReader(expectationsName));
            while((line=test.readLine())!=null)
            {
                vectorInput = line.split(",");
                for(int i=0;i<map.length;i++)
                    for(int j = 0; j<map[i].length;j++)
                        if(i==0)
                            map[i][j].output = Float.parseFloat(vectorInput[j])/(dataRange);
                        else
                            map[i][j].performIteration();
                line = expectations.readLine();
                output = Integer.parseInt(line);
                for (int i = 0; i < map[map.length-1].length; i++)
                    if(map[map.length-1][i].output>map[map.length-1][max].output)
                        max=i;
                System.in.read();
                System.out.println("Real:"+output);
                System.out.println("Network:"+max);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}

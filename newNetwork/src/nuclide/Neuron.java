package nuclide;
public class Neuron
{
    public float output=1;
    public Neuron[] inputs;
    public float[] weights;
    private float bias=0;
    public float error=0;
    public float learningRate=1;
    public void performIteration()
    {
        float sum=bias;
        for(int i=0;i<inputs.length;i++) {
            sum += inputs[i].output * weights[i];
        }
        output = (float)(1/(1+Math.exp(-sum)));

    }
    public void performLearning()
    {
        float sigma = error * (output*(1-output));
        for(int i=0;i<inputs.length;i++)
        {
            float old = weights[i];
            weights[i] = weights[i] - inputs[i].output * sigma * learningRate;
            if(inputs!=null) inputs[i].error+=sigma*weights[i];
//            if(old-weights[i]!=0){
//                System.out.println(inputs[i].output);
//            System.out.println(sigma);  System.out.println(old-weights[i]);
//            }
        }
        bias = bias - learningRate * sigma;
        error=0;
    }

}

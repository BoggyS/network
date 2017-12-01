#!/bin/bash
#<x> <y> <numberOfInputs> <numberOfOutputs> <pathToTest> <pathToExpectations> <numberOfLearningIterations> <learningRate> <visualisation(monitor required)>
java -jar newNetwork.jar 3 784 784 10 10000 0.5 mnist_test_vectors.csv mnist_test_labels.csv

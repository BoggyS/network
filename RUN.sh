#!/bin/bash
#<x> <y> <numberOfInputs> <numberOfOutputs> <pathToTest> <pathToExpectations> <numberOfLearningIterations> <learningRate> <visualisation(monitor required)>
java -jar network.jar 3 784 784 10 mnist_test_vectors.csv mnist_test_labels.csv 10000 0.5 true

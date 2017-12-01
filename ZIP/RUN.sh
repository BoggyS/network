#!/bin/bash
#<x> <y> <numberOfInputs> <numberOfOutputs> <pathToTest> <pathToExpectations> <numberOfLearningIterations> <learningRate> <visualisation(monitor required)>
java -jar network.jar 4 5 10 10 100000 0.5 1 mnist_test_vectors.csv mnist_test_labels.csv

# Guessing Whether The Movie Will Be Successful Using Decision Tree

## 1. Project description
This program is aimed to predict the success of a movie in advance by utilizing decision tree. Name of the country, genre, rating, the existence of big actors, are used in the prediction which provides the likelihood of the moving being successful upon release. Once the user enters all the required information, the output will provide its prediction by displaying either TRUE, or FALSE, which represent successful and unsuccessful.

## 2. Description of training dataset
Dataset used in this project was downloaded from Kaggle.
https://www.kaggle.com/danielgrijalvas/movies#movies.csv

In order to clean up the data file, several columns are removed such as year, director, time of release, gross, movies’ name, runtime, votes and writer. After cleaning up the data file, the remaining features are country name, movie genre, movie rating, existence of big star and successfulness.

 1) Modified the “Score” columns. For movies with scores that are higher than 7, the movie is considered as successful.
<p align="center">
<img width="313" alt="screen shot 2019-03-07 at 2 15 19 pm" src="https://user-images.githubusercontent.com/18043807/53993184-7cf9a480-40e3-11e9-958c-a97b27f81cbc.png">
</p>
 2) Imported CSV file into the database and used SQL to check the amount of unique country variables in the training dataset. Then merged the countries with only has a few records into one group called Other.
<p align="center">
<img width="209" alt="screen shot 2019-03-07 at 2 21 41 pm" src="https://user-images.githubusercontent.com/18043807/53993479-5be58380-40e4-11e9-8110-fded697ac010.png">
</p>
 3) Modified the “Budget” columns. When the budget of the movie is over 15 million dollars, the movie is considered as using a big movie star in its production.
<p align="center">
<img width="323" alt="screen shot 2019-03-07 at 2 23 19 pm" src="https://user-images.githubusercontent.com/18043807/53993722-08276a00-40e5-11e9-9248-c8d4705e5275.png">
</p>
 4) For the rating column, it rates a film's suitability for certain audiences based on its content.Reference:https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system
<p align="center">
<img width="291" alt="screen shot 2019-03-07 at 2 27 18 pm" src="https://user-images.githubusercontent.com/18043807/53993903-8257ee80-40e5-11e9-94f1-c2541baa3777.png">
</p>
 5) Finally, This is the result after selecting and modifying the features.(6820 rows)
<p align="center">
<img width="289" alt="screen shot 2019-03-07 at 2 33 02 pm" src="https://user-images.githubusercontent.com/18043807/53994084-132eca00-40e6-11e9-8e1e-ef916c625b10.png">
</p>

## 3. Description of algorithm
Decision tree is used to calculate the successfulness of a movie. Country, genre, rating, big star, and successfulness of past movies are used to build a classifier. The decision tree will check each attribute from the training data, and use these attributes to split data into different subsets. For instance, there are many different types of genres. If one genre is always successful in the market, then this specific genre is considered as an important factor in the movie.
In my program, I will use a single node to split the data. Firstly, I should find a particular node(root node) in the tree and I have a set of training dataset that falls into this root node. Secondly, I created a decision node for the attribute which has some alternatives such as country, genre, rating, big star and success, and each value of that attribute we are going to create a new child node. Then sorting the dataset into the child nodes. If the user enters “USA” as a country name, I only need to put the USA training example into that subset. So I am going to iterate over all children (all subsets) and look at whether they are prettier or not. If the subset is pure(all positive or all negative), we will stop. Otherwise, we will call the algorithm again on the child node and it will have a subset of the training dataset(not all of them, only the ones that have this particular attribute value). Meanwhile, I have to consider which attribute to split on. I am going to use the measure which is entropy (H(S) = - p0 log2 p0 - p1 log2 p1 - ...). The entropy is a measure of the uncertainty associated with a random variable. Then use information gain to get the certainty of an attribute. It takes each attribute in the dataset to compute the information gain, compute how many bits gained by doing the split. Picking the attribute with highest gain that will provide the most clarity towards the final prediction.

## 4. Design and implementation
• Environment – programming language, any library/tool, system information
Programming language:
I use Java as my programming language.

Library: I only need to use basic Java Library and a package called DecisionTree.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

--> use those library to read the data from csv file.

import java.util.Scanner;

--> allow users to enter the data they want.

import java.util.ArrayList;
import java.util.List;

--> put the data from into a list.

import java.util.TreeSet;

--> provides total ordering on its elements which are ordered by a Comparator.

import java.util.Comparator;

--> it is used to control the order of certain data structures. I use it to compare each
nodes.

For convenience, I only use : 
import java.io.*;
import java.util.*;

<p align="center">
<img width="536" alt="screen shot 2019-03-07 at 2 37 48 pm" src="https://user-images.githubusercontent.com/18043807/53994392-12e2fe80-40e7-11e9-9c7e-1f7d62513eb9.png">
<img width="480" alt="screen shot 2019-03-07 at 2 38 03 pm" src="https://user-images.githubusercontent.com/18043807/53994508-59d0f400-40e7-11e9-81b6-299d00deb836.png">
<img width="488" alt="screen shot 2019-03-07 at 2 38 24 pm" src="https://user-images.githubusercontent.com/18043807/53994532-6a816a00-40e7-11e9-87fb-bd465e17c575.png">
</p>

## Output:
<p align="center">
<img width="602" alt="screen shot 2019-03-07 at 2 46 25 pm" src="https://user-images.githubusercontent.com/18043807/53994700-d4017880-40e7-11e9-9b48-9b53067a91a5.png">
</p>

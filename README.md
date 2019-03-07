# Guessing Whether The Movie Will Be Successful Using Decision Tree

## 1. Project description
This program is aimed to predict the success of a movie in advance by utilizing decision tree.
Name of the country, genre, rating, the existence of big actors, are used in the prediction
which provides the likelihood of the moving being successful upon release. Once the user
enters all the required information, the output will provide its prediction by displaying
either TRUE, or FALSE, which represent successful and unsuccessful.

## 2. Description of training dataset
Dataset used in this project was downloaded from Kaggle.
https://www.kaggle.com/danielgrijalvas/movies#movies.csv
In order to clean up the data file, several columns are removed such as year, director, time
of release, gross, movies’ name, runtime, votes and writer. After cleaning up the data file,
the remaining features are country name, movie genre, movie rating, existence of big star
and successfulness.

1) Modified the “Score” columns. For movies with scores that are higher than 7, the movie
is considered as successful.


# Guessing Whether The Movie Will Be Successful Using Decision Tree

## 1. Project description
This program is aimed to predict the success of a movie in advance by utilizing decision tree. Name of the country, genre, rating, the existence of big actors, are used in the prediction which provides the likelihood of the moving being successful upon release. Once the user enters all the required information, the output will provide its prediction by displaying either TRUE, or FALSE, which represent successful and unsuccessful.

## 2. Description of training dataset
Dataset used in this project was downloaded from Kaggle.
https://www.kaggle.com/danielgrijalvas/movies#movies.csv
In order to clean up the data file, several columns are removed such as year, director, time of release, gross, movies’ name, runtime, votes and writer. After cleaning up the data file, the remaining features are country name, movie genre, movie rating, existence of big star and successfulness.

1) Modified the “Score” columns. For movies with scores that are higher than 7, the movie is considered as successful.

<img width="313" alt="screen shot 2019-03-07 at 2 15 19 pm" src="https://user-images.githubusercontent.com/18043807/53993184-7cf9a480-40e3-11e9-958c-a97b27f81cbc.png">

2) Imported CSV file into the database and used SQL to check the amount of unique country variables in the training dataset. Then merged the countries with only has a few records into one group called Other.

<img width="209" alt="screen shot 2019-03-07 at 2 21 41 pm" src="https://user-images.githubusercontent.com/18043807/53993479-5be58380-40e4-11e9-8110-fded697ac010.png">

3) Modified the “Budget” columns. When the budget of the movie is over 15 million dollars, the movie is considered as using a big movie star in its production.

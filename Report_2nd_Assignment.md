# Second Assignment Report

## Introduction
The goal of the assignment is to get information from a weather API and work with JSON data to show the current weather of a city. To do this, I used Gradle as the package manager and git for version control.

## Design and Implementation
I used org.json library to be able to get any specific information from the data.
First, the city name is passed to the getWeatherData function and it returns a string including all the data of the given city. To get the specific information we want, the string is passed to other functions.
In the getWindDirection function, I converted short-form 16-point directions to original long ones. 

## Testing and Evaluation
I checked the program results with the real weather of the city to make sure it's working properly.

## Conclusion
I learned how to work with JSON objects and a little about APIs.
# Foodmora - Recipe Manager
A terminal application:
# Introduction : 
Foodmora is a small Swedish startup company with roots in the Swedish town of Mora. As they failed to get traction and outcompete leksandknäcke with their
mora-knäcke they have now pivot into the food-tech market. Essentially, they want to create an app that helps you generate a schedule for what to eat each day. They
are in the process of designing an app but need a prototype. They have settled for using a terminal app.

## Mandatory requirements:
1. Create a recipe
The dietician can create new recipes and add them to the common recipe pool. Each recipe must contain a name, a list of ingredients and a set of steps to complete the
recipe.

2. Generate recipes for a week
A user can automatically generate recipes for a week. This week-object is then stored. A user must be able to retrieve past generated recipe-weeks.

3. View recipe
A user must be able to view recipes from their generated recipe-week. This should display both the ingredients and the steps.
A dietician must be able to view the recipes in the common recipe pool. A user wants to view the recipes for the current week and the recipe for today.

4. Edit/Update a recipe
A dietician must be able to edit a recipe. There must be an option for deleting ingredients and steps. There must also be an option for adding new ingredients and
steps to a recipe.

5. Changing between the user and dietician
There must be a way to change between the user and the dietician. When switching it is important that changes made by either the user or the dietician are preserved
during runtime. Generating a new recipe-week, switching to dietician, updating one of the recipes, switching back to the user, then the generated recipe-week should
still be viewable.

## UML Diagrams and User Stories
The src folder contains the code, UML diagram and the User Stories.

## How to run
* Create a jar file https://www.youtube.com/watch?v=_XQjs1xGtaU.
* Use cmd (command line) to run by writing the following 
```bash
java -jar RecipeManager.jar
``` 

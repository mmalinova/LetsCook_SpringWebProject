# LetsCook (SpringWebProject)

Let's Cook is a web application for cook recipes. The application is construct using Spring and MySQL Database.

## Database

The following data is stored in the database:
* Users
* User roles
* Recipes
* Images
* Categories
* Comments

## Access in the application

There are two types of access:

* User - has its own profile in the system, which can edit. Can send an inquiry to the administrator, give feedback about the site, contact him through his social networks. Each user can also add recipes to be useful to other users or leave comments.
* Administrator - has all the roles of the users and in the future there will be added more.

Authorized access is required for the following sections:
* Add a recipe;
* Write a comment;
* Exit.

## Technical characteristics

For the program implementation of the web application, the following is used:

* Programming language – Java;
* Scripting language – HTML, PHP;
* Structured language – HQL, SQL;
* Development environment – IntelliJ IDEA;
* Operating system – Windows 11;
* Architecture – Gradle;
* Databases – MySQL;
* ORM – Hibernate.

## Functionalities

The application has the following functionalities, through which the user can easily manage his activity:
* Search for cooking recipes by name;
* Search for cooking recipes by category;

Here the user can specify only what he needs: breakfast, lunch, dinner or dessert.

* Search for cooking recipes by subcategory;

Here the user can specify the subcategory such as: main meal, starter, soup, salad, cake, cupcake, cream, cookies, pie, pasta.

* Search for cooking recipes by ingredients;
* Add recipes;

Here the user has the opportunity to add their own recipes with which to help other users.

* Submit feedback.

Through the "Contacts" section of the web application each user can give us review, share his impressions or disappointments. It can also give ideas
to improve the user experience or simply ask a questions of any nature related to Let's Cook. In this section can also be found all the coordinates of
the owner of the application, including social networks.

## Who can use it?

The developed web application can be used by anyone who wants to get ideas for cooking or help other users with their own recipes.

## How it can be used?

Since the site is not yet hosted/deployed anywhere, it is launched/started on localhost in order to be used.

For this purpose I use a WAMP server. However, a few additional settings are required for the application to be fully functional. In the `application.yml` file there must be specified the username and password that can be used to connect to the database.

The other important setting related to sending Emails is to take the .php files `read_recipe.php`, `read_photo.php`, `Databse.php`, `Recipe.php`, `Photo.php` and `feedback.php` and upload them to the server folder (such as WAMP server) on your computer.
Also, one of the files `feedback.php` requires an external library PHPMailer, which you can find in the Additional Links and Resources below.

####IMPORTANT

In order the database to be able to save Bulgarian/Cyrillic words and symbols it should be created with `utf8mb4_general_ci` encoding!

## For future work/Improvements

In the future there will be more functionalities related to website such as:
1. Update/Delete recipe;
2. Use Cloudinary to save and retrieve images;
3. Register/Login via Social networks;
4. Sections such as "My recipes", "Liked recipes", "My products", "Products that I need";
5. Localization across the whole web app;
6. Events using

### Additional Links and Resources

* [PHPMailer - for sending feedback](https://github.com/PHPMailer/PHPMailer)


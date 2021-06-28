**Yavuz Orbey Case Study**

*Summary*: 3 part app - Recipe Creation, Meal Planning and Food Inventory Management

**Technologies:**

*Front-End*:  HTML, CSS, Javascript, Axios, Animate.css, Font Awesome  
*Back-End*: Java, MySQL, Spring(Boot), Spring Data JPA, Tomcat Server
Jackson (Java JSON library)  
*Design*: Paper + Pencil, Figma for Wireframes 

**Purpose**: The application will be used to create recipes and add recipes to your weekly meal plans based on what is available in your pantry at the time. The application will be used to maximize productivity in the kitchen by reducing a lot of the uncertainty when it comes to what ingredients you currently have in your pantry/refrigerator and how to plan out your meals. I hope this application makes meal planning easy for me personally as well as others.


**User Stories**:  
*Admin*:Admin can add, edit, delete, and view measurements  
Admin can add, edit, delete, and view nutrients  

*User*  
User can add, edit, delete, and view ingredients  
User can add, edit, delete, and view Recipes (for now)  
User can add ingredients to their pantry. 

*Pantry*  
User has one pantry that contains all their ingredients and quantities of those ingredients  
When user makes a recipe those ingredients will be deducted from the pantry.   
Users can add ingredients to their pantry at any time (say if they went shopping) and they can add specific quantities of that ingredient.  
User will always be able to see if he or she has enough ingredients for a given recipe at a time.  
If a user doesn't have the required ingredients in their pantry they won't be able to complete the recipe

**Challenges**:   
Challenge: Because I had a lot of pages I needed to quickly prototype css and not get too bogged down in style.   
How I dealt with it: To not have to worry about the Styling I used bootstrap just to get a quick prototype off the ground. Later on I will try to apply a lot more custom styling.

Challenge: A lot of what I wanted had to deal with dynamic inputs and I couldn't get that with traditional jsp forms  
How I dealt with it: I used a lot of Javascript in the codebase. Eventually I will go back through it all and clean it up  

Challenge: Because of the fact I used javascript for forms I needed to use a whole new way of sending form data to a controller.  
How I dealt with it: I used AJAX calls with AXIOS and sent them to several API controllers. I used the JACKSON Json library to parse this data from the controllers and oftentimes sent back a JSON response.  


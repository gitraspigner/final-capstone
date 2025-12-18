# Year Up United / Pluralsight - Final Capstone
## Course Taught By: Maaike Van Putten

### 🛠️ Tools Used
![Java](https://img.shields.io/badge/language-Java-blue.svg) / ![IDE](https://img.shields.io/badge/IDE-IntelliJ-orange) <br>
![SQL](https://img.shields.io/badge/language-SQL-blue.svg) / ![mySQL(Workbench)](https://img.shields.io/badge/database-MySQL-4479A1.svg?logo=mysql&logoColor=white) <br>
![Maven](https://img.shields.io/badge/build-Maven-C71A36.svg?logo=apachemaven&logoColor=white) <br> , ![Spring](https://img.shields.io/badge/framework-Spring-6DB33F.svg?logo=spring&logoColor=white) <br>
![HTML](https://img.shields.io/badge/language-HTML5-E34F26.svg?logo=html5&logoColor=white) , ![CSS](https://img.shields.io/badge/language-CSS3-1572B6.svg?logo=css3&logoColor=white) , ![JavaScript](https://img.shields.io/badge/language-JavaScript-F7DF1E.svg?logo=javascript&logoColor=black) <br> / ![Mustache](https://img.shields.io/badge/template-Mustache-FF6600.svg?logo=mustache&logoColor=white) <br>

| Active/Inactive                                                   | Active Dates            |
|-------------------------------------------------------------------|-------------------------|
| ![Status](https://img.shields.io/badge/status-active-brightgreen) | 12/12/2025 - 12/18/2025 |

### 📝 Description
My final capstone project. <br>
The PDF containing the capstone writeup cannot be uploaded due to Pluralsight ownership rights. <br>
But below you will find brief descriptions of each of the projects, which should give a better idea of their intended function and requirements. <br>
The Javadoc-style class comments I've made for each of the java files developed for each capstone were designed to be descriptive enough to give the reader a good idea of their intended function and requirements as well.

#### 🕒 Commits History Here<br>
[Click Here](https://github.com/gitraspigner/final-capstone/commits/master) <br>

#### 📋 Project Boards Here<br>
[Click Here](https://github.com/gitraspigner?tab=projects) <br>

### 💭 Capstone Detailed: <br>

---
### Capstone 3
 - **Capstone Title:** Web Store (Video Game Store - Ravi Games (Website))
 - **Description:** -In Progress-
 - **Application Screens:** -In Progress-
  - **Interesting Code Snippet:** -In Progress-
    - **Snippet:** -In Progress-
    - **Why it's interesting:** -In Progress-
  - **Additional Thoughts:** -In Progress-
---
# Capstone 2
  ## - **Capstone Title:** Sandwich Shop (Command Line Application)
  - **Description:** Simulates a Sandwich Shop application (ran from a command line interface). <br>
    The user (typically an employee of the shop) navigates a series of menus to accomplish basic order-related tasks of a sandwich shop. These tasks include creating a new order (with an order name) that consists of a series of items (either Sandwich, Chip, or Drink). Sandwiches can have multiple toppings, Chips are only offered of one size (Regular). Drinks and Sandwiches are offered of one of three sizes: Small, Medium, or Large. For Sandwiches, Meat & Cheese toppings each have a charge based off of the size of the Sandwich, and if the topping is an extra helping or not. Other toppings (which include Veggies and Sauces) have no charge for them. A user may also display reports including the current receipts/transactions of the day, and the total revenue from all previous user sessions. All receipts are written to a file (which, for this program is called "receipts.csv"). Each user session has its date recorded to the receipts file. All orders from that user session are recorded under the date of the user session, each order contains the name, order number (which starts from order #001 for each user session), and the items of the order.
<br> <br>
  - **UML Diagram:**
![UML Diagram.png](src/com/pluralsight/capstone2/Screenshots/UML%20Diagram.png)
  - **Application Screens:**
    - **First Program Run & Welcome Message (With No Prior Receipts File):** <br>
![firstProgramRunNoReceiptsFile.png](src/com/pluralsight/capstone2/Screenshots/firstProgramRunNoReceiptsFile.png)
    - **Invalid User Input (Number Out Of Range Of Menu Options):** <br> <br>
![invalidInputNumberOutOfRange.png](src/com/pluralsight/capstone2/Screenshots/invalidInputNumberOutOfRange.png)
    - **Invalid User Input (Word or Number Expected):** <br> <br>    
![invalidInputNonWordOrNonNumber.png](src/com/pluralsight/capstone2/Screenshots/invalidInputNonWordOrNonNumber.png)
    - **(New) Signature Sandwich Orders:** <br> <br>
![signatureSandwiches.png](src/com/pluralsight/capstone2/Screenshots/signatureSandwiches.png)
    - **A Complex Sandwich (pt1):** <br> <br>
![addComplexSandwich1.png](src/com/pluralsight/capstone2/Screenshots/addComplexSandwich1.png)
    - **A Complex Sandwich (pt2):** <br> <br>
![addComplexSandwich2.png](src/com/pluralsight/capstone2/Screenshots/addComplexSandwich2.png)
    - **A Complex Sandwich (pt3):** <br> <br>
![addComplexSandwich3.png](src/com/pluralsight/capstone2/Screenshots/addComplexSandwich3.png)
    - **A Non-Sandwich Order:** <br> <br>
![addNonSandwichOrder.png](src/com/pluralsight/capstone2/Screenshots/addNonSandwichOrder.png)
    - **Add Simple Sandwich (No Drink or Chip) Order:** <br> <br>
![addSimpleSandwichOrder.png](src/com/pluralsight/capstone2/Screenshots/addSimpleSandwichOrder.png)
    - **Display Orders of Current Session:** <br> <br>
![displayOrdersOfCurrentSession.png](src/com/pluralsight/capstone2/Screenshots/displayOrdersOfCurrentSession.png)
    - **Receipts File After Tests (Above):** <br> <br>
![receipts-csv-afterExitingApplication.png](src/com/pluralsight/capstone2/Screenshots/receipts-csv-afterExitingApplication.png)
    - **Display Total Revenue Of Previous Sessions:** <br> <br>
![displayTotalRevenuePreviousSessions.png](src/com/pluralsight/capstone2/Screenshots/displayTotalRevenuePreviousSessions.png)
    - **Receipts File, Multiple Days/Sessions Supported** <br> <br>
![receipts-csv-multipleDaysSessions.png](src/com/pluralsight/capstone2/Screenshots/receipts-csv-multipleDaysSessions.png)

    <br> <br>
  - **Interesting Code Snippets (Lambda Functions):**
  ```java
      public static Order getOrder(int orderNumber) {
          return orders.stream()
              .filter(o -> o.getOrderNumber() == orderNumber)
              .findFirst()
              .orElse(null);
    }
  ```
  ```java
        public static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders for the day found");
            return;
        }
        System.out.println("----All Orders----");
        orders.stream().forEach(o -> {
            System.out.println("-Order Number: " + o.getOrderNumber() +
                    ", Order Name: " + o.getOrderName());
            if (o.getItems().isEmpty()) {
                System.out.println("No Items Found");
            } else {
                o.getItems().forEach(item ->
                        System.out.println("\tItem: " + item.toString())
                );
                System.out.println(o.totalPrice());
            }
        });
    }
  ```
  - **Why they're interesting:**
   - They use lambdas, and I had to use lambdas to fulfill a requirement for this capstone. They both use Java stream, getOrder() retrieves an order via its order number using filter(), and displayOrders() uses two nested forEachs to display Order and Item data fields in order to displays all orders. That's pretty much it, nothing too special here.
  - **Extra Features:**
   - Total Revenue (File Reading)
   - Total Revenue Of Specified Date (File Reading)
   - Class Javadoc-style comments are included for each class
   - Signature Sandwiches (Grand Philly Cheesesteak, Large Chicken Club)
  - **Additional Thoughts:**
   - It should be noted that for both extra features implemented (Total Revenue and Total Revenue Of Specified Date), that revenue from the current user session is not included. I thought this would be good design since revenue should only be added and reported for a successfully completed (and properly exited) user session (which typically should be at the end of the day of an employee operating the Sandwich Shop application to take orders).
   - Class Javadoc-style comments are included for each class, including the JUnit test classes.
   - Development branch & project board were utilized for this project. Neither were particularly hard to use for me.
   - The most recent orders/transactions are appended to the end of the receipts.csv file, which is different from my first capstone where they are appended to the top. This design lead to a more efficient program where all orders/transactions from the output file did not have to be read, built, stored, and written twice during a single program session (once at the start and once at the end).
   - There was an extra feature I wanted to implement: The Combo Meal, where if a user placed an order for a Sandwich, Drink, and Chip they'd receive 10% off their order. Due to time constraints and wanting to focus on other aspects of this project (including documentation, which I consider to be important as per my University of Washington teachings), I decided to omit this feature.
   - The Signature Sandwich feature was implemented last-minute. Its implementation is based off of Menus.getSandwich() as opposed to a static method within Sandwich. If I had more time, I would've done that instead, but I needed to focus on my presentation (and updating my documentation).
---


### 🔖 Citation
I wrote this README.md, but I did indeed use ChatGPT to give my initial framework and to learn markdown formatting. Therefore here is an APA Style Citation for it:  <br>
OpenAI. (2025). ChatGPT (Oct 1 version) [Large language model]. https://chatgpt.com/ <br>

*I have to give credit where it's due, right?* <br>

**Last Edited: 12/18/2025**

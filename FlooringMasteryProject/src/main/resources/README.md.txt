#Flooring Mastery Project

The goal of this mastery project is to create an application that can store and retrieve flooring orders from a database.

##Project Goals:
N-tier/MVC Architecture, including service layer
Interfaces
Spring Dependency Injection
Unit Testing (in service and dao layer)
Input Validation (ensure invalid data input redirects user to input data again of valid type})

##MVC Structure:
View: Responsible for user interaction and display.
Controller: Handles incoming requests from the view, processes them lightly, and interacts with the service layer to fetch or manipulate data. Is an intermediary between the view and the service.
Service: Contains business logic. Processes requests from the controller and interacts with the DAO to perform data operations.
DAO (Data Access Object): Provides methods for accessing and manipulating data in the database. Serves the service layer.
Model: Represents the data structure. Defines the properties and behaviours of the data used by the DAO. Models are often used to map data to the database.

##Classes/Database Tables:

###Order
properties:
OrderNumber – Integer {AUTOINCREMENT}
CustomerName – String
State – String
TaxRate – BigDecimal
ProductType – String
Area – BigDecimal
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
MaterialCost – BigDecimal
LaborCost – BigDecimal
Tax – BigDecimal
Total – BigDecimal
(Date – DateTime)
update with default values upon initialisation by controller via service layer:
OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,Date
1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06,01-06-2025
2,Doctor Who,WA,9.25,Wood,243.00,5.15,4.75,1251.45,1154.25,216.51,2622.21,02-06-2025
3,Albert Einstein,KY,6.00,Carpet,217.00,2.25,2.10,488.25,455.70,56.64,1000.59,02-06-2025

###Tax
StateAbbreviation – String
StateName – String
TaxRate – BigDecimal
update with default values upon initialisation by controller via service layer:
State,StateName,TaxRate
'TX', 'Texas', 4.45
'WA', 'Washington', 9.25
'KY', 'Kentucky', 6.00
'CA', 'Calfornia', 25.00

###Products
ProductType – String
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
update with default values upon initialisation by controller via service layer:
ProductType,CostPerSquareFoot,LaborCostPerSquareFoot
Carpet,2.25,2.10
Laminate,1.75,2.10
Tile,3.50,4.15
Wood,5.15,4.75

# User IO:
  * 1. Display Orders (for Date) {if none, give error message and return to main menu}
  * 2. Add an Order
  * 3. Edit an Order
  * 4. Remove an Order
  * 5. Export All Data {create/overwrite DataExport.txt in backup folder with all active orders}
  * 6. Quit {exit application}

##Add an Order
Query user for:
Order Date – validate in future
Customer Name – validate not blank and not \s* and is limited to regex [a-z0-9,.]+(\s[a-z0-9,.]+)*
State – validate that it exists in tax table
Product Type – show available products and pricing information for user to choose from
Area – validate is positive decimal, min 100 sq ft.
Remaining fields are calculated via user input and the tax/product information in the files.  once the calculations are completed and prompt the user as to whether they want to place the order (Y/N). If yes, the data will be added to in-memory storage. If no, simply return to the main menu.
Remaining order fields are calculated using user input and tax/product tables:
MaterialCost = (Area * CostPerSquareFoot)
LaborCost = (Area * LaborCostPerSquareFoot)
Tax = (MaterialCost + LaborCost) * (TaxRate/100)
Tax rates are stored as whole numbers {???}
Total = (MaterialCost + LaborCost + Tax)
then show order summary and confirm order. if yY -> add order to database. if nN -> return to main menu.
OrderNumber should be autoincremented.

##Edit an Order
For edit, user inputs date + order number. validate if order exists for that date (if not return order not found back to menu), display existing data, ask user to input new CustomerName/State/ProductType/Area or press enter to not change.
If the State/ProductType/Area changed, recalculate order.
Display new order summary and confirm save of any edits. If yes, change database and return to main menu. If no, do not save and return to the main menu.

##Remove an Order
For removal, input date and order number. If existing, display order information and confirm deletion. If yes, remove from database.
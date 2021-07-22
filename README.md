# Middleware : 
A B2B service for Efoods webapp which stores purchase orders and keeps track of inventory

## Technologies
Java

## Usage
Run simultaneously while the Efoods webapp is deployed 

## Implementation
-   To facilitate for platform independence, the main location of the PO (which is the main directory of the webapp) will be read as the main’s arguments. 
-   The subdirectory where the PO is located is assumed to be known to the middleware I.e (WEBCONTENT/PO). The middleware will then iterate through the PO. For each PO xml file, 
-   it will extract the items and corresponding quantity listed in the PO, and after processing that, moved the PO into the ‘processed PO’ folder. 
-   This process though, has to been ‘priorly agreed’ between the webapp and the middleware, as the webapp will need to iterated between two folders when showing users their PO link.

## Acknowledgements

- This project was inspired by Professor Hamzeh Roumani 
- This project was based on a final project submission for course EECS4413 E-Commerce Systems at York University



## License
[MIT](https://choosealicense.com/licenses/mit/)

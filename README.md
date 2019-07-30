A data analysis system that import lots of flat files, read, analyse the data, and output a report. The analysis must be executed every changes in the input directory.

### Flat input file layout
There are 3 kinds of data inside those files. For each kind of data there is a different layout.

 - Salesman
    - 001çCPFçNameçSalary

 - Customer
    - 002çCNPJçNameçBusiness Area
 - Sales
    - 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

### Flat output file data
 - Amount of clients in the input file
 - Amount of salesman in the input file
 - ID of the most expensive sale
 - Worst salesman ever

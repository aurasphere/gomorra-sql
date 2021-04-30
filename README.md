[![Travis](https://img.shields.io/travis/aurasphere/gomorra-sql.svg)](https://travis-ci.org/aurasphere/gomorra-sql)
[![Maintainability](https://api.codeclimate.com/v1/badges/43d564cf9ee6e93d8391/maintainability)](https://codeclimate.com/github/aurasphere/gomorra-sql/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/43d564cf9ee6e93d8391/test_coverage)](https://codeclimate.com/github/aurasphere/gomorra-sql/test_coverage)
[![Join the chat at https://gitter.im/gomorra-sql/community](https://badges.gitter.im/gomorra-sql/community.svg)](https://gitter.im/gomorra-sql/community)
[![Donate](https://img.shields.io/badge/Donate-PayPal-orange.svg)](https://www.paypal.com/donate/?cmd=_donations&business=8UK2BZP2K8NSS)

# GomorraSQL
GomorraSQL it's a easy and straightforward interpreted SQL language that allows you to write simpler and more understandable queries in Napoletan language.

## Set up
GomorraSQL can be used either as a Java library or as a standalone SQL database client.

### Java library
To use it as a Java library, download the latest jar in the release section and import it into your project, along with the driver of the database you want to use. Then, you can use the methods exposed by the class ```co.aurasphere.gomorrasql.GomorraSqlInterpreter``` which allow you to either execute a GomorraSQL query against your database (to which you will provide a connection) or just translate it into plain old SQL.

### Database client
To use GomorraSQL as a database client, download the latest jar in the release section and execute it with the command:

    java -jar gomorra-sql.jar -cp <path_to_your_db_driver>
    
The client will ask for a JDBC string representing the database to connect to (including credentials). After the connection is established, you can start running commands.

## Language basics
Before delving into the specific commands, it's important to consider some general rules to avoid "cuoppo" mistakes:

- paremthesis are not valid characters in GomorraSQL queries. In the following examples they are just used to distinguish between (mandatory parameters) and [optional parameters]
-  there's no end query character, since GomorraSQL doesn't allow multi-line queries
-  spacing is very important when using operators! A query using a condition ```a = 5``` will work but the same query with ```a= 5``` or ```a =5``` or ```a=5``` will not

## Data Manipulation Language
Similar to standard SQL, GomorraSQL allows to perform data manipulation. Here's a list of supported operations:

### Retrieving data
To retrieve data, you can use the ```ripigliammo``` command. Here's the command syntax:

    ripigliammo (<comma_separated_column_names> || tutto chillo ch'era 'o nuostro) mmiez 'a <table_name> [pesc e pesc <table_name>] [arò <condition>]

The first argument for the ```ripigliammo``` command is the columns to retrieve. They can be specified either as a list of comma separated values or with the ```tutto chillo ch'era 'o nuostro``` which will return all the columns.
After the columns, the following parameter is the name of the table where to fetch the data with ```mmiez 'a```. Data can be fetched from multiple tables by using the optional join operator ```pesc e pesc``` followed by another table name. Currently there's no limit on the number of ```pesc e pesc``` that can be applied to a single ```ripigliammo```. The join condition is specified in the ```arò``` clause, along with the row filtering.
Finally, you can filter the rows using the optional ```arò``` clause, followed by one or more conditions. For more info, see the conditions section.

### Deleting data
Data deletion can be performed using the ```facimm na' strage``` command which supports a subset of options from the ```ripigliammo``` command. Here's the syntax:

    facimm na' strage mmiez 'a <table_name> [arò <condition>]
    
### Updating data
The command ```rifacimm``` is used to update data in a table. The syntax is:

    rifacimm <table_name> accunza <column_1> accussì <value_1>, <column_2> accussì <value_2>, ... [arò <conditions>]
    
The  ```accunza``` operator marks the begin of a list of column/values assignments using the assignment operator ```accussì```.

### Inserting data
Data insertion can be performed using the ```nzipp 'ngoppa``` operator as following:

     ```nzipp 'ngoppa <table_name> (<column_1>, <column_2>...) chist <value_1>, <value_2>...```
    
After the table name, you can specify a list of columns whose data are being inserted. If not present, GomorraSQL will default to all columns. The ```chist``` keyword marks the beginning of a comma separed list of values to insert. Only one row can be inserted at time.

## Transaction support
Being a fully ACID compliant language, GomorraSQL offers basic transaction management. To begin a transaction, you can issue the command ```ua uagliò```. You can then commit the transaction with the command ```iamme bello ia'``` or perform rollback with the command ```sfaccimm```.

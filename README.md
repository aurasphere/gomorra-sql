[![Travis](https://img.shields.io/travis/aurasphere/gomorra-sql.svg)](https://travis-ci.org/aurasphere/gomorra-sql)
[![Maintainability](https://api.codeclimate.com/v1/badges/43d564cf9ee6e93d8391/maintainability)](https://codeclimate.com/github/aurasphere/gomorra-sql/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/43d564cf9ee6e93d8391/test_coverage)](https://codeclimate.com/github/aurasphere/gomorra-sql/test_coverage)
[![Join the chat at https://gitter.im/gomorra-sql/community](https://badges.gitter.im/gomorra-sql/community.svg)](https://gitter.im/gomorra-sql/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Donate](https://img.shields.io/badge/Donate-PayPal-orange.svg)](https://www.paypal.com/donate/?cmd=_donations&business=8UK2BZP2K8NSS)

# GomorraSQL
GomorraSQL it's an easy and straightforward interpreted SQL language that allows you to write simpler and more understandable queries in Napoletan language.

## Set up
GomorraSQL can be used either as a Java library or as a standalone SQL database client.

### Java library
To use it as a Java library, download the latest jar in the release section and import it into your project, along with the driver of the database you want to use. Then, you can use the methods exposed by the class ```co.aurasphere.gomorrasql.GomorraSqlInterpreter``` which allow you to either execute a GomorraSQL query against your database (to which you will provide a connection) or just translate it into plain old SQL.
GomorraSQL throws a ```CaggiaFaException``` with useful debugging information whenever an error occurs.

### Database client
To use GomorraSQL as a database client, download the latest jar in the release section and execute it with the command:

    java -cp gomorra-sql-1.0.0.jar;<path_to_your_db_driver> co.aurasphere.gomorrasql.GomorraSqlShell
    
The client will ask for a JDBC string representing the database to connect to (including credentials). After the connection is established, you can start running commands.

## Language basics
Before delving into the specific commands, it's important to consider some general rules to avoid "cuoppo" mistakes:

- parenthesis are not valid characters in GomorraSQL queries. In the following examples, they are just used to distinguish between (mandatory parameters) and [optional parameters]
-  GomorraSQL doesn't allow multi-line queries. Therefore, there's no end-of-query character (like ; in SQL)
-  spacing is very important when using operators! A query using a condition ```a = 5``` will work but the same query with ```a= 5``` or ```a =5``` or ```a=5``` will not

## Data Manipulation Language
Likewise standard SQL, GomorraSQL allows performing data manipulation. Here's a list of supported operations:

### Retrieving data
To retrieve data, you can use the ```ripigliammo``` command. Here's the command syntax:

    ripigliammo (<comma_separated_column_names> || tutto chillo ch'era 'o nuostro) mmiez 'a <table_name> [pesc e pesc <table_name>...] [arò <condition>]

The first argument for the ```ripigliammo``` command is the columns to retrieve. They can be specified either as a list of comma-separated values or with the ```tutto chillo ch'era 'o nuostro``` which will return all the columns.

After the columns, the following parameter is the name of the table where to fetch the data with ```mmiez 'a```. Data can be fetched from multiple tables by using the optional join operator ```pesc e pesc``` followed by another table name. Currently, there's no limit on the number of ```pesc e pesc``` that can be applied to a single ```ripigliammo```. The join condition is specified in the ```arò``` clause, along with the row filtering.

Finally, you can filter the rows using the optional ```arò``` clause, followed by one or more conditions. For more info, see the conditions section.

Here are some sample queries:

    ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a user  # retrieves all users' data
    ripigliammo email mmiez 'a user arò id = 6 o name è nisciun  # retrieves all the emails of the users with id 6 or null name
    ripigliammo email mmiez 'a user pesc e pesc city pesc e pesc account arò user.id = 6 e user.birth_city = city.id e user.account_id = account.id  # retrieves the data of the user with id 6 along joined with his birth city and his account data

### Deleting data
Data deletion can be performed using the ```facimm na' strage``` command which supports a subset of options from the ```ripigliammo``` command. Here's the syntax:

    facimm na' strage mmiez 'a <table_name> [arò <condition>]
    
Here are some sample queries:

    facimm na' strage mmiez 'a user  # deletes all users' data
    facimm na' strage mmiez 'a user arò name nun è nisciun o deleted è true  # deletes the users with name not null or with deleted = true
    
### Updating data
The command ```rifacimm``` is used to update data in a table. The syntax is:

    rifacimm <table_name> accunza <column_1> accussì <value_1>, <column_2> accussì <value_2>, ... [arò <conditions>]
    
The  ```accunza``` operator marks the begin of a list of column/values assignments using the assignment operator ```accussì```.

Here are some sample queries:

    rifacimm user accunza name accussì "Pippo"  # sets the name "Pippo" for all the users
    rifacimm user accunza name accussì "Pinco", surname accussì "Pallo" arò name è nisciun  # sets the name to "Pinco" and surname to "Pallo" for all users with null name

### Inserting data
Data insertion can be performed using the ```nzipp 'ngoppa``` operator as following:

     ```nzipp 'ngoppa <table_name> (<column_1>, <column_2>...) chist <value_1>, <value_2>...```
    
After the table name, you can specify a list of columns whose data are being inserted. If not present, GomorraSQL will default to all columns. The ```chist``` keyword marks the beginning of a comma-separated list of values to insert. Each insert statement can only add one row.

Here are some sample queries:

    nzipp 'ngoppa user chist 1, "Pinco", "Pallo"  # inserts a new user with all his data
    nzipp 'ngoppa user name chist "Pinco"  # inserts a new user with only his name set


## Transaction support
Being a fully ACID compliant language, GomorraSQL offers basic transaction management. To begin a transaction, you can issue the command ```ua uagliò```. You can then commit the transaction with the command ```iamme bello ia'``` or perform rollback with the command ```sfaccimm```.

## Language reference
Follows a table that roughly maps GomorraSQL language to standard SQL:

| GomorraSQL keyword             | SQL equivalent | Valid in...            |
|--------------------------------|----------------|------------------------|
| ripigliammo                    | SELECT         | SELECT                 |
| rifacimm                       | UPDATE         | UPDATE                 |
| nzipp                          | INSERT         | INSERT                 |
| 'ngoppa                        | INTO           | INSERT                 |
| facimm na' strage              | DELETE         | DELETE                 |
| pesc e pesc                    | INNER JOIN     | SELECT                 |
| mmiez 'a                       | FROM           | SELECT, DELETE         |
| tutto chillo ch'era 'o nuostro | *              | SELECT                 |
| arò                            | WHERE          | SELECT, UPDATE, DELETE |
| e                              | AND            | ANY WHERE CLAUSE       |
| o                              | OR             | ANY WHERE CLAUSE       |
| nisciun                        | NULL           | ANY WHERE CLAUSE       |
| è                              | IS             | ANY WHERE CLAUSE       |
| nun è                          | IS NOT         | ANY WHERE CLAUSE       |
| chist                          | VALUES         | INSERT                 |
| accunza                        | SET            | UPDATE                 |
| accussì                        | = (assignment) | UPDATE                 |
| >                              | >              | ANY WHERE CLAUSE       |
| <                              | <              | ANY WHERE CLAUSE       |
| = (comparison)                 | = (comparison) | ANY WHERE CLAUSE       |
| !=                             | !=             | ANY WHERE CLAUSE       |
| <>                             | <>             | ANY WHERE CLAUSE       |
| <=                             | <=             | ANY WHERE CLAUSE       |
| >=                             | >=             | ANY WHERE CLAUSE       |
| sfaccimm                       | ROLLBACK       | TRANSACTION            |
| iamme bello ia'                | COMMIT         | TRANSACTION            |
| ua uagliò                      | BEGIN TRANSACTION | TRANSACTION         |

## Supported Database
GomorraSQL has been extensively tested with MySQL and H2. Other databases may not work properly.

## Training
Video lessons on GomorraSQL syntax and philosophy [are available here](https://www.nowtv.it/watch/home/asset/gomorra-la-serie/skyatlantic_7bb8b3e11d19439fb71c68349b2cfab3). If you are also interested in corporate training, feel free to contact me for pricing.

## Contributions
If you want to contribute to this project, just fork this repo and submit a pull request with your changes. Improvements are always appreciated!

## Project status
This project is considered completed and won't be developed further.

## Contacts
You can contact me using my account e-mail or opening an issue on this repo. I'll try to reply ASAP.

## License
The project is released under the MIT license, which lets you reuse the code for any purpose you want (even commercial) with the only requirement being copying this project license on your project.

<sub>Copyright (c) 2021 Donato Rimenti</sub>

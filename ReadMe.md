GHCO Trade aggregation Application 

The purpose of this application is to print trade volumes aggregated on the basis of BBGCode and portfolioCode.
The results are printed as below:

BBGCode: PortFolioCode ======= Currency ========Aggregated Trade Volume
GOOG US Equity:portfolio1 ======== KRW ======= 8.32956562E9
GOOG US Equity:portfolio1 ======== JPY ======= 8.191440082E9
GOOG US Equity:portfolio1 ======== EUR ======= 8.2669763E9
GOOG US Equity:portfolio1 ======== GBP ======= 8.415301613E9
GOOG US Equity:portfolio1 ======== USD ======= 8.428289943E9
GOOG US Equity:portfolio1 ======== NOK ======= 8.326384097E9

The results are displayed on the basis of Currency in addition to BBGCode and PortfolioCode.

Observation from provided file:
Every AMEND and CANCEL operation has subsequent NEW operation.

Assumptions:
CANCEL operations are not considered in aggregation. 
IF AMEND operation is present , traded volume for NEW operation is not included.

How to Run the application:
Clone the repository inti any IDE and run as spring boot application.

First it asks if you want to enter filename manually. If yes please enter 'Y'. If No enter 'N'
if yes, please provide a filepath from Windows in the format C:/Users/anmol/OneDrive/Desktop/trades1.csv
Currently we are not reading from UNIX.

if No, the System reads a file from resources folder i.e trades.csv.

Next once the file is read, the application prompts if the user wants to add any new records apart from the ones in file.
Enter Y if user wants to enter a new record. N if otherwise. the System keeps asking for the record till the user inputs N.

Once the additional records are entered, System computes aggregated data for all the records.

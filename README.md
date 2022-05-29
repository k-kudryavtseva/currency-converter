# currency-converter

For running test suite use the following command:
mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testing-acceptance.xml clean test

Two options for param -Dbrowser available: chrome and firefox

After run is completed generate allure report:
allure generate

Find generated report in:
/allure-report/index.html
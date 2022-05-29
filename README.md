# Currency Converter

## Running
To run test suite use the following command (from the project root directory):
```
$ mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testing-acceptance.xml clean test
```

Two options for param -Dbrowser available:
- `chrome`
- `firefox`

## Reporting
After a run an allure report can be generated (from the project root directory):
```
$ allure generate
```

The generated report is located at `${PROJECT_ROOT_DIR}/allure-report/index.html`
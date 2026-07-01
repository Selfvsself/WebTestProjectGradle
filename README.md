# WebTestProject

This repository contains a test assignment solution featuring automated UI and API tests.

## 🛠️ Tech Stack
* **Language:** Java
* **UI Testing:** Selenide
* **API Testing:** Retrofit
* **Test Runner:** TestNG
* **Reporting:** Allure Report

## 🚀 Quick Start
Make sure you have JDK and Maven installed, then run:

```bash
./gradlew clean test -Dbrowser=firefox -Dheadless=true
```

Allure reports can be generated after the test run using:
```bash
./gradlew allureServe
```
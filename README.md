# WebTestProject

Automated Testing Framework (UI & API)

This repository contains UI and API tests within a single Gradle project.

# Tech Stack
* **Language:** Java
* **UI Testing:** Selenide
* **API Testing:** Retrofit and OkHttp 3
* **Test Runner:** TestNG
* **Reporting:** Allure Report

# Test Cases & Scenarios

## User Interface Automation (UI)
### Test Case 1: Validate Navigation Header "Shop" Submenu Items

Browser is opened at the Main Page.
* Click on the main navigation item "Shop" in the header.

**Expected Result**: A dropdown submenu expands, containing exactly 7 mandatory resource titles in the following order: “Shop by Subject”, “Textbooks”, “Professional Books & Resources”, “Exam Guides”, “Courseware”, “Imprints”, “Book Stores”.

### Test Case 2: Verify Search Dropdown Popup Layout Alignment

Browser is opened at the Main Page.
* Type the search term "Java" into the search field.

**Expected Result**: The autocomplete suggestions popup appears. The left and right horizontal boundaries of the suggestion window strictly align with the edges of the search input field.

### Test Case 3: Validate Search Cards Consistency on Results Page

Browser is opened at the Main Page.
1. Type the search term "Java" into the search field.
2. Press the Enter key to submit the form.

**Expected Result**: The user is redirected to the Search Results page. The page renders exactly 10 product cards. Every card title contains the searched term "Java".

### Test Case 4: Verify Required Headers on Student Resources Page

Browser is opened at the Main Page.
* Click on the main navigation item "Teach & Grow" in the header.
* Click on the "Student resources" sub-headline link.

**Expected Result**: The user is navigated to the "Student Resources" target page. The page successfully loads and renders 6 mandatory content blocks with the following headers: “Textbooks”, “WileyPLUS”, “Knewton Alta”, “zyBooks”, “Digital Tools”, “Exam Guides”.


## Application Programming Interface Automation (API)
### Test Case 5: Validate Autocomplete Query Suggestions (Search API)

* Send an HTTP GET request to the search autocomplete endpoint with a partial product name query parameter (q=Java).

**Expected Result**: The JSON response body structure is valid and contains a fixed list of 6 product suggestions. Each product title string contains the matching term "Java".

### Test Case 6: Verify Connection Holding Compliance (Delay API)

* Send an HTTP GET request to the media processing endpoint https://httpbin.io/delay/{delay}

**Expected Result**: The server responds successfully. The actual network response duration corresponds to the specified value.

### Test Case 7: Binary Stream Integrity Verification (Image API)

* Send an HTTP GET request to the media processing endpoint https://httpbin.io/image/png

**Expected Result**: The server returns a simple PNG image.


# Quick Start
Make sure you have JDK and Maven installed, then run:

```bash
./gradlew clean test -Dbrowser=firefox -Dheadless=true
```

Allure reports can be generated after the test run using:
```bash
./gradlew allureServe
```
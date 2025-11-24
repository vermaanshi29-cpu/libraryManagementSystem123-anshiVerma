# libraryManagementSystem123-anshiVerma
# Library Management System — README (Concise)

## Overview

A simple Java-based **Library Management System** that manages:

* Books
* Members
* Borrowing & returning
* Tracking due dates and availability

The project demonstrates **OOP**, **DAO pattern**, **service layer**, and optional **SQLite/JDBC** persistence.

---

## Project Structure (Typical)

```
src/
  model/       # Book, Member, Loan
  dao/         # BookDAO, MemberDAO, LoanDAO, Database
  service/     # LibraryService
  ui/          # ConsoleUI or GUI
  util/        # DateUtils, Validation
resources/     # schema.sql, library.db
```

---

## Core Classes

### **Book**

Represents a book with title, author, isbn, total/available copies.

### **Member**

Library member details: name, contact, active status.

### **Loan**

Tracks borrowed books: borrow date, due date, return date.

### **DAO Layer**

Handles CRUD using JDBC.

* **BookDAO**: add/update/search books
* **MemberDAO**: register/find members
* **LoanDAO**: create/close loans

### **LibraryService**

Business logic:

* Borrow book → check availability + create loan
* Return book → update stock + close loan
* Search books/members

### **UI Layer**

Console/Swing interface calling service functions.

---

## How It Works (Flow)

1. User selects an action (add book / register member / borrow / return).
2. UI calls **LibraryService**.
3. Service uses **DAOs** to read/write the database.
4. Updated results shown to the user.

---

## Database (SQLite Example)

Tables: `books`, `members`, `loans`.

Run DB manually:

```bash
sqlite3 library.db
```

---

## Build & Run

### Compile

```bash
javac -d out $(find src -name "*.java")
```

### Run

```bash
java -cp out com.anshi.library.Main
```

(Add SQLite JDBC jar to classpath if required.)

---

## Features

* Add/search books
* Register members
* Borrow/return books
* Automatic due date handling

---

## Improvements

* Add GUI
* Automated overdue reminders
* Fines system
* Role-based login

---

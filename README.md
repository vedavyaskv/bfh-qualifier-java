Understood 👍 — Here is a clean, professional **README.md** without emojis, images, informal lines, or extra decorative formatting.

You can copy and paste this directly into GitHub.

---

# **README.md (Professional Version)**

````markdown
# Bajaj Finserv Health – Java Qualifier Project

This project is developed as part of the Bajaj Finserv Health Hiring Qualifier Challenge (Java Track).  
The application demonstrates Spring Boot development, REST API interaction, webhook processing, SQL problem-solving, and Maven-based packaging.

---

## Project Objective
- Generate a webhook URL and authentication token from the Bajaj Finserv Health API.
- Process the webhook response and extract the SQL problem.
- Solve the given SQL challenge programmatically.
- Submit the final SQL query to the specified API endpoint using the provided JWT token.
- Package the project as a runnable JAR file.

---

## Tech Stack
| Technology | Purpose |
|------------|---------|
| Java 21 / 22 | Core application logic |
| Spring Boot 4.0.0 | Web and REST API handling |
| REST Template | Service-to-service communication |
| Maven | Dependency and build management |
| SQL | Analytical query solution |
| Git & GitHub | Version control and deployment |

---

## SQL Problem Solved
SQL Question: Highest salary employee from each department excluding payments made on the 1st day of the month.

### Requirements
- Identify the highest salary in each department.
- Exclude salaries processed on the 1st day of every month.
- Output fields: department_name, salary amount, employee full name, age.

### Final SQL Query Submitted

```sql
SELECT 
    d.department_name,
    p.amount AS salary,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    FLOOR(DATEDIFF(CURRENT_DATE, e.dob) / 365) AS age
FROM payments p
JOIN employee e ON p.emp_id = e.emp_id
JOIN department d ON e.department = d.department_id
WHERE EXTRACT(DAY FROM p.payment_time) != 1
AND p.amount = (
    SELECT MAX(p2.amount)
    FROM payments p2
    JOIN employee e2 ON p2.emp_id = e2.emp_id
    WHERE e2.department = e.department
    AND EXTRACT(DAY FROM p2.payment_time) != 1
);
````

---

## How to Run the Project

### Prerequisites

* Java 21 or higher
* Maven installed
* Git (optional for repository cloning)

### Run using Maven

```bash
mvn spring-boot:run
```

### Run the generated JAR file

```bash
java -jar target/bfh-qualifier-java-0.0.1-SNAPSHOT.jar
```

Expected Output:

```
Final Response: {"success":true,"message":"Webhook processed successfully"}
```

---

## JAR Download Link (Required for submission)

Direct download:
[https://raw.githubusercontent.com/vedavyaskv/bfh-qualifier-java/main/release/bfh-qualifier-java-0.0.1-SNAPSHOT.jar](https://raw.githubusercontent.com/vedavyaskv/bfh-qualifier-java/main/release/bfh-qualifier-java-0.0.1-SNAPSHOT.jar)

---

## Project Structure

```
bfh-qualifier-java
│── src/
│── pom.xml
│── target/
│── release/
│    └── bfh-qualifier-java-0.0.1-SNAPSHOT.jar
```

---

## Completion Status

| Task                    | Status     |
| ----------------------- | ---------- |
| Webhook generated       | Completed  |
| SQL solution computed   | Completed  |
| Final submission sent   | Successful |
| JAR packaged and tested | Completed  |
| Repository published    | Public     |

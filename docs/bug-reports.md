# Sample Bug Reports

SauceDemo provides special test users that have deliberate bugs. These were found during exploratory testing.

## BUG-001: Product images are all the same for problem_user

| Field | Details |
|-------|---------|
| Environment | Chrome 130, Windows 11, https://www.saucedemo.com |
| Severity | Medium |
| Priority | High |
| Steps | 1. Log in as `problem_user` / `secret_sauce` 2. View the Products page |
| Expected | Each product shows its own image |
| Actual | Every product shows the same dog image |
| Status | New |

## BUG-002: Last name field cannot be typed for problem_user

| Field | Details |
|-------|---------|
| Environment | Chrome 130, Windows 11 |
| Severity | High (blocks checkout) |
| Priority | High |
| Steps | 1. Log in as `problem_user` 2. Add any item, open Cart, click Checkout 3. Type in First Name, then Last Name |
| Expected | Text appears in the Last Name field |
| Actual | Typing in Last Name changes the First Name field instead; checkout cannot continue |
| Status | New |

## BUG-003: Pages load slowly for performance_glitch_user

| Field | Details |
|-------|---------|
| Environment | Chrome 130, Windows 11 |
| Severity | Low |
| Priority | Medium |
| Steps | 1. Log in as `performance_glitch_user` / `secret_sauce` |
| Expected | Products page loads in under 2 seconds |
| Actual | Login takes around 5 seconds |
| Status | New |

Notes

---
###Section 1: Getting started w/ clean code

- Separating a test to three parts, the "setup" part may be refactored into a method so test shows more focus to data verification
- Setup methods should receive params for different test scenario 
- After all test parts refactored, do test name
  last, snake case for clarity. Method name describes the method like
  `private void testScenario_expectedResult()`
  - <sup>may differ per project coding style guide, consistency is key</sup>
- End: test should be highlighting what it is testing.

###Section 2: Principles of Simple design
#### Principles:
  - runs all test
  - less duplication
    - methods for repeated instructions
  - Clarity
    - formatting for humans
    - in a glance, code should easily explain what it does
  - Keep it small
    - small methods to lessen duplication **only**
    - keep bits that has business value, and others, if these can be clearly written within
#### Unit testing Principles
  - Easy to understand
  - Should fail with prod code
  - Should find all problems in prod code
  - Tests duplication to a minimum
  - Should run quickly

###Section 3: Refactoring
  - see `jshell` to fiddle with Java code expressions

###Section 4: Test Driven Devt
####Three Steps
  1. Red - Write a test that fails
  2. Green - Write the simplest code that passes
  3. Refactor - Keep it green
####Three Laws
  1. Production code w/o failing tests
  2. Just enough tests to make code fail
  3. Just enough code to pass the test
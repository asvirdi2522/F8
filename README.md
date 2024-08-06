# Standard Deviation Calculator

This is a Java application with a graphical user interface (GUI) for calculating the standard deviation of a set of numbers. The GUI is built using Java Swing. The application includes handling for various special cases and provides helpful error messages to the user.

## Features

- **Graphical User Interface**: Easy-to-use interface for entering numbers and displaying the result.
- **Special Case Handling**: 
  - Displays a message if the input is empty.
  - Displays a message if there is only one observation.
  - Displays a message if all numbers are zeros.
  - Displays a message if all numbers are identical.
- **Exception Handling**: Provides helpful error messages for invalid input.

## How to Use

1. **Download and Compile**:
    - Download the source code.
    - Compile the code using the following command:
    ```
    javac StandardDeviationCalculator.java
    ```

2. **Run the Application**:
    - Run the application using the following command:
    ```
    java StandardDeviationCalculator
    ```

3. **Enter Numbers**:
    - Enter numbers separated by commas in the input field.
    - Click the "Calculate" button to see the standard deviation.

## Special Cases

- **Empty Dataset**: If the input field is empty, the application will display "Empty dataset".
- **Single Observation**: If the input contains only one number, the application will display "Standard deviation is 0 for a single observation".
- **All Zeros**: If the input contains only zeros, the application will display "Standard deviation is 0 for all zeros".
- **Identical Values**: If the input contains identical values, the application will display "Standard deviation is 0 for identical values".

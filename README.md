Requirements:
- Implement a driverless Car based on the provided interface that would be capable of moving forward into different directions and turning clockwise in a car park
- The car park would be rectangular in shape with configurable dimensions
- The Car should remember its position and orientation
- An exception will be thrown when the car moves outside the car park boundaries
- Evidence should be provided to demonstrate that the Car implementation meets the acceptance criteria
- Implement as a Maven module, if you need to use any library, use only those available in Maven Central.
 
Design considerations:
- The Car interface below is provided as a guideline and can be changed
- Future extensibility for new Car features should be kept in mind
- Consider OO modeling for the application
- Keep minimal and viable for the final workable application
 
Example:
- A simple illustration of the exercise with a car initially positioned at X = 1 and Y = 1 and facing North would look like this on a car park with dimension 4x4:
 
(Y)
  +---+---+---+---+
4 |   |   |   |   |
  +---+---+---+---+
3 |   |   |   |   |     N
  +---+---+---+---+ W <-|-> E
2 |   |   |   |   |     S
  +---+---+---+---+
1 | C |   |   |   |
  +---+---+---+---+
    1   2   3   4 (X)
 
Acceptance Criteria:
- Given the Car is in position X = 1 and Y = 1 and facing North, when the Car turns clockwise, then the Car is still in the same position but is now facing East
- Given the Car is in position X = 1 and Y = 1 and facing North, when the Car moves forward, then the Car is still facing North but is now in position X = 1 and Y = 2
- Given the Car is in position X = 1 and Y = 1 and facing East, when the Car moves forward, then the Car is still facing East but is now in position X = 2 and Y = 1
- Given the Car is in position X = 1 and Y = 1 and facing West, when the Car moves forward, then an exception is thrown
- Given the Car is in position X = 1 and Y = 1 and facing East, when the Car moves forward twice, then the Car is still facing East but is now in position X = 3 and Y = 1
 
 
Example Interface:
 
public interface Car {
   void move(String command);
    int getPositionX();
    int getPositionY();
    String getOrientation();
}
 
Notes:
·        Acceptance criteria is what we can verify the functionalities of the application. They should be easy, automatic, repeatable ran and built for maintainability purpose.
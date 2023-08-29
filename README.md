# Graphs App

This is a console Java application that finds number of separate graphs from given set of connections.

## Usage

1. Run the `GraphsApp` class.

2. Enter how many connections do you want to enter.

3. According to your previous value, put number of nodes which are connected.

4. The application will then return number of separated graphs.

## Dependencies

The application uses Java's built-in libraries and does not require any external dependencies.

## Notes

- The application uses a loops and recursion to check all nodes. It is collecting nodes visited already. If after one searching session there are still unvisited nodes, they are visited in next loop steps

- If you don't want to use get through user interface and input all connections from code, You must comment firth three lines and uncomment `Set<int[]> connections` initialization. In this case You must remember to initialize all connections correctly - without repetitions.

- The method `printOutput` can be used for printing connections like it is done in task.

---

Created by Maciej Wyrzykowski for LSN

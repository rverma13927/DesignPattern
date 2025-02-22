
---

## **🔥 Key Patterns for Machine Coding Rounds**
| **Pattern** | **Why Use It?** | **Example Usage** |
|------------|---------------|----------------|
| ✅ **Use Interfaces** | Decouples implementation, makes it extensible | `IBoard`, `IPlayer` in Tic-Tac-Toe |
| ✅ **Abstract Classes** | Helps share common behavior between different subclasses | `AbstractPlayer` for `HumanPlayer` & `AIPlayer` |
| ✅ **Factory Pattern** | Creates objects dynamically, supports dependency injection | `PlayerFactory.createPlayer()` |
| ✅ **Strategy Pattern** | Defines multiple strategies without modifying core logic | Different AI strategies for Tic-Tac-Toe |
| ✅ **Observer Pattern** | Helps event-driven programming (e.g., notifications, live updates) | Chat apps, real-time games |
| ✅ **Singleton Pattern** | Ensures a class has only one instance (used for config, logging) | `DatabaseConnection.getInstance()` |
| ✅ **Custom Exceptions** | Improves error handling & debugging | `InvalidMoveException` |
| ✅ **Logging Instead of Print Statements** | Helps debugging in a structured way | `Logger.info("Move made at (1,2)")` |
| ✅ **Enums for Constants** | Avoids magic numbers & improves readability | `GameStatus.WON, GameStatus.DRAW` |
| ✅ **Builder Pattern** | Simplifies object creation for complex objects | `GameConfig.builder().size(3).build()` |

---

## **📌 1. Use Interfaces for Extensibility**
### **✅ Example: Defining Board & Players via Interfaces**
```java
interface IBoard {
    void printBoard();
    boolean makeMove(int row, int col, char symbol);
    boolean checkWin(char symbol);
}

interface IPlayer {
    String getName();
    char getSymbol();
    int[] makeMove();
}
```
**💡 Why?**
- Allows future enhancements (e.g., 5x5 board, different player types)
- Decouples **interface** from **implementation**

---

## **📌 2. Use Factory Pattern for Object Creation**
**✅ Example: Dynamically create Human or AI Player**
```java
class PlayerFactory {
    public static IPlayer createPlayer(String type, String name, char symbol) {
        if (type.equalsIgnoreCase("HUMAN")) {
            return new HumanPlayer(name, symbol);
        } else if (type.equalsIgnoreCase("AI")) {
            return new AIPlayer(name, symbol);
        }
        throw new IllegalArgumentException("Invalid player type");
    }
}
```
```java
IPlayer player1 = PlayerFactory.createPlayer("HUMAN", "Alice", 'X');
IPlayer player2 = PlayerFactory.createPlayer("AI", "Bot", 'O');
```
**💡 Why?**
- Allows **dynamic** creation of players without modifying the game logic.
- New player types (e.g., `OnlinePlayer`) can be added without changing existing code.

---

## **📌 3. Use Strategy Pattern for AI Moves**
**✅ Example: Multiple AI Strategies for Tic-Tac-Toe**
```java
interface AIMoveStrategy {
    int[] getMove(Board board);
}

class RandomMoveStrategy implements AIMoveStrategy {
    @Override
    public int[] getMove(Board board) {
        // Returns a random empty position
    }
}

class MinimaxStrategy implements AIMoveStrategy {
    @Override
    public int[] getMove(Board board) {
        // Implement Minimax algorithm for unbeatable AI
    }
}

class AIPlayer implements IPlayer {
    private AIMoveStrategy strategy;

    public AIPlayer(String name, char symbol, AIMoveStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int[] makeMove() {
        return strategy.getMove(board);
    }
}
```
**💡 Why?**
- Easily switch AI strategies **without modifying AIPlayer**.
- Can add **different AI levels (Easy, Medium, Hard)** dynamically.

---

## **📌 4. Use Singleton Pattern for Shared Resources**
Example: **Logging Utility**
```java
class Logger {
    private static Logger instance = null;
    
    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
```
**💡 Why?**
- **Ensures only one** logger instance is used throughout the application.
- **Improves debugging** by structuring logs better.

---

## **📌 5. Use Custom Exceptions for Better Error Handling**
**✅ Example: Throwing Custom Exceptions for Invalid Moves**
```java
class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}

class Board {
    public void makeMove(int row, int col, char symbol) throws InvalidMoveException {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new InvalidMoveException("Move out of bounds!");
        }
        if (grid[row][col] != '-') {
            throw new InvalidMoveException("Cell already occupied!");
        }
        grid[row][col] = symbol;
    }
}
```
**💡 Why?**
- Makes debugging easier instead of just returning **true/false**.
- **Clearly defines** error cases instead of relying on `if-else`.

---

## **📌 6. Use Enums for Game States**
**✅ Example: Using `GameStatus` Enum**
```java
enum GameStatus {
    IN_PROGRESS, DRAW, PLAYER1_WINS, PLAYER2_WINS
}
```
Instead of using **magic strings like `"win", "draw"`**, use:
```java
GameStatus status = GameStatus.IN_PROGRESS;
```
**💡 Why?**
- **Improves code readability & maintainability**.
- Avoids bugs caused by typos (`"draw"` vs `"Draw"`).

---

## **📌 7. Use DTOs for API-Based Systems**
If you're coding a **REST API**, use **DTOs** instead of passing raw objects.
```java
class MoveRequest {
    private int row;
    private int col;
    private char symbol;
    
    // Getters & Setters
}
```
This helps **API clients** understand what data is needed.

---

## **📌 8. Avoid Hardcoding & Use Configurable Parameters**
**✅ Example: Configurable Board Size**
```java
class GameConfig {
    public static final int BOARD_SIZE = 3;
}
```
Replace:
```java
private static final int SIZE = 3;  // ❌ Hardcoded
```
With:
```java
private static final int SIZE = GameConfig.BOARD_SIZE;  // ✅ Configurable
```
**💡 Why?**
- Easily **change board size** without modifying logic.
- Useful for **dynamic difficulty settings**.

---

## **🔥 Final Machine Coding Checklist**
✅ **Follow SOLID principles** (especially **Interface Segregation & Open-Closed**).  
✅ **Use interfaces for abstraction** (`IBoard`, `IPlayer`).  
✅ **Use Factory Pattern** for object creation.  
✅ **Use Strategy Pattern** for AI logic.  
✅ **Use Singleton for logging/config**.  
✅ **Use Enums for GameStatus**.  
✅ **Use Custom Exceptions for error handling**.  
✅ **Write unit tests** (if possible).

---

## **🔹 Next Steps**
Want a **real-time multiplayer** Tic-Tac-Toe with WebSockets or a **Minimax AI bot**?  
Let me know what you’d like to implement next! 🚀🔥
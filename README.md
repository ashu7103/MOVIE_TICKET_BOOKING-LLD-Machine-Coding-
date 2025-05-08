

# 🎬 Movie Booking System

A Java-based movie ticket booking system that supports:
- Cinema and show management
- Seat booking and cancellation
- Viewing available seats
- Running method calls directly from an input file

---

## ✅ Features

- Add cinemas and shows
- Book and cancel tickets
- List available cinemas and shows by movie or city
- Count free seats for a show
- Supports input automation via `input.txt` file

---

## 📁 Project Structure
```

├── Solution.java    // Main logic for booking system & also contain Main to run file without txt file
├── Cinema.java      // Defines Cinema structure, screens, seats
├── Show.java        // Defines Show details (movieId, timing, etc.)
├── Ticket.java      // Defines Ticket structure (seat numbers, showId)
├── Runner.java      // Reads method calls from input.txt and executes
├── input.txt        // Sample method calls to be executed

```


---

## 📝 How to Use

### 🔧 Step 1: Prepare the Input File

Make sure your `input.txt` is placed in the **root directory** (same level as the `.java` files).  
You can also provide an absolute path in `Runner.java` if needed.

# Example `input.txt`:
```

addCinema(0, 1, 4, 5, 10)

addShow(1, 4, 0, 1, 1710516108725, 1710523308725)

listCinemas(0, 1)

bookTicket("tkt-1", 1, 4)

cancelTicket("tkt-1")

getFreeSeatsCount(1)
```





All method calls in `input.txt` will be automatically executed, and results will be printed to the console.

---

## 🛠 Methods You Can Use
```
| Method Name         | Arguments                                               | Description                         |
| ------------------- | ------------------------------------------------------- | ----------------------------------- |
| `addCinema`         | `cinemaId cityId screenCount screenRow screenColumn`    | Add a new cinema                    |
| `addShow`           | `showId movieId cinemaId screenIndex startTime endTime` | Add a show to a cinema              |
| `bookTicket`        | `ticketId showId ticketsCount`                          | Book tickets for a show             |
| `cancelTicket`      | `ticketId`                                              | Cancel a previously booked ticket   |
| `listCinemas`       | `movieId cityId`                                        | Get cinemas showing a movie in city |
| `listShows`         | `movieId cinemaId`                                      | List shows of a movie in a cinema   |
| `getFreeSeatsCount` | `showId`                                                | Get number of free seats for a show |
```
---


## 👨‍💻 Example Output

```
Cinemas showing movie 4 in city 1: [0]
Shows for movie 4 in cinema 0: [1]
Booked ticket tkt-1: [0-0, 0-1, 0-2, 0-3]
Free seats in show 1: 46
Cancel ticket tkt-1: true
Free seats in show 1 after cancellation: 50
```


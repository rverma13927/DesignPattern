package org.example.designProblems.practice.Bookmyshow;

import lombok.*;

import javax.validation.constraints.Null;
import java.awt.print.Book;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Bms {
    public static void main(String[] args) {
        // Create users
        User user1 = new User();
        user1.setId(1);
        user1.setName("Alice");

        User user2 = new User();
        user2.setId(2);
        user2.setName("Bob");

        // Create state and location
        State state = new State("California", "CA");
        Location location = new Location(1, 12345, state, "Los Angeles");

        // Create movie
        Movie movie = new Movie("1", "Avengers", new ArrayList<>(), Duration.ofMinutes(180));
        MovieManager.getMovieManager().addMovie(movie);

        // Create theatre and show
        Theatre theatre = new Theatre(location, "AMC", new HashMap<>());
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime = startTime.plus(movie.getDuration());
        Map<Integer, Boolean> seats = new ConcurrentHashMap<>();
        for (int i = 1; i <= 10; i++) seats.put(i, true);  // 10 seats
        Show show = new Show(LocalDate.of(2025,03,16), startTime, endTime, movie, seats, new ArrayList<>());
        theatre.addShow(show);

        // Book tickets
        List<Integer> seatsToBook = Arrays.asList(1, 2, 3);
        PaymentStrategy paymentStrategy = PaymentFactory.getPaymentStrategy(PAYTMENTTYPE.UPI);

        if (show.bookSeats(seatsToBook)) {
            Booking booking = new Booking(theatre, PAYTMENTTYPE.UPI, show, 300.0, PaymentStatus.COMPLETED, user1, paymentStrategy, seatsToBook);
            show.getBookings().add(booking);
            System.out.println("Booking successful for user: " + user1.getName());
        } else {
            System.out.println("Booking failed: Seats not available");
        }
    }
}

@Getter
@Setter
class User{
    private Integer id;
    private String name;
}

@Getter
@Setter
@AllArgsConstructor
class State{
    private String name;
    private String id;
}
@Getter
@Setter
@AllArgsConstructor
class Location{
    private Integer id;
    private Integer postalCode;
    private State state;
    private String city;
}
@Getter
@Setter
@AllArgsConstructor
class Theatre{
     private Location location;
     private String name;
     private Map<LocalDate,Show> showList= new HashMap<>()  ;

     public void addShow(Show show){
         showList.put(show.getDate(),show);
     }
}
class Actor{
    private User user;
}



@Getter
@Setter
@AllArgsConstructor
@Data
class Movie{
    private String id;
    private String name;
    private List<Actor> actorList;
    private Duration duration;
 }

 @Data
 @AllArgsConstructor
 class Show{
     private LocalDate date;
     private LocalDateTime startTime;
     private LocalDateTime endTime;
     private Movie movie;
     private Map<Integer, Boolean> seats;
     private List<Booking> bookings;

     public boolean bookSeats(List<Integer> seatsToBook) {
         synchronized (this) {
             for (int seat : seatsToBook) {
                 if (!seats.getOrDefault(seat, false)) {
                     return false; // Seat already booked
                 }
             }
             for (int seat : seatsToBook) {
                 seats.put(seat, false); // Mark seat as booked
             }
             return true;
         }
     }

 }

///  Make it singleton
 class MovieManager{
    private static Map<String,Movie> movieMap = new ConcurrentHashMap<>();
    private static MovieManager movieManager;
    private MovieManager(){}

    public static MovieManager getMovieManager(){
        if(movieManager==null){
            synchronized (MovieManager.class){
                if(movieManager==null){
                    movieManager = new MovieManager();
                }
            }
        }
        return movieManager;
    }
    public boolean addMovie(Movie movie){
        if(!movieMap.containsKey(movie.getName()))
        movieMap.put(movie.getName(),movie);
        else return false;
        return true;
    }
    public boolean removeMovie(String name){
           movieMap.remove(name);
         return true;
     }

 }
 // Strategy pattern
 abstract class PaymentStrategy{
     abstract  boolean pay(double amount);
 }

 class UPIPayment extends  PaymentStrategy{

     @Override
     boolean pay(double amount) {
         return false;
     }
 }
class CreditCardPayment extends  PaymentStrategy{

    @Override
    boolean pay(double amount) {
        return false;
    }
}

class PaymentFactory {
    public static PaymentStrategy getPaymentStrategy(PAYTMENTTYPE type) {
        switch (type) {
            case UPI:
                return new UPIPayment();
            case CREDITCARD:
                return new CreditCardPayment();
            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}
enum PAYTMENTTYPE{
     UPI,
    CREDITCARD
}
enum  PaymentStatus{
     COMPLETED,
    IN_PROGRESS,
    FAILED
}

@AllArgsConstructor
class Booking{
     private Theatre theatre;
     private PAYTMENTTYPE paytmenttype;
     private Show show;
     private Double amount;
     private PaymentStatus paymentStatus;
     private  User user;
     private PaymentStrategy paymentStrategy;
     private List<Integer> seats;

}

class BookingService {

    public Booking bookShow(Theatre theatre, Show show, User user, List<Integer> seatsToBook, PAYTMENTTYPE paymentType, double amount) {
        synchronized (show) {
            if (!show.bookSeats(seatsToBook)) {
                throw new RuntimeException("One or more seats are already booked.");
            }
        }

        PaymentStrategy paymentStrategy = PaymentFactory.getPaymentStrategy(paymentType);
        boolean paymentSuccess = paymentStrategy.pay(amount);

        PaymentStatus status = paymentSuccess ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
        Booking booking = new Booking(theatre, paymentType, show, amount, status, user, paymentStrategy, seatsToBook);
        show.getBookings().add(booking);

        return booking;
    }
}

What is the Observer Pattern?
The Observer Design Pattern is a behavioral design pattern that defines a one-to-many dependency between objects.

When one object (Subject) changes state, all its dependent objects (Observers) are automatically notified and updated.

Key Components:

Subject: The object being observed. It maintains a list of observers and notifies them of any changes.
Observer: The object that wants to be notified when the subject changes.
Concrete Subject: Implements the subject interface and notifies observers.
Concrete Observer: Implements the observer interface and updates itself based on changes in the subject.
Real-World Analogy:
YouTube Channel (Subject): Notifies subscribers (observers) whenever a new video is uploaded.
Subscribers (Observers): Receive a notification when the channel uploads a new video.
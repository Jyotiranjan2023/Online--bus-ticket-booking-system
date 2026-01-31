# Online Bus Ticket Booking System

## Project Overview
This project is a backend system that allows passengers to search buses, book seats, and cancel bookings.
Admins can manage buses, routes, schedules, and seats.

## User Roles

### Passenger
- Register and login
- Search schedules
- Book seats
- View own bookings
- Cancel own bookings

### Admin
- Add buses
- Add routes
- Create schedules
- Add seats
- View bookings

## System Architecture
Controller → Service → Repository → Database

## Security
- HTTP Basic Authentication
- Role-based authorization
- BCrypt password encryption

## Database Design
Tables used:
- users
- buses
- routes
- schedules
- seats
- bookings
- booking_seats

## DTO Usage
DTOs are used to hide sensitive fields and return clean API responses.

## Error Handling
Global exception handling using `@RestControllerAdvice`.

## Future Enhancements
- JWT Authentication
- Payment gateway integration
- Pagination & sorting
- Email notifications

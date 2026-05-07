# transitly
A console application that allows users to view bus schedules and check arrival times at their stop.

Features :
Authentication :
- User signup with name, email, password, and confirm password
- Email format validation and duplicate email check
- Password strength validation (minimum 6 characters with letters and numbers)
- User login with email and password
- Admin login with hardcoded credentials
- Logout functionality

Admin :
- Add bus (bus id, bus number, bus name)
- Remove bus
- View all buses in table format
- Add route with multiple stops (First stop → source, Last stop → destination)
- Remove route
- View all routes
- Map bus to route with shift (AM/PM)
- Remove bus-route mapping
- View all bus-route mappings
- Add stop timings for each stop in a bus-route
- Update stop timings
- View stop timings by bus-route id

User :
- Search buses by source, destination, and shift (AM/PM)
- View matching buses with : Bus number, Bus name, Arrival time at the stop

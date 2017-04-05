
# Authors
[Johan Andersson](https://github.com/lol2kpe/) \
[David Fogelberg](https://github.com/davve94)\
[Sam Halali](https://github.com/samhal)\
[Gunnar Gunnarsson](https://github.com/GunnarGunnarsson)\
[Nandha Gopal Elangovan](https://github.com/nandhujit)\
[Miguel Angel Sanchez Cifo](https://github.com/goma12345)\
[Jonathan Granström](https://github.com/juntski)

Git repository: https://github.com/lol2kpe/EDA397_Team3
Issue tracker: https://github.com/lol2kpe/EDA397_Team3/projects/1

---


# Project Description
### Project  Title
H4U

### Platform
Android
### Minimum and target SDK
5.0

### Description

The H4U app helps a user search for hospitals, pharmacies and doctors including; doctor details, doctor availability and doctor reputation. It will also show the directions to each hospital by showing the route map. The route map will be set from a user’s current location and will show both a visual and descriptive way of the route, which will make it easy for a user to find the hospital location.

# Sprint Log: Sprint 1

## Commitment
List the features/stories the team commits to finish during the sprint.

### User Stories
ID | User story
----------------|----------------
2 | As a user, I want to be able to search for doctors
3 | As a user, I want to be able to search for hospitals
4 | As a user, I want to be able to make an emergency call
5 | As a user, I want to be able to search for pharmacies
7 | As a user, I want to be able to create my own profile
8 | As a user, I want to be able to get directions to a hospital
9 | As a user, I want to be able to schedule appointments
10 | As a user, I want to be able to get directions to a pharmacy
11 | As a user, I want to be able to consult with a doctor
12 | As a user, I want to be able to see all the nearest hospitals in my area
13 | As a user, I want to be able to update my profile
14 | As a user, I want to be able to set medicine reminders

### Tasks
ID | Tasks
----------------|----------------
41 | Add/remove markers from map
38 | Save user's filter selections
37 | Add basic filter options
36 | Create navigation drawer
35 | Create functional filter functionality
32 | Create Doctor object
31 | Create Pharmacy object
30 | Create Hospital object
28 | Add map to main activity
25 | Display directions on the map
24 | Fetch directions based on users location for selected hospital
23 | Add function for Sending requests to database
20 | Add filter option to GUI
19 | Fetch from database (create object from database entry)
18 | Connect to database
29 | Create markers for map
27 | Create database contents
26 | Create/start database server
17 | Find user's location
16 | Initiate map with Google API
42 | Create dummy data for map

## Work Done

Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
[Find user's location](https://github.com/lol2kpe/EDA397_Team3/issues/17) | [66dfdfa](https://github.com/lol2kpe/EDA397_Team3/commit/66dfdfaca710b806abd273740193d9d2314659e7) | [Sam Halali](https://github.com/samhal)<br> [Miguel Angel Sanchez Cifo](https://github.com/goma12345) | 2h | Pair programming
[Create/start database server](https://github.com/lol2kpe/EDA397_Team3/issues/26) | [c61f98f](https://github.com/lol2kpe/EDA397_Team3/commit/c61f98f2ebdb7bc21d6ce67038241ce2d37bbc53) [d9f984e](https://github.com/lol2kpe/EDA397_Team3/commit/d9f984ede6a61d9ec0dff35d8623c8773bce9f60) [f36f379](https://github.com/lol2kpe/EDA397_Team3/commit/f36f37928e486828a5f7420625c773fe564ac061) | [Johan Andersson](https://github.com/lol2kpe/) | 7h | Simple Design, Continuous Integration
[Seed database with objects](https://github.com/lol2kpe/EDA397_Team3/issues/33) | [31c7c57](https://github.com/lol2kpe/EDA397_Team3/commit/31c7c5754375c60689788dfacc11204cf71d0a7b) | [Johan Andersson](https://github.com/lol2kpe)<br> [David Fogelberg](https://github.com/davve94) | 35m | Pair programming
[Create Doctor object](https://github.com/lol2kpe/EDA397_Team3/issues/32) | [ff25688](https://github.com/lol2kpe/EDA397_Team3/commit/ff256885bee31e119e5f68b0d068062079cd655f) | [David Fogelberg](https://github.com/davve94) | 1h | Test-first
[Create Pharmacy object](https://github.com/lol2kpe/EDA397_Team3/issues/31) | [ff25688](https://github.com/lol2kpe/EDA397_Team3/commit/ff256885bee31e119e5f68b0d068062079cd655f) | [David Fogelberg](https://github.com/davve94) | 1h | Test-first
[Create Hospital object](https://github.com/lol2kpe/EDA397_Team3/issues/30) | [ff25688](https://github.com/lol2kpe/EDA397_Team3/commit/ff256885bee31e119e5f68b0d068062079cd655f) | [David Fogelberg](https://github.com/davve94) | 3h | Test-first
[Create markers for map](https://github.com/lol2kpe/EDA397_Team3/issues/29) | [ba9e5da](https://github.com/lol2kpe/EDA397_Team3/commit/ba9e5da2a734173cb1295c188187861b148b9aa5) | [Sam Halali](https://github.com/samhal) | 2h | -
[Add map to main activity](https://github.com/lol2kpe/EDA397_Team3/issues/28) | [b003734](https://github.com/lol2kpe/EDA397_Team3/commit/b003734eac86a67760fd226ad6a25743363e7c96) | [Miguel Angel Sanchez Cifo](https://github.com/goma12345)<br> [Gunnar Gunnarsson](https://github.com/GunnarGunnarsson) | 1h | -
[Create database contents](https://github.com/lol2kpe/EDA397_Team3/issues/27) | [5ee2fa7](https://github.com/lol2kpe/EDA397_Team3/commit/5ee2fa79e1daed9f5f201188080e7ee22441eb6e) | [Miguel Angel Sanchez Cifo](https://github.com/goma12345)<br> [Nandha Gopal Elangovan](https://github.com/nandhujit) | 4h | -
[Fetch from database (create object from database entry)](https://github.com/lol2kpe/EDA397_Team3/issues/19) | [3483f4f](https://github.com/lol2kpe/EDA397_Team3/commit/3483f4f73c5adcb74066c79993c4ffce8beb6c25) | [Johan Andersson](https://github.com/lol2kpe) | 3h | -
[Connect to database](https://github.com/lol2kpe/EDA397_Team3/issues/18) | [3483f4f](https://github.com/lol2kpe/EDA397_Team3/commit/3483f4f73c5adcb74066c79993c4ffce8beb6c25) | [Johan Andersson](https://github.com/lol2kpe)<br> [David Fogelberg](https://github.com/davve94) | 3h | Pair programming
[Find user's location](https://github.com/lol2kpe/EDA397_Team3/issues/17) | [66dfdfa](https://github.com/lol2kpe/EDA397_Team3/commit/66dfdfaca710b806abd273740193d9d2314659e7) | [Sam Halali](https://github.com/samhal) | 3h | -
[Initiate map with Google API](https://github.com/lol2kpe/EDA397_Team3/issues/16) | [b003734](https://github.com/lol2kpe/EDA397_Team3/commit/b003734eac86a67760fd226ad6a25743363e7c96) | [Miguel Angel Sanchez Cifo](https://github.com/goma12345)<br> [Gunnar Gunnarsson](https://github.com/GunnarGunnarsson) | 1h | -
[Save user’s filter selection] | [2564636](https://github.com/lol2kpe/EDA397_Team3/commit/2564636ae9d6e90acc090520a82819ae9e5a3eac) | [Jonathan Granström](https://github.com/juntski) | 2h | Testing first, Simple design, refactoring
[Add basic filter functionality] | [aa31251](https://github.com/lol2kpe/EDA397_Team3/commit/aa31251c84558390671b9aef8e8e9fe5160f9501) | [Jonathan Granström](https://github.com/juntski) | 2h | Simple design
[Create functional filter functionality] | [72f4557](https://github.com/lol2kpe/EDA397_Team3/commit/72f45573e243317a38931cd9a363037ecc4af2c6) | [Jonathan Granström](https://github.com/juntski)<br> [David Fogelberg](https://github.com/davve94) | 5h | Refactoring, simple design, pair programming
[Add/remove markers from map] | [c042c79](https://github.com/lol2kpe/EDA397_Team3/commit/c042c7916ab4063a62fb913c673d3b6b09c52b34) | [Gunnar Gunnarsson](https://github.com/GunnarGunnarsson)<br> [Jonathan Granström](https://github.com/juntski)<br> [David Fogelberg](https://github.com/davve94)<br> [Miguel Angel Sanchez Cifo](https://github.com/goma12345)<br> [Sam Halali](https://github.com/samhal) | 1h | Pair programming, simple design
[Report] | [60ed7d4] | [Nandha Gopal Elangovan](https://github.com/nandhujit) | 2h | Small release
[Create dummy data for map] | [46cb5fe] | [David Fogelberg](https://github.com/davve94) | 1h | -

## Reflections
Brainstorming was used to gather ideas for the project proposal. After getting the proposal accepted, the same method was used to gather ideas for functionality, these ideas were then formulated into user stories. Another brainstorming session was performed to decompose the most important user stories into tasks.

The 100$ method was used to prioritize the user stories according to relevance to
the applications goals. It was interesting to see how unaligned our estimates were except for those that clearly contributed to the goal.

Pair programming was used to solve challenging tasks and worked really well in the group, both in the start of the sprint and the end of the sprint. It was used to solve for example fetching data from the database, integrating data with filter and integrating the map with the rest of the functionality.  

Testing worked quite good for specific methods however for Android GUI parts it was more challenging. When it came to test-first it was really good when you got used to it but it is also something that we need to work on the next sprint. Sometimes you started writing the functions without writing tests first because you are so used to that kind of programming. 

Refactoring was used to improve the code and make it more readable. It is really good to always consider refactoring the code since there is almost always something to improve. 

We definitely would have liked more time for the planning phase of the project. Within the short span of time that was given from the group formation to the delivery of Sprint 1 only gave enough time to gather the most important tasks and delegate them before starting to implement them. Methods we would have liked to use during the planning phase in the next sprint:
- Analyze dependencies
- Planning poker to estimate work
- Software architectural design

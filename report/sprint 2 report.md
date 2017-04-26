# Authors
[Johan Andersson](https://github.com/lol2kpe/) \
[David Fogelberg](https://github.com/davve94)\
[Sam Halali](https://github.com/samhal)\
[Gunnar Gunnarsson](https://github.com/GunnarGunnarsson)\
[Nandha Gopal Elangovan](https://github.com/nandhujit)\
[Miguel Angel Sanchez Cifo](https://github.com/goma12345)\
[Jonathan Granström](https://github.com/juntski)

Git repository: https://github.com/lol2kpe/EDA397_Team3 \
Issue tracker: https://github.com/lol2kpe/EDA397_Team3/projects/2
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

# Sprint Log: Sprint 2

## Commitment
List the features/stories the team commits to finish during the sprint.

### User Stories
ID | User story
----------------|----------------
47 | As a user, I would like to find directions to a hospital, so that I know how to get to a selected hospital 
46 | As a user, I want to see information about shown hospitals, so that I know more about a selected hospital
45 | As a user, I want to search or filter by injury or problem, so that I can find relevant hospitals
44 | As a user, I want a login activity, so that I can create a profile or validate my credentials
43 | As a user, I want to see and change my search radius, so that I can see hospitals in my area

### Enhancement
ID | Enhancement
----------------|----------------
49 | As a developer, I want to query the database by filter options, so that I can find the relevant objects to show the user
50 | As a developer, I want a better class structure, so that it is easier to filter and search for relevant objects

### Tasks
ID | Tasks
----------------|----------------
48 | Create a "populate map"-function
51 | User profile creation and login
54 | Add more services to service class
55 | Fix database fetch data
56 | Create dummy data generator
57 | Change position of filter button to show directions
58 | Update database fetch for Place
59 | Create save for user profile
60 | Implement SQLite for save
61 | Create better visualiztion of selected objects on the map
62 | Add "get directions" functionality to selected map object
63 | Add "problem/injury" filter option
64 | Add proper search functionality
65 | Add functionality to user profile
66 | Add "medicine reminders" functionality
67 | Add "settings" functionality 
68 | Add possibility to search by radius (or to set the radius)
74 | Automatic zoom in to position in map
 

## Work Done

Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
[Create a “populate map”-function](https://github.com/lol2kpe/EDA397_Team3/issues/48)|[22b5de5](https://github.com/lol2kpe/EDA397_Team3/commit/22b5de59530b34fc2af9fdf0f08b7cc6604ba327)|[Gunnar Gunnarsson](https://github.com/GunnarGunnarsson)[Sam Halali](https://github.com/samhal)|3hr|Refactoring, Pair programming
[User profile creation and login](https://github.com/lol2kpe/EDA397_Team3/issues/51)|[202d397](https://github.com/lol2kpe/EDA397_Team3/commit/202d3979ad598fc4d07dc55c5dc04293e805ac5f)<br> [56f3a79](https://github.com/lol2kpe/EDA397_Team3/commit/56f3a79eb1560f15a871c3e366e9a90a6e73f0f6) <br> [bf4241e](https://github.com/lol2kpe/EDA397_Team3/commit/bf4241ec2e382a0c6dd0905414d2e89ba0f1bae6) <br> [1665fe4](https://github.com/lol2kpe/EDA397_Team3/commit/1665fe4b06cceb971c31098872fdb440fe835c61)<br>[0381c82](https://github.com/lol2kpe/EDA397_Team3/commit/caef8ed24f567fc829e08d18def5d2829046e835)[caef8ed](https://github.com/lol2kpe/EDA397_Team3/commit/caef8ed24f567fc829e08d18def5d2829046e835)|[David Fogelberg](https://github.com/dave94)<br> [Miguel Angel Sanchez Cifo](https://github.com/goma12345)<br> [Nandha Gopal Elangovan](https://github.com/nandhujit)|14hr|Test-first .Refactoring, Pair programming
[Refactor data-model objects](https://github.com/lol2kpe/EDA397_Team3/issues/52)|[4c2427b](https://github.com/lol2kpe/EDA397_Team3/commit/4c2427b521a0016bd0ceddf5f78c23b1b71bdae2)<br> [6c51291](https://github.com/lol2kpe/EDA397_Team3/commit/6c51291513f39f2ac1e132aa277e6e84641246aa)<br>[12e8a07](https://github.com/lol2kpe/EDA397_Team3/commit/12e8a07836acee15f037cd165cec9f6e97741c15) |[Sam Halali](https://github.com/samhal)<br>[Johan Andersson](https://github.com/lol2kpe) | 5hr| Refactoring, Simple-design
[Create a representation of opening hours](https://github.com/lol2kpe/EDA397_Team3/issues/53) | [155bc41](https://github.com/lol2kpe/EDA397_Team3/commit/155bc410b7940d6257c2bf9f259d955713ca0b42) | [Sam Halali](https://github.com/samhal) |5hr |Simple-design ,Test-first
[Create dummy data generator](https://github.com/lol2kpe/EDA397_Team3/issues/56) | [b20a1de](https://github.com/lol2kpe/EDA397_Team3/commit/b20a1de5a90c6dc4f733e58703750c655d6c3d3a) | [Sam Halali](https://github.com/samhal) | 2hr | Refactoring, Simple-design
[Create better visualization of selected objects on the map](https://github.com/lol2kpe/EDA397_Team3/issues/61)  | [0d51318](https://github.com/lol2kpe/EDA397_Team3/commit/0d51318d99b0ad125dfeabfe196143305c79ddd6) | [Sam Halali](https://github.com/samhal)<br> [Miguel Angel Sanchez Cifo](https://github.com/goma12345) | 4hr | Pair programming
[Add “get directions” functionality to selected map object](https://github.com/lol2kpe/EDA397_Team3/issues/62) | [0d51318](https://github.com/lol2kpe/EDA397_Team3/commit/0d51318d99b0ad125dfeabfe196143305c79ddd6) | [Sam Halali](https://github.com/samhal)<br> [Miguel Angel Sanchez Cifo](https://github.com/goma12345)<br>[Johan Andersson](https://github.com/lol2kpe)  | 4hr | pair programming, refactoring, simple-design
[Add possibility to search by radius (or to set the radius)](https://github.com/lol2kpe/EDA397_Team3/issues/68) | [e12702e](https://github.com/lol2kpe/EDA397_Team3/commit/e12702e9f4bb90f4134431aa11e0b3ee18459be2) |  [Nandha Gopal Elangovan](https://github.com/nandhujit) | 2hr | test-first
[Refactor FilterActivity](https://github.com/lol2kpe/EDA397_Team3/issues/71) | [0155388](https://github.com/lol2kpe/EDA397_Team3/commit/0155388a85efca3679209514f195fcd87cbe0e9e)<br> [224fb4c](https://github.com/lol2kpe/EDA397_Team3/commit/224fb4cfc4650e4f21565ae7e8a4c75d9a79b040) | [Jonathan Granström](https://github.com/juntski) |12hr | Refactoring, code standard, sustainable pace, continuous integration
[Add Tabs to FilterActivity](https://github.com/lol2kpe/EDA397_Team3/issues/72) | [bdd672c](https://github.com/lol2kpe/EDA397_Team3/commit/bdd672cb8b687128c3ee239d4239f18185f8be05) | [Jonathan Granström](https://github.com/juntski) | 6hr | Refactoring, code standard, sustainable pace, continuous integration
[Create save for user profile](https://github.com/lol2kpe/EDA397_Team3/issues/59) | [c6a3d09](https://github.com/lol2kpe/EDA397_Team3/commit/c6a3d099c6da95e85b91ebda087ee9135758e713) | [David Fogelberg](https://github.com/davve94) | 3hr | Continuous integration
[Implement SQLite for save](https://github.com/lol2kpe/EDA397_Team3/issues/60) | [c6a3d09](https://github.com/lol2kpe/EDA397_Team3/commit/c6a3d099c6da95e85b91ebda087ee9135758e713) |[David Fogelberg](https://github.com/davve94) |3hr | Continuous integration
[Update database fetch for Place](https://github.com/lol2kpe/EDA397_Team3/issues/58) | [c6a3d09](https://github.com/lol2kpe/EDA397_Team3/commit/c6a3d099c6da95e85b91ebda087ee9135758e713) | [David Fogelberg](https://github.com/davve94) | 1hr | Refactoring
[Add possibility to filter by symptoms/injury](https://github.com/lol2kpe/EDA397_Team3/issues/73) | [9da9d0d](https://github.com/lol2kpe/EDA397_Team3/commit/9da9d0dba2d2af6aeb6253e945be59605bac38b2) | [Jonathan Granström](https://github.com/juntski) | 2hr | Simple design
[Automatic zoom in to position in map](https://github.com/lol2kpe/EDA397_Team3/issues/74) | [caef8ed](https://github.com/lol2kpe/EDA397_Team3/commit/caef8ed24f567fc829e08d18def5d2829046e835) |[Miguel Angel Sanchez Cifo](https://github.com/goma12345) | 1hr
[Add Injury typ filter option](https://github.com/lol2kpe/EDA397_Team3/issues/63) |[d68b62b](https://github.com/lol2kpe/EDA397_Team3/commit/d68b62be42f0528e761f168a9d05cc2e2d55a659) |[Johan Andersson](https://github.com/lol2kpe) |2hr | code standard

## Reflections



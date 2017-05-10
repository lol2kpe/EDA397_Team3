# Authors
[Johan Andersson](https://github.com/lol2kpe/) \
[David Fogelberg](https://github.com/davve94)\
[Sam Halali](https://github.com/samhal)\
[Gunnar Gunnarsson](https://github.com/GunnarGunnarsson)\
[Nandha Gopal Elangovan](https://github.com/nandhujit)\
[Miguel Angel Sanchez Cifo](https://github.com/goma12345)\
[Jonathan Granström](https://github.com/juntski)

Git repository: https://github.com/lol2kpe/EDA397_Team3 \
Issue tracker: https://github.com/lol2kpe/EDA397_Team3/projects/3
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

# Sprint Log: Sprint 3

## Commitment
List the features/stories the team commits to finish during the sprint.

### User Stories
ID | User story
----------------|----------------
76 | As a user, I want to change the language of the application, so that I can better understand it
77 | As a user, I want to be able to search for a hospital directly, so that I can more quickly find a specific hospital
79 | As a user, I want to be able to update my profile, so that I can change or update my information
80 | As a user, I want to be able to set my favorite hospital, so that I don't have to search/look for that particular hospital all the time


### Tasks
ID | Tasks
----------------|----------------
75 |Fix continuous integration builds with Travis CI
78 |Add emergency call button
81 |report file
82 |Connect add favorite place and delete favorite place - profile
84 |Add search feature
87 |Translate app into Spanish
88 |Add app icon
89 |Refactor the filter to support language change
90 |Update filter with proper symptom filter functionality
95 |Add option to change the language manually
96 |Add swedish language option
85 |Refactor place model to support symptoms

## Work Done

Feature | Commits  | Group members | Effort | Practices
----------------|----------------|----------------|----------------|----------------
[Fix continuous integration builds with Travis CI](https://github.com/lol2kpe/EDA397_Team3/issues/75)|[a026c38](https://github.com/lol2kpe/EDA397_Team3/commit/a026c38a9bf59cb4509ea5017eab0f69170271d4)<br>[9e8be76](https://github.com/lol2kpe/EDA397_Team3/commit/9e8be7601e95a66bb702e68b0988856d5b047dbd)<br>[e65f194](https://github.com/lol2kpe/EDA397_Team3/commit/e65f19401c71c17b010351a640aa84d58c68276b)<br>[25a2109](https://github.com/lol2kpe/EDA397_Team3/commit/25a21095c0368871b5c1d492a411c441f0f02b59)<br>[5a8440a](https://github.com/lol2kpe/EDA397_Team3/commit/5a8440aea85c0115522ba5d9b34f82e7e2e689b7)<br>[a30d2dc](https://github.com/lol2kpe/EDA397_Team3/commit/a30d2dc02d181ea387ce7320fd6bf90a137b6911)<br>[7ad32fd](https://github.com/lol2kpe/EDA397_Team3/commit/7ad32fd0b6c3d55453e63fe182d7b925305b80ef)<br>[a452eb0](https://github.com/lol2kpe/EDA397_Team3/commit/a452eb041aa6d12bb3dc20b0babc3485a72b4015)<br>[c62dfd8](https://github.com/lol2kpe/EDA397_Team3/commit/c62dfd8a10a1b6dfefdd181a9bcb41887b6edb0e)<br>[679d99a](https://github.com/lol2kpe/EDA397_Team3/commit/679d99a1eebeeb412e3743c3ef7d05868f47b2cb)<br>[4f7da31](https://github.com/lol2kpe/EDA397_Team3/commit/4f7da3104d8dc0a527770097cc8b685a3e6f214c)<br>[05f9c24](https://github.com/lol2kpe/EDA397_Team3/commit/05f9c248e86afc55f5dafc6610fa5151bc731674)<br>[097a852](https://github.com/lol2kpe/EDA397_Team3/commit/097a85226a8202b24f129963ca6d2282f4f8a511)<br>[ca691a0](https://github.com/lol2kpe/EDA397_Team3/commit/ca691a0aaa9d94b06107ef2ac30c7c020498e6d0)<br>[45fdbfe](https://github.com/lol2kpe/EDA397_Team3/commit/45fdbfe609547a7dc8c001056587a827b40ad2a5)<br>[8c44e2d](https://github.com/lol2kpe/EDA397_Team3/commit/8c44e2d989f80441f81d38b5282234971d295c87)<br>[ce6c732](https://github.com/lol2kpe/EDA397_Team3/commit/ce6c7325af5d6ab0485eae73a3697eb840fca584)<br>[0805288](https://github.com/lol2kpe/EDA397_Team3/commit/08052886a58d5808ba4683df096c957cdefda011)<br>[0933317](https://github.com/lol2kpe/EDA397_Team3/commit/09333178c14c5b2686dd0d4357fe79412357ef9b)<br>[05af999](https://github.com/lol2kpe/EDA397_Team3/commit/05af999316d8aa3731b4a70089b2189b4c973f83)<br>[c08ed89](https://github.com/lol2kpe/EDA397_Team3/commit/c08ed89fce09356a0fe9abcde6f25f9796330d14)|[Johan Andersson](https://github.com/lol2kpe)|27hr|Refactoring
[Map does not fill entire screen](https://github.com/lol2kpe/EDA397_Team3/issues/83)|[e434e07](https://github.com/lol2kpe/EDA397_Team3/commit/e434e07f5e4d50fd7c004a3cecd3041838127c69)|[Johan Andersson](https://github.com/lol2kpe)<br>[Miguel Angel Sanchez Cifo](https://github.com/goma12345)|1hr|Pair Programming, Collective Codeownership, Coding Standards, Small Releases
[Add app icon #88](https://github.com/lol2kpe/EDA397_Team3/issues/88)|[3301033](https://github.com/lol2kpe/EDA397_Team3/commit/3301033515c8cb79a8498244552eda54f0cd6de5)|[Johan Andersson](https://github.com/lol2kpe)|2hr|Coding Standards, Small Releases
[Add swedish language option #96](https://github.com/lol2kpe/EDA397_Team3/issues/96)|[27e6264](https://github.com/lol2kpe/EDA397_Team3/commit/27e626404a0892cb00a397828b8e3a30a97ff787)|[Johan Andersson](https://github.com/lol2kpe)|2hr|Coding Standards, Small Releases
[Refactor the filter to support language change](https://github.com/lol2kpe/EDA397_Team3/issues/89)|[38e125a](https://github.com/lol2kpe/EDA397_Team3/commit/38e125a56e981bff0c7bb36eb5a39368aed45a1f)|[Jonathan Granström](https://github.com/juntski)|12hr|Sustainable Pace, Simple Design, Code Refactoring, Collective Code Ownership, Small Releases
[Update filter with proper symptom filter functionality](https://github.com/lol2kpe/EDA397_Team3/issues/90)|[20565b7](https://github.com/lol2kpe/EDA397_Team3/commit/20565b772a5e43443eec568b298ba054e4410d78)|[Jonathan Granström](https://github.com/juntski)<br>[Sam Halali](https://github.com/samhal)|4hr|Pair Programming, Code Refactoring, Simple Design, Sustainable Pace, Collective Code Ownership, Small Releases
[Change the Services class into a Symptom class](https://github.com/lol2kpe/EDA397_Team3/issues/54)|[d6c8e9d](https://github.com/lol2kpe/EDA397_Team3/commit/d6c8e9dfb1178870c08f036fb3d26bd18894f266)|[Sam Halali](https://github.com/samhal)|1hr|Simple Design, Refactoring
[Add search feature](https://github.com/lol2kpe/EDA397_Team3/issues/84)|[2fd7048](https://github.com/lol2kpe/EDA397_Team3/commit/2fd7048a78e325ad682f75d277d59bc2670a52fc)|[Sam Halali](https://github.com/samhal)<br>[Miguel Angel Sanchez Cifo](https://github.com/goma12345)|3hr|Simple Design, Refactoring, Pair Programming
[Add functionality to user profile]()|[2cfc325](https://github.com/lol2kpe/EDA397_Team3/commit/2cfc32514bc474d23c71048af4ee5feec9ebd986) |[David Fogelberg](https://github.com/davve94)|4hr|code refactoring, sustainable pace, collective code ownership
[Connect add favorite place and delete place profile ]() |[f60d04b](https://github.com/lol2kpe/EDA397_Team3/commit/f60d04bd817a23a800a75b01be4b638d1eb76775) |[David Fogelberg](https://github.com/davve94)|4hr |code refactoring, sustainable pace, collective code ownership
[create report file]() | [3acddab](https://github.com/lol2kpe/EDA397_Team3/commit/3acddab7143e477adaeb7d8f759d028b2a163f2d) |[Nandha Gopal Elangovan](https://github.com/nandhujit)|2hr| Small release
[Refactor model]() | [56f3a79](https://github.com/lol2kpe/EDA397_Team3/commit/56f3a79eb1560f15a871c3e366e9a90a6e73f0f6) |[Nandha Gopal Elangovan](https://github.com/nandhujit)|2hr|  code Refactoring
[Add option to change the language manually]() |[0d8d2ef](https://github.com/lol2kpe/EDA397_Team3/commit/0d8d2efc5ec407b11437d0e1fde3d81c0e71142f)|[Miguel Angel Sanchez Cifo](https://github.com/goma12345)|3hr|Simple design, Code refactoring
[Translate app into Spanish]() |[f114bbf](https://github.com/lol2kpe/EDA397_Team3/commit/f114bbf873d513fb6256c2cc615974fccdc1bb6e) |[Miguel Angel Sanchez Cifo](https://github.com/goma12345)|1hr| Simple design
[Add emergency call button](https://github.com/lol2kpe/EDA397_Team3/issues/78)|[a453db5](https://github.com/lol2kpe/EDA397_Team3/commit/a453db57b527969fa8773cb2c77fd09264aff746) |[Gunnar Örn Gunnarsson](https://github.com/GunnarGunnarsson)|2hr |Simple design
## Reflections


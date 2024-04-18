# Diamond Data
A project by Jaylee Baker, Noah Sternberg, and Max Thompson

## Installation and Use
Diamond Data is fully portable, and is built using Docker. You will need [Docker](https://docs.docker.com/engine/install/) or [Docker Desktop](https://www.docker.com/products/docker-desktop/) to run this application. Both of these will work functionally the same, however, only the engine is required.

To run the app, we have built 2 `Dockerfile`s, one in the `app` and one in the `api`, as well as a `docker-compose.yml` to run them together. To use Diamond Data, simply enter a terminal from the root of the app, and the following command:

```bash
docker-compose up --build
```

That's it! Diamond Data is now running in a developement environment. Please note, this may take some time to kick up and get fully running.

## Project Overview
The team intends to construct a full-stack statistics web-app designed to make managing a high school team/college team’s
baseball or softball day-to-day operations easier. A full list of planned features is attached to the end of this document, but
eventually the app will utilise AI agents or Machine Learning to help managers make roster decisions and player decisions.
From tracking statistics on players and using that data to compare and contrast players to forming rosters empowered by
AI to visualising data, this will make the lives of team managers easier.

- Managers can enter a few basic stats, such as hits, walks, outs, swings and misses etc, and the app will compute a wide
range of statistics. The team has this full list in a working document, and it is available upon request.
- The manager can customise their account. In the future, we plan to incoroporate more features to enable managers to communicate with thier players and other coaches.
- All stats will be visible in one cohesive dashboard, in an ”excel spreadsheet” format. 
- Statistics can be compiled and viewed on a team-wide scale
- The app will have a 'Bulk Entry' page for real time statistics entering
- Modules for adding and removing players from your rosters will be included as well.

## Tech Stack
- The front end will be designed using React.JS, as this is the framework that everyone has the most experience with. 
- The API will be maintained through a Java "*Spring Boot*" RESTful API.
- The Database will be a Microsoft SQL Server.

## Contact
Jaylee Baker: (Front End Engineer, Scrum Master)
- email: jbaker50@murraystate.edu

Noah Sternberg: (Lead Backend Engineer, DevOps Engineer)
- email: nsternberg@murraystate.edu

Max Thompson: (Front End Engineer, Field Expert)
- email: mthompson59@murraystate.edu


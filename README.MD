# Diamond Data
A project by Jaylee Baker, Noah Sternberg, and Max Thompson

## Installation and Use
As of right now, this application only functions from a "dev" environment. So, you have to clone this repo and then go through the setup process for the application. However, we have included a series of scripts you can use to make the setup process as simple as running one command.

### MacOS and Linux (*nix) Setup
1. Clone this repository
1. Change to the repository directory `cd <where_you_cloned_the_repo>`
1. Change to the `scripts/unix` directory: `cd scripts/unix`
1. Execute the `DiamondData.sh` script: `./DiamondData.sh`
1. When you are finished with the application, enter `ctrl+c` to terminate the api and app.

### Windows
1. Clone this repository
1. Change to repository directory `cd <where_you_cloned_the_repo>`
1. Change to the `scripts/windows` directory: `cd scripts/windows`
1. If you aren't already, open a Powershell instance (run `powershell` from a cmd terminal, or search for Powershell in the Windows Context Menu)
1. Execute the `DiamondData.ps1` script: `powershell -ExecutionPolicy bypass -File DiamondData.ps1` (note: you must bypass the execution policy setup by default on Windows machines, hence the `-ExecutionPolicy bypass`) portion.
1. When you are finished, enter `ctrl+c` to end the program.

## Project Overview
The team intends to construct a full-stack statistics web-app designed to make managing a high school team/college team’s
baseball or softball day-to-day operations easier. A full list of planned features is attached to the end of this document, but
essentially, the app will utilise AI agents or Machine Learning to help managers make roster decisions and player decisions.
From tracking statistics on players and using that data to compare and contrast players to forming rosters empowered by
AI to visualising data, this will make the lives of team managers easier.

- Managers can enter a few basic stats, such as hits, walks, outs, swings and misses etc, and the app will compute a wide
range of statistics. The team has this full list in a working document, and it is available upon request.
- The manager can customise their account, and manage up to 5 teams, and these teams can be either baseball or
softball. Each team they make can be customised with team colours, a name, etc. Changing the team colours can
change the site’s colour scheme to match the team colours.
- Managers will be able to generate ”Powered Rosters” that use a robust algorithm to generate a roster full of the best
possible players by the stats that exist on that player.
- Stats will be tracked by players in accordance to the LH/RH split. That is BA, OPS, etc will be tracked for a
player against **Left Handed Pitchers** and **Right Handed Pitchers**.
- Pitchers will be given a bit of special attention, as they are the defensive position with the most unique stats. All
pitchers will have their pitch-kit documented, and customisable. Additionally, a graphic showing a pitchers pitch tends
to move, its rough RPMs, and velocity range.
- All stats will be visible in one cohesive dashboard, in an ”excel spreadsheet” format. Additionally, each player can be
clicked on to show their stats as a visualisation (them compared to the average across your team) etc. Additionally, a
visualisation tab will be available to allow the manager to see their stats visualised on a large-scale level.
- Modules for adding and removing players from your rosters will be included as well.

## Tech Stack
- The front end will be designed using React.JS, as this is the framework that everyone has the most experience with. 
- The API will be maintained through a Java "*Spring Boot*" RESTful API.
- The Database will be a Microsoft SQL Server maintained by Murray State University.

## Developer Roles
- Jaylee Baker: Scrum Master, Front End Engineer
- Noah Sternberg: DevOps Engineer, Back End Engineer
- Max Thompson: Full Stack Developer, Field Expert

## Housekeeping
Standup: Mondays, 8:45 - 9:00

### Contact
Jaylee Baker:
- email: jbaker50@murraystate.edu

Noah Sternberg:
- email: nsternberg@murraystate.edu

Max Thompson
- email: mthompson59@murraystate.edu

